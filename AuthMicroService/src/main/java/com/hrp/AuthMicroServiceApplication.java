package com.hrp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class AuthMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthMicroServiceApplication.class);
    }
}