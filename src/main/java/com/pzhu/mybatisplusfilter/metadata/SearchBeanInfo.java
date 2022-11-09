package com.pzhu.mybatisplusfilter.metadata;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author 75073
 */
@Data
@Setter(AccessLevel.PACKAGE)
@Accessors(chain = true)
public class SearchBeanInfo implements Constants {
    private String select;
    private String tables;
    private String autoMapTo;
    private String name;
    private transient Map<String, SearchBeanField> searchBeanFieldMap;
}
