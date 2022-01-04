package com.pzhu.mybatisplusfilter;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(autoResultMap = true)
@Builder
public class Role {
    private Long id;
    private String name;
}
