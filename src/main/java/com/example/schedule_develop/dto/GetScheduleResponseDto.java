package com.example.schedule_develop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetScheduleResponseDto {
    private final Long id;
    private final String title;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetScheduleResponseDto(Long id, String title, Long userId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
