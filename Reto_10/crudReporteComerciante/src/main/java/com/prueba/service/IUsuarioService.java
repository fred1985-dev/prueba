package com.prueba.service;


import com.prueba.model.Users;

public interface IUsuarioService {

	public Users findByUsername(String username);

	public Object findByEmail(String email);
	
	 

}
