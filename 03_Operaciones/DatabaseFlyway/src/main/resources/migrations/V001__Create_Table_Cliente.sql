-- Script que crea la tabla Cliente en el esquema public
-- Autor: Henry Wong
-- Correo: hwongu@gmail.com

CREATE TABLE public.cliente (
    id_cliente SERIAL PRIMARY KEY,     -- Campo autoincremental y clave primaria
    name VARCHAR(50) NOT NULL,         -- Campo nombre, no nulo
    last_name VARCHAR(100) NOT NULL,   -- Campo apellido, no nulo
    dni VARCHAR(8) NOT NULL,           -- Campo dni, no nulo
    email VARCHAR(120) NOT NULL,       -- Campo email, no nulo
    birth_date DATE NOT NULL,          -- Campo fecha de nacimiento, no nulo
    enabled BOOLEAN NOT NULL           -- Campo booleano, no nulo
);