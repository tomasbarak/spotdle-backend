package com.spotdle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpotdleApplication {

	@Value("${url.frontendDomain}")
    private String frontendDomain;

	@Value("${url.backendDomain}")
    private String backendDomain;

	public static void main(String[] args) {
		SpringApplication.run(SpotdleApplication.class, args);
	}
}
