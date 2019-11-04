package com.snack.news.config;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        ApplicationConversionService.configure(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}