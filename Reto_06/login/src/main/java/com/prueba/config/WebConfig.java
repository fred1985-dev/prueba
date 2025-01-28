package com.prueba.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
/*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Permite peticiones desde tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Accept")  // Asegura que Authorization está permitido
                .allowCredentials(true);  // Permite enviar cookies o cabeceras de autenticación
    }
   */ 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite solicitudes desde cualquier origen (esto es útil en desarrollo)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Cambia por tu URL de frontend si es necesario
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Permitir cualquier cabecera
                .allowCredentials(true); // Permite el envío de credenciales (si es necesario)
    }
    
    
}
