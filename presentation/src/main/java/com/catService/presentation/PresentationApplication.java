package com.catService.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.catService.kafka.*",
		"com.catService.security.*",
		"com.catService.presentation.*"
})
public class PresentationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PresentationApplication.class, args);
	}

}
