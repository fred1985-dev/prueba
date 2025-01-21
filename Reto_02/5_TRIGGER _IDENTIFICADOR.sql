/*==============================================================*/
CREATE OR REPLACE TRIGGER trg_comerciante_id
BEFORE INSERT ON COMERCIANTE
FOR EACH ROW
BEGIN
  :NEW.comerciante_id := S_COMERCIANTE.NEXTVAL;
END;

/*==============================================================*/
CREATE OR REPLACE TRIGGER trg_departamento_id
BEFORE INSERT ON DEPARTAMENTO
FOR EACH ROW
BEGIN
  :NEW.departamento_id := S_DEPARTAMENTO.NEXTVAL;
END;

/*==============================================================*/
CREATE OR REPLACE TRIGGER trg_establecimiento_id
BEFORE INSERT ON ESTABLECIMIENTO
FOR EACH ROW
BEGIN
  :NEW.establecimiento_id := S_ESTABLECIMIENTO.NEXTVAL;
END;
/*==============================================================*/

CREATE OR REPLACE TRIGGER trg_municipio_id
BEFORE INSERT ON MUNICIPIO
FOR EACH ROW
BEGIN
  :NEW.municipio_id := S_MUNICIPIO.NEXTVAL;
END;

/*==============================================================*/