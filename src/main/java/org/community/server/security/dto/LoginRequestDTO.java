package org.community.server.security.dto;

import java.beans.ConstructorProperties;

public class LoginRequestDTO {

    private final String userId;
    private final String password;

    @ConstructorProperties({"userId", "password"})
    public LoginRequestDTO(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}