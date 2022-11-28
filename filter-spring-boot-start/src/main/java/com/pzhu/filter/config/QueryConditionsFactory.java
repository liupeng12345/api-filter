package com.pzhu.filter.config;

import com.pzhu.filter.utils.MongoQueryConditions;
import com.pzhu.filter.utils.MybatisQueryConditions;
import com.pzhu.filter.utils.QueryConditions;

public interface QueryConditionsFactory<T extends QueryConditions<?>> {
    T getQueryConditions();
}

class MongoQueryConditionsFactory implements QueryConditionsFactory<MongoQueryConditions> {

    @Override
    public MongoQueryConditions getQueryConditions() {
        return new MongoQueryConditions();
    }
}

class MybatisQueryConditionsFactory implements QueryConditionsFactory<MybatisQueryConditions> {

    @Override
    public MybatisQueryConditions getQueryConditions() {
        return new MybatisQueryConditions();
    }
}