package com.naderaria.common_core.exception;

public class DuplicateDataException extends BusinessException {

    public DuplicateDataException(){
        this("Data already exists");
    }

    public DuplicateDataException(String message) {
        super(message);
    }
}
