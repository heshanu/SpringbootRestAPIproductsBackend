package com.company.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsNotFound extends RuntimeException{
    private final String errorCode;

    public ProductsNotFound(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
