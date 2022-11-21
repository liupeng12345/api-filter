package com.pzhu.filter.config;

import com.pzhu.filter.repository.DocumentMongoRepository;
import com.pzhu.filter.repository.EnableMongoRepositoriesConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Configuration
@Import(EnableMongoRepositoriesConfiguration.class)
public class MongoAutoConfig {
}
