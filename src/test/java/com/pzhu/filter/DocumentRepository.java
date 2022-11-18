package com.pzhu.filter;

import com.pzhu.filter.wrapper.MongoWrapper;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface DocumentRepository<T, ID> extends MongoRepository<T, ID> {

    Optional<T> findOne(Document doc);

    List<T> findAll(Document doc);

    Page<T> page(Document doc, Pageable pageable);

    List<T> findAll(Document doc, Sort sort);

    long count(Document doc);

    List<T> findAll(MongoWrapper mongoWrapper);

    T findOne(MongoWrapper mongoWrapper);

    List<T> page(MongoWrapper mongoWrapper);

    long count(MongoWrapper mongoWrapper);
}
