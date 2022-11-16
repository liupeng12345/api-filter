package com.pzhu.filter.autoConfiguration;

import com.pzhu.filter.injector.ProSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 75073
 */
@Configuration
public class MybatisPlusAutoConfig {

    /**
     * 使用自定义sql注入器
     */
    @Bean
    public ProSqlInjector proSqlInjector() {
        return new ProSqlInjector();
    }
}
