package com.example.user.service;

import com.example.user.dto.CreateUserRequestDto;
import com.example.user.dto.CreateUserResponseDto;
import com.example.user.dto.GetUserResponseDto;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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
    @Transactional(readOnly = true)
    public List<GetUserResponseDto> findAll() {
        List<User> Users = userRepository.findAll();
        List<GetUserResponseDto> dtos = new ArrayList<>();
        for(User user  : Users){
            GetUserResponseDto dto = new GetUserResponseDto(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    @Transactional(readOnly = true)
    public GetUserResponseDto findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return new GetUserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
