package com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException;

public class DivideByZeroException extends CommandExecutingException{
    public DivideByZeroException(String message) {
        super(message);
    }

    public DivideByZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
