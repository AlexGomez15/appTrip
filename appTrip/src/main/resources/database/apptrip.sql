CREATE DATABASE IF NOT EXISTS apptrip DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE apptrip;

CREATE TABLE IF NOT EXISTS perfil (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_perfil VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_categoria VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_rol VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_usuario VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    activo TINYINT(1),
    fecha DATETIME
);

CREATE TABLE IF NOT EXISTS trip (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_trip VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    costo DOUBLE,
    imagen VARCHAR(255),
    detalles TEXT,
    activo TINYINT(1),
    fecha DATETIME,
    id_categoria INT,
    CONSTRAINT fk_trip_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS perfil_usuario (
    perfil_id INT NOT NULL,
    usuario_id INT NOT NULL,
    PRIMARY KEY (perfil_id, usuario_id),
    CONSTRAINT fk_perfil_usuario_perfil FOREIGN KEY (perfil_id) REFERENCES perfil(id),
    CONSTRAINT fk_perfil_usuario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS trip_usuario (
    trip_id INT NOT NULL,
    usuario_id INT NOT NULL,
    PRIMARY KEY (trip_id, usuario_id),
    CONSTRAINT fk_trip_usuario_trip FOREIGN KEY (trip_id) REFERENCES trip(id),
    CONSTRAINT fk_trip_usuario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

INSERT INTO rol (id, nom_rol, descripcion, activo, fecha) VALUES
    (1, 'Vendedor', 'Puede ofrecer y gestionar trips para los visitantes', 1, NOW()),
    (2, 'Visitante', 'Puede consultar la informacion de los trips disponibles', 1, NOW()),
    (3, 'Coordinador', 'Puede coordinar actividades y revisar reservaciones', 1, NOW())
ON DUPLICATE KEY UPDATE nom_rol = VALUES(nom_rol);

INSERT INTO categoria (id, nom_categoria, descripcion, activo, fecha) VALUES
    (1, 'Playas', 'Trips hacia playas de El Salvador', 1, NOW()),
    (2, 'Montana', 'Trips de montana y naturaleza', 1, NOW()),
    (3, 'Ciudad', 'Trips culturales y urbanos', 1, NOW())
ON DUPLICATE KEY UPDATE nom_categoria = VALUES(nom_categoria);

INSERT INTO perfil (id, nom_perfil, descripcion, activo, fecha) VALUES
    (1, 'Administrador', 'Puede administrar la aplicacion', 1, NOW()),
    (2, 'Cliente', 'Puede consultar y publicar trips', 1, NOW())
ON DUPLICATE KEY UPDATE nom_perfil = VALUES(nom_perfil);

INSERT INTO usuario (id, nom_usuario, username, password, activo, fecha) VALUES
    (1, 'Carlos Perez', 'carlos', '12345', 1, NOW()),
    (2, 'Maria Lopez', 'maria', '12345', 1, NOW())
ON DUPLICATE KEY UPDATE nom_usuario = VALUES(nom_usuario);

INSERT INTO perfil_usuario (perfil_id, usuario_id) VALUES
    (1, 1),
    (2, 1),
    (2, 2)
ON DUPLICATE KEY UPDATE perfil_id = VALUES(perfil_id);

INSERT INTO trip (id, nom_trip, descripcion, costo, imagen, detalles, activo, fecha, id_categoria) VALUES
    (1, 'Playa El Tunco', 'Viaje a la playa El Tunco', 25.00, 'trip01.png', 'Salida desde San Salvador, transporte incluido y guia turistico.', 1, NOW(), 1),
    (2, 'Ruta de las Flores', 'Paseo por pueblos turisticos', 35.00, 'trip02.png', 'Visita a Ataco, Apaneca y Juayua. Incluye paradas para fotografias.', 1, NOW(), 2),
    (3, 'Centro Historico', 'Recorrido cultural por San Salvador', 15.00, 'tirp03.png', 'Recorrido por plazas, iglesia El Rosario, Palacio Nacional y Biblioteca Nacional.', 1, NOW(), 3)
ON DUPLICATE KEY UPDATE nom_trip = VALUES(nom_trip);

INSERT INTO trip_usuario (trip_id, usuario_id) VALUES
    (1, 1),
    (2, 1),
    (2, 2),
    (3, 2)
ON DUPLICATE KEY UPDATE trip_id = VALUES(trip_id);
