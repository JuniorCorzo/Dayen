-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: dayen
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lotes`
--

DROP TABLE IF EXISTS `lotes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lotes` (
  `id_lote` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(12) NOT NULL,
  `fase` varchar(50) NOT NULL,
  `hectareas` int NOT NULL DEFAULT '0',
  `nombre` tinyblob,
  `titulo-imagen` tinyblob,
  PRIMARY KEY (`id_lote`),
  KEY `fk_lotes_usuarios_idx` (`id_usuario`),
  CONSTRAINT `fk_lotes_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id_personal` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(12) NOT NULL,
  `nombre` tinytext NOT NULL,
  `telefono` int NOT NULL,
  PRIMARY KEY (`id_personal`),
  KEY `fk_personal_usuarios1_idx` (`id_usuario`),
  CONSTRAINT `fk_personal_usuarios1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personal_procesos`
--

DROP TABLE IF EXISTS `personal_procesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_procesos` (
  `id_personal` int NOT NULL,
  `id_proceso` int NOT NULL,
  PRIMARY KEY (`id_personal`,`id_proceso`),
  KEY `fk_personal_has_procesos_procesos1_idx` (`id_proceso`),
  KEY `fk_personal_has_procesos_personal1_idx` (`id_personal`),
  CONSTRAINT `fk_personal_has_procesos_personal1` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`),
  CONSTRAINT `fk_personal_has_procesos_procesos1` FOREIGN KEY (`id_proceso`) REFERENCES `procesos` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `procesos`
--

DROP TABLE IF EXISTS `procesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procesos` (
  `id_proceso` int NOT NULL AUTO_INCREMENT,
  `id_lote` int NOT NULL,
  `id_tipo` int NOT NULL,
  `descripcion` varchar(3000) NOT NULL,
  `realizado_en` timestamp NOT NULL,
  PRIMARY KEY (`id_proceso`),
  KEY `fk_procesos_lotes1_idx` (`id_lote`),
  KEY `fk_procesos_tipo_procesos1_idx` (`id_tipo`),
  CONSTRAINT `fk_procesos_lotes1` FOREIGN KEY (`id_lote`) REFERENCES `lotes` (`id_lote`),
  CONSTRAINT `fk_procesos_tipo_procesos1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_procesos` (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `procesos_productos`
--

DROP TABLE IF EXISTS `procesos_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procesos_productos` (
  `id_proceso` int NOT NULL,
  `id_producto` int NOT NULL,
  PRIMARY KEY (`id_proceso`,`id_producto`),
  KEY `fk_procesos_has_productos_productos1_idx` (`id_producto`),
  KEY `fk_procesos_has_productos_procesos1_idx` (`id_proceso`),
  CONSTRAINT `fk_procesos_has_productos_procesos1` FOREIGN KEY (`id_proceso`) REFERENCES `procesos` (`id_proceso`),
  CONSTRAINT `fk_procesos_has_productos_productos1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id_producto` int NOT NULL AUTO_INCREMENT,
  `nombre` tinytext NOT NULL,
  `funcion` tinytext NOT NULL,
  PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_procesos`
--

DROP TABLE IF EXISTS `tipo_procesos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_procesos` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `tipo_proceso` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` varchar(12) NOT NULL,
  `nombre` tinytext NOT NULL,
  `apellido` tinytext NOT NULL,
  `rol` varchar(13) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `clave` tinytext NOT NULL,
  `token` tinytext,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo_UNIQUE` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-03 15:06:04
