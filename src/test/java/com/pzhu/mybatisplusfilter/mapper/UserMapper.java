package com.pzhu.mybatisplusfilter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pzhu.mybatisplusfilter.Message;
import com.pzhu.mybatisplusfilter.User;
import com.pzhu.mybatisplusfilter.annotation.SearchMapper;
import com.pzhu.mybatisplusfilter.query.SearchWrapper;
import com.pzhu.mybatisplusfilter.search.UserSearch;
import com.pzhu.mybatisplusfilter.vo.UserVo;
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
}
