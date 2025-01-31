package com.prueba.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.dialect.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.prueba.dto.ComercianteDTO;

@Component
public class ComercianteRepository {

    private final ComercianteRepositoryInter comercianteRepository;

    @Autowired
    public ComercianteRepository(ComercianteRepositoryInter comercianteRepository) {
        this.comercianteRepository = comercianteRepository;
    }

	
	
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ComercianteDTO> obtenerComerciantesActivos() {
        return jdbcTemplate.execute((Connection connection) -> {
            CallableStatement callableStatement = connection.prepareCall("{? = call obtener_comerciantes_activos}");
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.execute();

            ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
            List<ComercianteDTO> comerciantes = new ArrayList<>();

            while (resultSet.next()) {
                ComercianteDTO comerciante = new ComercianteDTO();
                    resultSet.getString("razon_social");
                    resultSet.getString("departamento");
                    resultSet.getString("municipio");
                    resultSet.getString("TELEFONO");
                    resultSet.getString("correo_electronico");
                    resultSet.getDate("FECHA_REGISTRO");
                    resultSet.getString("ESTADO");
                    resultSet.getInt("cantidad_establecimientos");
                    resultSet.getDouble("total_activos");
                    resultSet.getInt("cantidad_empleados");
              
                comerciantes.add(comerciante);
            }

            return comerciantes;
        });
    }

	public void eliminarComerciante(Long comercianteId) {
		// TODO Auto-generated method stub
	//eliminarComerciante
		comercianteRepository.eliminarComerciante(comercianteId);
	}
    
    
}
