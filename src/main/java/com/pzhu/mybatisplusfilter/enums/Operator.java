package com.pzhu.mybatisplusfilter.enums;

/**
 * 过滤运算符
 *
 */
public enum Operator {
    /**
     * 等于
     */
    EQUAL,

    /**
     * 大于等于
     */
    GREATER_EQUAL,

    /**
     * 大于
     */
    GREATER_THAN,

    /**
     * 小于等于
     */
    LESS_EQUAL,

    /**
     * 小于
     */
    LESS_THAN,

    /**
     * 不等于
     */
    NOT_EQUAL,

    /**
     * 包含
     * like '%xxx%'
     */
    CONTAIN,

    /**
     * 以 .. 开始
     * like 'xxx%'
     */
    START_WITH,

    /**
     * 以 .. 结束
     * like '%xxx'
     */
    END_WITH,

    /**
     * 表示字段可以 使用 is NULL 或者 is NOT NULL
     */
    NULL,

    /**
     * 多值
     * in
     */
    MULTI_VALUE;


    public static Operator from(Object op) {
        if (op == null) {
            return null;
        }
        if (op instanceof Operator) {
            return (Operator) op;
        }
        switch (op.toString()) {
            case "=":
                return EQUAL;
            case ">=":
                return GREATER_EQUAL;
            case ">":
                return GREATER_THAN;
            case "<=":
                return LESS_EQUAL;
            case "<":
                return LESS_THAN;
            case "!=":
                return NOT_EQUAL;
            case "like":
            case "LIKE":
                return CONTAIN;
            case "start with":
                return START_WITH;
            case "end with":
                return END_WITH;
            case "is NOT NULL":
            case "is NULL":
                return NULL;
            case ":":
                return MULTI_VALUE;
            default:
                return null;
        }
    }

}