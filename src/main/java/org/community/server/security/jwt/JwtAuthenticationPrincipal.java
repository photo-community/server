package org.community.server.security.jwt;

public class JwtAuthenticationPrincipal {

    private final Long id;

    private final String name;

    public JwtAuthenticationPrincipal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
