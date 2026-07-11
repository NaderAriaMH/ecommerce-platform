package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class ForbiddenException extends BusinessException {

    public ForbiddenException(){
        this("Forbidden_exception");
    }


    public ForbiddenException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("forbidden_exception");
        errorResponse.setStatus(403);
        return errorResponse;
    }
}
