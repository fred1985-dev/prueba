CREATE OR REPLACE PACKAGE "DATACOMERCIANTE"."PACK_COMERCIANTE_PAGE" AS


    PROCEDURE p_comerciante_page (
        p_cursor OUT SYS_REFCURSOR,
        p_resultado OUT NUMBER,
        p_limit IN NUMBER,  -- Nuevo parámetro: cantidad de registros
        p_offset IN NUMBER  -- Nuevo parámetro: punto de inicio de los registros
    );

END PACK_COMERCIANTE_PAGE;
/