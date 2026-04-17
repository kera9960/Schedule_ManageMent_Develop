package com.example.user.controller;

import com.example.user.dto.CreateUserRequestDto;
import com.example.user.dto.CreateUserResponseDto;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
