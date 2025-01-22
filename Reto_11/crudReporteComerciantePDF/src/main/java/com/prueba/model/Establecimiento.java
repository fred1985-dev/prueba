package com.prueba.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data // Esta anotación genera los getters y setters automáticamente
@NoArgsConstructor
@Entity
@Table(name = "ESTABLECIMIENTO")
public class Establecimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "establecimiento_seq")
    @SequenceGenerator(name = "establecimiento_seq", sequenceName = "S_ESTABLECIMIENTO", allocationSize = 1)
    @Column(name = "ESTABLECIMIENTO_ID", nullable = false, updatable = false)
    private Long establecimientoId;

    @Column(name = "COMERCIANTE_ID", nullable = false)
    @NotNull(message = "El ID del comerciante es obligatorio")
    private Long comercianteId;

    @Column(name = "MUNICIPIO_ID", nullable = false)
    @NotNull(message = "El ID del municipio es obligatorio")
    private Long municipioId;

    @Column(name = "NOMBRE_ESTABLE", nullable = false, length = 100)
    @NotBlank(message = "El nombre del establecimiento es obligatorio")
    @Size(max = 100, message = "El nombre del establecimiento no debe superar los 100 caracteres")
    private String nombreEstable;

    @Column(name = "INGRESOS", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Los ingresos son obligatorios")
    @Digits(integer = 10, fraction = 2, message = "El formato de los ingresos no es válido")
    private BigDecimal ingresos;

    @Column(name = "NUMERO_EMPLEADOS", nullable = false)
    @NotNull(message = "El número de empleados es obligatorio")
    @Min(value = 1, message = "El número de empleados debe ser mayor o igual a 1")
    private Integer numeroEmpleados;

    @Column(name = "UPDATE_AT", nullable = false)
    private java.util.Date updatedAt;

    @Column(name = "USUARIO", nullable = false, length = 100)
    @NotBlank(message = "El usuario es obligatorio")
    @Size(max = 100, message = "El usuario no debe superar los 100 caracteres")
    private String usuario;

	public Long getEstablecimientoId() {
		return establecimientoId;
	}

	public void setEstablecimientoId(Long establecimientoId) {
		this.establecimientoId = establecimientoId;
	}

	public Long getComercianteId() {
		return comercianteId;
	}

	public void setComercianteId(Long comercianteId) {
		this.comercianteId = comercianteId;
	}

	public Long getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}

	public String getNombreEstable() {
		return nombreEstable;
	}

	public void setNombreEstable(String nombreEstable) {
		this.nombreEstable = nombreEstable;
	}

	public BigDecimal getIngresos() {
		return ingresos;
	}

	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}

	public Integer getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(Integer numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public java.util.Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(java.util.Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    
    
    
    
    
    
}
