CREATE DATABASE IF NOT EXISTS Postulantes;

USE Postulantes;

CREATE TABLE Carrera (
    id_carrera INT AUTO_INCREMENT PRIMARY KEY,
    nombre_carrera VARCHAR(100) NOT NULL
);

CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ci VARCHAR(20) UNIQUE NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    id_carrera INT,
    contrasenia VARCHAR(50),
    FOREIGN KEY (id_carrera) REFERENCES Carrera(id_carrera)
);

CREATE TABLE Postulacion (
    id_postulacion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_carrera INT NOT NULL,
    fecha_postulacion DATE NOT NULL,
    estado ENUM('pendiente','aceptado','rechazado') DEFAULT 'pendiente',
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_carrera) REFERENCES Carrera(id_carrera)
);

CREATE TABLE MetodosPago(
    id_metodo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo VARCHAR(50)
);

CREATE TABLE Pago(
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_metodo INT NOT NULL,
    id_monto DECIMAL(7,2) NOT NULL,
    fecha_pago DATE NOT NULL
);

CREATE TABLE carreras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    facultad VARCHAR(100) NOT NULL
);

INSERT INTO carreras (nombre, facultad) VALUES
('Ingeniería de Sistemas', 'Facultad de Ciencias y Tecnología'),
('Medicina', 'Facultad de Medicina'),
('Derecho', 'Facultad de Ciencias Jurídicas'),
('Arquitectura', 'Facultad de Arquitectura y Urbanismo');

CREATE TABLE documentos (
    id_documento INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    ci_pdf LONGBLOB,
    diploma_pdf LONGBLOB,
    fotografia_pdf LONGBLOB,
    certificado_nacimiento_pdf LONGBLOB,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
