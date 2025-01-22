package com.prueba.service;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Paragraph;
import com.prueba.model.Comerciante;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PdfExportService {

    public ByteArrayOutputStream generatePdf(Optional<Comerciante> comerciantes) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        // Titulo del documento
        document.add(new Paragraph("Listado de Comerciantes").setFontSize(18));

        // Crear una tabla con 4 columnas (ajusta según lo que necesites)
        Table table = new Table(4);
        table.addHeaderCell("ID");
        table.addHeaderCell("Nombre");
        table.addHeaderCell("Email");
        table.addHeaderCell("Teléfono");

        // Agregar datos de comerciantes a la tabla
        for (Comerciante comerciante : comerciantes) {
            table.addCell(String.valueOf(comerciante.getComercianteId()));
            table.addCell(comerciante.getNombre());
            table.addCell(comerciante.getMunicipio());

            table.addCell(comerciante.getTelefono());
        }

        // Agregar tabla al documento
        document.add(table);

        document.close();

        return outputStream;
    }
}
