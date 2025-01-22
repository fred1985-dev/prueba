CREATE OR REPLACE FUNCTION obtener_comerciantes_activos RETURN SYS_REFCURSOR IS
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
      -- Cálculos
      NVL(COUNT(e.ESTABLECIMIENTO_ID), 0) AS cantidad_establecimientos,
      NVL(SUM(e.INGRESOS), 0) AS total_activos,
      NVL(SUM(e.NUMERO_EMPLEADOS), 0) AS cantidad_empleados
   FROM 
      COMERCIANTE c
   JOIN MUNICIPIO m ON c.MUNICIPIO_ID = m.MUNICIPIO_ID
   JOIN DEPARTAMENTO d ON m.DEPARTAMENTO_ID = d.DEPARTAMENTO_ID
   LEFT JOIN ESTABLECIMIENTO e ON c.COMERCIANTE_ID = e.COMERCIANTE_ID
   WHERE 
      c.ESTADO = 'Activo' -- Filtrar solo comerciantes activos
   GROUP BY 
      c.NOMBRE, d.NOMBRE_DEPARTAMENTO, m.NOMBRE_MUNICIPIO, c.TELEFONO, c.USUARIO, 
      c.FECHA_REGISTRO, c.ESTADO
   ORDER BY 
      cantidad_establecimientos DESC; 

   RETURN v_cursor;
EXCEPTION
   WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20001, 'Error en obtener_comerciantes_activos: ' || SQLERRM);
END obtener_comerciantes_activos;
/
