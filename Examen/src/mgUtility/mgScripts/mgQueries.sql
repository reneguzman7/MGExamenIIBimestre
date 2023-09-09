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

CREATE TABLE IF NOT EXISTS  TablaJoin
(
    Usuario VARCHAR(30) NOT NULL,
    Tipo_Coordenada VARCHAR(10) NOT NULL,
    Coordenada VARCHAR(10) NOT NULL,
    Arsenal VARCHAR(50) NOT NULL,
    Dia VARCHAR(10) ,
    Hora VARCHAR(10)
);

-- Inserta los datos en la tabla JUNTO
INSERT INTO TablaJoin (Usuario, Tipo_Coordenada, Coordenada, Arsenal, Dia, Hora) VALUES
('harryson.montesdeoca@epn.edu.ec', 'South', '00', 'Avion', '', ''),
('harryson.montesdeoca@epn.edu.ec', 'North', '05', 'Avion,Barco,Convoy,Dron,Tanque', 'Viernes', '05-10'),
('harryson.montesdeoca@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('harryson.montesdeoca@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('harryson.montesdeoca@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('harryson.montesdeoca@epn.edu.ec', 'North', '09', 'Avion,Barco', 'Lunes', '01-02'),
('harryson.montesdeoca@epn.edu.ec', 'East', '02', 'Avion,Barco', 'Martes', '02-04'),
('harryson.montesdeoca@epn.edu.ec', 'East', '02', 'Avion,Barco', 'Martes', '02-04'),
('harryson.montesdeoca@epn.edu.ec', 'North', '07', 'Avion,Barco,Convoy,Dron', 'Miercoles', '03-06'),
('harryson.montesdeoca@epn.edu.ec', 'North', '01', 'Avion','Lunes', '01-02'),

('rene.guzman@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('rene.guzman@epn.edu.ec', 'North', '08', 'Avion,Barco,Convoy', 'Miercoles', '02-04'),
('rene.guzman@epn.edu.ec', 'East', '02', 'Avion,Barco', 'Martes', '02-04'),
('rene.guzman@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('rene.guzman@epn.edu.ec', 'East', '06', 'Avion,Barco,Convoy,Dron,Tanque', 'Jueves', '04-08'),
('rene.guzman@epn.edu.ec', 'North', '03', 'Avion,Barco,Convoy','Miercoles', '03-06'),
('rene.guzman@epn.edu.ec', 'South', '00', 'Avion', '', ''),
('rene.guzman@epn.edu.ec', 'North', '05', 'Avion,Barco,Convoy,Dron,Tanque', 'Viernes', '05-10'),
('rene.guzman@epn.edu.ec', 'North', '07', 'Avion,Barco,Convoy,Dron', 'Miercoles', '03-06'),
('rene.guzman@epn.edu.ec', 'North', '01', 'Avion','Lunes', '01-02');



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

SELECT
    MGU.Usuario AS Usuario,
    CDT.CoordenadaTipo AS TipoCoordenada,
    C.Coordenada AS Coordenada,
    A.TipoArsenal AS Arsenal,
    H.Dia AS Dia,
    H.Hora AS Hora
FROM
    MG_USUARIOS MGU
INNER JOIN
    Coordenada C ON MGU.IdUsuario = C.UsuarioId
INNER JOIN
    Arsenal A ON C.id = A.CoordenadaId
INNER JOIN
    Horarios H ON A.id = H.ArsenalId
INNER JOIN
    CoordenadaTipo CDT ON C.CoordenadaTipo = CDT.CoordenadaTipo;



