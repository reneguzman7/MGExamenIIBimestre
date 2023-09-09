-- Revisar el entorno de trabajo
.version
.database
.show
.tables

-- BORRAR TABLAS
DROP TABLE MG_USUARIOS;



--TABLA
CREATE TABLE IF NOT EXISTS  MG_USUARIOS
(
    IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Usuario VARCHAR(30) UNIQUE NOT NULL,
    Contrasenia VARCHAR(10) NOT NULL
);





--Queries de usuarios con claves encriptadas con MD5
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("profe", "827ccb0eea8a706c4c34a16891f84e7b");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("rene.guzman@epn.edu.ec", "502aa294fa760f42958ed49fd06c1d07");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("harryson.montesdeoca@epn.edu.ec", "225869c60b61a8d5bf83fb61bafb5ce4");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("Paccha", "bd7042282fc9fdd9b81498fbdcf372bb");


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

DROP TABLE Arsenal;
DROP TABLE Coordenada;
DROP TABLE CoordenadaTipo;
DROP TABLE Horarios;


SELECT
    MG_USUARIOS.Usuario AS Usuario,
    CoordenadaTipo.CoordenadaTipo AS "Tipo coordenada",
    Coordenada.Coordenada AS "Coordenada",
    Arsenal.TipoArsenal AS "Arsenal"
FROM
    MG_USUARIOS
INNER JOIN
    Coordenada ON MG_USUARIOS.IdUsuario = Coordenada.UsuarioId
INNER JOIN
    CoordenadaTipo ON Coordenada.CoordenadaTipo = CoordenadaTipo.id
INNER JOIN
    Arsenal ON Coordenada.id = Arsenal.CoordenadaId;

SELECT
    MG_USUARIOS.Usuario AS Usuario,
    CoordenadaTipo.CoordenadaTipo AS "Tipo coordenada",
    Coordenada.Coordenada AS "Coordenada",
    Arsenal.TipoArsenal AS "Arsenal"
FROM
    MG_USUARIOS
INNER JOIN
    Coordenada ON MG_USUARIOS.IdUsuario = Coordenada.UsuarioId
INNER JOIN
    CoordenadaTipo ON Coordenada.CoordenadaTipo = CoordenadaTipo.id
INNER JOIN
    Arsenal ON Coordenada.id = Arsenal.CoordenadaId;