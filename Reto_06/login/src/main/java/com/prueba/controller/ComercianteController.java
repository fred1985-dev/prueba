package com.prueba.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.dto.ComercianteDTO;
import com.prueba.service.ComercianteService;
import com.prueba.service.ComercianteServiceIn;
import com.prueba.util.ConstantesRolesMP;


import jakarta.servlet.http.HttpServletResponse;


@CrossOrigin(origins = ConstantesRolesMP.DIRECCION_IP, maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT }, allowedHeaders = "*")

@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {
	
    @Autowired
    private ComercianteServiceIn comercianteService;
    
    @Autowired
    private ComercianteService comercianteServicereportR;
    
    
    
    
    @GetMapping("/exportar")
    public void exportarComerciantesActivos(HttpServletResponse response) {
        try {
        	comercianteServicereportR.generarArchivoPlano(response);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar el archivo CSV", e);
        }
    }
    @Secured(ConstantesRolesMP.ADMIN)
	@GetMapping("/getListComerciantePage/")
	public ResponseEntity<?> getPropietarioVehiculoPage(
	    @RequestParam(value = "page", defaultValue = "0") int page,
	    @RequestParam(value = "size", defaultValue = "10") int size) {

	    Map<String, Object> response = new HashMap<>();

	    try {
	        int offset = page * size;
	        int limit = size;

	        List<ComercianteDTO> resultados = comercianteService.buscaComerciantePage(limit, offset);

	        int totalItems =6;
	        int totalPages = (int) Math.ceil((double) totalItems / size);

	        response.put("comercianteList", resultados);
	        response.put("currentPage", page);
	        response.put("totalItems", totalItems);
	        response.put("totalPages", totalPages);

	        return new ResponseEntity<>(response, HttpStatus.OK);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	    
	    
	}
    

    // Endpoint para activar o inactivar un comerciante
    @PatchMapping("/modificar-estado/{comercianteId}")
    public ResponseEntity<String> modificarEstadoComerciante(@PathVariable Long comercianteId, @RequestParam String estado) {
        if (!estado.equalsIgnoreCase("Activo") && !estado.equalsIgnoreCase("Inactivo")) {
            return ResponseEntity.badRequest().body("Estado debe ser 'Activo' o 'Inactivo'");
        }
      //  comercianteService.modificarEstado(comercianteId, estado);
        return ResponseEntity.ok("Estado del comerciante actualizado exitosamente");
    }
    
    
	@Secured(ConstantesRolesMP.ADMIN)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		Map<String, Object> response = new HashMap<>();
		try {
		
			comercianteServicereportR.eliminarComerciante(id);

			
		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar el registro  en la db");
			response.put("mensaje", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Comerciante ha sido eliminado correctamente.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}