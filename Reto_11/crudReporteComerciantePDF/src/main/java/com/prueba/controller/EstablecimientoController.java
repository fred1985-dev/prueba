package com.prueba.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.prueba.model.Establecimiento;
import com.prueba.service.EstablecimientoService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/establecimientos")
public class EstablecimientoController {

    @Autowired
    private EstablecimientoService establecimientoService;

    // Consultar por Comerciante
    @Secured("Administrador")
    @GetMapping("/comerciante/{comercianteId}")
    public ResponseEntity<List<Establecimiento>> obtenerPorComerciante(@PathVariable Long comercianteId) {
        List<Establecimiento> establecimientos = establecimientoService.obtenerPorComerciante(comercianteId);
        return ResponseEntity.ok(establecimientos);
    }

    // Crear Establecimiento
    @PostMapping
	@Secured("Administrador")
    public ResponseEntity<Establecimiento> crearEstablecimiento(@Valid @RequestBody Establecimiento establecimiento) {
        Establecimiento nuevoEstablecimiento = establecimientoService.crearEstablecimiento(establecimiento);
        return ResponseEntity.ok(nuevoEstablecimiento);
    }

    // Actualizar Establecimiento
    @PutMapping("/{id}")
    @Secured("Administrador")
    public ResponseEntity<Establecimiento> actualizarEstablecimiento(
            @PathVariable Long id, @Valid @RequestBody Establecimiento establecimiento) {
        Establecimiento actualizado = establecimientoService.actualizarEstablecimiento(id, establecimiento);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar Establecimiento
    @DeleteMapping("/{id}")
    @Secured("Administrador")
    public ResponseEntity<Void> eliminarEstablecimiento(@PathVariable Long id) {
        establecimientoService.eliminarEstablecimiento(id);
        return ResponseEntity.noContent().build();
    }
}
