package com.example.schedule_develop.user.service;

import com.example.schedule_develop.config.PasswordEncoder;
import com.example.schedule_develop.exception.BadRequestException;
import com.example.schedule_develop.exception.DuplicateEmailException;
import com.example.schedule_develop.exception.NotFoundException;
import com.example.schedule_develop.user.dto.*;
import com.example.schedule_develop.user.entity.User;
import com.example.schedule_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponseDto save(CreateUserRequestDto requestDto) {
        Optional<User> foundUser = userRepository.findByEmail(requestDto.getEmail());
        if (foundUser.isPresent()){
            throw new DuplicateEmailException("중복된 이메일입니다.");
        }
        User user = new User(
                requestDto.getUserName(),
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);

        return CreateUserResponseDto.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<GetUserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(GetUserResponseDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetUserResponseDto findOne(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
        return GetUserResponseDto.from(user);
    }

    @Transactional
    public UpdateUserResponseDto update(Long userId, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
        user.update(
                requestDto.getUserName(),
                requestDto.getEmail());
        return UpdateUserResponseDto.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
        userRepository.delete(user);
    }

    @Transactional
    public SessionUserDto login(String email, String password){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("없는 유저입니다.")
        );
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new BadRequestException("비밀번호가 틀렸습니다.");
        }
        SessionUserDto sessionUserDto = new SessionUserDto(user.getId(), user.getEmail());
        return sessionUserDto;
    }
}
