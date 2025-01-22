package com.prueba.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/login**", "/oauth2/authorization/**").permitAll()  // Permite acceso a login y OAuth2
            .anyRequest().authenticated()  // Requiere autenticación para cualquier otra solicitud
            .and()
            .oauth2Login();  // Habilita OAuth2 para el login

        return http.build();  // Construye la configuración de seguridad
    }
}
