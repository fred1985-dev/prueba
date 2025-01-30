package com.prueba.service;

import java.util.List;

import com.prueba.dto.ComercianteDTO;

public interface ComercianteServiceIn {

	
	public List<ComercianteDTO> buscaComerciantePage(int limit, int offset);

}
