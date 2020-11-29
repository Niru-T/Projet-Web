package com.api.project.Api.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan({"com.api.project.Api.Project"})
public class ApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProjectApplication.class, args);
	}

}
