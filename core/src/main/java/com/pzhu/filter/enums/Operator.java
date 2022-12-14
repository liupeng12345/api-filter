package com.pzhu.filter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 过滤运算符
 *
 * @author liupeng
 */
@AllArgsConstructor
public enum Operator {
    /**
     * 等于
     */
    EQUAL("="),

    /**
     * 大于等于
     */
    GREATER_EQUAL(">="),

    /**
     * 大于
     */
    GREATER_THAN(">"),

    /**
     * 小于等于
     */
    LESS_EQUAL("<="),

    /**
     * 小于
     */
    LESS_THAN("<"),

    /**
     * 不等于
     */
    NOT_EQUAL("!="),

    /**
     * 包含
     * like '%xxx%'
     */
    CONTAIN("like"),

    /**
     * 以 .. 开始
     * like 'xxx%'
     */
    START_WITH("$sw"),

    /**
     * 以 .. 结束
     * like '%xxx'
     */
    END_WITH("$ew"),

    /**
     * 表示字段可以 使用 is NULL
     */
    NULL("is NULL"),

    /**
     * 表示非空
     */
    NOT_NULL("is NOT NULL"),

    /**
     * 多值
     * in
     */
    MULTI_VALUE("in");

    @Getter
    private final String symbol;

    public static Operator from(Object op) {
        if (op == null) {
            return null;
        }
        if (op instanceof Operator) {
            return (Operator) op;
        }
        return switch (op.toString().toLowerCase()) {
            case "=" -> EQUAL;
            case ">=" -> GREATER_EQUAL;
            case ">" -> GREATER_THAN;
            case "<=" -> LESS_EQUAL;
            case "<" -> LESS_THAN;
            case "!=" -> NOT_EQUAL;
            case "like" -> CONTAIN;
            case "$sw" -> START_WITH;
            case "$ew" -> END_WITH;
            case "is not null" -> NOT_NULL;
            case "is null" -> NULL;
            case ":" -> MULTI_VALUE;
            default -> null;
        };
    }
}