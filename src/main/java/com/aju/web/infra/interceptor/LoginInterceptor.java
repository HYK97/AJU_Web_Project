package com.aju.web.infra.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aju.web.infra.exception.ApplicationException;
import com.aju.web.infra.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod && ((HandlerMethod)handler).hasMethodAnnotation(LoginCheck.class)) {
            HttpSession session = request.getSession();
            if (session == null || session.getAttribute("admin") == null) {
                throw new ApplicationException(ErrorCode.UNAUTHORIZED);
            }
        }
        return true;
    }
}