package com.example.schedule_develop.schedule.dto;

import com.example.schedule_develop.schedule.enitity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetSchedulePageResponseDto {

    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String userName;

    public GetSchedulePageResponseDto(String title, String content, Long commentCount, LocalDateTime createdAt, LocalDateTime updatedAt, String userName) {
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
    }

    public static GetSchedulePageResponseDto from(Schedule schedule,Long commentCount){
        return new GetSchedulePageResponseDto(
                schedule.getTitle(),
                schedule.getContent(),
                commentCount,
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getUserName()
        );
    }
}
