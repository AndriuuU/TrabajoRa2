CREATE TABLE Alumnos (
    dni VARCHAR(10),
    nombre VARCHAR(100),
    apellidos VARCHAR(200),
    email VARCHAR(100),
    fecha_nac DATE,
    foto VARCHAR(100),
    telefono int(9),
    pass VARCHAR(20) not null,
    CONSTRAINT alumnos_dni_pk PRIMARY KEY (dni)
);

INSERT INTO `alumnos` VALUES ('123456789A','Andres','Molina Gonzalez','andres@gmail.com','2001-08-09','/foto/AndresMolina',651321562,'molina');
INSERT INTO `alumnos` VALUES ('987654321B','Nacho','Franco','nache@gmail.com','2000-01-02','/foto/NachoFranco',234567812,'franco');
INSERT INTO `alumnos` VALUES ('123456324C','Juan Jesus','Fernandez','juanjesus@gmail.com','2000-04-03','/foto/JuanJesus',98765434,'fernandez');

CREATE TABLE Profesor(
    dni VARCHAR(10),
    nombre VARCHAR(100),
    apellidos VARCHAR(200),
    email VARCHAR(100),
    pass VARCHAR(20) not null,
    CONSTRAINT profesor_dni_pk PRIMARY KEY (dni)
);

INSERT INTO `profesor` VALUES ('74544252J','Raul','Reyes','raulreyes@gmail.com','reyes');
INSERT INTO `profesor` VALUES ('9723412523H','David','Mateo','davidmateo@gmail.com','mateo');

CREATE TABLE Asignatura(
    codAsig VARCHAR(10),
    nombre VARCHAR(200),
    horas int(4),
    dniProfesor VARCHAR(10) DEFAULT null,
    CONSTRAINT Asignatura_nomb_pk PRIMARY KEY (codAsig),
    CONSTRAINT asignatura_dniProfe_fk FOREIGN KEY (dniProfesor) REFERENCES Profesor(dni)
);

INSERT INTO `asignatura` VALUES ('Prog-123','Programacion JAVA',350,'74544252J');
INSERT INTO `asignatura` VALUES ('DI-321','Desarrollo de interfaces',300,'9723412523H');

CREATE TABLE RA(
    id VARCHAR(10),
    nombre VARCHAR(20),
    descripcion VARCHAR(150),
    ponderacion float(2),
    codAsig VARCHAR(10),
    CONSTRAINT ra_id_pk PRIMARY KEY (id),
    CONSTRAINT ra_codAsig_fk FOREIGN KEY (codAsig) REFERENCES Asignatura(codAsig)
);

INSERT INTO `ra` VALUES ('Ra1-prog','Bucles','Aprender a hacer figuras con bucles',50,'Prog-123');
INSERT INTO `ra` VALUES ('Ra2-prog','Arrays','Desarollar una biblioteca con arrays',50,'Prog-123');

INSERT INTO `ra` VALUES ('Ra1-di','Laravel','Aprender laravel',25,'DI-321');
INSERT INTO `ra` VALUES ('Ra2-di','Web','Desarrollar una pagina web',75,'DI-321');

CREATE TABLE Matricula (
    dniAlumno VARCHAR(10),
    codAsig VARCHAR(10),
    CONSTRAINT matricula_dni_codAsi_pk PRIMARY KEY (dniAlumno,codAsig),
    CONSTRAINT matricula_codAsi_fk foreign key (codAsig) references Asignatura(codAsig),
    CONSTRAINT matricula_dni_fk foreign key (dniAlumno) references Alumnos(dni)
);

INSERT INTO `matricula` VALUES ('123456789A','Prog-123');
INSERT INTO `matricula` VALUES ('123456789A','DI-321');

INSERT INTO `matricula` VALUES ('987654321B','Prog-123');

INSERT INTO `matricula` VALUES ('123456324C','Prog-123');
INSERT INTO `matricula` VALUES ('123456324C','DI-321');

CREATE TABLE Califica (
    dniAlumno VARCHAR(10),
    idRa VARCHAR(10),
    nota float(4),
    CONSTRAINT califica_dni_codRa_pk PRIMARY KEY (dniAlumno,idRa),
    CONSTRAINT califica_codAsi_fk foreign key (idRa) references RA(id),
    CONSTRAINT califica_dni_fk foreign key (dniAlumno) references Alumnos(dni)
);

INSERT INTO `califica` VALUES ('123456789A','Ra1-prog',8);
INSERT INTO `califica` VALUES ('123456789A','Ra2-prog',7);
INSERT INTO `califica` VALUES ('123456789A','Ra1-di',2);
INSERT INTO `califica` VALUES ('123456789A','Ra2-di',4);

INSERT INTO `califica` VALUES ('987654321B','Ra1-prog',9);

INSERT INTO `califica` VALUES ('123456324C','Ra1-prog',6);
INSERT INTO `califica` VALUES ('123456324C','Ra2-prog',5);
INSERT INTO `califica` VALUES ('123456324C','Ra1-di',3);
INSERT INTO `califica` VALUES ('123456324C','Ra2-di',4);
