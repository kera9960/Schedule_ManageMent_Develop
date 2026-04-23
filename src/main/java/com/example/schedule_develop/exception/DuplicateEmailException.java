package com.example.schedule_develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException{
    public DuplicateEmailException(String message){
        super(HttpStatus.CONFLICT,message);
    }
}
