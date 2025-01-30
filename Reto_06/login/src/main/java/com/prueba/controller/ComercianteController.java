package com.prueba.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
}