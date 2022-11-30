package org.community.server.api.user.controller;

import lombok.RequiredArgsConstructor;
import org.community.server.api.common.ResponseDTO;
import org.community.server.api.user.domain.dto.UserResponseDTO;
import org.community.server.api.user.domain.enums.UserMessage;
import org.community.server.api.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseDTO<UserResponseDTO.FindOne> findOneUser(@PathVariable("userId") String userId) {
        return new ResponseDTO<>(userService.findByUserId(userId), UserMessage.SUCCESS_ADD_USER);
    }
}
