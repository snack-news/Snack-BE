package com.snack.news;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnackApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/home/ec2-user/snack-be/config/real-application.yml";

	public static void main(String[] args) {
		try {
			new SpringApplicationBuilder(SnackApplication.class)
					.properties(APPLICATION_LOCATIONS)
					.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}