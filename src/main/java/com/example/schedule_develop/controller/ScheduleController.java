package com.example.schedule_develop.controller;

import com.example.schedule_develop.dto.CreateScheduleRequestDto;
import com.example.schedule_develop.dto.CreateScheduleResponseDto;
import com.example.schedule_develop.dto.GetScheduleResponseDto;
import com.example.schedule_develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponseDto> create(
            @RequestBody CreateScheduleRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponseDto>> getSchedules(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponseDto> getSchedule(@PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }
}
