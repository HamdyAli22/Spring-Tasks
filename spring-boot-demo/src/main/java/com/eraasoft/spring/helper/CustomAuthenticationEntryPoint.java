package com.eraasoft.spring.helper;

import com.eraasoft.spring.controller.vm.ExceptionResponse;
import com.eraasoft.spring.service.bundlemessage.BundleMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private BundleMessageService bundleMessageService;

    public CustomAuthenticationEntryPoint(BundleMessageService bundleMessageService) {
        this.bundleMessageService = bundleMessageService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = bundleMessageService.getMessage("student.user.unauthorized");
        ExceptionResponse body = new ExceptionResponse(message);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(body));
    }
}
