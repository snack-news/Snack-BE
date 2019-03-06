package com.snack.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnackApplication {
	public static void main(String[] args) {
		SpringApplication.run(SnackApplication.class, args);
	}
}