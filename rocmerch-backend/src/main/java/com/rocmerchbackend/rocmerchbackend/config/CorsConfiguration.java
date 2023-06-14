package com.rocmerchbackend.rocmerchbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://roc-merch-fullstack.web.app", "https://rocmerch-fullstack-app-production.up.railway.app")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .exposedHeaders("Access-Control-Allow-Origin")
                .allowCredentials(true);
    }
}