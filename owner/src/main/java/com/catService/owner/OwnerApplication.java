package com.catService.owner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.catService.infrastructure.repositories")
@EntityScan(basePackages = "com/catService/infrastructure/entities")
@ComponentScan(basePackages = {
        "com.catService.owner.*",
        "com.catService.kafka.*"
})
public class OwnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnerApplication.class, args);
    }

}