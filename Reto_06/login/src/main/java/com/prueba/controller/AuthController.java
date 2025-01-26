package com.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;



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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
    	
    	System.out.println("datos del login"+loginRequest.getEmail());
        try {
            // Autenticación de usuario usando el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
try {
    // Generación del token JWT
    String token = jwtTokenUtil.generateToken(authentication);
    System.out.println("datos tocken "+token);
    return ResponseEntity.ok(new JwtResponse(token));
	
}catch (Exception e) {
	// TODO: handle exception
    System.out.println("datos tocken "+e.getMessage().toString());
    System.out.println("datos tocken "+e.getStackTrace().toString());

}

       
    
        	
        	
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
		return null;
    }
}
