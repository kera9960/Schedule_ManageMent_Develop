package com.example.schedule_develop.comment.controller;

import com.example.schedule_develop.comment.dto.CreateCommentRequestDto;
import com.example.schedule_develop.comment.dto.CreateCommentResponseDto;
import com.example.schedule_develop.comment.dto.GetCommentResponseDto;
import com.example.schedule_develop.comment.service.CommentService;
import com.example.schedule_develop.exception.UnauthorizedException;
import com.example.schedule_develop.user.dto.SessionUserDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @Valid @RequestBody CreateCommentRequestDto requestDto,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId,requestDto,sessionUserDto.getId()));
    }

    @GetMapping
    public ResponseEntity<List<GetCommentResponseDto>> getComments(
            @PathVariable Long scheduleId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findAll(scheduleId));
    }
}
