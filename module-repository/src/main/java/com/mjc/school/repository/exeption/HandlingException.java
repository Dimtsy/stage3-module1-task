package com.mjc.school.repository.exeption;


public class HandlingException extends Exception {
    public HandlingException() {
    }

    public HandlingException(String message) {
        super(message);
    }

    public HandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlingException(Throwable cause) {
        super(cause);
    }
}

