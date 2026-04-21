package com.example.schedule_develop.comment.service;

import com.example.schedule_develop.comment.dto.CreateCommentRequestDto;
import com.example.schedule_develop.comment.dto.CreateCommentResponseDto;
import com.example.schedule_develop.comment.entity.Comment;
import com.example.schedule_develop.comment.repository.CommentRepository;
import com.example.schedule_develop.exception.NotFoundException;
import com.example.schedule_develop.schedule.enitity.Schedule;
import com.example.schedule_develop.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponseDto save(Long scheduleId, CreateCommentRequestDto requestDto){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NotFoundException("없는 일정입니다.")
        );
        Comment comment = new Comment(
                requestDto.getContent(),
                schedule);
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponseDto.from(savedComment);
    }
}
