package com.example.javaholicsbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class JavaholicsBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaholicsBotApplication.class, args);
        ApiContextInitializer.init();
    }

}
