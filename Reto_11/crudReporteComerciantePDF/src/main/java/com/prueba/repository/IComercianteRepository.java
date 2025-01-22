package com.prueba.repository;


import com.prueba.model.Comerciante;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IComercianteRepository extends JpaRepository<Comerciante, Long> {
    // Métodos adicionales si es necesario

    List<Comerciante> findByIList(Long id); 
}
