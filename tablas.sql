CREATE TABLE Alumnos (
    dni VARCHAR(10),
    nombre VARCHAR(100),
    apellidos VARCHAR(200),
    email VARCHAR(100),
    fecha_nac DATE,
    foto VARCHAR(100),
    telefono int(9),
    pass VARCHAR(20),
    CONSTRAINT alumnos_dni_pk PRIMARY KEY (dni)
);

CREATE TABLE Profesor(
    dni VARCHAR(10),
    nombre VARCHAR(100),
    apellidos VARCHAR(200),
    email VARCHAR(100),
    pass VARCHAR(20),
    CONSTRAINT profesor_dni_pk PRIMARY KEY (dni)
);

CREATE TABLE Asignatura(
    codAsig VARCHAR(5),
    nombre VARCHAR(200),
    horas int(4),
    dniProfesor VARCHAR(10),
    CONSTRAINT Asignatura_nomb_pk PRIMARY KEY (codAsig),
    CONSTRAINT asignatura_dniProfe_fk FOREIGN KEY (dniProfesor) REFERENCES Profesor(dni)
);

CREATE TABLE RA(
    id VARCHAR(5),
    nombre VARCHAR(20),
    descripcion VARCHAR(150),
    ponderacion float(2),
    codAsig VARCHAR(5),
    CONSTRAINT ra_id_pk PRIMARY KEY (id),
    CONSTRAINT ra_codAsig_fk FOREIGN KEY (codAsig) REFERENCES Asignatura(codAsig)
);

CREATE TABLE Matricula (
    dniAlumno VARCHAR(10),
    codAsig VARCHAR(5),
    CONSTRAINT matricula_dni_codAsi_pk PRIMARY KEY (dniAlumno,codAsig),
    CONSTRAINT matricula_codAsi_fk foreign key (codAsig) references Asignatura(codAsig),
    CONSTRAINT matricula_dni_fk foreign key (dniAlumno) references Alumnos(dni)
);

CREATE TABLE Califica (
    dniAlumno VARCHAR(10),
    idRa VARCHAR(5),
    nota float(4),
    CONSTRAINT califica_dni_codRa_pk PRIMARY KEY (dniAlumno,idRa),
    CONSTRAINT califica_codAsi_fk foreign key (idRa) references RA(id),
    CONSTRAINT califica_dni_fk foreign key (dniAlumno) references Alumnos(dni)

);