package com.nyong.calendar.core.service;

import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.domain.entity.repository.UserRepository;
import com.nyong.calendar.core.dto.UserCreateReq;
import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import com.nyong.calendar.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final Encryptor encryptor;

    @Transactional
    public User create(UserCreateReq req) {
        userRepository.findByEmail(req.getEmail())
                .ifPresent(u -> {
                    throw new CalendarException(ErrorCode.USER_NOT_FOUND);
                });
        return userRepository.save(req.toEntity(encryptor));
    }

    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.isMatch(encryptor, password) ? user : null);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CalendarException(ErrorCode.USER_NOT_FOUND));
    }
}
