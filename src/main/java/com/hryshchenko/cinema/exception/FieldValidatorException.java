package com.hryshchenko.cinema.exception;

public class FieldValidatorException extends Exception{
    public FieldValidatorException() {
        super();
    }

    public FieldValidatorException(String message) {
        super(message);
    }

    public FieldValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FieldValidatorException(Throwable cause) {
        super(cause);
    }
}
