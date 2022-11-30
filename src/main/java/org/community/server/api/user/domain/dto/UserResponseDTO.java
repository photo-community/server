package org.community.server.api.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.community.server.api.user.domain.entity.User;
import org.community.server.api.user.domain.entity.UserRole;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserResponseDTO {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class FindOne {

        private final Long id;
        private final String userId;
        private final String username;
        private final Set<String> userRoleList;

        public static FindOne of(User user, FindRoles roles) {
            return FindOne.builder()
                    .id(user.getId())
                    .userId(user.getUserId())
                    .username(user.getName())
                    .userRoleList(roles.getUserRoles())
                    .build();
        }
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class FindRoles {

        private final Set<String> userRoles;

        public static FindRoles from(Set<UserRole> userRoleEntities) {
            final Set<String> convertedUserRoles = new HashSet<>();

            for (UserRole role : userRoleEntities) {
                convertedUserRoles.add(role.getRole().name());
            }

            return FindRoles.builder()
                    .userRoles(convertedUserRoles)
                    .build();
        }
    }
}
