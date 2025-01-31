package com.prueba.service;

import java.util.List;

import com.prueba.dto.ComercianteDTO;
import com.prueba.model.Comerciante;

public interface ComercianteServiceIn {

	
	public List<ComercianteDTO> buscaComerciantePage(int limit, int offset);

	void eliminarComerciante(Long comercianteId);




}
