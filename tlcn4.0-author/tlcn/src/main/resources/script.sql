-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: coffeesystem
-- ------------------------------------------------------
-- Server version	8.0.17

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1kec5bwba2rl0j8garlarwe3d` (`employee_id`),
  CONSTRAINT `FK1kec5bwba2rl0j8garlarwe3d` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'anonymousUser','2019-11-20 23:34:12','anonymousUser','2019-11-20 23:34:12','huynhtu.abcd@gmail.com',_binary '','$2a$10$jAsgfWuxyctVzDlyzB2nj.Sfkx59r92.6ZcYquL2BITy2xXs8/Wza','huynhtu',1),(2,'anonymousUser','2019-11-20 23:38:32','anonymousUser','2019-11-20 23:38:32','tien@gmail.com',_binary '','$2a$10$2y2r/j8Y8sQPOtzD2zRVk.JcS7gBF74OCzkyf237wbiH36SlvSJnW','camtien',2),(3,'anonymousUser','2019-11-20 23:39:32','anonymousUser','2019-11-20 23:39:32','vuthanh@gmail.com',_binary '','$2a$10$BWQWE5/GByXMNc0H35IU4eA5tMbOFT6ispQ6ZwCEFmH7HVgSDcRgi','thanhvu',5),(4,'anonymousUser','2019-11-20 23:40:24','anonymousUser','2019-11-20 23:40:24','linh@gmail.com',_binary '','$2a$10$fEmKTSF6.5zc9luF0VAXBuQGvmLQa/ggfAT9ZbIqg6u8Ef0x6OTBm','hongvan',8),(5,'anonymousUser','2019-11-21 11:54:07','anonymousUser','2019-11-21 11:54:07','myvan@gmail.com',_binary '','$2a$10$rA2.optplNIEdIauOZn/.O8C4jLfdUWR84Ptx.mvXuoV9W30S8oNu','myvan',4);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_role` (
  `account_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`account_id`,`role_id`),
  KEY `FKpmvq83t0lkmb6ny7tt7aqpld0` (`role_id`),
  CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKpmvq83t0lkmb6ny7tt7aqpld0` FOREIGN KEY (`role_id`) REFERENCES `role_names` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1),(1,2),(2,3),(5,4),(4,5),(3,6);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch_shop`
--

DROP TABLE IF EXISTS `branch_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch_shop`
--

LOCK TABLES `branch_shop` WRITE;
/*!40000 ALTER TABLE `branch_shop` DISABLE KEYS */;
INSERT INTO `branch_shop` VALUES (1,'anonymousUser','2019-11-20 23:22:08','anonymousUser','2019-12-05 08:44:45','1 Võ Văn Ngân phường Linh Chiểu, Quận Thủ Đức, TpHCM',_binary '','Chi nhánh trung tâm'),(2,'anonymousUser','2019-11-20 23:22:30','anonymousUser','2019-12-05 08:46:04','205 Lê Văn Việt phường Hiệp Phú, Quận 9, TpHCM',_binary '','Chi nhánh LVV'),(3,'anonymousUser','2019-11-20 23:22:56','anonymousUser','2019-12-05 08:47:58','315 Hai Bà Trưng, phường 8, Quận 3, TpHCM',_binary '','Chi nhánh HBT'),(4,'anonymousUser','2019-12-05 08:50:16','anonymousUser','2019-12-05 08:50:16','206 Bình Quới, phường 28, Quận Bình Thanh, TpHCM ',_binary '','Chi nhánh Thanh Đa');
/*!40000 ALTER TABLE `branch_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coffee_table`
--

DROP TABLE IF EXISTS `coffee_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coffee_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `number_of_chair` int(11) DEFAULT NULL,
  `table_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrc6f06av5gi7shx5p3mmmlmw7` (`table_type_id`),
  CONSTRAINT `FKrc6f06av5gi7shx5p3mmmlmw7` FOREIGN KEY (`table_type_id`) REFERENCES `table_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coffee_table`
--

LOCK TABLES `coffee_table` WRITE;
/*!40000 ALTER TABLE `coffee_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `coffee_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` bit(1) NOT NULL,
  `total_purchase` float NOT NULL,
  `customer_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nonbx33y5nkpeeohhjs6r18c0` (`email`),
  KEY `FKn8vf9jf3m29plqn6rx45p2pl7` (`customer_type_id`),
  CONSTRAINT `FKn8vf9jf3m29plqn6rx45p2pl7` FOREIGN KEY (`customer_type_id`) REFERENCES `customer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'anonymousUser','2019-11-21 11:37:35','anonymousUser','2019-12-01 16:49:03','string','1995-01-27','tu@gmail.com',_binary '','Huỳnh Tú',NULL,'0123456789',_binary '\0',0,1),(2,'anonymousUser','2019-11-21 11:38:55','anonymousUser','2019-12-01 16:49:03','string','1992-05-20','minh@gmail.com',_binary '','Quang Minh',NULL,'0123456788',_binary '',0,1),(3,'anonymousUser','2019-11-21 11:40:00','anonymousUser','2019-11-21 11:40:00','25 Nam Kỳ Khởi Nghĩa','1985-04-13','tuan@gmail.com',_binary '','Anh Tuấn',NULL,'0123456710',_binary '\0',0,2),(4,'anonymousUser','2019-12-05 09:10:42','anonymousUser','2019-12-05 09:10:42','string','1985-03-23','haicao@gmail.com',_binary '','Cao Văn Hải',NULL,'0973205302',_binary '',0,2),(5,'anonymousUser','2019-12-05 09:12:27','anonymousUser','2019-12-05 09:12:27','string','1982-08-20','ngothu@gmail.com',_binary '','Ngô Thị Cẩm Thu',NULL,'0935205144',_binary '\0',0,1),(6,'anonymousUser','2019-12-05 09:13:27','anonymousUser','2019-12-05 09:13:27','string','1987-03-11','chienngo@gmail.com',_binary '','Ngô Quân Chiến',NULL,'0934605254',_binary '',0,2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount_value` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'anonymousUser','2019-11-21 11:31:13','anonymousUser','2019-11-21 11:31:13',_binary '','Khách hàng thân thiết',NULL,0),(2,'anonymousUser','2019-11-21 11:31:46','anonymousUser','2019-12-01 20:55:48',_binary '','Khách hàng VIP','5%',0.05);
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drink`
--

DROP TABLE IF EXISTS `drink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `drink_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ajbquw0g004epcrxf6fbs39y` (`drink_type_id`),
  CONSTRAINT `FK9ajbquw0g004epcrxf6fbs39y` FOREIGN KEY (`drink_type_id`) REFERENCES `drink_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drink`
--

LOCK TABLES `drink` WRITE;
/*!40000 ALTER TABLE `drink` DISABLE KEYS */;
INSERT INTO `drink` VALUES (1,'anonymousUser','2019-11-21 11:26:49','anonymousUser','2019-11-21 11:26:49',NULL,_binary '','Espresso',1),(2,'anonymousUser','2019-11-21 11:27:32','anonymousUser','2019-11-21 11:27:32',NULL,_binary '','Capuchino',1),(3,'anonymousUser','2019-11-21 11:27:52','anonymousUser','2019-11-21 11:27:52',NULL,_binary '','Cà phê đen',1),(4,'anonymousUser','2019-11-21 11:27:59','anonymousUser','2019-11-21 11:27:59',NULL,_binary '','Cà phê sữa',1),(5,'anonymousUser','2019-11-21 11:28:19','anonymousUser','2019-11-21 11:28:19',NULL,_binary '','Ép cóc',4),(6,'anonymousUser','2019-11-21 11:28:27','anonymousUser','2019-11-21 11:28:27',NULL,_binary '','Ép ổi',4),(7,'anonymousUser','2019-11-21 11:28:35','anonymousUser','2019-11-21 11:28:35',NULL,_binary '','Ép táo',4),(8,'anonymousUser','2019-11-21 11:29:59','anonymousUser','2019-11-21 11:29:59',NULL,_binary '','7Up',3);
/*!40000 ALTER TABLE `drink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drink_price`
--

DROP TABLE IF EXISTS `drink_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drink_price` (
  `date_drink` date NOT NULL,
  `id` int(11) NOT NULL,
  `id_drink` int(11) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `initial_price` float NOT NULL,
  `price` float NOT NULL,
  `drink_drink_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`date_drink`,`id`,`id_drink`),
  KEY `FKsdlgc45vssmkqa2m0l3i97xy4` (`drink_drink_id`),
  CONSTRAINT `FKsdlgc45vssmkqa2m0l3i97xy4` FOREIGN KEY (`drink_drink_id`) REFERENCES `drink` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drink_price`
--

LOCK TABLES `drink_price` WRITE;
/*!40000 ALTER TABLE `drink_price` DISABLE KEYS */;
INSERT INTO `drink_price` VALUES ('2018-12-31',1,1,'anonymousUser','2019-11-23 11:18:37','anonymousUser','2019-11-23 11:18:48',_binary '\0',5000,20000,1),('2018-12-31',2,1,'anonymousUser','2019-11-23 11:18:48','anonymousUser','2019-11-23 11:19:03',_binary '\0',5000,20000,1),('2019-01-01',3,1,'anonymousUser','2019-11-23 11:19:03','anonymousUser','2019-11-23 11:19:35',_binary '\0',5000,20000,1),('2019-01-01',4,1,'anonymousUser','2019-11-23 11:19:35','anonymousUser','2019-11-23 11:20:46',_binary '\0',5000,20000,1),('2019-01-01',5,1,'anonymousUser','2019-11-23 11:20:46','anonymousUser','2019-11-23 11:36:23',_binary '\0',6000,22000,1),('2019-01-01',6,1,'anonymousUser','2019-11-23 11:36:23','anonymousUser','2019-11-23 11:36:23',_binary '',6000,22000,1),('2019-01-22',7,2,'anonymousUser','2019-11-24 20:01:27','anonymousUser','2019-11-24 20:01:27',_binary '\0',5000,20000,2),('2019-01-22',8,2,'anonymousUser','2019-11-24 20:01:30','anonymousUser','2019-11-24 20:01:30',_binary '\0',5000,20000,2),('2019-01-22',9,2,'anonymousUser','2019-11-24 20:01:33','anonymousUser','2019-11-24 20:01:33',_binary '\0',5000,20000,2),('2019-06-01',10,2,'anonymousUser','2019-11-24 20:08:55','anonymousUser','2019-11-24 20:08:58',_binary '\0',5000,25000,2),('2019-06-01',11,2,'anonymousUser','2019-11-24 20:08:58','anonymousUser','2019-11-24 20:09:00',_binary '\0',5000,25000,2),('2019-06-01',12,2,'anonymousUser','2019-11-24 20:09:01','anonymousUser','2019-11-24 20:09:27',_binary '\0',5000,25000,2),('2019-06-01',14,2,'anonymousUser','2019-11-24 20:17:20','anonymousUser','2019-11-24 20:17:20',_binary '',5000,25000,2),('2019-07-01',13,2,'anonymousUser','2019-11-24 20:09:27','anonymousUser','2019-11-24 20:17:20',_binary '\0',5000,30000,2);
/*!40000 ALTER TABLE `drink_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drink_type`
--

DROP TABLE IF EXISTS `drink_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drink_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drink_type`
--

LOCK TABLES `drink_type` WRITE;
/*!40000 ALTER TABLE `drink_type` DISABLE KEYS */;
INSERT INTO `drink_type` VALUES (1,'anonymousUser','2019-11-21 11:24:53','anonymousUser','2019-11-21 11:24:53',_binary '','Coffee'),(2,'anonymousUser','2019-11-21 11:25:00','anonymousUser','2019-11-21 11:25:00',_binary '','Trà'),(3,'anonymousUser','2019-11-21 11:25:12','anonymousUser','2019-11-21 11:25:12',_binary '','Nước giải khát'),(4,'anonymousUser','2019-11-21 11:25:24','anonymousUser','2019-11-21 11:25:24',_binary '','Nước ép');
/*!40000 ALTER TABLE `drink_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `branch_shop_id` int(11) DEFAULT NULL,
  `employee_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6i0l4ficqo3n8hvf4jli8g2ss` (`branch_shop_id`),
  KEY `FKks0jnjwhw9tjwa2b1l0klv1fb` (`employee_type_id`),
  CONSTRAINT `FK6i0l4ficqo3n8hvf4jli8g2ss` FOREIGN KEY (`branch_shop_id`) REFERENCES `branch_shop` (`id`),
  CONSTRAINT `FKks0jnjwhw9tjwa2b1l0klv1fb` FOREIGN KEY (`employee_type_id`) REFERENCES `employee_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'anonymousUser','2019-11-20 23:23:55','anonymousUser','2019-11-20 23:23:55','huynhtu.abcd@gmail.com',_binary '','Huỳnh Tú',1,6),(2,'anonymousUser','2019-11-20 23:24:20','anonymousUser','2019-11-20 23:24:20','hieuminh@gmail.com',_binary '','Minh Hiếu',1,2),(3,'anonymousUser','2019-11-20 23:24:45','anonymousUser','2019-11-20 23:24:45','tiennguyen@gmail.com',_binary '','Cẩm Tiên',1,1),(4,'anonymousUser','2019-11-20 23:25:04','anonymousUser','2019-11-20 23:25:04','myvan@gmail.com',_binary '','Mỹ Vân',1,3),(5,'anonymousUser','2019-11-20 23:25:18','anonymousUser','2019-11-20 23:25:18','vupham@gmail.com',_binary '','Phạm Thanh Vũ',1,5),(6,'anonymousUser','2019-11-20 23:25:59','anonymousUser','2019-11-20 23:25:59','ngocminh45@gmail.com',_binary '','Minh Ngọc',1,7),(7,'anonymousUser','2019-11-20 23:26:09','anonymousUser','2019-11-20 23:26:09','danhphan@gmail.com',_binary '','Phan Hữu Danh',1,7),(8,'anonymousUser','2019-11-20 23:26:31','anonymousUser','2019-11-20 23:26:31','linhnh@gmail.com',_binary '','Nguyễn Hồng Linh',1,4),(9,'anonymousUser','2019-12-05 09:23:21','anonymousUser','2019-12-05 09:23:21','trinhptt@gmail.com',_binary '','Phạm Thị Thùy Trinh',2,2),(10,'anonymousUser','2019-12-05 09:24:49','anonymousUser','2019-12-05 09:24:49','khoado@gmail.com',_binary '','Đỗ Đăng Khoa',2,1),(11,'anonymousUser','2019-12-05 09:26:15','anonymousUser','2019-12-05 09:26:15','hantp@gmail.com',_binary '','Tống Phước Hân',2,5),(12,'anonymousUser','2019-12-05 09:27:38','anonymousUser','2019-12-05 09:27:38','hoanhai@gmail.com',_binary '','Phạm Thị Hoa Nhài',2,7),(13,'anonymousUser','2019-12-05 09:28:16','anonymousUser','2019-12-05 09:28:16','thang12nd@gmail.com',_binary '','Nguyễn Đức Thắng',2,7),(14,'anonymousUser','2019-12-05 09:29:25','anonymousUser','2019-12-05 09:29:25','phuongyen@gmail.com',_binary '','Nguyễn Thị Phương Yến',3,7),(15,'anonymousUser','2019-12-05 09:30:00','anonymousUser','2019-12-05 09:30:00','huonglth@gmail.com',_binary '','Lê Thị Thanh Hương',3,7),(16,'anonymousUser','2019-12-05 09:31:33','anonymousUser','2019-12-05 09:31:33','huyennguye@gmail.com',_binary '','Nguyễn Thị Huyền',3,1),(17,'anonymousUser','2019-12-05 09:32:15','anonymousUser','2019-12-05 09:32:15','phunghm@gmail.com',_binary '','Huỳnh Minh Phụng',3,3),(18,'anonymousUser','2019-12-05 09:33:08','anonymousUser','2019-12-05 09:33:08','hailong@gmail.com',_binary '','Phạm Long Hải',3,5),(19,'anonymousUser','2019-12-05 09:33:55','anonymousUser','2019-12-05 09:33:55','hongntt@gmail.com',_binary '','Nguyễn Thị Thu Hồng',3,4),(20,'anonymousUser','2019-12-05 09:35:35','anonymousUser','2019-12-05 09:35:35','thaothudt@gmail.com',_binary '','Đỗ Thị Thu Thảo',4,3),(21,'anonymousUser','2019-12-05 09:36:14','anonymousUser','2019-12-05 09:36:14','khuongdc@gmail.com',_binary '','Đỗ Cao Khương',4,5),(22,'anonymousUser','2019-12-05 09:37:15','anonymousUser','2019-12-05 09:37:15','truchdt@gmail.com',_binary '','Huỳnh Đào Thanh Trúc',4,2),(23,'anonymousUser','2019-12-05 09:38:26','anonymousUser','2019-12-05 09:38:26','tuanquoc@gmail.com',_binary '','Phạm Quốc Tuấn',4,7),(24,'anonymousUser','2019-12-05 09:39:20','anonymousUser','2019-12-05 09:39:20','huongthanh@gmail.com',_binary '','Nguyễn Thị Thanh Hương',4,7);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_type`
--

LOCK TABLES `employee_type` WRITE;
/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` VALUES (1,'anonymousUser','2019-11-20 23:19:35','anonymousUser','2019-11-20 23:19:35',_binary '','hr'),(2,'anonymousUser','2019-11-20 23:19:53','anonymousUser','2019-11-20 23:19:53',_binary '','branch manager'),(3,'anonymousUser','2019-11-20 23:20:06','anonymousUser','2019-11-20 23:20:06',_binary '','cashier'),(4,'anonymousUser','2019-11-20 23:20:20','anonymousUser','2019-11-20 23:20:20',_binary '','accountant'),(5,'anonymousUser','2019-11-20 23:20:35','anonymousUser','2019-11-20 23:20:35',_binary '','chef'),(6,'anonymousUser','2019-11-20 23:20:44','anonymousUser','2019-11-20 23:20:44',_binary '','IT'),(7,'anonymousUser','2019-11-20 23:25:41','anonymousUser','2019-11-20 23:25:41',_binary '','wait');
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `number_position` int(11) DEFAULT NULL,
  `payment_status` bit(1) NOT NULL,
  `total_discount` float NOT NULL,
  `total_price` float NOT NULL,
  `vat` float NOT NULL,
  `branch_shop_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `order_type_id` int(11) DEFAULT NULL,
  `real_pay` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhktv3nl8hj89hbo3x5j283nrf` (`branch_shop_id`),
  KEY `FK5e32ukwo9uknwhylogvta4po6` (`customer_id`),
  KEY `FKnvsnfi42440u3ct7s2wwyno3u` (`order_type_id`),
  CONSTRAINT `FK5e32ukwo9uknwhylogvta4po6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKhktv3nl8hj89hbo3x5j283nrf` FOREIGN KEY (`branch_shop_id`) REFERENCES `branch_shop` (`id`),
  CONSTRAINT `FKnvsnfi42440u3ct7s2wwyno3u` FOREIGN KEY (`order_type_id`) REFERENCES `order_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'myvan','2019-11-21 11:50:11','myvan','2019-11-21 11:50:11','2019-11-21 11:48:16',_binary '',5,_binary '',0,0,0,1,1,1,0),(2,'myvan','2019-11-21 11:50:31','myvan','2019-11-21 11:50:31','2019-11-21 11:48:16',_binary '',NULL,_binary '',0,0,0,1,2,2,0),(3,'myvan','2019-11-21 11:54:39','myvan','2019-11-21 11:54:39','2019-11-21 11:48:16',_binary '',NULL,_binary '',0,0,0,1,2,2,0),(4,'myvan','2019-11-22 20:53:16','myvan','2019-11-22 20:53:16','2019-11-22 20:15:37',_binary '',2,_binary '',0,0,0,1,1,1,0),(5,'myvan','2019-11-22 22:16:44','huynhtu','2019-12-05 00:09:23','2019-11-22 22:16:44',_binary '',2,_binary '',0,0,0,1,2,1,0),(6,'myvan','2019-11-22 22:31:33','myvan','2019-11-22 22:31:33','2019-11-22 22:31:32',_binary '',NULL,_binary '\0',0,0,0,1,3,2,0);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_detail`
--

DROP TABLE IF EXISTS `invoice_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_detail` (
  `id` int(11) NOT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `discount` float NOT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float NOT NULL,
  `unit_price` float NOT NULL,
  `drink_id` int(11) NOT NULL,
  `invoice_id` int(11) NOT NULL,
  PRIMARY KEY (`drink_id`,`id`,`invoice_id`),
  KEY `FKit1rbx4thcr6gx6bm3gxub3y4` (`invoice_id`),
  CONSTRAINT `FK3107cm8xsvq06xg102k8rhgps` FOREIGN KEY (`drink_id`) REFERENCES `drink` (`id`),
  CONSTRAINT `FKit1rbx4thcr6gx6bm3gxub3y4` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_detail`
--

LOCK TABLES `invoice_detail` WRITE;
/*!40000 ALTER TABLE `invoice_detail` DISABLE KEYS */;
INSERT INTO `invoice_detail` VALUES (2,'anonymousUser','2019-11-26 15:31:38','anonymousUser','2019-11-26 15:31:38',0,0,'it da',0,0,1,1),(3,'anonymousUser','2019-11-26 15:32:06','anonymousUser','2019-11-26 15:32:06',0,0,'it da',0,0,1,2),(4,'anonymousUser','2019-11-26 15:32:55','anonymousUser','2019-11-26 15:32:55',0,0,NULL,0,0,1,2),(5,'anonymousUser','2019-11-26 15:33:02','anonymousUser','2019-11-26 15:33:02',0,0,NULL,0,1,1,2);
/*!40000 ALTER TABLE `invoice_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `inventory` float NOT NULL,
  `max_inventory` float NOT NULL,
  `min_inventory` float NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `material_type_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbi2x1y0wd21rwst858gdylxr7` (`material_type_id`),
  KEY `FKffb619hl9g5jnro5kpyxfsld6` (`unit_id`),
  CONSTRAINT `FKbi2x1y0wd21rwst858gdylxr7` FOREIGN KEY (`material_type_id`) REFERENCES `material_type` (`id`),
  CONSTRAINT `FKffb619hl9g5jnro5kpyxfsld6` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'anonymousUser','2019-11-21 11:21:11','anonymousUser','2019-11-21 11:21:11',_binary '',0,0,0,'7Up',1,4),(2,'anonymousUser','2019-11-21 11:21:38','anonymousUser','2019-11-21 11:21:38',_binary '',0,0,0,'Pepsi',1,4),(3,'anonymousUser','2019-11-21 11:22:00','anonymousUser','2019-11-21 11:22:00',_binary '',0,0,0,'Cocacola',1,4),(4,'anonymousUser','2019-11-21 11:22:33','anonymousUser','2019-11-21 11:22:33',_binary '',0,0,0,'Coffee',2,5),(5,'anonymousUser','2019-11-21 11:23:05','anonymousUser','2019-11-21 11:23:05',_binary '',0,0,0,'Trà khô',3,5),(6,'anonymousUser','2019-11-21 11:23:20','anonymousUser','2019-11-21 11:23:20',_binary '',0,0,0,'Chanh',4,5);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_price`
--

DROP TABLE IF EXISTS `material_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_price` (
  `date_material` date NOT NULL,
  `id` int(11) NOT NULL,
  `id_material` int(11) NOT NULL,
  `enable` bit(1) NOT NULL,
  `price` float NOT NULL,
  `material_material_id` int(11) DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`date_material`,`id`,`id_material`),
  KEY `FKo5g5focw5jgco5pkce0dnd5a9` (`material_material_id`),
  CONSTRAINT `FKo5g5focw5jgco5pkce0dnd5a9` FOREIGN KEY (`material_material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_price`
--

LOCK TABLES `material_price` WRITE;
/*!40000 ALTER TABLE `material_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_type`
--

DROP TABLE IF EXISTS `material_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_type`
--

LOCK TABLES `material_type` WRITE;
/*!40000 ALTER TABLE `material_type` DISABLE KEYS */;
INSERT INTO `material_type` VALUES (1,'anonymousUser','2019-11-21 11:13:43','anonymousUser','2019-11-21 11:13:43',_binary '','Nước giải khát'),(2,'anonymousUser','2019-11-21 11:13:51','anonymousUser','2019-11-21 11:13:51',_binary '','Coffee'),(3,'anonymousUser','2019-11-21 11:13:58','anonymousUser','2019-11-21 11:14:22',_binary '','Trà'),(4,'anonymousUser','2019-11-21 11:14:46','anonymousUser','2019-11-21 11:14:46',_binary '','Trái cây');
/*!40000 ALTER TABLE `material_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_type`
--

DROP TABLE IF EXISTS `order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_type`
--

LOCK TABLES `order_type` WRITE;
/*!40000 ALTER TABLE `order_type` DISABLE KEYS */;
INSERT INTO `order_type` VALUES (1,'anonymousUser','2019-11-21 11:13:17','anonymousUser','2019-11-21 11:13:17',_binary '','Ngồi tại bàn'),(2,'anonymousUser','2019-11-21 11:13:26','anonymousUser','2019-11-21 11:13:26',_binary '','Mang về');
/*!40000 ALTER TABLE `order_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `drink_id` int(11) NOT NULL,
  `material_id` int(11) NOT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`drink_id`,`material_id`),
  KEY `FKlowjvqe07rpjj61ibqsxnetpr` (`material_id`),
  KEY `FK7v2bies7mi0f2i97g66iio5ec` (`unit_id`),
  CONSTRAINT `FK7v2bies7mi0f2i97g66iio5ec` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`),
  CONSTRAINT `FKlcdqsddjuw9n6p77rbc666mle` FOREIGN KEY (`drink_id`) REFERENCES `drink` (`id`),
  CONSTRAINT `FKlowjvqe07rpjj61ibqsxnetpr` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_names`
--

DROP TABLE IF EXISTS `role_names`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_names` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8tluj2qwmwu7b017rlyy1rhf8` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_names`
--

LOCK TABLES `role_names` WRITE;
/*!40000 ALTER TABLE `role_names` DISABLE KEYS */;
INSERT INTO `role_names` VALUES (1,_binary '','ROLE_ADMIN'),(2,_binary '','ROLE_HR'),(3,_binary '','ROLE_BRANCH_MANAGER'),(4,_binary '','ROLE_CASHIER'),(5,_binary '','ROLE_ACCOUNTANT'),(6,_binary '','ROLE_CHEF');
/*!40000 ALTER TABLE `role_names` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tax_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `total_purchase` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'anonymousUser','2019-11-27 20:46:12','anonymousUser','2019-11-27 20:46:12','string','string',_binary '','vinamilk','string','string','string',0),(2,'anonymousUser','2019-11-27 20:46:29','anonymousUser','2019-11-27 20:46:29','string','string',_binary '','cocacola','string','string','string',0),(3,'anonymousUser','2019-11-27 20:46:47','anonymousUser','2019-11-27 20:46:47','string','string',_binary '','cocacola','string','string','string',0),(4,'anonymousUser','2019-11-27 20:47:21','anonymousUser','2019-11-27 20:47:21','string','string',_binary '','nestle','string','string','string',0);
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply_contract`
--

DROP TABLE IF EXISTS `supply_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply_contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `total_price` float NOT NULL,
  `branch_shop` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbjtxy687nm2rhwvhjn5kd3tk` (`branch_shop`),
  KEY `FKslba8w3pap43cme20wark14aj` (`supplier_id`),
  CONSTRAINT `FKrbjtxy687nm2rhwvhjn5kd3tk` FOREIGN KEY (`branch_shop`) REFERENCES `branch_shop` (`id`),
  CONSTRAINT `FKslba8w3pap43cme20wark14aj` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply_contract`
--

LOCK TABLES `supply_contract` WRITE;
/*!40000 ALTER TABLE `supply_contract` DISABLE KEYS */;
INSERT INTO `supply_contract` VALUES (1,'anonymousUser','2019-11-27 20:47:54','anonymousUser','2019-11-27 20:47:54','2019-05-30',_binary '',2000000,1,4),(2,'anonymousUser','2019-11-27 20:48:52','anonymousUser','2019-11-27 20:48:52','2019-06-03',_binary '',1500000,1,4),(3,'anonymousUser','2019-11-27 20:50:04','anonymousUser','2019-11-27 20:50:04','2019-06-01',_binary '',3000000,1,1);
/*!40000 ALTER TABLE `supply_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supply_contract_detail`
--

DROP TABLE IF EXISTS `supply_contract_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supply_contract_detail` (
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `amount` float NOT NULL,
  `delivery_time` date DEFAULT NULL,
  `payment_time` date DEFAULT NULL,
  `unit_price` float NOT NULL,
  `material_id` int(11) NOT NULL,
  `supply_contract_id` int(11) NOT NULL,
  PRIMARY KEY (`material_id`,`supply_contract_id`),
  KEY `FKlb49o7jxqx9f3m14qma2a5qvv` (`supply_contract_id`),
  CONSTRAINT `FK4qp781dunx8b7lyynsufmvw76` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `FKlb49o7jxqx9f3m14qma2a5qvv` FOREIGN KEY (`supply_contract_id`) REFERENCES `supply_contract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supply_contract_detail`
--

LOCK TABLES `supply_contract_detail` WRITE;
/*!40000 ALTER TABLE `supply_contract_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `supply_contract_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_type`
--

DROP TABLE IF EXISTS `table_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_type`
--

LOCK TABLES `table_type` WRITE;
/*!40000 ALTER TABLE `table_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `table_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'anonymousUser','2019-11-21 11:10:38','anonymousUser','2019-11-21 11:10:38',_binary '','gam'),(2,'anonymousUser','2019-11-21 11:10:45','anonymousUser','2019-11-21 11:10:45',_binary '','ml'),(3,'anonymousUser','2019-11-21 11:11:02','anonymousUser','2019-11-21 11:11:02',_binary '','lit'),(4,'anonymousUser','2019-11-21 11:11:30','anonymousUser','2019-11-21 11:11:30',_binary '','lon'),(5,'anonymousUser','2019-11-21 11:12:25','anonymousUser','2019-11-21 11:12:25',_binary '','kg'),(6,'anonymousUser','2019-11-21 11:12:36','anonymousUser','2019-11-21 11:12:36',_binary '','chai');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-05 19:39:59
