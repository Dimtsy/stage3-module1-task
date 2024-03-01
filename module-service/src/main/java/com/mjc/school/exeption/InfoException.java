package com.mjc.school.exeption;

public class InfoException extends Exception{

    private final String errorInfo;

    public InfoException(String errorInfo) {
        super (errorInfo);
        this.errorInfo = errorInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
