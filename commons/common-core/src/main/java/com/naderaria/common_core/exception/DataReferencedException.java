package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class DataReferencedException extends BusinessException {

    public DataReferencedException(){
        this("can't deleted Data, because is still referenced other entity");
    }

    public DataReferencedException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("data_referenced_exception");
        errorResponse.setStatus(304);
        return errorResponse;
    }
}
