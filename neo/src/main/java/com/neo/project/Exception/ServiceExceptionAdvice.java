package com.neo.project.Exception;

public class ServiceExceptionAdvice extends Exception {

    private int code;
    String message;

    public ServiceExceptionAdvice(int code, String message){
        this.code = code;
        this.message = message;
    }
}
