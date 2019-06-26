package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.*")
@EnableWebMvc
@PropertySource("classpath:/mysql.properties")
public class AppConfig {
	
	
}
