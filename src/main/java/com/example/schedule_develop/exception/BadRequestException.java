package com.example.schedule_develop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends ServiceException {
    public BadRequestException(String message){
        super(HttpStatus.BAD_REQUEST,message);
    }
}
