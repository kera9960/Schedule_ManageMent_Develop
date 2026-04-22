package com.example.schedule_develop.schedule.controller;

import com.example.schedule_develop.exception.UnauthorizedException;
import com.example.schedule_develop.schedule.dto.*;
import com.example.schedule_develop.schedule.service.ScheduleService;
import com.example.schedule_develop.user.dto.SessionUserDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
            @Valid @RequestBody CreateScheduleRequestDto requestDto,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto ==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(requestDto,sessionUserDto.getId()));
    }

//    @GetMapping("/schedules")
//    public ResponseEntity<List<GetScheduleResponseDto>> getSchedules(){
//        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
//    }

    @GetMapping("/schedules")
    public ResponseEntity<Page<GetSchedulePageResponseDto>> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedulePage(page,size));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponseDto> getSchedule(
            @PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponseDto> update(
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequestDto requestDto,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId,requestDto,sessionUserDto.getId()));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        scheduleService.delete(scheduleId,sessionUserDto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
