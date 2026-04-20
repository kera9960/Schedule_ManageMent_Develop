package com.example.schedule_develop.user.controller;

import com.example.schedule_develop.exception.BadRequestException;
import com.example.schedule_develop.exception.ForbiddenException;
import com.example.schedule_develop.exception.UnauthorizedException;
import com.example.schedule_develop.user.dto.*;
import com.example.schedule_develop.user.entity.User;
import com.example.schedule_develop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
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
           @Valid @RequestBody CreateUserRequestDto requestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(requestDto));
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponseDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponseDto> getUSer(
            @PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findOne(userId));
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequestDto requestDto,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        if (!sessionUserDto.getId().equals(userId)){
            throw new ForbiddenException("본인만 수정 가능합니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId,requestDto));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId,
            HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        if (!sessionUserDto.getId().equals(userId)){
            throw new ForbiddenException("본인만 삭제 가능합니다.");
        }
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request,
            HttpSession session){
        User user = userService.login(request.getEmail(), request.getPassword());
        SessionUserDto sessionUserDto = new SessionUserDto(user.getId(),user.getEmail());
        session.setAttribute("loginUser",sessionUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(user.getId(), user.getEmail()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        SessionUserDto sessionUserDto = (SessionUserDto) session.getAttribute("loginUser");
        if (sessionUserDto==null){
            throw new UnauthorizedException("로그인이 필요합니다.");
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
