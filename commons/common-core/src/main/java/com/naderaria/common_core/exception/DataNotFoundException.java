package com.naderaria.common_core.exception;

public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(){
        this("Data Not Found");
    }


    public DataNotFoundException(String message) {
        super(message);
    }
}
