-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_liberary
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `books_table`
--

DROP TABLE IF EXISTS `books_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books_table` (
  `name` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `publeshed_date` date NOT NULL,
  `id` int NOT NULL,
  `info` varchar(255) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books_table`
--

LOCK TABLES `books_table` WRITE;
/*!40000 ALTER TABLE `books_table` DISABLE KEYS */;
INSERT INTO `books_table` VALUES ('my book','me','2022-02-22',1,'just a book','NOT AVAILABLE'),('second','qwerty','2023-01-12',2,'some info','AVAILABLE'),('3.Trird','333','2023-01-03',3,'third book','NOT AVAILABLE'),('Afourth','mentos','2022-01-28',4,'dream ','AVAILABLE'),('All','everybody','2023-01-26',5,'smt','AVAILABLE');
/*!40000 ALTER TABLE `books_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `depts_table`
--

DROP TABLE IF EXISTS `depts_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `depts_table` (
  `id` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `depts_table`
--

LOCK TABLES `depts_table` WRITE;
/*!40000 ALTER TABLE `depts_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `depts_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_books_table`
--

DROP TABLE IF EXISTS `order_books_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_books_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_books_table__order_fk` (`order_id`),
  KEY `order_books_table_books_table_id_fk` (`book_id`),
  CONSTRAINT `order_books_table__order_fk` FOREIGN KEY (`order_id`) REFERENCES `orders_table` (`id`),
  CONSTRAINT `order_books_table_books_table_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_books_table`
--

LOCK TABLES `order_books_table` WRITE;
/*!40000 ALTER TABLE `order_books_table` DISABLE KEYS */;
INSERT INTO `order_books_table` VALUES (1,1,12),(2,2,12),(3,3,12),(4,4,12),(5,1,12),(6,4,12),(7,2,13),(8,5,13),(9,2,16),(10,3,16),(11,4,16),(12,1,17),(13,2,17),(14,3,17),(15,4,17),(16,5,17),(17,2,22),(18,3,22),(19,1,14),(20,2,14),(21,4,15),(22,1,15),(23,1,23),(24,2,23),(25,5,24),(26,3,24),(27,1,25),(28,2,25),(29,3,25),(30,5,26),(31,4,26);
/*!40000 ALTER TABLE `order_books_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_table`
--

DROP TABLE IF EXISTS `orders_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_table` (
  `user_id` int NOT NULL,
  `librarian_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `subscription` tinyint(1) DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_table_fk0` (`user_id`),
  KEY `orders_table_fk1` (`librarian_id`),
  CONSTRAINT `orders_table_fk0` FOREIGN KEY (`user_id`) REFERENCES `users_table` (`id`),
  CONSTRAINT `orders_table_fk1` FOREIGN KEY (`librarian_id`) REFERENCES `users_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_table`
--

LOCK TABLES `orders_table` WRITE;
/*!40000 ALTER TABLE `orders_table` DISABLE KEYS */;
INSERT INTO `orders_table` VALUES (1,3,9,'2022-12-27','CANCELED',NULL,NULL),(1,NULL,10,'2023-02-08','CREATED',NULL,NULL),(1,3,11,'2022-12-28','CANCELED',1,NULL),(5,NULL,12,'2023-01-03','CANCELED',NULL,NULL),(2,NULL,13,'2023-01-03','CREATED',NULL,NULL),(3,NULL,14,'2023-01-04','CREATED',NULL,NULL),(3,NULL,15,'2023-01-04','CREATED',NULL,NULL),(2,NULL,16,'2023-01-05','CREATED',NULL,NULL),(1,NULL,17,'2023-01-05','CREATED',NULL,NULL),(1,NULL,21,'2023-01-06','CREATED',NULL,NULL),(1,NULL,22,'2023-01-06','CREATED',NULL,NULL),(3,NULL,23,'2023-02-03','CREATED',NULL,NULL),(3,3,24,'2023-02-03','CANCELED',1,'2022-11-26'),(3,NULL,25,'2023-02-04','CREATED',NULL,NULL),(3,NULL,26,'2023-02-04','CREATED',NULL,NULL);
/*!40000 ALTER TABLE `orders_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_table`
--

DROP TABLE IF EXISTS `roles_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_table` (
  `name` varchar(255) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_table`
--

LOCK TABLES `roles_table` WRITE;
/*!40000 ALTER TABLE `roles_table` DISABLE KEYS */;
INSERT INTO `roles_table` VALUES ('ADMIN',0),('LIBRARIAN',1),('CLIENT',2);
/*!40000 ALTER TABLE `roles_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_table`
--

DROP TABLE IF EXISTS `users_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  `isblocked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `users_table_fk0` (`role_id`),
  CONSTRAINT `users_table_fk0` FOREIGN KEY (`role_id`) REFERENCES `roles_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_table`
--

LOCK TABLES `users_table` WRITE;
/*!40000 ALTER TABLE `users_table` DISABLE KEYS */;
INSERT INTO `users_table` VALUES (1,'admin','admin','adaa','dminaa',0,1),(2,'lib','lib','ti','rex',1,1),(3,'user','user','not me','me',2,0),(4,'Key','O','first name','no',1,0),(5,'fifth','5555','5opka','2oka',1,0),(6,'fifth','5555','six','shest',1,0),(7,'administr','ad','sem','even',1,1),(8,'man','silver','tidsadas','rexdsada',1,1);
/*!40000 ALTER TABLE `users_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_liberary'
--
/*!50003 DROP PROCEDURE IF EXISTS `CREATE_NEW_ORDER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `CREATE_NEW_ORDER`(IN user_id int, IN book_ids text)
proc_label:BEGIN

    -- step 1: set error handler to process and rollback transaction
    DECLARE errno INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
        BEGIN
            GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
            SELECT errno AS MYSQL_ERROR;
            ROLLBACK;
        END;

    -- step 2: open new transaction
    START TRANSACTION;

    -- step 3: set temporary variable for input book_ids
    SET @book_ids = book_ids;

    -- step 4: determine length of book_ids string
    SET @string_length = LENGTH(@book_ids) - LENGTH(REPLACE(@book_ids, ',', ''));

    -- step 5: create new order
    INSERT INTO orders_table (user_id, create_date, status) VALUES (user_id, CURDATE(),'CREATED');

    -- step 6: get order_id after insert
    SET @last_order_id = LAST_INSERT_ID();

    -- step 7: init loop for book_ids
    loop_label: WHILE (@string_length > 0)
        DO
            -- step 8: get first book_id from book_ids
            SET @book_id = SUBSTRING_INDEX(@book_ids, ',', 1);

            -- step 8.1: insert value into table
            IF (@book_id != '') THEN
                INSERT INTO order_books_table (order_id, book_id) VALUES (@last_order_id,  @book_id);

                -- step 8.2: if book_id wasn't found then exit loop
            ELSE
                LEAVE loop_label;
            END IF;

            -- step 9: calculate remaining string length
            SET @string_length = LENGTH(@book_ids) - LENGTH(REPLACE(@book_ids, ',', ''));

            -- step 9.1: if book_ids length is 0 then exit loop
            IF (@string_length = 0) THEN
                LEAVE loop_label;
            END IF;

            -- step 10: exclude received book_id from string and set new value for book_ids
            SET @book_ids = SUBSTRING(@book_ids, LENGTH(SUBSTRING_INDEX(@book_ids, ',', 1)) + 2);
        END WHILE;

    -- step 11: commit transaction
    COMMIT WORK;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `DELETE_FULL_ORDER` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `DELETE_FULL_ORDER`(IN orderId int)
BEGIN
    delete from orders_table where id = orderId;
    delete from order_books_table where order_id = orderId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-04 17:41:19
