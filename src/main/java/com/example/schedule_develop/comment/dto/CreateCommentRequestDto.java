package com.example.schedule_develop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank
    @Size(min = 5, max = 200)
    private String content;
    @NotBlank
    @Size(max = 4)
    private String author;
    @NotBlank
    @Size(min = 8,max = 20)
    private String password;
}
