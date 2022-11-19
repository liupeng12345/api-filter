package com.pzhu.filter.enums;

import java.util.Objects;

public enum Connection {
    and,
    or;

    public static Connection from(Object connection) {
        if (Objects.isNull(connection)) {
            return null;
        }
        if (connection instanceof Connection) {
            return (Connection) connection;
        }
        return switch (connection.toString()) {
            case "and", "AND" -> and;
            case "or", "OR" -> or;
            default -> null;
        };
    }
}
