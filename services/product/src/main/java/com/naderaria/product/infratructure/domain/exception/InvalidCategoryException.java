package com.naderaria.product.infratructure.domain.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.BusinessException;

public class InvalidCategoryException extends BusinessException {
    @Override
    public ErrorResponse getErrorResponse() {//"category cannot be null"
        return null;
    }
}
