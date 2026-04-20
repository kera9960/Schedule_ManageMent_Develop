package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.*;
import com.example.schedule_develop.enitity.Schedule;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.user.entity.User;
import com.example.schedule_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    @Transactional
    public CreateScheduleResponseDto save(CreateScheduleRequestDto requestDto,Long loginUserId) {
        User user = userRepository.findById(loginUserId).orElseThrow(
                ()-> new IllegalStateException("없는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContent(),
                user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getUser().getId(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt());
    }
    @Transactional(readOnly = true)
    public List<GetScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetScheduleResponseDto> dtos = new ArrayList<>();
        for(Schedule schedule : schedules){
            GetScheduleResponseDto dto = new GetScheduleResponseDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getUser().getId(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public GetScheduleResponseDto findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalStateException("없는 일정입니다.")
        );
        return new GetScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUser().getId(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
    @Transactional
    public UpdateScheduleResponseDto update(Long scheduleId, UpdateScheduleRequestDto requestDto,Long loginUserId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalStateException("없는 일정입니다.")
        );
        if (!loginUserId.equals(schedule.getUser().getId())){
            throw new IllegalStateException("본인만 수정 가능합니다.");
        }
        schedule.update(
                requestDto.getTitle(),
                requestDto.getContent());
        return new UpdateScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUser().getId(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }
    @Transactional
    public void delete(Long scheduleId, Long loginUserId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalStateException("없는 일정입니다.")
        );
        if (!loginUserId.equals(schedule.getUser().getId())){
            throw new IllegalStateException("본인만 삭제 가능합니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
