CREATE OR REPLACE PACKAGE BODY "PACK_COMERCIANTE_PAGE" AS

    PROCEDURE p_comerciante_page (
        p_cursor OUT SYS_REFCURSOR,
        p_resultado OUT NUMBER,
        p_limit IN NUMBER,  -- Cantidad de registros
        p_offset IN NUMBER  -- Punto de inicio de los registros
    )
    AS
    BEGIN
        -- Validación de parámetros de entrada
        IF p_limit <= 0 THEN
            p_resultado := -2; -- Error: el límite debe ser mayor que cero
            RETURN;
        END IF;

        IF p_offset < 0 THEN
            p_resultado := -3; -- Error: el desplazamiento no puede ser negativo
            RETURN;
        END IF;

        BEGIN
            OPEN p_cursor FOR
                SELECT *
                FROM (
                    SELECT 
                        c.COMERCIANTE_ID AS comerciante_id,  -- Se agrega la columna comerciante_id
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
                        NVL(SUM(e.NUMERO_EMPLEADOS), 0) AS cantidad_empleados,
                        ROW_NUMBER() OVER (ORDER BY NVL(COUNT(e.ESTABLECIMIENTO_ID), 0) DESC) AS rn
                    FROM 
                        COMERCIANTE c
                    JOIN MUNICIPIO m ON c.MUNICIPIO_ID = m.MUNICIPIO_ID
                    JOIN DEPARTAMENTO d ON m.DEPARTAMENTO_ID = d.DEPARTAMENTO_ID
                    LEFT JOIN ESTABLECIMIENTO e ON c.COMERCIANTE_ID = e.COMERCIANTE_ID
                    WHERE 
                        c.ESTADO = 'Activo' -- Filtrar solo comerciantes activos
                    GROUP BY 
                        c.COMERCIANTE_ID, c.NOMBRE, d.NOMBRE_DEPARTAMENTO, m.NOMBRE_MUNICIPIO, 
                        c.TELEFONO, c.USUARIO, c.FECHA_REGISTRO, c.ESTADO
                )
                WHERE rn > p_offset AND rn <= (p_offset + p_limit);

            p_resultado := 1; -- Éxito

        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                p_resultado := 0; -- No se encontraron datos
            WHEN OTHERS THEN
                p_resultado := -1; -- Otro error
                -- Agregar más detalles al error
                DBMS_OUTPUT.PUT_LINE('Error en la consulta: ' || SQLERRM);
        END;

    END p_comerciante_page;

END PACK_COMERCIANTE_PAGE;
