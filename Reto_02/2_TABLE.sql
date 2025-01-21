/*==============================================================*/
/* Table: COMERCIANTE                                           */
/*==============================================================*/
create table COMERCIANTE 
(
   COMERCIANTE_ID       NUMBER(10)           not null,
   MUNICIPIO_ID         NUMBER(10)           not null,
   NOMBRE               VARCHAR2(100)        not null,
   TELEFONO             VARCHAR2(100)        not null,
   FECHA_REGISTRO       DATE                 not null,
   ESTADO               VARCHAR2(20)         not null,
   UPDATE_AT            DATE                 not null,
   USUARIO              VARCHAR2(100)        not null,
   constraint PK_COMERCIANTE primary key (COMERCIANTE_ID)
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO 
(
   DEPARTAMENTO_ID      NUMBER(10)           not null,
   NOMBRE_DEPARTAMENTO  VARCHAR2(100)        not null,
   CREATED_AT           DATE                 not null,
   UPDATED_AT           DATE                 not null,
   USUARIO              VARCHAR2(100)        not null,
   constraint PK_DEPARTAMENTO primary key (DEPARTAMENTO_ID)
);

/*==============================================================*/
/* Table: ESTABLECIMIENTO                                       */
/*==============================================================*/
create table ESTABLECIMIENTO 
(
   ESTABLECIMIENTO_ID   NUMBER(10)           not null,
   COMERCIANTE_ID       NUMBER(10)           not null,
   MUNICIPIO_ID         NUMBER(10)           not null,
   NOMBRE_ESTABLE       VARCHAR2(100)        not null,
   ATTRIBUTE_24         DATE                 not null,
   INGRESOS             NUMBER(10,2)         not null,
   NUMERO_EMPLEADOS     NUMBER               not null,
   UPDATE_AT            DATE                 not null,
   USUARIO              VARCHAR2(100)        not null,
   constraint PK_ESTABLECIMIENTO primary key (ESTABLECIMIENTO_ID)
);
/*==============================================================*/
/* Table: MUNICIPIO                                             */
/*==============================================================*/
create table MUNICIPIO 
(
   MUNICIPIO_ID         NUMBER(10)           not null,
   DEPARTAMENTO_ID      NUMBER(10)           not null,
   NOMBRE_MUNICIPIO     VARCHAR2(100)        not null,
   CREATED_AT           DATE                 not null,
   UPDATED_AT           DATE,
   USUARIO              VARCHAR2(100)        not null,
   constraint PK_MUNICIPIO primary key (MUNICIPIO_ID)
);
