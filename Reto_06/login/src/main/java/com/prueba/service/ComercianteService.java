package com.prueba.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prueba.dto.ComercianteDTO;
import com.prueba.repository.ComercianteRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ComercianteService implements ComercianteServiceIn {

    @Autowired
    private ComercianteRepository comercianteRepository;


    @Autowired
    private JdbcTemplate jdbcTemplate;
    
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

	@Override
	public List<ComercianteDTO> buscaComerciantePage(int limit, int offset) {
	
		    return jdbcTemplate.execute(
		        con -> {                                    
		            // Ajustamos el nombre del procedimiento y los parámetros
		            CallableStatement cs = con.prepareCall("{call PACK_COMERCIANTE_PAGE.p_comerciante_page(?, ?, ?, ?)}");
		            cs.registerOutParameter(1, OracleTypes.CURSOR);  // Cursor de salida
		            cs.registerOutParameter(2, Types.NUMERIC);       // Código de resultado
		            cs.setInt(3, limit);                             // Parámetro de límite
		            cs.setInt(4, offset);                            // Parámetro de offset
		            return cs;
		        },
		        (CallableStatementCallback<List<ComercianteDTO>>) cs -> {
		            cs.execute();  
		            ResultSet rs = (ResultSet) cs.getObject(1);   // Obtiene el cursor de resultado
		            int resultado = cs.getInt(2);                  // Obtiene el código de resultado

		            if (resultado == 1) {
		                Map<Long, ComercianteDTO> comercianteMap = new HashMap<>();
		                
		                while (rs.next()) {
		                    Long comercianteId = rs.getLong("COMERCIANTE_ID");

		                    // Si el comerciante no existe en el mapa, lo creamos
		                    ComercianteDTO comerciante = comercianteMap.get(comercianteId);
		                    if (comerciante == null) {
		                        comerciante = new ComercianteDTO();
		                        comerciante.setComercianteId(rs.getInt("comerciante_id"));
		                        comerciante.setRazonSocial(rs.getString("razon_social"));
		                        comerciante.setDepartamento(rs.getString("departamento"));
		                        comerciante.setMunicipio(rs.getString("municipio"));
		                        comerciante.setTelefono(rs.getString("telefono"));
		                        comerciante.setCorreoElectronico(rs.getString("correo_electronico"));
		                        comerciante.setFechaRegistro(rs.getDate("fecha_registro"));
		                        comerciante.setEstado(rs.getString("estado"));
		                        comerciante.setCantidadEstablecimientos(rs.getInt("cantidad_establecimientos"));
		                        comerciante.setTotalActivos(rs.getDouble("total_activos"));
		                        comerciante.setCantidadEmpleados(rs.getInt("cantidad_empleados"));
		                        
		                        // Añadimos el comerciante al mapa
		                        comercianteMap.put(comercianteId, comerciante);
		                    }

		                    // Aquí podemos agregar más lógica si el procedimiento devuelve más información
		                    // Como en tu caso, si el comerciante tiene vehículos o SOAT asociados, se pueden agregar a las listas
		                }

		                return new ArrayList<>(comercianteMap.values());
		            } else if (resultado == 0) {
		                return new ArrayList<>();  // No hay datos
		            } else {
		                throw new SQLException("Error en el procedimiento almacenado, código: " + resultado);
		            }
		        }
		    );
		}

	}

