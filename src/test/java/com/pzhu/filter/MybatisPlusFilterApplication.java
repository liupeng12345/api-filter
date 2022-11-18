package com.pzhu.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(repositoryBaseClass = DocumentMongoRepository.class)
public class MybatisPlusFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusFilterApplication.class, args);
    }
}
