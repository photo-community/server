package org.community.server.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.community.server.api.user.domain.entity.User;
import org.community.server.api.user.exception.UserCrudErrorCode;
import org.community.server.api.user.service.UserService;
import org.community.server.exception.common.BizException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.ClassUtils;

import static java.util.stream.Collectors.toList;

@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    public JwtAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return processUserAuthentication(
                String.valueOf(authentication.getPrincipal()),
                String.valueOf(authentication.getCredentials().toString())
        );
    }

    private Authentication processUserAuthentication(String userId, String password) {
        try {
            User user = userService.login(userId, password);
            JwtAuthenticationToken token = new JwtAuthenticationToken(
                    new JwtAuthenticationPrincipal(user.getId(), user.getUserId()),
                    password,
                    (userService.findAllByUserId(user.getId())).getUserRoles()
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(toList())
            );
            token.setDetails(user);
            return token;
        } catch (BizException e) {
            log.error(UserCrudErrorCode.INCORRECT_USERID_OR_PASSWORD.getMsg());
            throw new BadCredentialsException(UserCrudErrorCode.INCORRECT_USERID_OR_PASSWORD.getMsg());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ClassUtils.isAssignable(JwtAuthenticationToken.class, authentication);
    }
}
