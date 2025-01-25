package com.prueba.auth;

import java.security.Principal;
import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import com.prueba.model.Users;
import com.prueba.service.IUsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenUtil {
	@Autowired
	private IUsuarioService usuarioService;
	
    private String jwtSecret = "12345";  // Esta debería ser una clave privada RSA

    public String generateToken(Principal authentication) {
        Users  usuario1 = usuarioService.findByUsername(authentication.getName());

        return Jwts.builder()
                .setSubject(usuario1.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // Expiración en 1 hora
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Otros métodos para validar y obtener información del token...
}
