package com.nyong.calendar.core.service;

import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.domain.entity.repository.UserRepository;
import com.nyong.calendar.core.dto.UserCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateReq userCreateReq) {
        userRepository.findByEmail(userCreateReq.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("user already existed");
                });
        return userRepository.save(new User(
                userCreateReq.getName(),
                userCreateReq.getEmail(),
                userCreateReq.getPassword(),
                userCreateReq.getBirthday()
        ));
    }

    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password) ? user : null);
    }
}
