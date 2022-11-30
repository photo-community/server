package org.community.server.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.community.server.api.user.exception.UserCrudErrorCode;
import org.community.server.security.dto.LoginRequestDTO;
import org.community.server.security.jwt.Jwt;
import org.community.server.security.jwt.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.community.server.security.handler.AuthResultHandler.sendErrorResponse;
import static org.community.server.security.handler.AuthResultHandler.sendResponse;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final Jwt jwt;
    private final String[] allowCors;

    public JwtLoginFilter(String url, AuthenticationManager authManager, Jwt jwt, String[] allowCors) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.jwt = jwt;
        this.allowCors = allowCors;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        try {
            LoginRequestDTO req = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDTO.class);
            return getAuthenticationManager().authenticate(
                    new JwtAuthenticationToken(
                            req.getUserId(), req.getPassword(), new ArrayList<>()
                    )
            );
        } catch (MismatchedInputException e) {
            sendErrorResponse(response, e, request.getHeader("origin"));
            throw new BadCredentialsException(UserCrudErrorCode.INCORRECT_USERID_OR_PASSWORD.getMsg());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        sendResponse(response, authentication, jwt, request.getHeader("origin"));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        sendErrorResponse(response, failed, request.getHeader("origin"));
    }
}
