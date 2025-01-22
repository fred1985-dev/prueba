package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}
