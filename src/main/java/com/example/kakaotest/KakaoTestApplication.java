package com.example.kakaotest;

import com.example.kakaotest.component.mail.ExternalEnv;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KakaoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoTestApplication.class, args);
    }

}
