
/*===================================================================*/
 /*  Se implementa  triggers separados para mantener la claridad y   */
/* un control más fino sobre cada tipo de operación.                */


/*======================= COMERCIANTE ============================================*/
CREATE OR REPLACE TRIGGER trg_comerciante_insert
BEFORE INSERT ON COMERCIANTE
FOR EACH ROW
BEGIN
  -- Asigna la fecha de inserción
  :NEW.UPDATE_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;


CREATE OR REPLACE TRIGGER trg_comerciante_update
BEFORE UPDATE ON COMERCIANTE
FOR EACH ROW
BEGIN
  -- Asigna la fecha de actualización
  :NEW.UPDATED_AT := SYSDATE;
  -- Asigna el usuario que está realizando la actualización
  :NEW.usuario := USER;
END;
/*===================================================================*/


/*======================= DEPARTAMENTO ============================================*/
CREATE OR REPLACE TRIGGER trg_departamento_insert
BEFORE INSERT ON DEPARTAMENTO
FOR EACH ROW
BEGIN
  -- Asigna la fecha de inserción
  :NEW.CREATED_AT := SYSDATE;
  :NEW.UPDATED_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;


CREATE OR REPLACE TRIGGER trg_departamento_update
BEFORE UPDATE ON DEPARTAMENTO
FOR EACH ROW
BEGIN
  -- Asigna la fecha de actualización
  :NEW.UPDATED_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;

/*===================================================================*/


/*======================= ESTABLECIMIENTO ============================================*/

CREATE OR REPLACE TRIGGER trg_establecimiento_insert
BEFORE INSERT ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
  -- Asigna la fecha de inserción
  :NEW.UPDATE_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;

CREATE OR REPLACE TRIGGER trg_establecimiento_update
BEFORE UPDATE ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
  -- Asigna la fecha de actualización
  :NEW.UPDATE_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;



/*===================================================================*/


/*======================= MUNICIPIO ============================================*/

CREATE OR REPLACE TRIGGER trg_municipio_insert
BEFORE INSERT ON MUNICIPIO
FOR EACH ROW
BEGIN
  -- Asigna la fecha de inserción
   :NEW.CREATED_AT := SYSDATE;
  :NEW.UPDATED_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;

CREATE OR REPLACE TRIGGER trg_municipio_update
BEFORE UPDATE ON 
FOR EACH ROW
BEGIN
  -- Asigna la fecha de actualización
  :NEW.UPDATED_AT := SYSDATE;
  -- Asigna el usuario
  :NEW.usuario := USER;
END;



/*===================================================================*/