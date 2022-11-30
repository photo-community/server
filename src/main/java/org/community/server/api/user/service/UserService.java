package org.community.server.api.user.service;

import lombok.RequiredArgsConstructor;
import org.community.server.api.user.domain.dto.UserResponseDTO.FindOne;
import org.community.server.api.user.domain.dto.UserResponseDTO.FindRoles;
import org.community.server.api.user.domain.entity.User;
import org.community.server.api.user.repository.UserRepositoryAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryAdapter userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        user.login(passwordEncoder, password);
        return user;
    }

    public FindOne findByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        return FindOne.of(user, findAllByUserId(user.getId()));
    }

    public FindRoles findAllByUserId(Long userId) {
        return FindRoles.from(userRepository.findAllByUserId(userId));
    }

}
