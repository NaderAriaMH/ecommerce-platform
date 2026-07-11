package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class DuplicateDataException extends BusinessException {

    public DuplicateDataException(){
        this("Data already exists");
    }

    public DuplicateDataException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("duplicate_data_exception");
        errorResponse.setStatus(400);
        return errorResponse;
    }
}
