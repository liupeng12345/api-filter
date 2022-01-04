package com.pzhu.mybatisplusfilter.exception;

/**
 * @author 75073
 */
public class DetailedIllegalArgumentException extends RuntimeException {
    public DetailedIllegalArgumentException(String message) {
        super(message,null,false,false);
    }
}
