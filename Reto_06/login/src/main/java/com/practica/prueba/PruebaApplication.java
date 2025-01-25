package com.practica.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication(scanBasePackages = { "com.*"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@EntityScan("com.prueba.model") 
@ComponentScan(basePackages = {"com.prueba.service"})
@EnableJpaRepositories("com.prueba.repository")
@ComponentScan(basePackages = {"com.prueba", "com.prueba.auth", "com.prueba.service"})
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

}
