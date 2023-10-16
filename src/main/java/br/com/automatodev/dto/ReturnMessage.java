package br.com.automatodev.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_EMPTY)
public record ReturnMessage(String message, HttpStatus statusCode, Object artifact) {
    
}
