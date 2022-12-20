package ru.otus.flightsearch.exception;

public class WrongTicketDataException extends RuntimeException {
    public WrongTicketDataException(String message) {
        super(message);
    }
}