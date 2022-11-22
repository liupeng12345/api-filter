package com.pzhu.filter.repository;

import com.pzhu.filter.wrapper.MongoWrapper;
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

public class DocumentMongoRepository<T, ID> extends SimpleMongoRepository<T, ID> implements DocumentRepository<T, ID> {

    @Getter
    private final MongoOperations mongoOperations;

    @Getter
    private final MongoEntityInformation<T, ID> entityInformation;

    public DocumentMongoRepository(MongoEntityInformation<T, ID> entityInformation, MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = entityInformation;
    }

    public Optional<T> findOne(Document doc) {
        return Optional.ofNullable(getMongoOperations()
                .findOne(new BasicQuery(doc), getEntityInformation().getJavaType()));
    }

    public List<T> findAll(Document doc) {
        return getMongoOperations()
                .find(new BasicQuery(doc), getEntityInformation().getJavaType());
    }

    public Page<T> page(Document doc, Pageable pageable) {
        Query query = new BasicQuery(doc).with(pageable);
        long count = getMongoOperations().count(query, getEntityInformation().getJavaType());
        List<T> content =
                getMongoOperations().find(query, getEntityInformation().getJavaType());
        return new PageImpl<>(content, pageable, count);
    }

    public List<T> findAll(Document doc, Sort sort) {
        return getMongoOperations()
                .find(new BasicQuery(doc).with(sort), getEntityInformation().getJavaType());
    }

    public long count(Document doc) {
        return getMongoOperations()
                .count(new BasicQuery(doc), getEntityInformation().getJavaType());
    }

    @Override
    public List<T> findAll(MongoWrapper mongoWrapper) {
        return getMongoOperations()
                .find(
                        new BasicQuery(mongoWrapper.getDocument()),
                        getEntityInformation().getJavaType());
    }

    @Override
    public Object findOne(MongoWrapper mongoWrapper) {
        Sort sort = mongoWrapper.getSort();
        Query query = new BasicQuery(mongoWrapper.getDocument());
        Optional.ofNullable(sort).ifPresent(query::with);
        return getMongoOperations().findOne(query, getEntityInformation().getJavaType());
    }

    @Override
    public Page<T> page(MongoWrapper mongoWrapper) {
        Sort sort = mongoWrapper.getSort();
        Query query = new BasicQuery(mongoWrapper.getDocument());
        Optional.ofNullable(sort).ifPresent(query::with);
        long count = getMongoOperations().count(query, getEntityInformation().getJavaType());
        List<T> content =
                getMongoOperations().find(query, getEntityInformation().getJavaType());
        return new PageImpl<>(content, mongoWrapper.getPage(), count);
    }

    @Override
    public long count(MongoWrapper mongoWrapper) {
        return getMongoOperations()
                .count(
                        new BasicQuery(mongoWrapper.getDocument()),
                        getEntityInformation().getJavaType());
    }
}
