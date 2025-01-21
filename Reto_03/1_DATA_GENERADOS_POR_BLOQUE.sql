
/*==============================================================*/
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO DEPARTAMENTO (DEPARTAMENTO_ID, NOMBRE_DEPARTAMENTO)
    VALUES (
      i, 
      'Departamento ' || DBMS_RANDOM.STRING('U', 5)  -- NOMBRE aleatorio
    );
  END LOOP;
  COMMIT;
END;
/


/*==============================================================*/

/*==============================================================*/

BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO MUNICIPIO (MUNICIPIO_ID, DEPARTAMENTO_ID, NOMBRE_MUNICIPIO)
    VALUES (
      i,  -- MUNICIPIO_ID
      TRUNC(DBMS_RANDOM.VALUE(1, 10)),  -- DEPARTAMENTO_ID aleatorio entre 1 y 10
      'Municipio ' || DBMS_RANDOM.STRING('U', 5)  -- NOMBRE aleatorio
    );
  END LOOP;
  COMMIT;
END;
/

/*==============================================================*/
BEGIN
  FOR i IN 1..10 LOOP
    INSERT INTO COMERCIANTE (COMERCIANTE_ID, MUNICIPIO_ID, NOMBRE, TELEFONO, FECHA_REGISTRO, ESTADO, UPDATE_AT, USUARIO)
    VALUES (
      i, 
      TRUNC(DBMS_RANDOM.VALUE(24, 33)),  -- MUNICIPIO_ID aleatorio entre 1 y 100
      'Comerciante ' || DBMS_RANDOM.STRING('U', 5),  -- NOMBRE aleatorio
      TO_CHAR(DBMS_RANDOM.VALUE(1,10)),  -- TELEFONO aleatorio
      SYSDATE - TRUNC(DBMS_RANDOM.VALUE(1, 365)),  -- FECHA_REGISTRO aleatorio en el último año
      CASE WHEN DBMS_RANDOM.VALUE(0, 1) > 0.5 THEN 'Activo' ELSE 'Inactivo' END,  -- ESTADO aleatorio
      SYSDATE,  -- UPDATE_AT
      'Usuario_' || DBMS_RANDOM.STRING('U', 5)  -- USUARIO aleatorio
    );
  END LOOP;
  COMMIT;
END;
/
/*==============================================================*/


BEGIN
  FOR i IN 1..30 LOOP
    INSERT INTO ESTABLECIMIENTO (ESTABLECIMIENTO_ID, COMERCIANTE_ID, MUNICIPIO_ID, NOMBRE_ESTABLE, ATTRIBUTE_24, INGRESOS, NUMERO_EMPLEADOS, UPDATE_AT, USUARIO)
    VALUES (
      i,  -- ESTABLECIMIENTO_ID
      TRUNC(DBMS_RANDOM.VALUE(60, 69)),  -- COMERCIANTE_ID aleatorio entre 1 y 10
      TRUNC(DBMS_RANDOM.VALUE(24, 33)),  -- MUNICIPIO_ID aleatorio entre 1 y 100
      'Establecimiento ' || DBMS_RANDOM.STRING('U', 5),  -- NOMBRE_ESTABLE aleatorio
      SYSDATE - TRUNC(DBMS_RANDOM.VALUE(1, 365)),  -- ATTRIBUTE_24 aleatorio en el último año
      DBMS_RANDOM.VALUE(1000, 50000),  -- INGRESOS aleatorios entre 1000 y 50000
      TRUNC(DBMS_RANDOM.VALUE(1, 200)),  -- NUMERO_EMPLEADOS aleatorio entre 1 y 200
      SYSDATE,  -- UPDATE_AT
      'Usuario_' || DBMS_RANDOM.STRING('U', 5)  -- USUARIO aleatorio
    );
  END LOOP;
  COMMIT;
END;
/

