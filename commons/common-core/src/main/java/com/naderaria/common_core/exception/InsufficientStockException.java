package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class InsufficientStockException extends BusinessException {

    public InsufficientStockException() {
        this("insufficient_stock_exception");
    }


    public InsufficientStockException(String message) {
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("insufficient_stock_exception");
        errorResponse.setStatus(401);
        return errorResponse;
    }
}
