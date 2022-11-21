CREATE SCHEMA IF NOT EXITSTS usuaris;

CREATE TABLE IF NOT EXISTS usuaris.credencials (
    id INTEGER NOT NULL,
    nom_usuari VARCHAR(30) NOT NULL ,
    contrasenya VARCHAR(30) NOT NULL
);

create table if not exists usuaris.usuari_generic (
    id integer not null,
    nom varchar(30) not null,
    cognoms varchar(50) not null,
    nom_usuari varchar(30) not null,
    edat integer not null
);
create table if not exists usuaris.usuari_gim inherits usuari_generic ()

create table if not exists usuaris.entrenador inherits usuari_generic ()
