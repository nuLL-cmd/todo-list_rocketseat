package br.com.automatodev.infrastructure.exception;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(String artifact) {
        super(String.format("%s not found.  Cannot proceed", artifact));
    }
}
