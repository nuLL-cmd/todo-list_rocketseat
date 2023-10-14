package br.com.automatodev.infrastructure.exception;

public class ConflictException extends RuntimeException{
    
    public ConflictException() {
        super("Existing artifact. Cannot save.");
    }
}
