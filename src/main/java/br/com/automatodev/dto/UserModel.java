package br.com.automatodev.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty(access = Access.READ_ONLY)
    private String id;

    @JsonProperty("nickName")
    private String userName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("fieldPassword")
    private String password;

}
