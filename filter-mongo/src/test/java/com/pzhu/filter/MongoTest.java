package com.pzhu.filter;

import com.pzhu.filter.filter.BsonFilterVisitor;
import com.pzhu.filter.g4.FilterLexer;
import com.pzhu.filter.g4.FilterParser;
import com.pzhu.filter.metadata.SearchBeanInfo;
import com.pzhu.filter.metadata.SearchBeanInfoHelper;
import com.pzhu.filter.model.UserSearchMongo;
import com.pzhu.filter.repository.UserRepository;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
public class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public  void Test2(){
        String filter = "name $sw '名字' and ( name $ew '名字' or name = '名字' )";
        Lexer lexer = new FilterLexer(CharStreams.fromString(filter));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        FilterParser parser = new FilterParser(tokenStream);
        SearchBeanInfo info = SearchBeanInfoHelper.getInfo(UserSearchMongo.class);
        BsonFilterVisitor mongoFilter = new BsonFilterVisitor(info);
        final FilterParser.FilterContext tree = parser.filter();
        Bson visit = (Bson) mongoFilter.visit(tree);
        BsonDocument bsonDocument =
                visit.toBsonDocument();
        DocumentCodec codec = new DocumentCodec();
        DecoderContext decoderContext = DecoderContext.builder().build();
        Document document = codec.decode(new BsonDocumentReader(bsonDocument), decoderContext);
        List<UserSearchMongo> all = userRepository.findAll(document);
        all.forEach(System.out::println);
    }
}

