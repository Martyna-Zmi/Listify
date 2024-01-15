package com.example.listifyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ListifyFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListifyFrontApplication.class, args);
	}

}
