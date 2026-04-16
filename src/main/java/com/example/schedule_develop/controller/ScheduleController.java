package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.CreateScheduleRequestDto;
import com.example.schedule_develop.dto.CreateScheduleResponseDto;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponseDto> create(
            @RequestBody CreateScheduleRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto));
    }
}
