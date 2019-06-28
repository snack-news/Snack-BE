package com.snack.news;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnackApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/snack-news/real-application.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(SnackApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}