-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-05-2023 a las 23:14:02
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `problema3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exportacion`
--

CREATE TABLE `exportacion` (
  `id` int(11) NOT NULL,
  `NombrePro` varchar(145) NOT NULL,
  `PresentacionPro` varchar(145) NOT NULL,
  `CantidadPro` varchar(100) NOT NULL,
  `TipoEnvio` varchar(45) NOT NULL,
  `CiudadOrigen` varchar(50) NOT NULL,
  `CiudadDestino` varchar(50) NOT NULL,
  `PaisDestino` varchar(50) NOT NULL,
  `MonedaPago` varchar(45) NOT NULL,
  `NombreEmpleado` varchar(250) NOT NULL,
  `NombreFuncionario` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `exportacion`
--

INSERT INTO `exportacion` (`id`, `NombrePro`, `PresentacionPro`, `CantidadPro`, `TipoEnvio`, `CiudadOrigen`, `CiudadDestino`, `PaisDestino`, `MonedaPago`, `NombreEmpleado`, `NombreFuncionario`) VALUES
(13, 'Panela pulverizada', 'En polvo', '1000', 'Aéreo', 'Cali', 'Paris', 'Francia', 'Dólares', 'Andres Cardona', 'Luis usma'),
(14, 'Panela pulverizada', 'En polvo', '1025', 'Aéreo', 'Cali', 'Paris', 'Francia', 'Euros', 'Andres Cardona', 'Luis usma'),
(15, 'Panela ', 'Entera', '1000', 'Aéreo', 'Cali', 'Paris', 'Francia', 'Euros', 'Andres Cardona', 'Luis usma'),
(16, 'Panela en cuartos', 'Entera', '1000', 'Aéreo', 'Cali', 'Paris', 'Francia', 'Pesos', 'Andres Cardona', 'Luis usma'),
(31, 'Azucar blanca', 'Entera', '250', 'Aéreo', 'Medellin', 'Madrid', 'España', 'Euros', 'henrry', 'Juan'),
(33, 'Azucar morena', 'Entera', '300', 'Aéreo', 'Medellin', 'Madrid', 'España', 'Euros', 'henrry', 'Juan'),
(34, 'panela', 'Entera', '500', 'Marítimo', 'Antioquia', 'Paris', 'Francia', 'Euros', 'Don juan', 'Melissa'),
(35, 'trhrt', 'Seleccione', '123.33', 'Seleccione', 'hrthrh', 'hrth', 'Seleccione', 'Seleccione', 'thrrt', 'rthrt');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(20) NOT NULL,
  `nombres` varchar(245) NOT NULL,
  `apellidos` varbinary(245) NOT NULL,
  `correo` varchar(245) NOT NULL,
  `usuario` varchar(145) NOT NULL,
  `contrasena` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombres`, `apellidos`, `correo`, `usuario`, `contrasena`) VALUES
(1, 'Luis', 0x55736d61, 'Usma@gmail.com', 'luisusma', '123456'),
(2, 'prueba', 0x707275656261, 'prueba@gmail.com', 'prueba', '123'),
(4, 'luis', 0x75736d61, 'cardona', 'usma', '123'),
(5, 'Andres', 0x616e64657273, 'Andres', 'prueba2', '12'),
(8, 'prueba', 0x6461, 'ad', 'prueba1', '1'),
(9, 'Andres', 0x436172646f6e61, 'adne', 'andres', '123'),
(12, 'melissa', 0x766172676173, 'lid', 'meli', '123'),
(13, 'pruena', 0x656e616465, 'prueba@edu.co', 'web', 'web'),
(14, 'Luis', 0x75736d61, 'usma@gmail.com', 'luis', 'MXJdaTeIIPJ+0yS0or6fFg=='),
(15, 'prueba4', 0x70727565626134, 'prueba4@gm.co', 'prueba4', 'MXJdaTeIIPJ+0yS0or6fFg==');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `exportacion`
--
ALTER TABLE `exportacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `exportacion`
--
ALTER TABLE `exportacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
