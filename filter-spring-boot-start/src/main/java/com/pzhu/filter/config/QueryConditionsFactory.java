package com.pzhu.filter.config;

import com.pzhu.filter.utils.MongoQueryConditions;
import com.pzhu.filter.utils.MybatisQueryConditions;
import com.pzhu.filter.utils.QueryConditions;
import com.pzhu.filter.wrapper.MongoWrapper;
import com.pzhu.filter.wrapper.MybatisWrapper;

public interface QueryConditionsFactory<T extends QueryConditions<?>> {

    boolean check(Class<?> wrapperType);

    T getQueryConditions(int page, int pageSize, String filter, String order);
}

class MongoQueryConditionsFactory implements QueryConditionsFactory<MongoQueryConditions> {
    @Override
    public boolean check(Class<?> wrapperType) {
        return wrapperType == MongoWrapper.class;
    }

    @Override
    public MongoQueryConditions getQueryConditions(int page, int pageSize, String filter, String order) {
        MongoQueryConditions queryConditions = new MongoQueryConditions();
        queryConditions.init(page, pageSize, filter, order);
        return queryConditions;
    }
}

class MybatisQueryConditionsFactory implements QueryConditionsFactory<MybatisQueryConditions> {

    @Override
    public boolean check(Class<?> wrapperType) {
        return wrapperType == MybatisWrapper.class;
    }

    @Override
    public MybatisQueryConditions getQueryConditions(int page, int pageSize, String filter, String order) {
        MybatisQueryConditions queryConditions = new MybatisQueryConditions();
        queryConditions.init(page, pageSize, filter, order);
        return queryConditions;
    }
}