package ru.happy.entities.jwt;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
