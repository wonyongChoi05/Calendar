package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.LoginReq;
import com.nyong.calendar.api.dto.SignUpReq;
import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.dto.UserCreateReq;
import com.nyong.calendar.core.service.UserService;
import com.nyong.calendar.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class LoginService {

    public final static String LOGIN_SESSION_KEY = "USER_ID";
    private Encryptor encryptor;
    private final UserService userService;

    public void signUp(SignUpReq signUpReq, HttpSession session) {
        final User user = userService.create(new UserCreateReq(
                signUpReq.getName(),
                signUpReq.getEmail(),
                signUpReq.getPassword(),
                signUpReq.getBirthday()
        ));
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }

    public void login(LoginReq loginReq, HttpSession session) {
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
        if (userId != null) {
            return;
        }
        final Optional<User> user = userService.findPwMatchUser(loginReq.getEmail(), loginReq.getPassword());

        if (user.isPresent()) {
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        } else {
            throw new RuntimeException("email 또는 password가 틀렸습니다.");
        }
    }

    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_SESSION_KEY);
    }
}
