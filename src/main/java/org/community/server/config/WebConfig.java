package org.community.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Configuration Common Web setting
 *
 * @author MC Lee
 * @created 2022-10-24
 * @since 2.6.3 spring boot
 * @since 0.0.1 dev
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String[] allowCors;

    public WebConfig(@Value("${allow-cors}") String[] allowCors) {
        this.allowCors = allowCors;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.stream()
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                .findFirst()
                .ifPresent(converter -> ((MappingJackson2HttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowCors)
                .allowedMethods("GET", "POST", "DELETE", "OPTIONS");
    }
}