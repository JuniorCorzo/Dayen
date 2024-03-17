-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: dayen
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `lotes`
--

LOCK TABLES `lotes` WRITE;
/*!40000 ALTER TABLE `lotes` DISABLE KEYS */;
INSERT INTO `lotes` VALUES (1,'1234567890','Preparacion',8),(2,'1234567890','Preparacion',4),(4,'1234567890','Siembra',4),(5,'1234567890','Siembra',4),(6,'1234567890','Siembra',4);
/*!40000 ALTER TABLE `lotes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'1234567890','Silviano Aragon',32),(2,'1234567890','Angel Corzo',352163412),(4,'1234567890','Marino Fernandez',352163412);
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal_procesos`
--

LOCK TABLES `personal_procesos` WRITE;
/*!40000 ALTER TABLE `personal_procesos` DISABLE KEYS */;
INSERT INTO `personal_procesos` VALUES (1,1),(2,1),(1,5),(1,6),(2,6);
/*!40000 ALTER TABLE `personal_procesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procesos`
--

LOCK TABLES `procesos` WRITE;
/*!40000 ALTER TABLE `procesos` DISABLE KEYS */;
INSERT INTO `procesos` VALUES (1,1,1,'Se modifico el primer proceso','2024-03-03 01:02:00'),(2,1,1,'es un segundo proceso','2024-02-25 17:22:34'),(3,1,1,'es un tercer proceso','2024-03-03 01:02:00'),(5,1,1,'Este proceso se eliminara','2024-03-03 01:02:00'),(6,1,1,'Este proceso se eliminara','2024-03-03 01:02:00');
/*!40000 ALTER TABLE `procesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `procesos_productos`
--

LOCK TABLES `procesos_productos` WRITE;
/*!40000 ALTER TABLE `procesos_productos` DISABLE KEYS */;
INSERT INTO `procesos_productos` VALUES (1,1),(5,1),(6,1),(1,2),(6,2);
/*!40000 ALTER TABLE `procesos_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Glifosato','Herbicida'),(2,'akfjalñjfdl','Insectisida');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_procesos`
--

LOCK TABLES `tipo_procesos` WRITE;
/*!40000 ALTER TABLE `tipo_procesos` DISABLE KEYS */;
INSERT INTO `tipo_procesos` VALUES (1,'Fumigacion'),(2,'Abonada');
/*!40000 ALTER TABLE `tipo_procesos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('1234567890','Angel','Corzo','ADMINISTRADOR','josedanielmmf@gmail.com','{bcrypt}$2a$10$GpuRSg38k/icRK.rbQwG5e.QCWxAlcq0gUb2Hbw6OOXg9Vjy2wpa6','MTNiNjc1NWQtMjMxNi00MDJkLTk1YzktYzk4NmEzYjVkNDQ2LjE3MTA3MDU5Njc2NTM='),('1234567891','Yesica','Vargas','ADMINISTRADOR','prueba1@gmail.com','{bcrypt}$2a$10$MpvHUukVB3tFSuqeVoL1q.ujmRrYTzqRnzYXReFL6hoqqCeO5JWbG',NULL),('1234567892','Angel','Corzo','ADMINISTRADOR','prueba2@gmail.com','{bcrypt}$2a$10$L9JC2AC.x7XzT.6DwmpUcOoKiYw4S7vAgQOyCD9euJuSB5CNHGlNC',''),('1234567893','Leidy','Rojas','ADMINISTRADOR','prueba3@gmail.com','{bcrypt}$2a$10$L9JC2AC.x7XzT.6DwmpUcOoKiYw4S7vAgQOyCD9euJuSB5CNHGlNC','ESTO_ES_UN_TOKEN'),('1234567894','Angel','Corzo','ADMINISTRADOR','prueba5@gmail.com','{bcrypt}$2a$10$txThPaOst1xgKSdQwqMgluzjwvR29ecZXTAssQ9/M3MVD0wUpPAn6',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-17 14:37:21
