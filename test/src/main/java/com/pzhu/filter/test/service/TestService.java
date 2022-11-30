package com.pzhu.filter.test.service;

import com.pzhu.filter.test.repository.UserRepository;
import com.pzhu.filter.wrapper.MongoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;

    public org.springframework.data.domain.Page<com.pzhu.filter.test.model.UserSearchMongo> test(MongoWrapper mongoWrapper) {
        return userRepository.page(mongoWrapper);
    }
}
