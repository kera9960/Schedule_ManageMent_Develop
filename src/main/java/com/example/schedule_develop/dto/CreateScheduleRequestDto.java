package com.example.schedule_develop.dto;

import jakarta.validation.constraints.Max;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @Max(10)
    private String title;
    private String content;
}
