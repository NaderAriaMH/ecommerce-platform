package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class ValidationException extends BusinessException {

    public ValidationException(){
        this("Validation_Exception");
    }


    public ValidationException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("validation_exception");
        errorResponse.setStatus(400);
        return errorResponse;
    }
}
