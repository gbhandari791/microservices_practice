package com.auth.exception;

import com.auth.payload.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> resourceNotFoundEceptionHandeler(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ResponseDto dto = new ResponseDto(message, false);

        return new ResponseEntity<ResponseDto>(dto, HttpStatus.NOT_FOUND);
    }
}
