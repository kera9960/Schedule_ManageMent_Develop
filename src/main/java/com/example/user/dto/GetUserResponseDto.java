package com.example.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetUserResponseDto {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetUserResponseDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
