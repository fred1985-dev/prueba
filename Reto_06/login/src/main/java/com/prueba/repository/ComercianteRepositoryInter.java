package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.prueba.model.Comerciante;

public interface ComercianteRepositoryInter extends JpaRepository<Comerciante, Long> {
	
    // Llama al procedimiento para eliminar un comerciante
    @Procedure(procedureName = "PK_COMERCIANTE.ELIMINAR_COMERCIANTE")
    void eliminarComerciante(
        @Param("p_comerciante_id") Long comercianteId
    );
    

}
