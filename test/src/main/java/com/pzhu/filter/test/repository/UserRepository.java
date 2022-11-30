package com.pzhu.filter.test.repository;

import com.pzhu.filter.repository.DocumentRepository;
import com.pzhu.filter.test.model.UserSearchMongo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DocumentRepository<UserSearchMongo, Long> {
}
