package com.prueba.model;

import java.io.Serializable;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;



@Table ( name = "user_roles")
@Entity
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -2969524610059270447L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@SequenceGenerator(name="S_USER_ROLES", sequenceName="S_USER_ROLES",allocationSize=1)
    @Column
	private Long user_roles_id;
	
	
	
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JoinColumn(name = "id_user", nullable = false)
	 private Users user;
	
	 @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 @JoinColumn(name = "id", nullable = false)
	 private Role role;
	 
	 
	   
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	
	
	
}
