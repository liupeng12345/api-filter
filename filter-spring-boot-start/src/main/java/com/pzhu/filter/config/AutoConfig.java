package com.pzhu.filter.config;

import com.pzhu.filter.utils.MongoQueryConditions;
import com.pzhu.filter.utils.MybatisQueryConditions;
import com.pzhu.filter.utils.QueryConditions;
import com.pzhu.filter.wrapper.QueryWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class AutoConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnClass(MongoQueryConditions.class)
    public MongoQueryConditionsFactory mongoQueryConditions() {
        return new MongoQueryConditionsFactory();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnClass(MybatisQueryConditions.class)
    public MybatisQueryConditionsFactory mybatisQueryConditions() {
        return new MybatisQueryConditionsFactory();
    }


    @Configuration
    @ConditionalOnClass(WebMvcConfigurer.class)
    public static class MvcAutoConfig implements WebMvcConfigurer {

        @Resource
        private List<QueryConditionsFactory<? extends QueryConditions<? extends QueryWrapper>>> queryConditionsFactoryList;

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            argumentResolvers.add(new FilterHandlerMethodArgumentResolver(queryConditionsFactoryList));
        }
    }


    @Configuration
    @ConditionalOnClass(WebFluxConfigurer.class)
    public static class FluxConfig implements WebFluxConfigurer {

        @Resource
        private List<QueryConditionsFactory<? extends QueryConditions<? extends QueryWrapper>>> queryConditionsFactoryList;

        @Override
        public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
            configurer.addCustomResolver(new FilterHandlerMethodArgumentResolverFlux(queryConditionsFactoryList));
        }
    }

}
