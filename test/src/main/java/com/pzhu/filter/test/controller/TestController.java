package com.pzhu.filter.test.controller;

import com.pzhu.filter.annotation.RequestFilter;
import com.pzhu.filter.test.model.UserSearchMongo;
import com.pzhu.filter.test.repository.UserRepository;
import com.pzhu.filter.test.service.TestService;
import com.pzhu.filter.wrapper.MongoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/test")
    public Page<UserSearchMongo> test(@RequestFilter(filterClass = UserSearchMongo.class) MongoWrapper mongoWrapper) {
        return testService.test(mongoWrapper);
    }
}
