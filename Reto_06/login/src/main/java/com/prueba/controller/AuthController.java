package com.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.auth.JwtTokenProvider;
import com.prueba.auth.JwtTokenUtil;
import com.prueba.model.JwtResponse;
import com.prueba.model.LoginRequest;
import com.prueba.model.Users;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
    	
    	System.out.println("datos del login"+loginRequest.getEmail());
        try {
            // Autenticación de usuario usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // Generación del token JWT
            String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
    }
}
