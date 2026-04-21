package com.example.schedule_develop.comment.dto;

import com.example.schedule_develop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponseDto {
    private final Long id;
    private final String content;
    private final Long scheduleId;
    private final Long userId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetCommentResponseDto(Long id, String content, Long scheduleId, Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static GetCommentResponseDto from(Comment comment){
        return new GetCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getSchedule().getId(),
                comment.getUser().getId(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
