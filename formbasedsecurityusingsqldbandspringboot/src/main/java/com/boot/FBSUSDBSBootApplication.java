package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FBSUSDBSBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(FBSUSDBSBootApplication.class, args);
    }
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FBSUSDBSBootApplication.class);
    }
}
