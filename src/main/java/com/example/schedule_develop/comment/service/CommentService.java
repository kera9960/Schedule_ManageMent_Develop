package com.example.schedule_develop.comment.service;

import com.example.schedule_develop.comment.dto.CreateCommentRequestDto;
import com.example.schedule_develop.comment.dto.CreateCommentResponseDto;
import com.example.schedule_develop.comment.dto.GetCommentResponseDto;
import com.example.schedule_develop.comment.entity.Comment;
import com.example.schedule_develop.comment.repository.CommentRepository;
import com.example.schedule_develop.exception.NotFoundException;
import com.example.schedule_develop.schedule.enitity.Schedule;
import com.example.schedule_develop.schedule.repository.ScheduleRepository;
import com.example.schedule_develop.user.dto.SessionUserDto;
import com.example.schedule_develop.user.entity.User;
import com.example.schedule_develop.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponseDto save(Long scheduleId, CreateCommentRequestDto requestDto, Long loginUserId){
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NotFoundException("없는 일정입니다.")
        );
        Comment comment = new Comment(
                requestDto.getContent(),
                schedule,
                user);
        Comment savedComment = commentRepository.save(comment);
        return CreateCommentResponseDto.from(savedComment);
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponseDto> findAll(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new NotFoundException("없는 일정입니다.")
        );
        List<Comment> comments = commentRepository.findCommentsBySchedule(schedule);
        return comments.stream()
                .map(comment -> GetCommentResponseDto.from(comment)).toList();
    }
}
