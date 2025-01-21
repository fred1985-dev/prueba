
/*==============================================================*/
/* Index: TENER_FK                                              */
/*==============================================================*/
create index TENER_FK on COMERCIANTE (
   MUNICIPIO_ID ASC
);

/*==============================================================*/
/* Index: TIENE_FK                                     */
/*==============================================================*/
create index TIENE_FK on ESTABLECIMIENTO (
   MUNICIPIO_ID ASC
);

/*==============================================================*/
/* Index: OBTENER_FK                                            */
/*==============================================================*/
create index OBTENER_FK on ESTABLECIMIENTO (
   COMERCIANTE_ID ASC
);


/*==============================================================*/
/* Index: CONTENER_FK                                           */
/*==============================================================*/
create index CONTENER_FK on MUNICIPIO (
   DEPARTAMENTO_ID ASC
);
