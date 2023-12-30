package com.example.listifyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = "com.example.listifyapi")
public class ListifyDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListifyDataApplication.class, args);
	}

}
