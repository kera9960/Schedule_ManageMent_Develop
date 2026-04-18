package com.example.schedule_develop.user.controller;

import com.example.schedule_develop.user.dto.*;
import com.example.schedule_develop.user.dto.*;
import com.example.schedule_develop.user.service.UserService;
import jakarta.validation.Valid;
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
           @Valid @RequestBody CreateUserRequestDto requestDto
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

    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UpdateUserRequestDto requestDto
    ){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId,requestDto));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId
    ){  userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
