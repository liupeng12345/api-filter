package com.pzhu.mybatisplusfilter;

import com.mongodb.reactivestreams.client.FindPublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.pzhu.mybatisplusfilter.filter.FilterVisitor;
import com.pzhu.mybatisplusfilter.filter.MongoFilter;
import com.pzhu.mybatisplusfilter.g4.FilterLexer;
import com.pzhu.mybatisplusfilter.g4.FilterParser;
import com.pzhu.mybatisplusfilter.metadata.SearchBeanInfoHelper;
import com.pzhu.mybatisplusfilter.search.UserSearch;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootTest
@MapperScan("com.pzhu.**.mapper")
@AutoConfigureDataMongo
public class MongoTest {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Test
    public  void Test2(){
        String filter = "name $sw '名字'";
        Lexer lexer = new FilterLexer(CharStreams.fromString(filter));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokenStream);
        MongoFilter mongoFilter = new MongoFilter(SearchBeanInfoHelper.getInfo(UserSearch.class));
        final FilterParser.FilterContext tree = parser.filter();
        Object visit = mongoFilter.visit(tree);
        System.out.println(visit.toString());
        Mono<MongoCollection<Document>> mongoTemplateCollection = mongoTemplate.getCollection("test");
        FindPublisher<Document> findPublisher = Objects.requireNonNull(mongoTemplateCollection.block()).find((Bson) visit);
        Objects.requireNonNull(Flux.from(findPublisher).collectSortedList().block()).forEach(System.out::println);
    }
}
