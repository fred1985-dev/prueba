package com.prueba.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.service.ComercianteService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    @GetMapping("/exportar")
    public void exportarComerciantesActivos(HttpServletResponse response) {
        try {
            comercianteService.generarArchivoPlano(response);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al generar el archivo CSV", e);
        }
    }
}