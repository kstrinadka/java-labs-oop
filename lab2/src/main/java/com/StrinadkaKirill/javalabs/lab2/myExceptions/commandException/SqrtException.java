package com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException;

public class SqrtException extends CommandExecutingException{
    public SqrtException(String message) {
        super(message);
    }

    public SqrtException(String message, Throwable cause) {
        super(message, cause);
    }
}
