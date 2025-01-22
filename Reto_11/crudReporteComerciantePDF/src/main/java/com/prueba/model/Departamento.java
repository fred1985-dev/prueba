package com.prueba.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {

	private static final long serialVersionUID = -8563574556412347549L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_basidprtmnto")
	@SequenceGenerator(name = "s_departamento", sequenceName = "s_departamento", allocationSize = 1)
	private Long departamento_id;

	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 4, max = 12, message = "El tamaño tiene que estar entre 4 y 12")
	@Column(name = "nombre_departamento")
	private String nombre_departamento;

	@Column(name = "created_at")
	private java.util.Date createdAt;

	@Column(name = "updated_at")
	private java.util.Date updatedAt;


    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;


	public Long getDepartamento_id() {
		return departamento_id;
	}


	public void setDepartamento_id(Long departamento_id) {
		this.departamento_id = departamento_id;
	}


	public String getNombre_departamento() {
		return nombre_departamento;
	}


	public void setNombre_departamento(String nombre_departamento) {
		this.nombre_departamento = nombre_departamento;
	}


	public java.util.Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(java.util.Date createdAt) {
		this.createdAt = createdAt;
	}


	public java.util.Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(java.util.Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
    
}
