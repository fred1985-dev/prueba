package com.prueba.dto;

import java.util.Date;
import lombok.Data;

@Data // Esta anotación genera los getters y setters automáticamente
public class ComercianteDTO {
	
	private int comercianteId;
    private String razonSocial;
    private String departamento;
    private String municipio;
    private String telefono;
    private String correoElectronico;
    private Date fechaRegistro;
    private String estado;
    private int cantidadEstablecimientos;
    private double totalActivos;
    private int cantidadEmpleados;


    // Getters and Setters...

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCantidadEstablecimientos() {
		return cantidadEstablecimientos;
	}

	public void setCantidadEstablecimientos(int cantidadEstablecimientos) {
		this.cantidadEstablecimientos = cantidadEstablecimientos;
	}

	public double getTotalActivos() {
		return totalActivos;
	}

	public void setTotalActivos(double totalActivos) {
		this.totalActivos = totalActivos;
	}

	public int getCantidadEmpleados() {
		return cantidadEmpleados;
	}

	public void setCantidadEmpleados(int cantidadEmpleados) {
		this.cantidadEmpleados = cantidadEmpleados;
	}

	public int getComercianteId() {
		return comercianteId;
	}

	public void setComercianteId(int comercianteId) {
		this.comercianteId = comercianteId;
	}
    
    
}
