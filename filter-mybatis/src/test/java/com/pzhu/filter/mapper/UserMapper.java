package com.pzhu.filter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pzhu.filter.User;
import com.pzhu.filter.search.UserSearch;
import com.pzhu.filter.wrapper.MybatisWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import static com.pzhu.filter.filter.MybatisFilterVisitor.FILTER_WRAPPER;

/**
 * @author miemie
 * @since 2018-08-12
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserSearch> selectSearch(@Param(FILTER_WRAPPER) MybatisWrapper mybatisWrapper);

}
