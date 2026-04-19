package com.example.schedule_develop.comment.dto;

import com.example.schedule_develop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponseDto {

    private final Long id;
    private final Long scheduleId;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateCommentResponseDto(Long id, Long scheduleId, String content, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateCommentResponseDto from(Comment comment){
        return new CreateCommentResponseDto(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
