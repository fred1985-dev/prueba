package com.prueba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.prueba.model.Departamento;
import com.prueba.repository.DepartamentoRepository;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Cacheable("departamentos")
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }
}
