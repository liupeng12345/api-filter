package com.pzhu.filter.config;

import com.pzhu.filter.repository.MongoRepositoriesRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MongoRepositoriesRegistrar.class)
public class MongoAutoConfig {
}
