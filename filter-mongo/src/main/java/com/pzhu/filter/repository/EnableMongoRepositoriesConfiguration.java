package com.pzhu.filter.repository;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(repositoryBaseClass = DocumentMongoRepository.class)
public class EnableMongoRepositoriesConfiguration {}
