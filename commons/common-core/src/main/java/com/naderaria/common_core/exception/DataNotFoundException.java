package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(){
        this("can't find data in database");
    }


    public DataNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("data_not_found");
        errorResponse.setStatus(404);
        return errorResponse;
    }
}
