package com.naderaria.common_core.exception;

import com.naderaria.common_core.dto.response.ErrorResponse;

import java.time.Instant;

public class SaveInDatabaseException extends BusinessException{

    public SaveInDatabaseException(){
        super("A problem occurred; it is not possible to save the data.");
    }

    public SaveInDatabaseException(String message){
        super(message);
    }

    @Override
    public ErrorResponse getErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(this.message);
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setCode("save_in_database_exception");
        errorResponse.setStatus(500);
        return errorResponse;
    }
}
