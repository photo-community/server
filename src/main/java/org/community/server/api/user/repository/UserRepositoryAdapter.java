package org.community.server.api.user.repository;

import lombok.RequiredArgsConstructor;
import org.community.server.api.user.domain.entity.User;
import org.community.server.api.user.domain.entity.UserRole;
import org.community.server.api.user.exception.UserCrudErrorCode;
import org.community.server.exception.common.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional(rollbackFor = {RuntimeException.class})
public class UserRepositoryAdapter {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new BizException(UserCrudErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Set<UserRole> findAllByUserId(Long userId) {
        return userRoleRepository.findAllByUserId(userId);
    }
}
