package com.prueba.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.ComercianteDTO;
import com.prueba.repository.ComercianteRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ComercianteService {

    @Autowired
    private ComercianteRepository comercianteRepository;

    public void generarArchivoPlano(HttpServletResponse response) throws IOException {
        List<ComercianteDTO> comerciantes = comercianteRepository.obtenerComerciantesActivos();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=comerciantes_activos.csv");

        try (PrintWriter writer = response.getWriter()) {
            // Escribir encabezados
            writer.println("Nombre|Departamento|Municipio|Teléfono|Correo Electrónico|Fecha de Registro|Estado|Cantidad de Establecimientos|Total Activos|Cantidad de Empleados");

            // Escribir datos
            for (ComercianteDTO comerciante : comerciantes) {
                writer.printf("%s|%s|%s|%s|%s|%s|%s|%d|%.2f|%d%n",
                    comerciante.getRazonSocial(),
                    comerciante.getDepartamento(),
                    comerciante.getMunicipio(),
                    comerciante.getTelefono(),
                    comerciante.getCorreoElectronico(),
                    comerciante.getFechaRegistro().toString(),
                    comerciante.getEstado(),
                    comerciante.getCantidadEstablecimientos(),
                    comerciante.getTotalActivos(),
                    comerciante.getCantidadEmpleados()
                );
            }
        }
    }
}
