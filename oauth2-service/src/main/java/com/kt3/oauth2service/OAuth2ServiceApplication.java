package com.kt3.oauth2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class OAuth2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2ServiceApplication.class, args);
	}
}
