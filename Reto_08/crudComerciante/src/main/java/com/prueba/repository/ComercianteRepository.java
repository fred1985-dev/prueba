package com.prueba.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prueba.dto.ComercianteDTO;
import com.prueba.model.Comerciante;

import jakarta.validation.Valid;

@Repository
public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {

    // Llama al procedimiento para consultar comerciantes con filtros y paginación
    @Procedure(procedureName = "PK_COMERCIANTE.CONSULTAR_COMERCIANTES")
    List<ComercianteDTO> consultarComerciantes(
        @Param("p_nombre") String nombre,
        @Param("p_municipio_id") Long municipioId,
        @Param("p_fecha_registro") LocalDate fechaRegistro,
        @Param("p_estado") String estado,
        @Param("p_limite") Integer limite,
        @Param("p_offset") Integer offset
    );

    // Llama al procedimiento para obtener total de ingresos y empleados para un comerciante
    @Procedure(procedureName = "PK_COMERCIANTE.TOTAL_INGRESOS_Y_EMPLEADOS")
    Object[] calcularIngresosYEmpleados(@Param("idComerciante") Long idComerciante);

    // Llama al procedimiento para crear un comerciante
    @Procedure(procedureName = "PK_COMERCIANTE.CREAR_COMERCIANTE")
    void crearComerciante(
        @Param("p_municipio_id") Long municipioId,
        @Param("p_nombre") String nombre,
        @Param("p_telefono") String telefono,
        @Param("p_fecha_registro") LocalDate fechaRegistro,
        @Param("p_estado") String estado
    );


    // Llama al procedimiento para eliminar un comerciante
    @Procedure(procedureName = "PK_COMERCIANTE.ELIMINAR_COMERCIANTE")
    void eliminarComerciante(
        @Param("p_comerciante_id") Long comercianteId
    );
    
    // Procedimiento para modificar el estado de un comerciante
    @Query(nativeQuery = true, value = "CALL PKG_COMERCIANTE.modificar_estado_comerciante(:comercianteId, :estado)")
    ComercianteDTO modificarEstado(@Param("comercianteId") Long comercianteId, @Param("estado") String estado);


    // Llamar a la función de conteo de comerciantes dentro de un paquete
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM TABLE(PKG_COMERCIANTE.consultar_comerciantes(:nombre, :municipioId, :estado, 0, 0))")
    long countComerciantes(
            @Param("nombre") String nombre,
            @Param("municipioId") Long municipioId,
            @Param("estado") String estado);
    
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_COMERCIANTE.consultar_comerciante_por_id(:comercianteId))")
    ComercianteDTO consultarComerciantePorId(@Param("comercianteId") Long comercianteId);
    
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_COMERCIANTE.actualizar_comerciante(:comercianteId, :nombre, :municipioId, :estado, :usuario))")
	ComercianteDTO actualizarComerciante(@Param("comercianteId") Long comercianteId,
                                         @Param("nombre") String nombre,
                                         @Param("municipioId") Long municipioId,
                                         @Param("estado") String estado,
                                         @Param("usuario") String usuario);
    // Llama al procedimiento para eliminar un comerciante

	

	ComercianteDTO actualizarComerciante(Long comercianteId, ComercianteDTO comercianteDTO);

	void crearComerciante(@Valid ComercianteDTO comercianteDTO);
}
