package com.example.veiwServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		 registry.addMapping("/**")
         .allowedOrigins("http://localhost:8094", "https://192.168.0.229:8094")
         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
         .allowedHeaders("*")
         .exposedHeaders("Authorization","MemberId","role","msgLogin","msg")
         .allowCredentials(true);
	}
}
