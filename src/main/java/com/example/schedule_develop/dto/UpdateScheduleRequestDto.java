package com.example.schedule_develop.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private String title;
    private String content;
    private Long userId;
}
