package org.example.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // ap dung cho tat ca endpoint
        .allowedOrigins("http://localhost:5173") //set port duoc cho phep
        .allowCredentials(true) // cho phep gui credentials
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); //them nhuwng HTTP method duoc phep su dung
    }
}
