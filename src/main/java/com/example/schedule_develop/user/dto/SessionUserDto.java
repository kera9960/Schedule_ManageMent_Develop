package com.example.schedule_develop.user.dto;

import lombok.Getter;

@Getter
public class SessionUserDto {

    private final Long id;
    private final String email;

    public SessionUserDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
