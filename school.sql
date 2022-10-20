-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-10-2022 a las 22:33:02
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `school`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `fecha_nac` date DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`dni`, `nombre`, `apellidos`, `email`, `fecha_nac`, `foto`, `telefono`, `pass`) VALUES
('123456324C', 'Juan Jesus', 'Fernandez', 'correojuan@gmail.com', '2000-04-03', 'files/pics/123456324CJuanJesus.jpg', 98765434, 'fernandez'),
('123456789A', 'Andres', 'Molina Gonzalez', 'andres@gmail.com', '2001-08-09', '/foto/AndresMolina', 651321562, 'molina'),
('32095961K', 'Nacho', 'Franco', 'nacho@gmail.com', '2000-01-10', 'foto', 647610275, 'qwerty');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `codAsig` varchar(5) NOT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `horas` int(4) DEFAULT NULL,
  `dniProfesor` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`codAsig`, `nombre`, `horas`, `dniProfesor`) VALUES
('A002', 'BASE DATOS', 200, '4123412k'),
('DI-32', 'Desarrollo de interfaces', 300, NULL),
('M004', 'Python', 200, '4123412k'),
('Prog-', 'Programacion JAVA', 350, NULL),
('progg', 'programasion', 200, '4123412k'),
('XXXX', 'PRUEBA', 100, '4123412k');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `califica`
--

CREATE TABLE `califica` (
  `dniAlumno` varchar(10) NOT NULL,
  `idRa` varchar(5) NOT NULL,
  `nota` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `califica`
--

INSERT INTO `califica` (`dniAlumno`, `idRa`, `nota`) VALUES
('123456324C', 'Ra1-p', 6),
('123456324C', 'Ra2-d', 4),
('123456324C', 'Ra2-p', 5),
('123456789A', 'Ra1-p', 8),
('123456789A', 'Ra2-d', 4),
('123456789A', 'Ra2-p', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `dniAlumno` varchar(10) NOT NULL,
  `codAsig` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`dniAlumno`, `codAsig`) VALUES
('123456324C', 'DI-32'),
('123456324C', 'progg'),
('123456324C', 'XXXX'),
('123456789A', 'DI-32'),
('123456789A', 'Prog-');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(200) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`dni`, `nombre`, `apellidos`, `email`, `pass`) VALUES
('3298439k', 'Jesus', 'Garcia Aguado', 'jesusgarcia@gmail.com', 'qwerty'),
('4123412k', 'paco', 'pepe', 'paco@pepe.com', 'qwerty');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ra`
--

CREATE TABLE `ra` (
  `id` varchar(5) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `descripcion` varchar(150) DEFAULT NULL,
  `ponderacion` float DEFAULT NULL,
  `codAsig` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ra`
--

INSERT INTO `ra` (`id`, `nombre`, `descripcion`, `ponderacion`, `codAsig`) VALUES
('Ra1-p', 'Bucles', 'Aprender a hacer figuras con bucles', 50, 'Prog-'),
('Ra2-d', 'Web', 'Desarrollar una pagina web', 75, 'DI-32'),
('Ra2-p', 'Arrays', 'Desarollar una biblioteca con arrays', 50, 'Prog-');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`codAsig`),
  ADD KEY `asignatura_dniProfe_fk` (`dniProfesor`);

--
-- Indices de la tabla `califica`
--
ALTER TABLE `califica`
  ADD PRIMARY KEY (`dniAlumno`,`idRa`),
  ADD KEY `califica_codAsi_fk` (`idRa`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`dniAlumno`,`codAsig`),
  ADD KEY `matricula_codAsi_fk` (`codAsig`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `ra`
--
ALTER TABLE `ra`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ra_codAsig_fk` (`codAsig`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD CONSTRAINT `asignatura_dniProfe_fk` FOREIGN KEY (`dniProfesor`) REFERENCES `profesor` (`dni`) ON DELETE SET NULL;

--
-- Filtros para la tabla `califica`
--
ALTER TABLE `califica`
  ADD CONSTRAINT `califica_codAsi_fk` FOREIGN KEY (`idRa`) REFERENCES `ra` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `califica_dni_fk` FOREIGN KEY (`dniAlumno`) REFERENCES `alumnos` (`dni`);

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_codAsi_fk` FOREIGN KEY (`codAsig`) REFERENCES `asignatura` (`codAsig`) ON DELETE CASCADE,
  ADD CONSTRAINT `matricula_dni_fk` FOREIGN KEY (`dniAlumno`) REFERENCES `alumnos` (`dni`) ON DELETE CASCADE;

--
-- Filtros para la tabla `ra`
--
ALTER TABLE `ra`
  ADD CONSTRAINT `ra_codAsig_fk` FOREIGN KEY (`codAsig`) REFERENCES `asignatura` (`codAsig`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
