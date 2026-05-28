CREATE DATABASE IF NOT EXISTS apptrip DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE apptrip;

CREATE TABLE IF NOT EXISTS perfil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomPerfil VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomCategoria VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomRol VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomUsuario VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS trip (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomTrip VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    costo DOUBLE,
    imagen VARCHAR(255),
    detalles TEXT,
    activo TINYINT(1),
    fecha DATETIME,
    idCategoria INT,
    CONSTRAINT fk_trip_categoria FOREIGN KEY (idCategoria) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS perfil_usuario (
    Perfil_id INT NOT NULL,
    Usuario_id INT NOT NULL,
    PRIMARY KEY (Perfil_id, Usuario_id),
    CONSTRAINT fk_perfil_usuario_perfil FOREIGN KEY (Perfil_id) REFERENCES perfil(id),
    CONSTRAINT fk_perfil_usuario_usuario FOREIGN KEY (Usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS trip_usuario (
    Trip_id INT NOT NULL,
    Usuario_id INT NOT NULL,
    PRIMARY KEY (Trip_id, Usuario_id),
    CONSTRAINT fk_trip_usuario_trip FOREIGN KEY (Trip_id) REFERENCES trip(id),
    CONSTRAINT fk_trip_usuario_usuario FOREIGN KEY (Usuario_id) REFERENCES usuario(id)
);
