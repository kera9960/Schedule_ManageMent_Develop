package com.example.user.service;

import com.example.user.dto.CreateUserRequestDto;
import com.example.user.dto.CreateUserResponseDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public CreateUserResponseDto save(CreateUserRequestDto requestDto) {
        User user = new User(
                requestDto.getUserName(),
                requestDto.getEmail());
        User savedUser = userRepository.save(user);

        return new CreateUserResponseDto(
                savedUser.getId(),
                savedUser.getUserName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt());
    }
}
