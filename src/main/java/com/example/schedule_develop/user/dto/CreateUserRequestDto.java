package com.example.schedule_develop.user.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    private String userName;
    private String email;
    @Size(min = 8)
    private String password;
}
