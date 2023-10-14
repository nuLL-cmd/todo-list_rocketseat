package br.com.automatodev.dto;

import org.springframework.http.HttpStatus;


public record ReturnMessage(String message, HttpStatus statusCode, Object artifact) {
    
}
