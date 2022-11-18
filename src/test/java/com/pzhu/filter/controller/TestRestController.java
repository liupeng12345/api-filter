package com.pzhu.filter.controller;

import com.pzhu.filter.User;
import com.pzhu.filter.annotation.RequestFilter;
import com.pzhu.filter.mapper.UserMapper;
import com.pzhu.filter.search.UserSearch;
import com.pzhu.filter.vo.UserVo;
import com.pzhu.filter.wrapper.SqlWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class TestRestController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("test")
    public List<UserVo> getUser(@RequestFilter(filterClass = UserSearch.class) SqlWrapper searchWrapper) {
        log.info("searchWrapper:{}", searchWrapper.toString());
        User user = new User();
        user.setEmail("127@qq.com");
        user.setName("test");
        user.setAge(23);
        user.setId(7L);
        userMapper.insert(user);
        return userMapper.test3(searchWrapper);
    }
}
