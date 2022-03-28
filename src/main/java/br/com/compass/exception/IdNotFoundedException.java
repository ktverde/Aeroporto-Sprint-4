package br.com.compass.exception;

public class IdNotFoundedException extends RuntimeException{
    public IdNotFoundedException(String message) {
        super(message);
    }
}
