package com.pzhu.filter.repository;

import com.pzhu.filter.model.UserSearchMongo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DocumentRepository<UserSearchMongo, Long> {

}
