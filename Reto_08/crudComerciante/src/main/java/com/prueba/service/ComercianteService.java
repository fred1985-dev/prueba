package com.prueba.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ComercianteDTO;
import com.prueba.dto.PaginacionDTO;
import com.prueba.repository.ComercianteRepository;

import jakarta.validation.Valid;

@Service
public class ComercianteService {

    private final ComercianteRepository comercianteRepository;

    @Autowired
    public ComercianteService(ComercianteRepository comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }
/*
    public ComercianteDTO crearComerciante(ComercianteDTO comercianteDTO) {
        // Llamamos al procedimiento para crear un comerciante
        return comercianteRepository.crearComerciante(comercianteDTO);
    }
    */
    // Método para calcular ingresos y empleados
    public Map<String, Object> calcularIngresosYEmpleados(Long idComerciante) {
        Object[] resultado = comercianteRepository.calcularIngresosYEmpleados(idComerciante);

        // Mapeo del resultado a un objeto legible
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("totalIngresos", resultado[0]);
        respuesta.put("totalEmpleados", resultado[1]);
        return respuesta;
    }



	// Método que realiza la consulta de comerciantes con paginación
    public PaginacionDTO<ComercianteDTO> consultarComerciantes(
            String nombre, 
            Long municipioId, 
            String estado, 
            int pagina, 
            int limite) {

        // Lógica para calcular el offset
        int offset = (pagina - 1) * limite;

        // Llamar al repositorio para obtener los comerciantes
        List<ComercianteDTO> comerciantes = comercianteRepository.consultarComerciantes(
                nombre, municipioId, null, estado, limite, offset);

        // Obtener el total de comerciantes para la paginación
        long totalElementos = comercianteRepository.countComerciantes(nombre, municipioId, estado);

        // Crear y devolver el objeto PaginacionDTO con la lista de comerciantes
        return new PaginacionDTO<>(comerciantes, totalElementos, pagina, limite);
    }
    
    public ComercianteDTO modificarEstado(Long comercianteId, String nuevoEstado) {
        return comercianteRepository.modificarEstado(comercianteId, nuevoEstado);
    }
    
    public ComercianteDTO actualizarComerciante(Long comercianteId, ComercianteDTO comercianteDTO) {
        return comercianteRepository.actualizarComerciante(comercianteId, comercianteDTO);
    }
    
    public ComercianteDTO consultarComerciantePorId(Long comercianteId) {
        return comercianteRepository.consultarComerciantePorId(comercianteId);
    }
    
    public void eliminarComerciante(Long comercianteId) {
        comercianteRepository.eliminarComerciante(comercianteId);
    }

	public void crearComerciante(@Valid ComercianteDTO comercianteDTO) {
		// TODO Auto-generated method stub
		comercianteRepository.crearComerciante(comercianteDTO);
	}
}
