CREATE DATABASE IF NOT EXISTS Registro;
USE Registro;

CREATE TABLE Sport (
	sport_id INT PRIMARY KEY AUTO_INCREMENT,
    sport_name VARCHAR(50) UNIQUE,
    sport_value INT UNIQUE
);

CREATE TABLE IF NOT EXISTS elementos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Carreras (
    id_carrera INT AUTO_INCREMENT PRIMARY KEY,
    nombre_carrera VARCHAR(150) NOT NULL,
    codigo_carrera VARCHAR(15) UNIQUE NOT NULL,
    descripcion TEXT,
    duracion_semestres INT NOT NULL,
    modalidad ENUM('presencial', 'virtual', 'semipresencial') DEFAULT 'presencial',
    estado ENUM('habilitada', 'deshabilitada') DEFAULT 'habilitada',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO Sport(sport_name, sport_value)
VALUES ('volleyball', 1);

SELECT * FROM Sport;

SELECT * FROM elementos;
