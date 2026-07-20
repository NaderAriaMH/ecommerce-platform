package com.naderaria.product.infratructure.domain.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.BusinessException;

public class NotEnoughInventoryException extends BusinessException {

    @Override
    public ErrorResponse getErrorResponse() {
        //"quantity cannot be less than available quantity"
        return null;
    }
}
