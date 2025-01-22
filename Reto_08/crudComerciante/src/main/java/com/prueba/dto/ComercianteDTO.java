package com.prueba.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class ComercianteDTO {

    private String nombre;
    private String nombreDepartamento;
    private String nombreMunicipio;
    private String telefono;
    private String usuario;
    private LocalDate fechaRegistro;
    private String estado;
    private BigDecimal totalIngresos;
    private Integer totalEmpleados;

    // Constructor completo
    public ComercianteDTO(String nombre, String nombreDepartamento, String nombreMunicipio, 
                          String telefono, String usuario, LocalDate fechaRegistro, 
                          String estado, BigDecimal totalIngresos, Integer totalEmpleados) {
        this.nombre = nombre;
        this.nombreDepartamento = nombreDepartamento;
        this.nombreMunicipio = nombreMunicipio;
        this.telefono = telefono;
        this.usuario = usuario;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.totalIngresos = totalIngresos;
        this.totalEmpleados = totalEmpleados;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public Integer getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(Integer totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    @Override
    public String toString() {
        return "ComercianteDTO{" +
                "nombre='" + nombre + '\'' +
                ", nombreDepartamento='" + nombreDepartamento + '\'' +
                ", nombreMunicipio='" + nombreMunicipio + '\'' +
                ", telefono='" + telefono + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado='" + estado + '\'' +
                ", totalIngresos=" + totalIngresos +
                ", totalEmpleados=" + totalEmpleados +
                '}';
    }

	public void setComercianteId(Long comercianteId) {
		// TODO Auto-generated method stub
		
	}
}
