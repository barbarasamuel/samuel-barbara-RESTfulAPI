-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: poseiden
-- ------------------------------------------------------
-- Server version	8.0.39

--
-- Table structure for table `bidlist`
--

DROP TABLE IF EXISTS `bidlist`;

CREATE TABLE `bidlist` (
  `id` int NOT NULL,
  `account` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `bid_quantity` double DEFAULT NULL,
  `ask_quantity` bigint DEFAULT NULL,
  `bid` double DEFAULT NULL,
  `ask` double DEFAULT NULL,
  `benchmark` varchar(125) DEFAULT NULL,
  `bid_list_date` date DEFAULT NULL,
  `commentary` varchar(125) DEFAULT NULL,
  `security` varchar(125) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `trader` varchar(125) DEFAULT NULL,
  `book` varchar(125) DEFAULT NULL,
  `creation_name` varchar(125) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `revision_name` varchar(125) DEFAULT NULL,
  `revision_date` date DEFAULT NULL,
  `deal_name` varchar(125) DEFAULT NULL,
  `deal_type` varchar(125) DEFAULT NULL,
  `source_list_id` varchar(125) DEFAULT NULL,
  `side` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `bidlist`
--

LOCK TABLES `bidlist` WRITE;
INSERT INTO `bidlist` VALUES (1,'1','type1',1.1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2','type2',1.2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;

--
-- Table structure for table `bidlist_seq`
--

DROP TABLE IF EXISTS `bidlist_seq`;
CREATE TABLE `bidlist_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `bidlist_seq`
--

LOCK TABLES `bidlist_seq` WRITE;
INSERT INTO `bidlist_seq` VALUES (251);
UNLOCK TABLES;

--
-- Table structure for table `curvepoint`
--

DROP TABLE IF EXISTS `curvepoint`;
CREATE TABLE `curvepoint` (
  `id` int NOT NULL,
  `curve_id` int DEFAULT NULL,
  `as_of_date` timestamp NULL DEFAULT NULL,
  `term` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `curvepoint`
--

LOCK TABLES `curvepoint` WRITE;
INSERT INTO `curvepoint` VALUES (1,1,NULL,1.1,1.3,NULL),(3,3,NULL,1.5,6,NULL),(52,4,NULL,1,10,NULL);
UNLOCK TABLES;

--
-- Table structure for table `curvepoint_seq`
--

DROP TABLE IF EXISTS `curvepoint_seq`;
CREATE TABLE `curvepoint_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `curvepoint_seq`
--

LOCK TABLES `curvepoint_seq` WRITE;
INSERT INTO `curvepoint_seq` VALUES (251);
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating` (
  `id` int NOT NULL,
  `moodys_rating` varchar(125) DEFAULT NULL,
  `sand_p_rating` varchar(125) DEFAULT NULL,
  `fitch_rating` varchar(125) DEFAULT NULL,
  `order_number` int DEFAULT NULL,
  `sandprating` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
INSERT INTO `rating` VALUES (2,'abcd',NULL,'hiji',120,'efgo');
UNLOCK TABLES;

--
-- Table structure for table `rating_seq`
--

DROP TABLE IF EXISTS `rating_seq`;
CREATE TABLE `rating_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rating_seq`
--

LOCK TABLES `rating_seq` WRITE;
INSERT INTO `rating_seq` VALUES (201);
UNLOCK TABLES;

--
-- Table structure for table `rulename`
--

DROP TABLE IF EXISTS `rulename`;
CREATE TABLE `rulename` (
  `id` int NOT NULL,
  `name` varchar(125) DEFAULT NULL,
  `description` varchar(125) DEFAULT NULL,
  `json` varchar(125) DEFAULT NULL,
  `template` varchar(512) DEFAULT NULL,
  `sql_str` varchar(125) DEFAULT NULL,
  `sql_part` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rulename`
--

LOCK TABLES `rulename` WRITE;
INSERT INTO `rulename` VALUES (1,'Dupond','Ma description','{de}','template','select id from ruleName','from'),(2,'Dupont','Autre description','{}','ruleName','select id from ruleName','select id');
UNLOCK TABLES;

--
-- Table structure for table `rulename_seq`
--

DROP TABLE IF EXISTS `rulename_seq`;
CREATE TABLE `rulename_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `rulename_seq`
--

LOCK TABLES `rulename_seq` WRITE;
INSERT INTO `rulename_seq` VALUES (201);
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade` (
  `id` int NOT NULL,
  `account` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `buy_quantity` double DEFAULT NULL,
  `sell_quantity` double DEFAULT NULL,
  `buy_price` double DEFAULT NULL,
  `sell_price` double DEFAULT NULL,
  `trade_date` timestamp NULL DEFAULT NULL,
  `security` varchar(125) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `trader` varchar(125) DEFAULT NULL,
  `benchmark` varchar(125) DEFAULT NULL,
  `book` varchar(125) DEFAULT NULL,
  `creation_name` varchar(125) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `revision_name` varchar(125) DEFAULT NULL,
  `revision_date` timestamp NULL DEFAULT NULL,
  `deal_name` varchar(125) DEFAULT NULL,
  `deal_type` varchar(125) DEFAULT NULL,
  `source_list_id` varchar(125) DEFAULT NULL,
  `side` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
INSERT INTO `trade` VALUES (1,'12','type2',1.1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
UNLOCK TABLES;

--
-- Table structure for table `trade_seq`
--

DROP TABLE IF EXISTS `trade_seq`;
CREATE TABLE `trade_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `trade_seq`
--

LOCK TABLES `trade_seq` WRITE;
INSERT INTO `trade_seq` VALUES (201);
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(125) NOT NULL,
  `password` varchar(125) NOT NULL,
  `fullname` varchar(125) NOT NULL,
  `role` varchar(125) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'admin','$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa','Administrator','ADMIN'),(2,'user','$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa','User','USER'),(3,'alexandre','$2a$10$EHlWuDqRPXdXEYHUvAuqjuE0byG9KyB5YBGuUmQbyF2rPD31gK3KO','Alexandre','ADMIN'),(4,'paul','$2a$10$Q0ZrFxqa1K7LuEv2rxmhnOCNFkLobnGMYx8fZ75iPJ3L0544VTKCq','Paul','USER');
UNLOCK TABLES;

--
-- Table structure for table `users_seq`
--

DROP TABLE IF EXISTS `users_seq`;
CREATE TABLE `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users_seq`
--

LOCK TABLES `users_seq` WRITE;
INSERT INTO `users_seq` VALUES (351);
UNLOCK TABLES;



-- Dump completed on 2025-02-04 17:45:51
