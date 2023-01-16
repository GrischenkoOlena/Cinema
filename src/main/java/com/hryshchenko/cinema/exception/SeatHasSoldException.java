package com.hryshchenko.cinema.exception;

public class SeatHasSoldException extends Exception {
    public SeatHasSoldException() {
    }

    public SeatHasSoldException(String message) {
        super(message);
    }

    public SeatHasSoldException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatHasSoldException(Throwable cause) {
        super(cause);
    }
}
