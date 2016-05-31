package com.jiangjian.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ComponentScan在Root Package可以扫描整个工程中的@Component的类，包括
 * @Configuration,如果没有这个注解的话，导入配置可以使用@Import这个注解
 * 来完成，例如:@Import(DatabaseConfig.class);.
 * 如果你有xml文件格式的配置，你可以使用@ImportResource完成导入配置的功能
 *
 * @EnableAutoConfiguration这个注解的功能是根据你当前系统配置的jar包，来
 * 推测你的配置，例如如果你的classpath当中有HSQLDB，Spring boot会自动为你配置
 * 好数据库相关的beans,建议在你的主配置Class中加入这个注解，例如当前这个sample
 * 项目就放在Application这个类上。
 * 如果你想了解都进行了哪些的自动配置，你可以在启动的时候加上'--debug'参数.
 * 如果你不想启动某些特定的自动配置，你可以给改注解给定参数 ，例如:
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}),
 * 如果exclude指向的类不在classpath,你应该使用excludeName去配置Class的全名。
 * 如果你不想通过注解的方式来配置，你还可以通过spring.autoconfigure.exclude系统参数来
 * 配置，建议使用注解的形式
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
@RestController
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
