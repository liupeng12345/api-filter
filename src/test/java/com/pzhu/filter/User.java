package com.pzhu.filter;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;

@Data
@TableName(autoResultMap = true)
public class User {
    protected Long tenantId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    protected Instant dateCreated;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Instant lastUpdated;
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
