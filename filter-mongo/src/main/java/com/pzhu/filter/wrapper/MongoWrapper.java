package com.pzhu.filter.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class MongoWrapper extends QueryWrapper {
    private Document document;
    private Sort sort;
    private Pageable page;
}
