package com.example.kakaotest.component.mail;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external")
@Data
public class ExternalEnv {
    private String apiKey;
}
