package com.perficient.sarker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
