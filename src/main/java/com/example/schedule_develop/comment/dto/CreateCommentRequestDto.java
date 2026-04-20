package com.example.schedule_develop.comment.dto;

import jakarta.validation.constraints.Max;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private String content;
    @Max(4)
    private String author;
    private String password;
}
