-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: i6c210.p.ssafy.io    Database: sunin
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint NOT NULL,
  `message` varchar(255) NOT NULL,
  `to_user_id` bigint NOT NULL,
  `local_date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=920 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follower`
--

DROP TABLE IF EXISTS `follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follower` (
  `follower_id` bigint NOT NULL AUTO_INCREMENT,
  `created_datetime` datetime DEFAULT NULL,
  `follower_member` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`follower_id`),
  KEY `FKrutjo10gecwcxs5lfmt75o112` (`follower_member`),
  KEY `FKr99xseniq62g6519wn3i3duak` (`user_id`),
  CONSTRAINT `FKr99xseniq62g6519wn3i3duak` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_seq`),
  CONSTRAINT `FKrutjo10gecwcxs5lfmt75o112` FOREIGN KEY (`follower_member`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=643 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follower`
--

LOCK TABLES `follower` WRITE;
/*!40000 ALTER TABLE `follower` DISABLE KEYS */;
INSERT INTO `follower` VALUES (43,'2022-02-13 16:56:10',2,3),(44,'2022-02-13 17:06:42',3,3),(45,'2022-02-13 17:06:44',4,3),(53,'2022-02-15 06:48:58',3,31),(54,'2022-02-15 06:50:17',31,3),(90,'2022-02-13 17:06:44',4,1),(274,'2022-02-15 11:21:16',29,13),(275,'2022-02-15 11:21:16',29,12),(277,'2022-02-13 16:56:10',2,1),(281,'2022-02-13 16:55:53',1,2),(347,'2022-02-14 06:47:33',23,2),(349,'2022-02-13 19:39:41',12,23),(355,'2022-02-13 17:06:42',3,12),(381,'2022-02-13 17:06:44',4,2),(383,'2022-02-13 16:56:10',2,23),(390,'2022-02-13 16:55:53',1,3),(396,'2022-02-13 16:56:10',2,4),(420,'2022-02-13 19:39:41',12,3),(433,'2022-02-14 06:47:33',23,3),(434,'2022-02-13 17:06:42',3,23),(493,'2022-02-13 17:06:42',3,19),(497,'2022-02-13 17:06:44',4,19),(504,'2022-02-14 06:47:33',23,112),(590,'2022-02-13 17:06:44',4,23),(620,'2022-02-17 13:28:11',111,23),(621,'2022-02-14 06:47:33',23,115),(622,'2022-02-14 06:47:33',23,4),(623,'2022-02-13 16:55:53',1,115),(634,'2022-02-14 06:47:33',23,1),(638,'2022-02-13 16:55:53',1,19);
/*!40000 ALTER TABLE `follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_seq` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `email` varchar(512) DEFAULT NULL,
  `email_verified_yn` varchar(1) NOT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `modified_at` datetime NOT NULL,
  `password` varchar(128) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(512) NOT NULL,
  `provider_type` varchar(20) NOT NULL,
  `role_type` varchar(20) NOT NULL,
  `sunin_days` int NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'서울','2022-02-13 16:55:53','aff4110@gmail.com','Y','안녕하세요','2022-02-13 16:55:53','NO_PASS','01086209061','https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/baf7bed1-f0ed-42a1-af8b-5f17cba84033.jpg','NAVER','USER',1,'oTsxiUAlPDUPS3jFUpgzea9HR4Wi8_cAeOiIX623w4Y','오렝지맛','aff****'),(2,NULL,'2022-02-13 16:56:10','NO_EMAIL','Y',NULL,'2022-02-13 16:56:10','NO_PASS',NULL,'http://k.kakaocdn.net/dn/cz3dXv/btroQ54Hj01/Q9ilyDTa253CEDprVsNXtK/img_110x110.jpg','KAKAO','USER',0,'2094647241','김희섭','김희섭'),(3,NULL,'2022-02-13 17:06:42','nutmy0236@gmail.com','Y',NULL,'2022-02-13 17:06:42','NO_PASS',NULL,'https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/1eb5bce1-37c8-4694-9fbe-d5bba8e1c370.jpeg','GOOGLE','USER',0,'110294460965107919471','김경민','김경민'),(4,NULL,'2022-02-13 17:06:44','goodevson@gmail.com','Y',NULL,'2022-02-13 17:06:44','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJy2ew9j8WhIJa8zgJNfBOzczeQQ4LVI2R88T-_N=s96-c','GOOGLE','USER',3,'107160356698106208026','손민기','손민기'),(19,NULL,'2022-02-13 20:48:55','nutmy52@gmail.com','Y',NULL,'2022-02-13 20:48:55','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJz74ZFOHSx4SPqwYUJRTYD-iXY8H4zZiFqwXdbJ=s96-c','GOOGLE','USER',0,'111019020986969337220','두치','두치'),(23,'null','2022-02-14 06:47:33','hanuirang5@gmail.com','Y','안녕하세요','2022-02-14 06:47:33','NO_PASS','null','https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/87805869-3809-4eb9-84c7-3082b49d4552.png','GOOGLE','USER',54,'107426481683178676704','하루','하루'),(26,'null','2022-02-14 20:35:06','akdsidalsyd1@gmail.com','Y','안녕하세요','2022-02-14 20:35:06','NO_PASS','null','https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/eee0c0e2-22ae-428e-a3d0-a97e71e4aa20.jpg','GOOGLE','USER',2,'116381220881389669382','윤진','윤진'),(28,NULL,'2022-02-15 10:51:27','happyvirusjae@gmail.com','Y',NULL,'2022-02-15 10:51:27','NO_PASS',NULL,'https://lh3.googleusercontent.com/a-/AOh14GhfXFcU1v-vuVJoaxQIg4PQ4F-oocAi00NVPK4V=s96-c','GOOGLE','USER',0,'116410628034213849795','하루','하루'),(29,'null','2022-02-15 11:21:16','jiaiha@naver.com','Y','안녕하세요','2022-02-15 11:21:16','NO_PASS','null','https://sunin-bucket.s3.ap-northeast-2.amazonaws.com/24e29dcd-e2ed-4587-9511-280a58881cf9.jpg','NAVER','USER',0,'IDiJUqsqTvRm-iFi76IbG4Yae3-vY1C-noohdW1nANQ','오복이','하루'),(31,NULL,'2022-02-15 11:31:22','nutmy52@naver.com','Y',NULL,'2022-02-15 11:31:22','NO_PASS',NULL,'https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER',0,'H-eAql8k-PkatVOTj_jRzctBEPtWLzXmmYm0nhy_ap0','김경민','김경민'),(60,NULL,'2022-02-15 16:42:22','nutmy752@gmail.com','Y',NULL,'2022-02-15 16:42:22','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJxOEg5J8tjkjUjDDO1hJ-0AagrLJzl3oJOy6UEy=s96-c','GOOGLE','USER',0,'109665576754625609456','김경민','김경민'),(71,NULL,'2022-02-17 10:48:45','funnyjin710@gmail.com','Y',NULL,'2022-02-17 10:48:45','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJywlhSO7PUnDJ3qexGbGAQZZVlH5nuPIXTxSr9l=s96-c','GOOGLE','USER',0,'100878876151898161286','오윤진','오윤진'),(74,NULL,'2022-02-17 11:02:55','dbswlshi@naver.com','Y',NULL,'2022-02-17 11:02:55','NO_PASS',NULL,'https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER',0,'SchZR54NbsntnQJ8QXnnvpMFJurFZYxKsC5GtTm3joo','윤진','윤진'),(87,NULL,'2022-02-17 11:11:52','tngh5220@naver.com','Y',NULL,'2022-02-17 11:11:52','NO_PASS',NULL,'https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER',0,'LpFYu6AHFF7L464XXBT_Lnaln6R5fQMrOxxBhiAF5d4','히히','히히'),(97,NULL,'2022-02-17 11:22:44','aff2110@gmail.com','Y',NULL,'2022-02-17 11:22:44','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJzGCNl59F4fP-2L--aBLtXJVuSUDV39OjTzjI29=s96-c','GOOGLE','USER',0,'114371345494156572709','유진희','유진희'),(100,NULL,'2022-02-17 11:30:09','cheery7272@naver.com','Y',NULL,'2022-02-17 11:30:09','NO_PASS',NULL,'https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER',0,'cskwqeO04hWoNOs51H8ZV_29jLN6hDeM3GeABsye6uU','che','che'),(105,NULL,'2022-02-17 11:35:48','NO_EMAIL','Y',NULL,'2022-02-17 11:35:48','NO_PASS',NULL,'','KAKAO','USER',0,'2115336643','김경민','김경민'),(106,NULL,'2022-02-17 11:36:12','NO_EMAIL','Y',NULL,'2022-02-17 11:36:12','NO_PASS',NULL,'http://k.kakaocdn.net/dn/DMGCe/btrrjAOfPxn/USv01nLxQXCDA3HFZQfe30/img_110x110.jpg','KAKAO','USER',0,'2123458750','방울','방울'),(107,NULL,'2022-02-17 11:36:30','aff4110@gmail.com','Y',NULL,'2022-02-17 11:36:30','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJyC8As9YlEzWbvFOfI39bJ-EDi_fqNzQEobjQcS=s96-c','GOOGLE','USER',0,'102629039878104857053','김희섭','김희섭'),(108,NULL,'2022-02-17 11:37:07','NO_EMAIL','Y',NULL,'2022-02-17 11:37:07','NO_PASS',NULL,'http://k.kakaocdn.net/dn/cscWT7/btrsyFub5oh/MfIvHep6peqOnzunIH5SIk/img_110x110.jpg','KAKAO','USER',0,'2120913053','지애','지애'),(112,NULL,'2022-02-17 14:08:24','megahawk88@gmail.com','Y',NULL,'2022-02-17 14:08:24','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJycYCClB2Inuqs06ITuqjiRH-wPfrEmzhzydvACzA=s96-c','GOOGLE','USER',0,'110323146446838766833','이상현','이상현'),(115,NULL,'2022-02-17 18:55:16','choidawoon960717@gmail.com','Y',NULL,'2022-02-17 18:55:16','NO_PASS',NULL,'https://lh3.googleusercontent.com/a/AATXAJwH_7LSjJ7HrZZkPw5dFkpxzDtfwlfbpbMuVnDV=s96-c','GOOGLE','USER',1,'104934551037160342887','최다운','최다운'),(116,NULL,'2022-02-17 20:30:28','choidawoon96@naver.com','Y',NULL,'2022-02-17 20:30:28','NO_PASS',NULL,'https://ssl.pstatic.net/static/pwe/address/img_profile.png','NAVER','USER',0,'biN9xcw9JH7c7ZfX2DXALe3m7iK1OjAP7kI8OFbqjGw','다운1996','다운1996');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_refresh_token`
--

DROP TABLE IF EXISTS `user_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_refresh_token` (
  `refresh_token_seq` bigint NOT NULL AUTO_INCREMENT,
  `refresh_token` varchar(256) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  PRIMARY KEY (`refresh_token_seq`),
  UNIQUE KEY `UK_qca3mjxv5a1egwmn4wnbplfkt` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_refresh_token`
--

LOCK TABLES `user_refresh_token` WRITE;
/*!40000 ALTER TABLE `user_refresh_token` DISABLE KEYS */;
INSERT INTO `user_refresh_token` VALUES (1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM0Mzc1M30.DKLa39GXBRnhQenrrBRRaDtU8immCZfaUu3Ko-pd8hQ','oTsxiUAlPDUPS3jFUpgzea9HR4Wi8_cAeOiIX623w4Y'),(2,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM0Mzc3MH0.Fj4WE-1zUW6_AD2V-fh1JHvBqg1eTef-jQH-p_KWag8','2094647241'),(3,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM0NDQwMX0.ZG6lRpNTAx6VWe-A6eM2BV5WcmOqy1N6hJYwsybXSE0','110294460965107919471'),(4,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM0NDQwM30.4kJp-YTcOmUogsYiwp7wjeBPaAhu5uCf_d_hGgCpr8o','107160356698106208026'),(5,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM1MzU4MH0.2cU0Owa_rULKCcxbPiiqeI9q1qWdAnukOJk9b4x-Lbo','104934551037160342887'),(6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM1NDA4NX0.MyTTsQorZKxJDQyDRJmP_3KwoI-kmzQwKLVBOc0t5JA','biN9xcw9JH7c7ZfX2DXALe3m7iK1OjAP7kI8OFbqjGw'),(7,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM1NzczNH0.aPhzlqxOvXLY_okzUTEWi4uKoLBiPfDuRSJgRcO3JHU','111019020986969337220'),(8,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTM5MzY1Mn0.IyCmVe7tAu3RoN2paKrrpngBQkHOnhb1YVk62RAlfr0','107426481683178676704'),(9,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTQ0MzMwNn0.HJiqhbrDwKHq9ljyL-6KtW8O5VDExgvFciEptgVpz2k','116381220881389669382'),(10,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTQ5NDY4N30.SeZUQ_lqzslqr5Upu-k95wmPXH4l6FjzzaXaG44KHjA','116410628034213849795'),(11,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTQ5NjQ3Nn0.b_fNQeRpcYyuzj1rWFuIma75HVUi65nGVYMVh7CyUaM','IDiJUqsqTvRm-iFi76IbG4Yae3-vY1C-noohdW1nANQ'),(12,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTQ5NzA4Mn0.Xj48W1m4KpH3PaOQGQcpHfqVm8vSCEX0T3V1FLA1Tbc','H-eAql8k-PkatVOTj_jRzctBEPtWLzXmmYm0nhy_ap0'),(13,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTUxNTc0Mn0.1htWDLbCJw1NSjIYKOY-THeyDRhZLEKjPFuBMwOr1zM','109665576754625609456'),(14,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY2ODcxMn0.vQS82GKUz5EYhxeMcC4GF4udDJjxywpOiyfMz55g-N8','LpFYu6AHFF7L464XXBT_Lnaln6R5fQMrOxxBhiAF5d4'),(15,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY2OTM2NH0.ly0xs85uMcmokRri_ifS4uHeVUCqjeSUhBVgTK2rELo','114371345494156572709'),(16,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY2OTgwOH0.Gng_9w_9UxKt-eOilrVY_Wbyl4J7UJbssY4Pn0Z-O7g','cskwqeO04hWoNOs51H8ZV_29jLN6hDeM3GeABsye6uU'),(17,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY3MDE0N30.VDBFTO411evl7pMi1cVRExy0--U2jhr9tU1NA1v4Cq8','2115336643'),(18,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY3MDE3MX0.UnkdKthvBULOyBnWi0OZGNlBm41myBHAX_6PGEosCLY','2123458750'),(19,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY3MDE5MH0.Swgp8qMQEylCwpw8WG50IF41r9nHAmn8bScVTaD0L28','102629039878104857053'),(20,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY3MDIyNn0.omtk_h2xGvukvVKE0KhGdVNqS2PiDn1x-jO9Nz8EqeA','2120913053'),(21,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY3OTMwM30._ZFHjfw-uXGswz_99sMIDy4niDpw10oK_VyHqNaRNEA','110323146446838766833'),(22,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MjZEOTZDOTAwMzBERDU4NDI5RDI3NTFBQzFCREJCQyIsImV4cCI6MTY0NTY4OTM1M30.TxKuwk2wMALh44NDQw8netwcJQCWCPKgmSVb_Hx1JSU','100878876151898161286');
/*!40000 ALTER TABLE `user_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sunin'
--

--
-- Dumping routines for database 'sunin'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 11:54:31
