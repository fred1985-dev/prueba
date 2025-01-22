package com.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "DEPARTAMENTO")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_DEPARTAMENTO")
    @SequenceGenerator(name = "S_DEPARTAMENTO", sequenceName = "S_DEPARTAMENTO", allocationSize = 1)
    @Column(name = "DEPARTAMENTO_ID")
    private Long id;

    @Column(name = "NOMBRE_DEPARTAMENTO", nullable = false)
    private String nombreDepartamento;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Date updatedAt;

    @Column(name = "USUARIO", nullable = false)
    private String usuario;


}
