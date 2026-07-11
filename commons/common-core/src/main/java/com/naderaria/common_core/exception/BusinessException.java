package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

public abstract class BusinessException extends RuntimeException {

    protected String message;

    public BusinessException(){
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public abstract ErrorResponse getErrorResponse();
}
