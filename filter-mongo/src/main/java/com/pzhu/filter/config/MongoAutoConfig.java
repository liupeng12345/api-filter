package com.pzhu.filter.config;

import com.pzhu.filter.repository.DocumentMongoRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConditionalOnClass(MongoRepository.class)
@EnableMongoRepositories(repositoryBaseClass = DocumentMongoRepository.class)
public class MongoAutoConfig {
}
