package com.prueba.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "MUNICIPIO")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_MUNICIPIO")
    @SequenceGenerator(name = "S_MUNICIPIO", sequenceName = "S_MUNICIPIO", allocationSize = 1)
    @Column(name = "MUNICIPIO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTAMENTO_ID", nullable = false)
    private Departamento departamento;

    @Column(name = "NOMBRE_MUNICIPIO", nullable = false)
    private String nombreMunicipio;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Date updatedAt;

    @Column(name = "USUARIO", nullable = false)
    private String usuario;

}
