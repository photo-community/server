package org.community.server.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.community.server.security.jwt.Jwt;
import org.community.server.security.jwt.JwtAuthenticationToken;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.community.server.security.handler.AuthResultHandler.sendErrorResponse;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Jwt jwt;

    public JwtAuthenticationFilter(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            // TODO: isAlreadyAuthenticated 문제. ThreadLocal 하지 않으며 이전 유저의 인증값이 사용됨
            if (isAlreadyAuthenticated()) {
                filterChain.doFilter(request, response);
                return;
            }

            JwtAuthenticationToken authentication = jwt.convertToAuthentication(request);
            if (authentication != null) {
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            log.info("Authentication Failed : {}", e.getMessage());
            sendErrorResponse(response, e, request.getHeader("origin"));
        }
    }

    public boolean isAlreadyAuthenticated() {
        // TODO: 이전에 요청했던 유저의 인증정보가 남아있음.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
