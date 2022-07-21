package com.nyong.calendar.api.config;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.nyong.calendar.api.service.LoginService.LOGIN_SESSION_KEY;

// Component 등록은 WebConfig 에서
public class AuthUserResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return AuthUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY,
                WebRequest.SCOPE_SESSION);
        if (userId != null) {
            return AuthUser.toAuthUser(userId);
        } else {
            throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
