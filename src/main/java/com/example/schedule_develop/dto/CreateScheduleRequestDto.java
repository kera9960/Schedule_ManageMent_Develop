package com.example.schedule_develop.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private String title;
    private Long userId;
    private String content;

}
