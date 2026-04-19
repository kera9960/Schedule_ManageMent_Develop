package com.example.schedule_develop.comment.controller;

import com.example.schedule_develop.comment.dto.CreateCommentRequestDto;
import com.example.schedule_develop.comment.dto.CreateCommentResponseDto;
import com.example.schedule_develop.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequestDto requestDto
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(scheduleId,requestDto));
    }
}
