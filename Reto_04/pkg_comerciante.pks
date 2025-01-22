  CREATE OR REPLACE PACKAGE pkg_comerciante AS
         
   -- Tipo de datos para el retorno
   TYPE t_resultado IS RECORD (
      codigo_error  NUMBER,
      mensaje_error VARCHAR2(4000)
   );
   
      -- Función para consultar un comerciante por ID
   FUNCTION consultar_por_id(p_comerciante_id NUMBER) 
      RETURN SYS_REFCURSOR;
   
     -- Función para consultar comerciantes con filtros y paginación
   FUNCTION consultar_comerciantes(
      p_nombre         VARCHAR2 DEFAULT NULL,
      p_municipio_id   NUMBER DEFAULT NULL,
      p_fecha_registro DATE DEFAULT NULL,
      p_estado         VARCHAR2 DEFAULT NULL,
      p_limite         NUMBER DEFAULT 10,
      p_offset         NUMBER DEFAULT 0
   ) RETURN SYS_REFCURSOR;
   
 
    -- Procedimiento para crear un comerciante
   PROCEDURE crear_comerciante(
      p_nombre           VARCHAR2,
      p_municipio_id     NUMBER,
      p_telefono         VARCHAR2,
      p_fecha_registro   DATE,
      p_estado           VARCHAR2,
      p_usuario          VARCHAR2,
      p_resultado        OUT t_resultado);
      
  PROCEDURE actualizar_comerciante(
      p_comerciante_id NUMBER,
      p_municipio_id   NUMBER,
      p_nombre         VARCHAR2,
      p_telefono       VARCHAR2,
      p_fecha_registro DATE,
      p_estado         VARCHAR2
   );
   
   
   
      -- Procedimiento para eliminar un comerciante
   PROCEDURE eliminar_comerciante(
      p_comerciante_id NUMBER,
      p_resultado      OUT t_resultado);
      
      END pkg_comerciante;
/