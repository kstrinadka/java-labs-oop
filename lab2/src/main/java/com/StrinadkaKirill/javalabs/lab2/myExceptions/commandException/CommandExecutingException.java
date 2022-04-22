package com.StrinadkaKirill.javalabs.lab2.myExceptions.commandException;

public class CommandExecutingException extends RuntimeException{


    public CommandExecutingException(String message) {
        super(message);
    }

    public CommandExecutingException(String message, Throwable cause) {
        super(message, cause);
    }


}
