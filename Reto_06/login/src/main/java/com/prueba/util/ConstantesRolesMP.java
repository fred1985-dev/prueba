package com.prueba.util;

import java.io.Serializable;

public final class ConstantesRolesMP implements Serializable {

	private static final long serialVersionUID = 6686288433672190584L;

	/**
	 * CONSTANTES PARA PERMISOS
	 **/
	public static final String PRM_C = "-C"; // CREAR
	public static final String PRM_L = "-L"; // LEER
	public static final String PRM_A = "-A"; // ACTUALIZAR
    public static final String DIRECCION_IP = "http://localhost:4200";
	/**
	 * CONSTANTES PARA ROLES
	 **/
	public static final String ROLE_USER = "AUXILIAR_REGISTRO"; // ROLE_USER
	public static final String ADMIN = "ADMIN"; // ADMINISTRADOR

}