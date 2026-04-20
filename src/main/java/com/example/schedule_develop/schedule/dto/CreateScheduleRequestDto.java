package com.example.schedule_develop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotBlank
    @Size(min = 3, max = 30)
    private String title;
    @NotBlank
    @Size(min = 10, max = 200)
    private String content;
}
