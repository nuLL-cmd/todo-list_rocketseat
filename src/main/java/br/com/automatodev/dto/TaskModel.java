package br.com.automatodev.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class TaskModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty(access = Access.READ_ONLY)
    private UUID id;

    @JsonProperty("taskDescription")
    private String description;

    @JsonProperty("title")
    private String title;

    @JsonProperty("startAt")
    private LocalDateTime startAt;

    @JsonProperty("endAt")
    private LocalDateTime endAt;
    
}
