-- Revisar el entorno de trabajo
.version
.database
.show   
.tables 

-- BORRAR TABLAS
DROP TABLE MG_USUARIOS;
DROP TABLE MG_DATOS_ATAQUE;


--TABLA
CREATE TABLE IF NOT EXISTS  MG_USUARIOS
(
    IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Usuario VARCHAR(30) UNIQUE NOT NULL,
    Contrasenia VARCHAR(10) NOT NULL
);

INSERT INTO USER (Usuario, Contrasenia) VALUES ("profe", "1234"); 

SELECT * FROM USER;

CREATE TABLE IF NOT EXISTS MG_DATOS_ATAQUE(
Coordenada VARCHAR(2),
CoordenadaTipo VARCHAR(20),
Lunes varchar(5),
Martes varchar(5),
Miercoles varchar(5),
Jueves varchar(5),
Viernes varchar(5),
TipoArsenal varchar(20)
);

INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("profe", "827ccb0eea8a706c4c34a16891f84e7b");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("rene.guzman@epn.edu.ec", "502aa294fa760f42958ed49fd06c1d07");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("harryson.montesdeoca@epn.edu.ec", "225869c60b61a8d5bf83fb61bafb5ce4");
INSERT INTO MG_USUARIOS (Usuario, Contrasenia) VALUES ("Paccha", "bd7042282fc9fdd9b81498fbdcf372bb");

