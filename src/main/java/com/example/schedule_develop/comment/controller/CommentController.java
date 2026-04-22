package com.example.schedule_develop.comment.controller;

import com.example.schedule_develop.comment.dto.*;
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

    @PutMapping("/{commentId}")
    public ResponseEntity<UpdateCommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequestDto requestDto,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(commentId,requestDto,sessionUserDto.getId()));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        commentService.delete(commentId,sessionUserDto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
