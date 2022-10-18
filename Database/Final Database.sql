-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: webproject
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` varchar(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  `project_factor` decimal(4,2) DEFAULT NULL,
  `test_factor` decimal(4,2) DEFAULT NULL,
  `exam_factor` decimal(4,2) DEFAULT NULL,
  `direction` varchar(20) DEFAULT NULL,
  `department` varchar(20) DEFAULT NULL,
  `professor_id` int DEFAULT NULL,
  `about` varchar(300) DEFAULT NULL,
  `books` varchar(45) DEFAULT NULL,
  `semester` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `professor_id_idx` (`professor_id`),
  CONSTRAINT `professor_id` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`professor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('CS101','Android',0.30,0.20,0.50,'TLES','CS',70,'A course to learn to make apps','Android handbook',NULL),('CS102','Data Stractures',0.30,0.20,0.50,'TLES','CS',70,'The subject of the course is the study of the basic data structures\n used in the development of algorithms','Data Stractures with C','D'),('CS103','Machine Learning',0.30,0.20,0.50,'TLES','CS',70,'Maths is important','Introduction to ML',NULL),('CS107','Algorithms',0.30,0.20,0.50,'TLES','CS',17,'Algorithms design','Genetic algorithms','D'),('CS110','Intro to Database',0.70,0.20,0.10,'Tles','Informatics',17,'','','C');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses_taken`
--

DROP TABLE IF EXISTS `courses_taken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses_taken` (
  `stude_id` int DEFAULT NULL,
  `cour_id` varchar(5) NOT NULL,
  KEY `stude_id` (`stude_id`),
  KEY `cour_id` (`cour_id`),
  CONSTRAINT `courses_taken_ibfk_1` FOREIGN KEY (`stude_id`) REFERENCES `students` (`registration_number`) ON DELETE SET NULL,
  CONSTRAINT `courses_taken_ibfk_2` FOREIGN KEY (`cour_id`) REFERENCES `courses` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses_taken`
--

LOCK TABLES `courses_taken` WRITE;
/*!40000 ALTER TABLE `courses_taken` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses_taken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `finalgrade` int NOT NULL,
  `examgrade` decimal(4,2) DEFAULT NULL,
  `projectgrade` decimal(4,2) DEFAULT NULL,
  `testgrade` decimal(4,2) DEFAULT NULL,
  `stud_id` int DEFAULT NULL,
  `courseId` varchar(5) DEFAULT NULL,
  KEY `stud_id_idx` (`stud_id`),
  KEY `courseId_idx` (`courseId`),
  CONSTRAINT `courseId` FOREIGN KEY (`courseId`) REFERENCES `courses` (`id`),
  CONSTRAINT `stud_id` FOREIGN KEY (`stud_id`) REFERENCES `students` (`registration_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (10,10.00,10.00,10.00,182,'CS101'),(9,10.00,0.00,20.00,182,'CS102'),(8,9.00,10.00,0.00,182,'CS103'),(7,3.50,2.10,1.60,182,'CS102'),(8,3.00,3.00,2.00,182,'CS101'),(9,5.00,2.40,1.40,20,'CS102'),(9,5.00,2.70,1.60,20,'CS103'),(8,5.00,1.80,1.00,20,'CS101'),(6,3.00,1.80,1.20,20,'CS101'),(6,4.50,1.20,0.00,20,'CS102'),(10,5.00,3.00,2.00,20,'CS102'),(7,4.00,2.70,0.00,20,'CS103');
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professors`
--

DROP TABLE IF EXISTS `professors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professors` (
  `professor_id` int NOT NULL,
  `about` varchar(900) DEFAULT NULL,
  `achievements` varchar(200) DEFAULT NULL,
  `users_id` int NOT NULL,
  PRIMARY KEY (`professor_id`),
  KEY `users_id_idx` (`users_id`),
  CONSTRAINT `users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`usersCounter`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professors`
--

LOCK TABLES `professors` WRITE;
/*!40000 ALTER TABLE `professors` DISABLE KEYS */;
INSERT INTO `professors` VALUES (13,'His research interests are in the field of Information Systems, Business Resource Management Systems (ERPs) and Knowledge Management.\n He is a member of the editorial board of the International Journal of Knowledge-based Development','Turing Award',1),(17,'She earned her Ph.D. from Graduate School and University Center, City University of New York. During her career she has taught at Queens College and Baruch College at City University of New York and has been an Assistant Professor \n(permanent) at State University of New York, Farmingdale.','Turing Award',2),(23,'He received his PhD from the University of London in 1980. He has published over 40 papers in international peer-reviewed journals.\n He is a member of the laboratory of Discrete Mathematics and Theoretical Informatics','Nobel Award',3),(70,'Dr. Christos Douligeris received his Diploma in Electrical Engineering from NTUA in 1984 and his MS, MPhil and PhD from Columbia University, New York, USA in 1985, 1987 and 1990 respectively. He held the position of Associate Professor at the University of Miami, USA. He is now a professor at the Department of Informatics at the University of Piraeus','',25),(207,'','',15);
/*!40000 ALTER TABLE `professors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `registration_number` int NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`registration_number`),
  KEY `users_id_idx` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `users` (`usersCounter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (182,17),(60,27),(20,28);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `usersCounter` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(15) NOT NULL,
  `department` varchar(20) NOT NULL,
  `capacity` varchar(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salts` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`usersCounter`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'John','Elton','johnjohn','Informatics','Professor','123123',NULL),(2,'Maria','Elton','mariamaria','Informatics','Professor','123123',NULL),(3,'Chris','Elton','chrischris','Informatics','Professor','123123',NULL),(4,'takis','Elton','takistaksi','Informatics','Secretary','123123',NULL),(15,'Klaus','Knuth','Duck','Physics','Professor','donald',NULL),(17,'Chrissy','Madam','chris','Maths','Student','chris',NULL),(24,'Mike','Tyson','makis','Physics','Secretary','84ec25bc006ce148f4d35de81c9a4afce0ec99447034ebbc11f540756ab5fc32','[B@5d3411d'),(25,'Klaudio','Zdrava','Django','Informatics','Professor','4f56412e880922b37a43ef6671371fb51b01a51310a3f07c227fae82084a3a1a','[B@47af6990'),(26,'Sonia','Kourtesi','sonia','Mathematics','Student','9509fd3b02deaeee249399016cc5bef38b25d6505bbd48eef5c2349edf0a1332','[B@5955cbed'),(27,'Sonia','Kourtesi','sonia','Mathematics','Student','16a6e1abdc842c5cd6f849b6826f4ced0d94257c7b664deb7c6a6756b74a7a6d','[B@a8b6c46'),(28,'Lex','Lexaros','lex','Physics','Student','9509fd3b02deaeee249399016cc5bef38b25d6505bbd48eef5c2349edf0a1332','[B@5955cbed');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-07 22:25:01
