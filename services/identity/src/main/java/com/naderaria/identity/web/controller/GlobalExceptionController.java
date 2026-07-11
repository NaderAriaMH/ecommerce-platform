package com.naderaria.identity.web.controller;

import com.naderaria.common_core.dto.response.ErrorResponse;
import com.naderaria.common_core.exception.DataNotFoundException;
import com.naderaria.common_core.exception.DataReferencedException;
import com.naderaria.common_core.exception.DuplicateDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionController {


    @ExceptionHandler(value = DuplicateDataException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDataException(DuplicateDataException exception, WebRequest request) {
       return ResponseEntity.badRequest().body( exception.getErrorResponse());
    }



    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundHandler(NullPointerException exception) {


        DataNotFoundException dataNotFoundException = new DataNotFoundException("Data is not exist,Request parameter is invalid");
        dataNotFoundException.setStackTrace(exception.getStackTrace());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getErrorResponse());
    }

    @ExceptionHandler(DataReferencedException.class)
    public ResponseEntity<ErrorResponse> dataReferencedException(DataReferencedException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrorResponse());
    }



}
