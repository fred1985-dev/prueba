alter table COMERCIANTE
   add constraint FK_COMERCIA_TENER_MUNICIPI foreign key (MUNICIPIO_ID)
      references MUNICIPIO (MUNICIPIO_ID);

alter table ESTABLECIMIENTO
   add constraint FK_ESTABLEC_OBTENER_COMERCIA foreign key (COMERCIANTE_ID)
      references COMERCIANTE (COMERCIANTE_ID);

alter table ESTABLECIMIENTO
   add constraint FK_ESTABLEC_RELATIONS_MUNICIPI foreign key (MUNICIPIO_ID)
      references MUNICIPIO (MUNICIPIO_ID);

alter table MUNICIPIO
   add constraint FK_MUNICIPI_CONTENER_DEPARTAM foreign key (DEPARTAMENTO_ID)
      references DEPARTAMENTO (DEPARTAMENTO_ID);
