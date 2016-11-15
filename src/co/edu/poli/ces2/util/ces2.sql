    --DROPS
drop table tbl_permisos;
drop table tbl_modulos;
drop table tbl_perfiles_usuarios;
drop table tbl_perfiles;
drop table tbl_usuarios;

--SEGURIDAD-----------
CREATE TABLE tbl_usuarios (
  id_usuario varchar(50) NOT NULL,
  nombre varchar(400) NOT NULL,
  apellido varchar(800) NOT NULL,
  correo varchar(100),
  clave varchar(50) NOT NULL,
  identificacion varchar(20) NOT NULL,
  mod_usuario varchar (50) not null,
  mod_fecha timestamp not null,
  CONSTRAINT pk_tbl_usuarios PRIMARY KEY (id_usuario),
  CONSTRAINT unq_tbl_usuarios_identificacion unique (identificacion)

);

insert into tbl_usuarios 
    (id_usuario, nombre, apellido, clave,identificacion, mod_usuario, mod_fecha )
values
    ('ces2','sistema','sistema','sistema','1045','ces2',current_timestamp);

alter table tbl_usuarios add constraint 
    fk_tbl_usarios_tbl_usuarios
    foreign key (mod_usuario) references tbl_usuarios (id_usuario) 
        on delete restrict
        on update cascade;

CREATE TABLE tbl_perfiles(
    id_perfil serial,
    nombre varchar(400) NOT NULL,
    descripcion varchar(800),
    mod_usuario varchar (50) not null,
    mod_fecha timestamp not null,
    constraint pk_tbl_perfiles primary key (id_perfil),
    constraint fk_tbl_perfiles_tbl_usuarios foreign key (mod_usuario)
        references tbl_usuarios (id_usuario) 
        on delete restrict on update cascade
);

CREATE TABLE tbl_perfiles_usuarios(
    id_perfil integer,
    id_usuario varchar(20),
    descripcion varchar(400),
    mod_usuario varchar (50) not null,
    mod_fecha timestamp not null,
    constraint pk_tbl_perfiles_usuarios primary key(id_perfil, id_usuario),
    CONSTRAINT fk_tbl_perfiles_usuarios_tbl_usuarios foreign key(id_usuario) 
            references tbl_usuarios (id_usuario) on delete cascade on update cascade,
    CONSTRAINT fk_tbl_perfiles_usuarios_tbl_perfiles foreign key(id_perfil) 
            references tbl_perfiles (id_perfil) on delete restrict on update cascade,
    constraint fk_tbl_perfiles_usuarios_tbl_usuarios_mod_usuario foreign key (mod_usuario)
            references tbl_usuarios (id_usuario) 
            on delete restrict on update cascade
);

CREATE TABLE tbl_modulos(
    id_modulo integer,
    nombre varchar(200) NOT NULL,
    descripcion varchar(400),
    mod_usuario varchar (50) not null,
    mod_fecha timestamp not null,
    CONSTRAINT pk_tbl_modulos primary key(id_modulo),
    constraint fk_tbl_modulos_tbl_usuarios foreign key (mod_usuario)
            references tbl_usuarios (id_usuario) 
            on delete restrict on update cascade

);
  
CREATE TABLE tbl_permisos(
    id_perfil integer,
    id_modulo integer,
    modifica boolean default false,
    elimina boolean default false,
    mod_usuario varchar (50) not null,
    mod_fecha timestamp not null,
    CONSTRAINT pk_tbl_permisos primary key(id_perfil, id_modulo),
    CONSTRAINT fk_tbl_permisos_tbl_perfiles foreign key(id_perfil) 
        references tbl_perfiles (id_perfil) on delete cascade on update cascade,
    CONSTRAINT fk_tbl_permisos_tbl_modulos foreign key(id_modulo) 
        references tbl_modulos (id_modulo) on delete restrict on update cascade,
    constraint fk_tbl_permisos_tbl_usuarios foreign key (mod_usuario)
        references tbl_usuarios (id_usuario) 
        on delete restrict on update cascade
);

