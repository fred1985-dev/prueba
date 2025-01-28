package com.prueba.service;


import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prueba.model.Users;
import com.prueba.repository.UserRepository;






@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Verifica si el email es nulo o vacío antes de intentar buscarlo en la base de datos
        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Email is empty or null");
        }

        // Busca el usuario por su email
        Users user = userRepository.findByEmailCustom(email);

        // Verifica si el usuario fue encontrado
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Si todo está bien, crea y devuelve el UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // Aquí usas el email
                user.getPassword(),
                new ArrayList<>() // Roles o permisos pueden ir aquí si los tienes
        );
    }
}
