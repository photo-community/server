package org.community.server.security.handler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.community.server.exception.common.ErrorResponseDTO;
import org.community.server.security.jwt.Jwt;
import org.community.server.security.jwt.JwtAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResultHandler {

    public static void sendResponse(HttpServletResponse response, Authentication authentication, Jwt jwt, String allowCors) throws IOException {
        String token = jwt.create((JwtAuthenticationToken) authentication);
        response.setHeader(Jwt.HEADER_KEY, Jwt.BEARER + token);
        response.setHeader("Access-Control-Allow-Origin", allowCors);
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().print(token);
    }

    public static void sendErrorResponse(HttpServletResponse response, AuthenticationException e, String allowCors) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Access-Control-Allow-Origin", allowCors);
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();
        response.getWriter().write(responseDTO.toString());
    }

    public static void sendErrorResponse(HttpServletResponse response, Exception e, String allowCors) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Access-Control-Allow-Origin", allowCors);
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();
        response.getWriter().write(responseDTO.toString());
    }
}
