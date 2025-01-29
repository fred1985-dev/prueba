package com.prueba.service;

import java.util.List;
import java.util.stream.Collectors;

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
public class UsuarioServiceImpl implements IUsuarioService,UserDetailsService {

    private final UserRepository userRepository;

    // Almacena el último usuario cargado para evitar problemas de cast
    private Users usuarioActual;
    
    @Autowired
    public UsuarioServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users usuario = userRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Error en el login, no existe el usuario en el sistema");
        }

        // Imprimir roles en consola para depuración
        usuario.getRoles().forEach(role -> {
            System.out.println("-----ROL_ID-------" + role.getRole().getId());
            System.out.println("------ROL-------" + role.getRole().getName());
        });

        // Convertir roles en SimpleGrantedAuthority
        // Convertir roles en SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authoritys = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                .peek(authority -> System.out.println("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authoritys);
    }
    
	@Transactional(readOnly=true)
	public Users findByUsers(String username) {
		return userRepository.findByUsername(username);
	}
    
	@Transactional(readOnly = true)
	public Users findByUsername(String username) {
	    try {
	        // Obtener el UserDetails
	        UserDetails userDetails = loadUserByUsername(username);
	        
	        // Ahora, usar el username para obtener el objeto Users
	        return userRepository.findByUsername(userDetails.getUsername());
	        
	    } catch (UsernameNotFoundException e) {
	        return null; // O manejar la excepción según sea necesario
	    }
	}
	  
	@Override
	public Object findByEmailDOs(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailCustom(email);
	}


}
