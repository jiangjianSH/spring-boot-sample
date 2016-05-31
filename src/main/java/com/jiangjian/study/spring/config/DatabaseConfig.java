package com.jiangjian.study.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public String name() {
        System.out.println("############################################3");
        return "jiangjian";
    }
}
