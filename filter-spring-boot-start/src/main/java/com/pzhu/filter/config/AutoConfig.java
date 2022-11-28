package com.pzhu.filter.config;

import com.pzhu.filter.utils.MongoQueryConditions;
import com.pzhu.filter.utils.MybatisQueryConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AutoConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnClass(MongoQueryConditions.class)
    public MongoQueryConditions mongoQueryConditions() {
        return new MongoQueryConditions();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnClass(MybatisQueryConditions.class)
    public MybatisQueryConditions mybatisQueryConditions() {
        return new MybatisQueryConditions();
    }
}
