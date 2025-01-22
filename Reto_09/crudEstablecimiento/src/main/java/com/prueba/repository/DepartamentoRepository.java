package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
