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
	
}
