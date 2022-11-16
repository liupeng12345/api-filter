package com.pzhu.filter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzhu.filter.Message;
import com.pzhu.filter.User;
import com.pzhu.filter.annotation.SearchMapper;
import com.pzhu.filter.query.SearchWrapper;
import com.pzhu.filter.search.UserSearch;
import com.pzhu.filter.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author miemie
 * @since 2018-08-12
 */
public interface UserMapper extends BaseMapper<User> {
    List<Message> getAllMsg(@Param("name") String name);

    void updateId(Long updateId);

    UserVo test2();

    @SearchMapper(value = UserSearch.class)
    List<UserSearch> testSearch(@Param(SearchWrapper.SEARCH_WRAPPER) SearchWrapper searchWrapper);

    @SearchMapper(value = UserSearch.class)
    List<UserVo> test3(@Param(SearchWrapper.SEARCH_WRAPPER) SearchWrapper searchWrapper);
}
