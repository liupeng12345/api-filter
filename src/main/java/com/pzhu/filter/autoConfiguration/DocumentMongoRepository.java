package com.pzhu.filter.autoConfiguration;

import lombok.Getter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;
import java.util.Optional;

public class DocumentMongoRepository<T, ID> extends SimpleMongoRepository<T, ID> {

    @Getter
    private final MongoOperations mongoOperations;

    @Getter
    private final MongoEntityInformation<T, ID> entityInformation;

    public DocumentMongoRepository(MongoOperations mongoOperations, MongoEntityInformation<T, ID> entityInformation) {
        super(entityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = entityInformation;
    }

    Optional<T> findOne(Document doc) {
        return Optional.ofNullable(getMongoOperations()
                .findOne(new BasicQuery(doc), getEntityInformation().getJavaType()));
    }

    List<T> findAll(Document doc) {
        return getMongoOperations()
                .find(new BasicQuery(doc), getEntityInformation().getJavaType());
    }

    Page<T> findAll(Document doc, Pageable pageable) {
        Query query = new BasicQuery(doc).with(pageable);
        long count = getMongoOperations().count(query, getEntityInformation().getJavaType());
        List<T> content =
                getMongoOperations().find(query, getEntityInformation().getJavaType());
        return new PageImpl<>(content, pageable, count);
    }

    List<T> findAll(Document doc, Sort sort) {
        return getMongoOperations()
                .find(new BasicQuery(doc).with(sort), getEntityInformation().getJavaType());
    }

    long count(Document doc) {
        return getMongoOperations()
                .count(new BasicQuery(doc), getEntityInformation().getJavaType());
    }
}
