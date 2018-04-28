package com.liao27.exceptions;

import lombok.Data;

/**
 * Created by main on 2018/4/28.
 */
@Data
public class BusinessException extends Exception {

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
