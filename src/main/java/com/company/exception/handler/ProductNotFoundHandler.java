package com.company.exception.handler;

import com.company.model.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductNotFoundHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductsNotFound.class)
    public ResponseEntity<ErrorRes> handleControllerNotFound(
            ProductsNotFound exception) {
        ErrorRes errorResponse = ErrorRes.builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
