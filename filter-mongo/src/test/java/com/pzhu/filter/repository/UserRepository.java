package com.pzhu.filter.repository;

import com.pzhu.filter.UserSearch;
import com.pzhu.filter.model.UserSearchMongo;
import com.pzhu.filter.wrapper.MongoWrapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DocumentRepository<UserSearchMongo, Long> {

    @Override
    UserSearchMongo findOne(MongoWrapper mongoWrapper);
}
