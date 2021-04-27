-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: social_network
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_name_UNIQUE` (`account_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'kien','123'),(2,'phin','123'),(3,'thinh','123'),(4,'cao','123');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child_comment`
--

DROP TABLE IF EXISTS `child_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `child_comment` (
  `child_comment_id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_image` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `parent_comment_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`child_comment_id`),
  KEY `parent_idx` (`parent_comment_id`),
  KEY `fdsfd_idx` (`user_id`),
  CONSTRAINT `fdsfd` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `parent` FOREIGN KEY (`parent_comment_id`) REFERENCES `parent_comment` (`parent_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child_comment`
--

LOCK TABLES `child_comment` WRITE;
/*!40000 ALTER TABLE `child_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `child_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `district_id` int NOT NULL AUTO_INCREMENT,
  `province_id` int NOT NULL,
  `district_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`district_id`),
  KEY `province_id` (`province_id`),
  CONSTRAINT `district_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `province` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,1,'a1'),(2,2,'b1');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourite` (
  `favourite_id` int NOT NULL AUTO_INCREMENT,
  `favourite_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`favourite_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
INSERT INTO `favourite` VALUES (1,'music'),(2,'book'),(3,'sport');
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite_user`
--

DROP TABLE IF EXISTS `favourite_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favourite_user` (
  `favourite_user_id` int NOT NULL AUTO_INCREMENT,
  `favourite_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`favourite_user_id`),
  KEY `favourite_id` (`favourite_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `favourite_user_ibfk_1` FOREIGN KEY (`favourite_id`) REFERENCES `favourite` (`favourite_id`),
  CONSTRAINT `favourite_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite_user`
--

LOCK TABLES `favourite_user` WRITE;
/*!40000 ALTER TABLE `favourite_user` DISABLE KEYS */;
INSERT INTO `favourite_user` VALUES (1,1,1),(2,2,1),(3,3,3),(4,2,4);
/*!40000 ALTER TABLE `favourite_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_request` (
  `friend_request_id` int NOT NULL AUTO_INCREMENT,
  `receiver_user_id` int NOT NULL,
  `send_user_id` int NOT NULL,
  PRIMARY KEY (`friend_request_id`),
  KEY `receiver_id_idx` (`receiver_user_id`),
  KEY `sender_id_idx` (`send_user_id`),
  CONSTRAINT `receiver_id` FOREIGN KEY (`receiver_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `sender_id` FOREIGN KEY (`send_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `friends_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `friend_id` int NOT NULL,
  PRIMARY KEY (`friends_id`),
  KEY `friend_id_idx` (`friend_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `friend_id` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,1,2),(2,2,1),(3,1,3),(4,3,1);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_request`
--

DROP TABLE IF EXISTS `group_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_request` (
  `group_request_id` int NOT NULL AUTO_INCREMENT,
  `from` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`group_request_id`),
  KEY `re_idx` (`group_id`),
  KEY `yt_idx` (`user_id`),
  CONSTRAINT `FKe6m95w9y50nhi8vh7f0rkoei4` FOREIGN KEY (`group_id`) REFERENCES `group_table` (`group_id`),
  CONSTRAINT `re` FOREIGN KEY (`group_id`) REFERENCES `group_social` (`group_id`),
  CONSTRAINT `yt` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_request`
--

LOCK TABLES `group_request` WRITE;
/*!40000 ALTER TABLE `group_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_social`
--

DROP TABLE IF EXISTS `group_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_social` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `group_published` date NOT NULL,
  `group_background` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `group_avatar` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `admin` int NOT NULL,
  `scope` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name_UNIQUE` (`group_name`),
  KEY `admin_idx` (`admin`),
  CONSTRAINT `admin` FOREIGN KEY (`admin`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_social`
--

LOCK TABLES `group_social` WRITE;
/*!40000 ALTER TABLE `group_social` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_social` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_table`
--

DROP TABLE IF EXISTS `group_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_table` (
  `group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `group_published` datetime(6) DEFAULT NULL,
  `group_avatar` varchar(255) DEFAULT NULL,
  `group_background` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `admin` int DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `FKhn4n6af6t7b08bb1l9oqkb49j` (`admin`),
  CONSTRAINT `FKhn4n6af6t7b08bb1l9oqkb49j` FOREIGN KEY (`admin`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_table`
--

LOCK TABLES `group_table` WRITE;
/*!40000 ALTER TABLE `group_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_user` (
  `group_user_id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`group_user_id`),
  KEY `group_idx` (`group_id`),
  KEY `user_idx` (`user_id`),
  CONSTRAINT `cx` FOREIGN KEY (`group_id`) REFERENCES `group_social` (`group_id`),
  CONSTRAINT `FK34g68g2qiha6iexg8t8y01bxd` FOREIGN KEY (`group_id`) REFERENCES `group_table` (`group_id`),
  CONSTRAINT `we` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_warning`
--

DROP TABLE IF EXISTS `group_warning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_warning` (
  `group_warning_id` int NOT NULL AUTO_INCREMENT,
  `warning_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `group_user_id` int NOT NULL,
  `warning_date` date NOT NULL,
  PRIMARY KEY (`group_warning_id`),
  KEY `tr_idx` (`group_user_id`),
  CONSTRAINT `tr` FOREIGN KEY (`group_user_id`) REFERENCES `group_user` (`group_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_warning`
--

LOCK TABLES `group_warning` WRITE;
/*!40000 ALTER TABLE `group_warning` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_warning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_comment`
--

DROP TABLE IF EXISTS `parent_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent_comment` (
  `parent_comment_id` int NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `comment_image` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `post_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`parent_comment_id`),
  KEY `post_idx` (`post_id`),
  KEY `user_idx` (`user_id`),
  CONSTRAINT `dsaf` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `post` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_comment`
--

LOCK TABLES `parent_comment` WRITE;
/*!40000 ALTER TABLE `parent_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `parent_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_login`
--

DROP TABLE IF EXISTS `persistent_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_login` (
  `series` varchar(64) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `token` varchar(64) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_login`
--

LOCK TABLES `persistent_login` WRITE;
/*!40000 ALTER TABLE `persistent_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `series` varchar(64) NOT NULL,
  `last_used` datetime(6) NOT NULL,
  `token` varchar(64) NOT NULL,
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `post_content` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `post_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `post_published` date NOT NULL,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_idx` (`user_id`),
  KEY `group_idx` (`group_id`),
  CONSTRAINT `FK5tcp6wrqnn88eanpa5416evro` FOREIGN KEY (`group_id`) REFERENCES `group_table` (`group_id`),
  CONSTRAINT `group` FOREIGN KEY (`group_id`) REFERENCES `group_social` (`group_id`),
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_image`
--

DROP TABLE IF EXISTS `post_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_image` (
  `post_image_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `image` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`post_image_id`),
  KEY `ps_idx` (`post_id`),
  CONSTRAINT `ps` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_image`
--

LOCK TABLES `post_image` WRITE;
/*!40000 ALTER TABLE `post_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `province_id` int NOT NULL AUTO_INCREMENT,
  `province_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'a'),(2,'b');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referent_account`
--

DROP TABLE IF EXISTS `referent_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referent_account` (
  `referent_account_id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `referent_account_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email_referent` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`referent_account_id`),
  KEY `user_id_idx` (`account_id`),
  CONSTRAINT `abc` FOREIGN KEY (`account_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKjaja33g1ceh6r8mqqmbyrrof0` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referent_account`
--

LOCK TABLES `referent_account` WRITE;
/*!40000 ALTER TABLE `referent_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `referent_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'a'),(2,'b');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `occupation` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_avatar` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `user_background` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `marriaged` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `status_id` int NOT NULL,
  `account_id` int NOT NULL,
  `ward_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `status_id_idx` (`status_id`),
  KEY `account_id_idx` (`account_id`),
  KEY `ward_id_idx` (`ward_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `ward_id` FOREIGN KEY (`ward_id`) REFERENCES `ward` (`ward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Kien','2000-01-01','Male','Electrican','111','kien@kien.kien','abc','abc','no',1,1,1),(2,'Phin','2001-02-02','Male','ChoiDo','222','phin@phin.phin','abc','abc','no',2,2,2),(3,'Thinh','2002-03-03','Male','Now','333','thinh@thinh.thinh','abc','abc','no',1,3,1),(4,'Cao','2003-04-04','Female','Bee','444','cao@cao.cao','abc','abc','no',2,4,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userconnection` (
  `userid` varchar(255) NOT NULL,
  `provideruserid` varchar(255) NOT NULL,
  `providerid` varchar(255) NOT NULL,
  `accesstoken` varchar(512) DEFAULT NULL,
  `displayname` varchar(255) DEFAULT NULL,
  `expiretime` bigint DEFAULT NULL,
  `imageurl` varchar(512) DEFAULT NULL,
  `profileurl` varchar(512) DEFAULT NULL,
  `rank` int NOT NULL,
  `refreshtoken` varchar(512) DEFAULT NULL,
  `secret` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`userid`,`provideruserid`,`providerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward`
--

DROP TABLE IF EXISTS `ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward` (
  `ward_id` int NOT NULL AUTO_INCREMENT,
  `district_id` int NOT NULL,
  `ward_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ward_id`),
  KEY `district_id` (`district_id`),
  CONSTRAINT `ward_ibfk_1` FOREIGN KEY (`district_id`) REFERENCES `district` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward`
--

LOCK TABLES `ward` WRITE;
/*!40000 ALTER TABLE `ward` DISABLE KEYS */;
INSERT INTO `ward` VALUES (1,1,'a1'),(2,2,'b1');
/*!40000 ALTER TABLE `ward` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 10:34:42
