-- Revisar el entorno de trabajo
.version
.database
.show
.tables

--QUERIES PARA CREAR LAS TABLAS
--TABLA
CREATE TABLE IF NOT EXISTS  MG_USUARIOS
(
    IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Usuario VARCHAR(30) UNIQUE NOT NULL,
    Contrasenia VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS  JUNTO
(
    Usuario VARCHAR(30) UNIQUE NOT NULL,
    Tipo Coordenada VARCHAR(10) NOT NULL
    Coordenada VARCHAR(10) NOT NULL
    Arsenal VARCHAR(10) NOT NULL
    Dia VARCHAR(10) NOT NULL
    Hora VARCHAR(10) NOT NULL
); 

-- Tabla Coordenada
CREATE TABLE IF NOT EXISTS Coordenada (
                                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                                          Coordenada VARCHAR(2)
);

-- Tabla CoordenadaTipo
CREATE TABLE IF NOT EXISTS CoordenadaTipo (
                                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                                              CoordenadaTipo VARCHAR(20)
);

-- Tabla Arsenal
CREATE TABLE IF NOT EXISTS Arsenal (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       TipoArsenal VARCHAR(50)
);

-- Tabla Horarios
CREATE TABLE IF NOT EXISTS Horarios (
                                        Lunes VARCHAR(5),
                                        Martes VARCHAR(5),
                                        Miercoles VARCHAR(5),
                                        Jueves VARCHAR(5),
                                        Viernes VARCHAR(5),
                                        HorariosID INTEGER PRIMARY KEY AUTOINCREMENT
);

-- Inserta los datos en la tabla JUNTO
INSERT INTO JUNTO (Usuario, TipoCoordenada, Coordenada, Arsenal, Dia, Hora) VALUES
('Harryson', 'Coord_South', '00', 'Avion', '', ''),
('Harryson', 'Coord_North', '05', 'abcdt', 'Viernes', '05-10'),
('Harryson', 'Coord_East', '06', 'abcdt', 'Jueves', '04-08'),
('Harryson', 'Coord_East', '06', 'abcdt', 'Jueves', '04-08'),
('Harryson', 'Coord_East', '06', 'abcdt', 'Jueves', '04-08'),
('Harryson', 'Coord_North', '09', 'ab', 'Lunes', '01-02'),
('Harryson', 'Coord_East', '02', '02-04', '', '', '', '02-04'),
('Harryson', 'Coord_East', '02', '02-04', '', '', '', 'ab'),
('Harryson', 'Coord_North', '07', '', '03-06', '', '', 'abcd'),
('Harryson', 'Coord_North', '01', '', '', '', '', 'a'),

('rene', 'Coord_South', '', '', '', '', 'a'),
('rene', 'Coord_North', '', '', '', '', '05-10', 'abcdt'),
('rene', 'Coord_East', '', '', '', '04-08', '', 'abcdt'),
('rene', 'Coord_East', '', '', '', '04-08', '', 'abcdt'),
('rene', 'Coord_East', '', '', '', '04-08', '', 'abcdt'),
('rene', 'Coord_North', '01-02', '', '', '', '', 'ab'),
('rene', 'Coord_East', '', '02-04', '', '', '', 'ab'),
('rene', 'Coord_East', '', '02-04', '', '', '', 'ab'),
('rene', 'Coord_North', '', '', '03-06', '', '', 'abcd'),
('rene', 'Coord_North', '01-02', '', '', '', '', 'a');


--Queries de usuarios con claves encriptadas con MD5
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("profe", "827ccb0eea8a706c4c34a16891f84e7b");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("rene.guzman@epn.edu.ec", "502aa294fa760f42958ed49fd06c1d07");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("harryson.montesdeoca@epn.edu.ec", "225869c60b61a8d5bf83fb61bafb5ce4");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("Paccha", "bd7042282fc9fdd9b81498fbdcf372bb");



-- QUERIES PARA BORRAR DATOS
DROP TABLE Arsenal;
DROP TABLE Coordenada;
DROP TABLE CoordenadaTipo;
DROP TABLE Horarios;



