package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.prueba.model.Users;

import java.util.Set;


 @Repository
  public interface UserRepository  extends JpaRepository<Users, Long> {

  public Users findByUsername(String username);
	
  public Set<Users> findByPassword(String password);
  
  public Iterable<Users>  findAllByActivo(Character activo);
 // @Query("SELECT u FROM Users u JOIN FETCH u.roles WHERE u.email = ?1")
  @Query("select u from Users u where u.email = ?1")
  public Users findByEmailCustom(String email);
  

}
