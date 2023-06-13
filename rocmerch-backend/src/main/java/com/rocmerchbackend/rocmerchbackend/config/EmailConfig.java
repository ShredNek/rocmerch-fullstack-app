package com.rocmerchbackend.rocmerchbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

        @Bean
        public JavaMailSender javaMailSender() {
            return new JavaMailSenderImpl();
        }
}

