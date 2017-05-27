package com.log.aes;


public class CustomException extends Exception {
 
    public CustomException() {
    }
 
    
    // custom exception class whoch throws custom exception
    public CustomException(String exc, Throwable show) {
        super(exc, show);
    }
}
