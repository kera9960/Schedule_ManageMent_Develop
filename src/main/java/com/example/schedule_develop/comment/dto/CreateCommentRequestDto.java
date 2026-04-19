package com.example.schedule_develop.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private String content;
    private String author;
    private String password;
}
