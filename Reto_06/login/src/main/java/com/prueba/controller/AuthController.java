package com.prueba.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.auth.JwtTokenUtil;
import com.prueba.model.JwtResponse;
import com.prueba.model.LoginRequest;
import com.prueba.util.ConstantesRolesMP;

@CrossOrigin(origins = ConstantesRolesMP.DIRECCION_IP, maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT }, allowedHeaders = "*")

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    System.out.println("Email recibido: " + loginRequest.getEmail());
	    System.out.println("Password recibido: " + loginRequest.getPassword());
	    try {
	        // Autenticación del usuario
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

	        System.out.print(authentication);
	        // Generación del token JWT
	        String token = jwtTokenUtil.generateToken(authentication);

	        // Crear la respuesta con el token
	        Map<String, Object> response = new HashMap<>();
	        response.put("informacion", authentication);
	       
	        response.put("access_token", token);

	        return ResponseEntity.ok(response);
	        
	        
	        
	        
	    } catch (BadCredentialsException e) {
	        // Manejo de credenciales inválidas
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
	    } catch (Exception e) {
	        // Manejo de otros errores
	        System.err.println("Error en el login: " + e.getMessage());
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Ocurrió un error al procesar la solicitud.");
	    }
	}

}
