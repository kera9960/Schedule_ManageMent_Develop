package com.example.schedule_develop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedException extends ServiceException{
    public UnauthorizedException(String message){
        super(HttpStatus.UNAUTHORIZED,message);
    }
}
