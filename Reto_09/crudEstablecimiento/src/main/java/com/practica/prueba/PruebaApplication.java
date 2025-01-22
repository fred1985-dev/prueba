package com.practica.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages = { "com.*"})
@SpringBootApplication(scanBasePackages = "com.prueba")
//@ComponentScan(basePackages = "com.prueba.auth")  
@EntityScan("com.prueba.model") 
@ComponentScan(basePackages = {"com.prueba.service","com.prue"})
@EnableJpaRepositories("com.prueba.repository")
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

}
