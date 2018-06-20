package com.liao27.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.EqualsExclude;

/**
 * Created by main on 2018/4/28.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends Exception {

    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
