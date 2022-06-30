package com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException;

public class DefineException extends CommandExecutingException{
    public DefineException(String message) {
        super(message);
    }

    public DefineException(String message, Throwable cause) {
        super(message, cause);
    }
}
