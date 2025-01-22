package com.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.prueba.model.Municipio;
import com.prueba.repository.MunicipioRepository;

import java.util.List;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Cacheable("municipios")
    public List<Municipio> getAllMunicipios() {
        return municipioRepository.findAll();
    }
}
