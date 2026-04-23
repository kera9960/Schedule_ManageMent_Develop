package com.example.schedule_develop.schedule.dto;

import com.example.schedule_develop.schedule.enitity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponseDto {

    private final Long id;
    private final String title;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateScheduleResponseDto(Long id, String title, Long userId, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateScheduleResponseDto from(Schedule schedule){
        return new CreateScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUser().getId(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
}
