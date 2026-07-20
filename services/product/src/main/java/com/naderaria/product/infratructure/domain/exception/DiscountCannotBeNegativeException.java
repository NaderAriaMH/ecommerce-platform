package com.naderaria.product.infratructure.domain.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.BusinessException;

public class DiscountCannotBeNegativeException extends BusinessException {
    @Override
    public ErrorResponse getErrorResponse() {
        return null;
    }
}
