package com.codingshuttle.springwebtutorial.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "external.post")
public class PostApiProperties {
    private String baseUrl;
}
