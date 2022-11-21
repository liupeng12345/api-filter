package com.pzhu.filter.utils;

import com.pzhu.filter.filter.BsonFilterVisitor;
import com.pzhu.filter.g4.FilterLexer;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.SearchBeanInfoHelper;
import com.pzhu.filter.wrapper.MongoWrapper;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.conversions.Bson;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class MongoQueryConditions extends QueryConditions<MongoWrapper> {

    public MongoQueryConditions(int page, int pageSize, String filter, String order) {
        super(page, pageSize, filter, order);
    }

    public MongoQueryConditions(String filter, String order) {
        super(filter, order);
    }

    @Override
    PageRequest pageInfo() {
        return PageRequest.of(page, pageSize);
    }

    @Override
    public MongoWrapper createSqlWrapper(Class<?> searchBeanClass) {
        return Optional.ofNullable(SearchBeanInfoHelper.getInfo(searchBeanClass))
                .map(searchBean -> {
                    searchBeanInfo = searchBean;
                    MongoWrapper mongoWrapper =
                            MongoWrapper.builder().page(pageInfo()).build();
                    loadFilter(mongoWrapper);
                    loadOrderBy(mongoWrapper);
                    return mongoWrapper;
                })
                .orElseThrow();
    }

    @Override
    protected void loadOrderBy(MongoWrapper mongoWrapper) {
        decodeInfo(order)
                .ifPresentOrElse(
                        decodeInfo -> {
                            order = decodeInfo;
                            doLoadOrderBy(mongoWrapper);
                        },
                        () -> {});
    }

    @Override
    protected void loadFilter(MongoWrapper mongoWrapper) {
        decodeInfo(filter)
                .ifPresentOrElse(
                        decodeInfo -> {
                            filter = decodeInfo;
                            doLoadFilter(mongoWrapper);
                        },
                        () -> {});
    }

    private void doLoadOrderBy(MongoWrapper mongoWrapper) {
        List<OrderByCondition> orderByConditions = orderByCondition();
        List<Sort.Order> orderList = orderByConditions.stream()
                .map(orderByCondition -> orderByCondition.getOrderType() == OrderType.ASC
                        ? Sort.Order.asc(orderByCondition.getField())
                        : Sort.Order.desc(orderByCondition.getField()))
                .toList();
        if (!CollectionUtils.isEmpty(orderList)) {
            mongoWrapper.setSort(Sort.by(orderList));
        }
    }

    private void doLoadFilter(MongoWrapper mongoWrapper) {
        Lexer lexer = new FilterLexer(CharStreams.fromString(filter));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokenStream);
        BsonFilterVisitor mongoFilter = new BsonFilterVisitor(searchBeanInfo);
        final FilterParser.FilterContext tree = parser.filter();
        Bson visit = (Bson) mongoFilter.visit(tree);
        BsonDocument bsonDocument = visit.toBsonDocument();
        DocumentCodec codec = new DocumentCodec();
        DecoderContext decoderContext = DecoderContext.builder().build();
        Document document = codec.decode(new BsonDocumentReader(bsonDocument), decoderContext);
        Optional.ofNullable(document).ifPresent(mongoWrapper::setDocument);
    }
}
