package com.prueba.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import com.prueba.dto.ComercianteDTO;
import com.prueba.dto.PaginacionDTO;
import com.prueba.service.ComercianteService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    // Endpoint para consultar comerciantes con paginación y filtros
    @GetMapping("/consultar")
    public ResponseEntity<PaginacionDTO<ComercianteDTO>> consultarComerciantes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long municipioId,
            @RequestParam(required = false) String estado,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int limite) {

        PaginacionDTO<ComercianteDTO> result = comercianteService.consultarComerciantes(nombre, municipioId, estado, pagina, limite);
        return ResponseEntity.ok(result);
    }

    // Endpoint para consultar comerciante por ID
    @GetMapping("/{comercianteId}")
    public ResponseEntity<ComercianteDTO> consultarComerciantePorId(@PathVariable Long comercianteId) {
        ComercianteDTO result = comercianteService.consultarComerciantePorId(comercianteId);
        return ResponseEntity.ok(result);
    }

    // Endpoint para crear un comerciante
    @PostMapping("/crear")
    public ResponseEntity<String> crearComerciante(@Valid @RequestBody ComercianteDTO comercianteDTO) {
        comercianteService.crearComerciante(comercianteDTO);
        return ResponseEntity.ok("Comerciante creado exitosamente");
    }

    // Endpoint para actualizar un comerciante
    @PutMapping("/actualizar/{comercianteId}")
    public ResponseEntity<String> actualizarComerciante(@PathVariable Long comercianteId, @Valid @RequestBody ComercianteDTO comercianteDTO) {
        comercianteDTO.setComercianteId(comercianteId);
        comercianteService.actualizarComerciante(comercianteId, comercianteDTO);
        return ResponseEntity.ok("Comerciante actualizado exitosamente");
    }

    // Endpoint para eliminar un comerciante
    @DeleteMapping("/eliminar/{comercianteId}")
    public ResponseEntity<String> eliminarComerciante(@PathVariable Long comercianteId) {
        comercianteService.eliminarComerciante(comercianteId);
        return ResponseEntity.ok("Comerciante eliminado exitosamente");
    }

    // Endpoint para activar o inactivar un comerciante
    @PatchMapping("/modificar-estado/{comercianteId}")
    public ResponseEntity<String> modificarEstadoComerciante(@PathVariable Long comercianteId, @RequestParam String estado) {
        if (!estado.equalsIgnoreCase("Activo") && !estado.equalsIgnoreCase("Inactivo")) {
            return ResponseEntity.badRequest().body("Estado debe ser 'Activo' o 'Inactivo'");
        }
        comercianteService.modificarEstado(comercianteId, estado);
        return ResponseEntity.ok("Estado del comerciante actualizado exitosamente");
    }
}
