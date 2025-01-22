package com.prueba.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.model.Comerciante;
import com.prueba.repository.IComercianteRepository;
import com.prueba.service.PdfExportService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
public class PdfExportController {

    @Autowired
    private PdfExportService pdfExportService;

    @Autowired
    private IComercianteRepository comercianteRepository; // Repositorio para obtener los comerciantes

    @GetMapping("/exportar-pdf/{id}")
    public ResponseEntity<byte[]> exportarPdf(@PathVariable Long id) {
        Optional<Comerciante> comerciantes = comercianteRepository.findById(id); // Busca los comerciantes por ID

        try {
            ByteArrayOutputStream byteArrayOutputStream = pdfExportService.generatePdf(comerciantes);

            // Preparar los encabezados de la respuesta para descarga de PDF
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=comerciantes.pdf");

            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
