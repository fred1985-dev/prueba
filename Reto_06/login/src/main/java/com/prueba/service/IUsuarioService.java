package com.prueba.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.prueba.model.Users;

public interface IUsuarioService {

	public Users findByUsername(String username);

	public Object findByEmailDOs(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	 

}
