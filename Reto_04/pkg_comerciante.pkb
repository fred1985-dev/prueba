CREATE OR REPLACE PACKAGE BODY pkg_comerciante AS
   -- Función para consultar un comerciante por ID
   FUNCTION consultar_por_id(p_comerciante_id NUMBER) 
      RETURN SYS_REFCURSOR IS
      v_cursor SYS_REFCURSOR;
   BEGIN
      OPEN v_cursor FOR
  SELECT 
         c.NOMBRE AS razon_social,
         d.NOMBRE_DEPARTAMENTO AS departamento,
         m.NOMBRE_MUNICIPIO AS municipio,
         c.TELEFONO,
         c.USUARIO AS correo_electronico,
         c.FECHA_REGISTRO,
         c.ESTADO,
         NVL(SUM(e.INGRESOS), 0) AS total_activos,
         NVL(SUM(e.NUMERO_EMPLEADOS), 0) AS cantidad_empleados
      FROM 
         COMERCIANTE c
      JOIN MUNICIPIO m ON c.MUNICIPIO_ID = m.MUNICIPIO_ID
      JOIN DEPARTAMENTO d ON m.DEPARTAMENTO_ID = d.DEPARTAMENTO_ID
      LEFT JOIN ESTABLECIMIENTO e ON c.COMERCIANTE_ID = e.COMERCIANTE_ID
      WHERE 
         c.COMERCIANTE_ID = p_comerciante_id
         GROUP BY 
         c.NOMBRE, d.NOMBRE_DEPARTAMENTO, m.NOMBRE_MUNICIPIO, c.TELEFONO, c.USUARIO, 
         c.FECHA_REGISTRO, c.ESTADO;
         
         
      RETURN v_cursor;
   EXCEPTION
      WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20001, 'Error en consultar_por_id: ' || SQLERRM);
   END;


/*
se implemento subconsulta para implementar la paginación mediante ROWNUM y ROW_NUMBER()
*/
-- Función para consultar comerciantes con filtros y paginación
FUNCTION consultar_comerciantes(
   p_nombre         VARCHAR2 DEFAULT NULL,
   p_municipio_id   NUMBER DEFAULT NULL,
   p_fecha_registro DATE DEFAULT NULL,
   p_estado         VARCHAR2 DEFAULT NULL,
   p_limite         NUMBER DEFAULT 10,
   p_offset         NUMBER DEFAULT 0
) RETURN SYS_REFCURSOR IS
   v_cursor SYS_REFCURSOR;
BEGIN
   OPEN v_cursor FOR
   SELECT *
   FROM (
      SELECT 
          c.NOMBRE AS razon_social,
          d.NOMBRE_DEPARTAMENTO AS departamento,
          m.NOMBRE_MUNICIPIO AS municipio,
          c.TELEFONO,
          c.USUARIO AS correo_electronico,
          c.FECHA_REGISTRO,
          c.ESTADO,
          NVL(SUM(e.INGRESOS), 0) AS total_activos,
          NVL(SUM(e.NUMERO_EMPLEADOS), 0) AS cantidad_empleados,
          ROW_NUMBER() OVER (ORDER BY c.NOMBRE) AS rn
      FROM 
         COMERCIANTE c
      JOIN MUNICIPIO m ON c.MUNICIPIO_ID = m.MUNICIPIO_ID
      JOIN DEPARTAMENTO d ON m.DEPARTAMENTO_ID = d.DEPARTAMENTO_ID
      LEFT JOIN ESTABLECIMIENTO e ON c.COMERCIANTE_ID = e.COMERCIANTE_ID
      WHERE 
         (p_nombre IS NULL OR UPPER(c.NOMBRE) LIKE UPPER('%' || p_nombre || '%'))
         AND (p_municipio_id IS NULL OR c.MUNICIPIO_ID = p_municipio_id)
         AND (p_fecha_registro IS NULL OR TRUNC(c.FECHA_REGISTRO) = TRUNC(p_fecha_registro))
         AND (p_estado IS NULL OR UPPER(c.ESTADO) = UPPER(p_estado))
      GROUP BY 
         c.NOMBRE, d.NOMBRE_DEPARTAMENTO, m.NOMBRE_MUNICIPIO, c.TELEFONO, c.USUARIO, 
         c.FECHA_REGISTRO, c.ESTADO
   ) subquery
   WHERE rn BETWEEN p_offset + 1 AND p_offset + p_limite;

   RETURN v_cursor;

EXCEPTION
   WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20002, 'Error en consultar_comerciantes: ' || SQLERRM);
END;


   -- Procedimiento para crear un comerciante
   PROCEDURE crear_comerciante(
      p_municipio_id   NUMBER,
      p_nombre         VARCHAR2,
      p_telefono       VARCHAR2,
      p_fecha_registro DATE,
      p_estado         VARCHAR2
   ) IS
      v_error_msg VARCHAR2(4000);
   BEGIN
      -- Validaciones de entrada
      IF p_municipio_id IS NULL THEN
         RAISE_APPLICATION_ERROR(-20003, 'El campo Municipio ID es obligatorio.');
      END IF;

      IF p_nombre IS NULL OR LENGTH(p_nombre) > 100 THEN
         RAISE_APPLICATION_ERROR(-20004, 'El campo Nombre es obligatorio y debe tener como máximo 100 caracteres.');
      END IF;

      IF p_telefono IS NOT NULL AND LENGTH(p_telefono) > 100 THEN
         RAISE_APPLICATION_ERROR(-20005, 'El campo Teléfono debe tener como máximo 100 caracteres.');
      END IF;

      IF p_fecha_registro IS NULL THEN
         RAISE_APPLICATION_ERROR(-20006, 'El campo Fecha de Registro es obligatorio.');
      END IF;

      IF p_estado IS NULL OR NOT REGEXP_LIKE(p_estado, '^(Activo|Inactivo)$') THEN
         RAISE_APPLICATION_ERROR(-20007, 'El campo Estado es obligatorio y debe ser "Activo" o "Inactivo".');
      END IF;

      -- Inserción en la tabla COMERCIANTE
      INSERT INTO COMERCIANTE (
         MUNICIPIO_ID, NOMBRE, TELEFONO, FECHA_REGISTRO, ESTADO, UPDATE_AT
      ) VALUES (
         p_municipio_id, p_nombre, p_telefono, p_fecha_registro, p_estado, SYSDATE
      );

   EXCEPTION
      WHEN OTHERS THEN
         v_error_msg := SQLERRM;
         RAISE_APPLICATION_ERROR(-20010, 'Error al crear comerciante: ' || v_error_msg);
   END crear_comerciante;


 -- Procedimiento para actualizar un comerciante
   PROCEDURE actualizar_comerciante(
      p_comerciante_id NUMBER,
      p_municipio_id   NUMBER,
      p_nombre         VARCHAR2,
      p_telefono       VARCHAR2,
      p_fecha_registro DATE,
      p_estado         VARCHAR2
   ) IS
      v_error_msg VARCHAR2(4000);
   BEGIN
      -- Validaciones de entrada
      IF p_comerciante_id IS NULL THEN
         RAISE_APPLICATION_ERROR(-20011, 'El campo Comerciante ID es obligatorio.');
      END IF;

      IF p_municipio_id IS NULL THEN
         RAISE_APPLICATION_ERROR(-20012, 'El campo Municipio ID es obligatorio.');
      END IF;

      IF p_nombre IS NULL OR LENGTH(p_nombre) > 100 THEN
         RAISE_APPLICATION_ERROR(-20013, 'El campo Nombre es obligatorio y debe tener como máximo 100 caracteres.');
      END IF;

      IF p_telefono IS NOT NULL AND LENGTH(p_telefono) > 100 THEN
         RAISE_APPLICATION_ERROR(-20014, 'El campo Teléfono debe tener como máximo 100 caracteres.');
      END IF;

      IF p_fecha_registro IS NULL THEN
         RAISE_APPLICATION_ERROR(-20015, 'El campo Fecha de Registro es obligatorio.');
      END IF;

      IF p_estado IS NULL OR NOT REGEXP_LIKE(p_estado, '^(Activo|Inactivo)$') THEN
         RAISE_APPLICATION_ERROR(-20016, 'El campo Estado es obligatorio y debe ser "Activo" o "Inactivo".');
      END IF;

      -- Actualización en la tabla COMERCIANTE
      UPDATE COMERCIANTE
      SET 
         MUNICIPIO_ID = p_municipio_id,
         NOMBRE = p_nombre,
         TELEFONO = p_telefono,
         FECHA_REGISTRO = p_fecha_registro,
         ESTADO = p_estado,
         UPDATE_AT = SYSDATE
      WHERE 
         COMERCIANTE_ID = p_comerciante_id;

   EXCEPTION
      WHEN OTHERS THEN
         v_error_msg := SQLERRM;
         RAISE_APPLICATION_ERROR(-20017, 'Error al actualizar comerciante: ' || v_error_msg);
   END actualizar_comerciante;
   
   
   
   -- Procedimiento para eliminar un comerciante
   PROCEDURE eliminar_comerciante(
      p_comerciante_id NUMBER,
      p_resultado      OUT t_resultado) IS
   BEGIN
      DELETE FROM COMERCIANTE
      WHERE COMERCIANTE_ID = p_comerciante_id;
      COMMIT;
      p_resultado.codigo_error := 0;
      p_resultado.mensaje_error := NULL;
   EXCEPTION
      WHEN OTHERS THEN
         p_resultado.codigo_error := SQLCODE;
         p_resultado.mensaje_error := SQLERRM;
   END;
   
   
END pkg_comerciante;
/
