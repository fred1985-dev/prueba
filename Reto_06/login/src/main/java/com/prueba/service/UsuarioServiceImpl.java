package com.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.model.Users;
import com.prueba.repository.UserRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UserRepository userRepository;

    @Autowired
    public UsuarioServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Object findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
