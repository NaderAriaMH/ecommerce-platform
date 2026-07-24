package com.naderaria.cart.application.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.BusinessException;

public class CartEmptyException extends BusinessException {
    @Override
    public ErrorResponse getErrorResponse() {
        return null;
    }
}
