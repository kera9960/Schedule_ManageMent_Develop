package com.example.schedule_develop.schedule.service;

import com.example.schedule_develop.comment.repository.CommentRepository;
import com.example.schedule_develop.exception.ForbiddenException;
import com.example.schedule_develop.exception.NotFoundException;
import com.example.schedule_develop.schedule.dto.*;
import com.example.schedule_develop.schedule.enitity.Schedule;
import com.example.schedule_develop.schedule.repository.ScheduleRepository;
import com.example.schedule_develop.user.entity.User;
import com.example.schedule_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponseDto save(CreateScheduleRequestDto requestDto, Long loginUserId) {
        User user = userRepository.findById(loginUserId).orElseThrow(
                ()-> new NotFoundException("없는 유저입니다.")
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
//    @Transactional(readOnly = true)
//    public List<GetScheduleResponseDto> findAll() {
//        List<Schedule> schedules = scheduleRepository.findAll();
//        List<GetScheduleResponseDto> dtos = new ArrayList<>();
//        for(Schedule schedule : schedules){
//            GetScheduleResponseDto dto = new GetScheduleResponseDto(
//                    schedule.getId(),
//                    schedule.getTitle(),
//                    schedule.getUser().getId(),
//                    schedule.getContent(),
//                    schedule.getCreatedAt(),
//                    schedule.getUpdatedAt()
//            );
//            dtos.add(dto);
//        }
//        return dtos;
//    }

    @Transactional(readOnly = true)
    public Page<GetSchedulePageResponseDto> getSchedulePage(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,"updatedAt");
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);
        Page<GetSchedulePageResponseDto> pageResponse = schedulePage.map(schedule -> new GetSchedulePageResponseDto(
                schedule.getTitle(),
                schedule.getContent(),
                commentRepository.countBySchedule(schedule),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getUser().getUserName()
        ));
        return pageResponse;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponseDto findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new NotFoundException("없는 일정입니다.")
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
    public UpdateScheduleResponseDto update(Long scheduleId, UpdateScheduleRequestDto requestDto, Long loginUserId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new NotFoundException("없는 일정입니다.")
        );
        if (!loginUserId.equals(schedule.getUser().getId())){
            throw new ForbiddenException("본인만 수정 가능합니다.");
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
                ()-> new NotFoundException("없는 일정입니다.")
        );
        if (!loginUserId.equals(schedule.getUser().getId())){
            throw new ForbiddenException("본인만 삭제 가능합니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
