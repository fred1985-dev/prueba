package com.prueba.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.model.Users;
import com.prueba.repository.UserRepository;


@Service
public class UserService implements IUsuarioService, UserDetailsService {
	
	@Autowired
	private  UserRepository userRepository;
	
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = userRepository.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login no existe el usuario en el sistema");
		     throw new UsernameNotFoundException("Error en el login no existe el usuario en el sistema");
		}

		
	    for (int i=0;i<	usuario.getRoles().size();i++) {
	        System.out.println("-----ROL_ID-------"+usuario.getRoles().get(i).getRole().getId());
	        System.out.println("------ROL-------"+usuario.getRoles().get(i).getRole().getName());
	      }
		
		List<SimpleGrantedAuthority> authoritys = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
				.peek(authority -> logger.info("Role:"+authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authoritys) ;
	
	}
	@Transactional(readOnly=true)
	public Users findByUsers(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public Users findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	
	  // Nuevo método findByEmail
    @Transactional(readOnly = true)
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
