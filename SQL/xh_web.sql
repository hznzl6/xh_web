-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 124.221.138.245    Database: xh_website
-- ------------------------------------------------------
-- Server version	5.7.40-log

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
-- Table structure for table `ai_conversation`
--

DROP TABLE IF EXISTS `ai_conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_conversation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usecode` varchar(45) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  `initiator` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ai_message`
--

DROP TABLE IF EXISTS `ai_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ai_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(45) DEFAULT NULL,
  `question` varchar(8000) DEFAULT NULL,
  `answer` varchar(8000) DEFAULT NULL,
  `token` varchar(45) DEFAULT NULL,
  `question_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gonggao_notice`
--

DROP TABLE IF EXISTS `gonggao_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gonggao_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(16000) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `houtai_account`
--

DROP TABLE IF EXISTS `houtai_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houtai_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `houtai_role`
--

DROP TABLE IF EXISTS `houtai_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houtai_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(45) DEFAULT NULL,
  `limit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `houtai_secure`
--

DROP TABLE IF EXISTS `houtai_secure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houtai_secure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `login_ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `houtai_site`
--

DROP TABLE IF EXISTS `houtai_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `houtai_site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `belong` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(16200) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_activity`
--

DROP TABLE IF EXISTS `shetuan_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `event` varchar(45) DEFAULT NULL,
  `describes` varchar(45) DEFAULT NULL,
  `person` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `expenditure` varchar(45) DEFAULT NULL,
  `start_time` varchar(45) DEFAULT NULL,
  `end_time` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `sign` varchar(45) DEFAULT NULL,
  `actual` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `mark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_apply`
--

DROP TABLE IF EXISTS `shetuan_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(45) DEFAULT NULL,
  `mass` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `apply_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_broadcast`
--

DROP TABLE IF EXISTS `shetuan_broadcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_broadcast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(16200) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_credit`
--

DROP TABLE IF EXISTS `shetuan_credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_credit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `semester` varchar(45) DEFAULT NULL,
  `founder` varchar(45) DEFAULT NULL,
  `declaration` varchar(45) DEFAULT NULL,
  `deadline` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_creditapply`
--

DROP TABLE IF EXISTS `shetuan_creditapply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_creditapply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `semester` varchar(45) DEFAULT NULL,
  `mass` varchar(45) DEFAULT NULL,
  `list` varchar(512) DEFAULT NULL,
  `apply_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_department`
--

DROP TABLE IF EXISTS `shetuan_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_intro`
--

DROP TABLE IF EXISTS `shetuan_intro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_intro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `html` varchar(15000) DEFAULT NULL,
  `charge` varchar(45) DEFAULT NULL,
  `sno` varchar(45) DEFAULT NULL,
  `teacher` varchar(45) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `gonghao` varchar(45) DEFAULT NULL,
  `setup` varchar(45) DEFAULT NULL,
  `memnums` int(11) DEFAULT '0',
  `acnums` int(11) DEFAULT '0',
  `artnums` int(11) DEFAULT '0',
  `hitnums` int(11) DEFAULT '0',
  `comnums` int(11) DEFAULT '0',
  `bronums` int(11) DEFAULT '0',
  `nums0` int(11) DEFAULT '0',
  `crenums` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_list`
--

DROP TABLE IF EXISTS `shetuan_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `info` varchar(45) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `president` varchar(45) DEFAULT NULL,
  `member` varchar(45) DEFAULT NULL,
  `create_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_login`
--

DROP TABLE IF EXISTS `shetuan_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(45) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `perfect` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_manager`
--

DROP TABLE IF EXISTS `shetuan_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `account` varchar(45) DEFAULT NULL,
  `pwd` varchar(45) DEFAULT '123456',
  `mail` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shetuan_user`
--

DROP TABLE IF EXISTS `shetuan_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shetuan_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(45) DEFAULT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `belong` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_secure`
--

DROP TABLE IF EXISTS `user_secure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_secure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` varchar(45) DEFAULT NULL,
  `login_time` timestamp NULL DEFAULT NULL,
  `login_ip` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wenzhang_article`
--

DROP TABLE IF EXISTS `wenzhang_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wenzhang_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mass` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(16000) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `hits` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-21  4:23:55
