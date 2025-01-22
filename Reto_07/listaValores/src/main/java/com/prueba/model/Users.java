package com.prueba.model;
import java.io.Serializable;
import java.util.List;


//import org.hibernate.annotations.GenericGenerator;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;

@Table ( name = "usuario")
@Entity
public class Users implements Serializable {
	
	private static final long serialVersionUID = -6833167247955613395L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@SequenceGenerator(name="s_user", sequenceName="s_user",allocationSize=1)
	private Long id_user;
	
	
	@Column 
	@NotBlank
	@Size(min=5,max=8,message="No se cumple las reglas de tamaño")

	private String firstname;
	
	@Column 
	@NotBlank
	private String lastname;
	@Column(unique = true) 
	@Email
	private String email;
	
	@Column(unique = true, length = 20) 
	private String username;
	
	@Column(name="passworduser", length = 100)
	private String password;
	
	//obmite este valor en la base de datos
	@Transient 
	private String confirmPassword;
	

	

	public Users() {	
		super();
	}
	
	public Users(Long id) {
		this.id_user = id;
      }



	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastName) {
		this.lastname = lastName; 
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	
	@Column
	private Character activo;

	public Character getActivo() {
		return activo;
	}

	public void setActivo(Character activo) {
		this.activo = activo;
	}
	
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="user_roles", joinColumns= @JoinColumn(name="id_user"),
	inverseJoinColumns=@JoinColumn(name="id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"id_user", "id"})})
	private List<UserRole> roles;
	
	
	
	/*
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	*/
	
	


	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id_user + ", firstName=" + firstname + ", lastName=" + lastname + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", roles=" + roles+  ", activo="+activo+ "]";
	}


	
}
