package com.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prueba.model.Departamento;
import com.prueba.model.Municipio;
import com.prueba.service.DepartamentoService;
import com.prueba.service.MunicipioService;
import com.prueba.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartamentoMunicipioController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private MunicipioService municipioService;

    @GetMapping("/departamentos")
    public ApiResponse<List<Departamento>> getDepartamentos() {
        List<Departamento> departamentos = departamentoService.getAllDepartamentos();
        return ApiResponse.success("Lista de departamentos obtenida con éxito", departamentos);
    }

    @GetMapping("/municipios")
    public ApiResponse<List<Municipio>> getMunicipios() {
        List<Municipio> municipios = municipioService.getAllMunicipios();
        return ApiResponse.success("Lista de municipios obtenida con éxito", municipios);
    }
}
