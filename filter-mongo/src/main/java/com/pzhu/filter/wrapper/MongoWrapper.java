package com.pzhu.filter.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@Builder
public class MongoWrapper extends QueryWrapper {
    private Document document;
    private Sort sort;
    private Pageable page;
    private Class<?> resultType;
}
