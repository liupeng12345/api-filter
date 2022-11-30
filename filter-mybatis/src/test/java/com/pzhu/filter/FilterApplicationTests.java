//package com.pzhu.filter;
//
//import com.pzhu.filter.mapper.UserMapper;
//import com.pzhu.filter.search.UserSearch;
//import com.pzhu.filter.utils.Filters;
//import com.pzhu.filter.utils.MybatisQueryConditions;
//import com.pzhu.filter.wrapper.MybatisWrapper;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//@MapperScan("com.pzhu.**.mapper")
//class FilterApplicationTests {
//    @Resource
//    private UserMapper userMapper;
//
//    @Test
//    void testSearchBean() {
////        User user = new User();
////        user.setEmail("127@qq.com");
////        user.setName("test");
////        user.setAge(23);
////        user.setId(7L);
////        userMapper.insert(user);
////        String orderBy = "name desc,age";
////        String filter = Filters.or(Filters.sw(UserSearch::getName, "test"), Filters.notNull(UserSearch::getName))
////                .getValue();
////        System.out.println(filter);
////        MybatisQueryConditions queryConditions = new MybatisQueryConditions(filter, orderBy);
////        MybatisWrapper searchWrapper = queryConditions.wrapper(UserSearch.class);
////        final List<UserSearch> userSearches = userMapper.selectSearch(searchWrapper);
////        System.out.println(userSearches);
//    }
//}
