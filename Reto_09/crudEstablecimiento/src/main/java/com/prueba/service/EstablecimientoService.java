package com.prueba.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.model.Establecimiento;
import com.prueba.repository.EstablecimientoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstablecimientoService {

    @Autowired
    private EstablecimientoRepository establecimientoRepository;

    public List<Establecimiento> obtenerPorComerciante(Long comercianteId) {
        return establecimientoRepository.findByComercianteId(comercianteId);
    }

    public Establecimiento crearEstablecimiento(Establecimiento establecimiento) {
        return establecimientoRepository.save(establecimiento);
    }

    public Establecimiento actualizarEstablecimiento(Long id, Establecimiento establecimiento) {
        Optional<Establecimiento> existente = establecimientoRepository.findById(id);
        if (existente.isPresent()) {
            Establecimiento actualizado = existente.get();
            actualizado.setNombreEstable(establecimiento.getNombreEstable());
            actualizado.setIngresos(establecimiento.getIngresos());
            actualizado.setNumeroEmpleados(establecimiento.getNumeroEmpleados());
            return establecimientoRepository.save(actualizado);
        } else {
            throw new RuntimeException("Establecimiento no encontrado");
        }
    }

    public void eliminarEstablecimiento(Long id) {
        establecimientoRepository.deleteById(id);
    }
}

