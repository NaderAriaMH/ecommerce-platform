package com.naderaria.common_core.exception;

public class DataReferencedException extends BusinessException {

    public DataReferencedException(){
        this("can't deleted Data, because is still referenced other entity");
    }

    public DataReferencedException(String message) {
        super(message);
    }
}
