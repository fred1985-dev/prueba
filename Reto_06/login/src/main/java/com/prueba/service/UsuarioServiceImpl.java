package com.prueba.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = userRepository.findByUsername(username);
		
		if(usuario == null) {
			//logger.error("Error en el login no existe el usuario en el sistema");
		     throw new UsernameNotFoundException("Error en el login no existe el usuario en el sistema");
		}

		
	    for (int i=0;i<	usuario.getRoles().size();i++) {
	        System.out.println("-----ROL_ID-------"+usuario.getRoles().get(i).getRole().getId());
	        System.out.println("------ROL-------"+usuario.getRoles().get(i).getRole().getName());
	      }
		
		List<SimpleGrantedAuthority> authoritys = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
				//.peek(authority -> logger.info("Role:"+authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authoritys) ;
	
	}
    
    
    
    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public Object findByEmailDOs(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailCustom(email);
	}


}
