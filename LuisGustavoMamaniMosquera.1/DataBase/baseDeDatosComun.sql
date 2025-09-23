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

INSERT INTO Sport(sport_name, sport_value)
VALUES ('volleyball', 1);

SELECT * FROM Sport;

SELECT * FROM elementos;