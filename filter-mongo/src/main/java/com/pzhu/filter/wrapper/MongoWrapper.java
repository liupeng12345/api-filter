package com.pzhu.filter.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
public class MongoWrapper extends QueryWrapper {
    private Document document;
    private SpringDataWebProperties.Sort sort;
    private Page<?> page;
}
