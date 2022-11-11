package com.pzhu.mybatisplusfilter.controller;

import com.pzhu.mybatisplusfilter.User;
import com.pzhu.mybatisplusfilter.annotation.RequestFilter;
import com.pzhu.mybatisplusfilter.mapper.UserMapper;
import com.pzhu.mybatisplusfilter.query.SearchWrapper;
import com.pzhu.mybatisplusfilter.search.UserSearch;
import com.pzhu.mybatisplusfilter.vo.UserVo;
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
    public List<UserVo> getUser(@RequestFilter(filterClass = UserSearch.class) SearchWrapper searchWrapper) {
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
