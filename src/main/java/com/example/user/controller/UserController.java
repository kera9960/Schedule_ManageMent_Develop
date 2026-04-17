package com.example.user.controller;

import com.example.user.dto.CreateUserRequestDto;
import com.example.user.dto.CreateUserResponseDto;
import com.example.user.dto.GetUserResponseDto;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponseDto> createUser(
            @RequestBody CreateUserRequestDto requestDto
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponseDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponseDto> getUSer(
            @PathVariable Long userId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }
}
