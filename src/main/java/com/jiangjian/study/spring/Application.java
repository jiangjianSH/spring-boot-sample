package com.jiangjian.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hi, Welcome to Spring Boot Learning.";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
