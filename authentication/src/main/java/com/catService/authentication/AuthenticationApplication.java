package com.catService.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.catService.infrastructure.repositories")
@EntityScan(basePackages = "com/catService/infrastructure/entities")
@ComponentScan(basePackages = {
        "com.catService.authentication.*",
        "com.catService.security.*",
        "com.catService.kafka.*"
})
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

}