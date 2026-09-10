package com.nexabank.exception;

public class CompteInexistantException extends RuntimeException {
    public CompteInexistantException(String message) {
        super(message);
    }
}
