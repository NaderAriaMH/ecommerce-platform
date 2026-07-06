package com.naderaria.common_core.exception;

public class SaveInDatabaseException extends BusinessException{

    public SaveInDatabaseException(){
        super("A problem occurred; it is not possible to save the data.");
    }

    public SaveInDatabaseException(String message){
        super(message);
    }
}
