package com.pzhu.filter;

import com.pzhu.filter.model.UserSearchMongo;
import com.pzhu.filter.repository.UserRepository;
import com.pzhu.filter.utils.MongoQueryConditions;
import com.pzhu.filter.wrapper.MongoWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.List;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void Test2() {
        UserSearchMongo searchMongo = UserSearchMongo.builder()
                .name("名字")
                .age(123)
                .dateCreated(Instant.now())
                .build();
        mongoTemplate.insert(searchMongo);
        String filter = "name $sw '名字' and ( name $ew '名字' or name = '名字' )";
        MongoQueryConditions queryConditions = new MongoQueryConditions(filter, null);
        MongoWrapper mongoWrapper = queryConditions.createSqlWrapper(UserSearchMongo.class);
        mongoWrapper.setResultType(UserSearch.class);
        UserSearch userSearch = userRepository.findOne(mongoWrapper);
        System.out.println(userSearch);
    }
}
