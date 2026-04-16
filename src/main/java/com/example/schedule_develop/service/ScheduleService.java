package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.CreateScheduleRequestDto;
import com.example.schedule_develop.dto.CreateScheduleResponseDto;
import com.example.schedule_develop.enitity.Schedule;
import com.example.schedule_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public CreateScheduleResponseDto save(CreateScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getUserName(),
                requestDto.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getUserName(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt());
    }
}
