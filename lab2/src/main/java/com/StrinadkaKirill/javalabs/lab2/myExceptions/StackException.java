package com.StrinadkaKirill.javalabs.lab2.myExceptions;

public class StackException extends RuntimeException{


    public StackException(String message) {
        super(message);
    }

    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}
