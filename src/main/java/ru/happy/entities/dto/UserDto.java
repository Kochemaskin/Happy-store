package ru.happy.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private String username;
    private String password;
    private String email;
    private String alias;
}
