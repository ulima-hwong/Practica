-- Insertar datos de ejemplo en la tabla cliente
-- Autor: Henry Wong
-- Correo: hwongu@gmail.com

INSERT INTO public.cliente (name, last_name, dni, email, birth_date, enabled)
VALUES
    ('Carlos', 'Perez', '12345678', 'carlos.perez@correo.com', '2000-05-10', true),
    ('Carla', 'Gomez', '87654321', 'carla.gomez@correo.com', '2001-08-20', true),
    ('Carmen', 'Ruiz', '11223344', 'carmen.ruiz@correo.com', '1999-03-15', true),
    ('Diego', 'Lopez', '22334455', 'diego.lopez@correo.com', '2002-11-02', true),
    ('Elena', 'Torres', '33445566', 'elena.torres@correo.com', '2000-01-25', false);