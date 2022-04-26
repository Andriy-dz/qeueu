package com.example.studentqueue.exeption;

public class DataProcessingException extends RuntimeException {

    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message
            , Throwable cause) {
        super(message, cause);
    }
}
