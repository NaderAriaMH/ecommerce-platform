package com.naderaria.product.application.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
    @Override
    public ErrorResponse getErrorResponse() {
        return null;
    }
}
