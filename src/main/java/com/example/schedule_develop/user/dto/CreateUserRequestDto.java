package com.example.schedule_develop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {
    @Size(max = 4)
    private String userName;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
}
