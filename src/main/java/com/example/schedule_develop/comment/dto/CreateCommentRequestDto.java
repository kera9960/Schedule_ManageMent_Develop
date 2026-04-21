package com.example.schedule_develop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    @NotBlank
    @Size(min = 5, max = 200)
    private String content;
}
