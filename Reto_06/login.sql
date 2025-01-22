/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     27/12/2020 5:13:00 p. m.                     */
/*==============================================================*/
--

alter table USER_ROLES
   drop constraint FK_USER_ROL_CONTENER_ROLE;

alter table USER_ROLES
   drop constraint FK_USER_ROL_TENER_USER;

drop table ROLE cascade constraints;

drop table "USER" cascade constraints;

drop index TENER_FK;

drop index CONTENER_FK;

drop table USER_ROLES cascade constraints;

drop sequence S_ROLE;



drop sequence S_USER;

drop sequence S_USER_ROLES;

create sequence S_ROLE;

create sequence S_USER;

create sequence S_USER_ROLES;

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE 
(
   ID                   NUMBER(10)           not null,
   NAME                 VARCHAR2(50)         not null,
   DESCRIPTION          VARCHAR2(50)         not null,
   constraint PK_ROLE primary key (ID)
);

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table "USUARIO" 
(
   ID_USER              NUMBER(10)           not null,
   FIRSTNAME            VARCHAR2(50)         not null,
   LASTNAME             VARCHAR2(50)         not null,
   EMAIL                VARCHAR2(50)         not null,
   USERNAME             VARCHAR2(50)         not null,
   PASSWORDUSER             VARCHAR2(100)        not null,
   ACTIVO               CHAR(1),
   constraint PK_USER primary key (ID_USER)
);

/*==============================================================*/
/* Table: USER_ROLES                                            */
/*==============================================================*/
create table USER_ROLES 
(
   USER_ROLES_ID        NUMBER(10)           not null,
   ID                   NUMBER(10)           not null,
   ID_USER              NUMBER(10)           not null,
   constraint PK_USER_ROLES primary key (USER_ROLES_ID)
);

/*==============================================================*/
/* Index: CONTENER_FK                                           */
/*==============================================================*/
create index CONTENER_FK on USER_ROLES (
   ID ASC
);

/*==============================================================*/
/* Index: TENER_FK                                              */
/*==============================================================*/
create index TENER_FK on USER_ROLES (
   ID_USER ASC
);

alter table USER_ROLES
   add constraint FK_USER_ROL_CONTENER_ROLE foreign key (ID)
      references ROLE (ID);

alter table USER_ROLES
   add constraint FK_USER_ROL_TENER_USER foreign key (ID_USER)
      references "USUARIO" (ID_USER);

