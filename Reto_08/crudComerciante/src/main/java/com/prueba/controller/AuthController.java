package com.prueba.controller;

import com.prueba.auth.JwtTokenProvider;
import com.prueba.model.LoginRequest;
import com.prueba.model.Users;
import com.prueba.model.JwtResponse;
import com.prueba.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService usuarioService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(JwtTokenProvider jwtTokenProvider, UserService usuarioService, BCryptPasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Users user = usuarioService.findByEmail(loginRequest.getEmail());  // Cambiar el tipo a Users
        
        // Verificar si el usuario existe y si la contraseña es válida
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        	String role = user.getRoles().stream()
                    .findFirst()  // Obtiene el primer rol (si existe)
                    .map(r -> r.getRole().getName())  
                    .orElse("ROLE_USER");
            // Generamos el token con la información del usuario
            String token = jwtTokenProvider.generateToken(user.getUsername());  
            
            // Retornar el token en la respuesta
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            // Retornar error de autenticación si el usuario no existe o la contraseña es incorrecta
            return ResponseEntity.status(401).body("Unauthorized: Invalid email or password");
        }
    }


}
