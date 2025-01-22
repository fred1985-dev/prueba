package com.prueba.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "COMERCIANTE")
public class Comerciante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comerciante_seq")
    @SequenceGenerator(name = "comerciante_seq", sequenceName = "S_COMERCIANTE", allocationSize = 1)
    @Column(name = "COMERCIANTE_ID", nullable = false)
    private Long comercianteId;

    @Column(name = "MUNICIPIO_ID", nullable = false)
    private Long municipioId;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TELEFONO", nullable = false, length = 100)
    private String telefono;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @Column(name = "UPDATE_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "USUARIO", nullable = false, length = 100)
    private String usuario;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMunicipio() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
