package com.pzhu.filter;
import com.pzhu.filter.repository.DocumentMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MybatisPlusFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusFilterApplication.class, args);
    }
}
