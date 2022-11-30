package org.community.server.config;

import org.community.server.api.user.service.UserService;
import org.community.server.security.filter.AllowCorsFilter;
import org.community.server.security.filter.JwtAuthenticationFilter;
import org.community.server.security.filter.JwtLoginFilter;
import org.community.server.security.handler.UnauthorizedHandler;
import org.community.server.security.jwt.Jwt;
import org.community.server.security.jwt.JwtAuthenticationProvider;
import org.community.server.security.jwt.JwtTokenProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtTokenProperty configure;
    private final UnauthorizedHandler unauthorizedHandler;
    private final String[] allowCors;

    public SecurityConfig(JwtTokenProperty configure, UnauthorizedHandler unauthorizedHandler, @Value("${allow-cors}") String[] allowCors) {
        this.configure = configure;
        this.unauthorizedHandler = unauthorizedHandler;
        this.allowCors = allowCors;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder builder, JwtAuthenticationProvider authenticationProvider) {
        builder.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationProvider jdbcAuthenticationProvider(@Lazy UserService userService) {
        return new JwtAuthenticationProvider(userService);
    }

    @Bean
    public Jwt jwt() {
        return new Jwt(configure.getClientSecret(), configure.getExpiration());
    }

    @Bean
    public AllowCorsFilter allowCorsFilter() {
        return new AllowCorsFilter(allowCors);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().disable()
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .formLogin().disable()
                .apply(new CustomDsl())
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpStatus.OK.value()))
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers("/api/v1/devices/**").permitAll()
                .antMatchers("/api/v1/users/**").authenticated()
                .anyRequest().permitAll();
        return http.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilterBefore(new AllowCorsFilter(allowCors), CorsFilter.class)
                    .addFilterBefore(new JwtLoginFilter("/api/login", authenticationManager, jwt(), allowCors), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JwtAuthenticationFilter(jwt()), UsernamePasswordAuthenticationFilter.class);
        }

    }
}
