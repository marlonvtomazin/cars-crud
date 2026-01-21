package com.marlonvtomazin.carscrud.exception;

public class CarUniqueViolationException extends RuntimeException {
    public CarUniqueViolationException(String message) {
        super(message);
    }
}
