-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: db_commodity_manage
-- ------------------------------------------------------
-- Server version	8.0.19

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

DROP DATABASE IF EXISTS `db_commodity_manage`;
CREATE DATABASE `db_commodity_manage`;
USE `db_commodity_manage`;

--
-- Table structure for table `tb_commodity`
--

DROP TABLE IF EXISTS `tb_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_commodity` (
  `commodity_id` int NOT NULL AUTO_INCREMENT COMMENT '主键 商品id',
  `commodity_name` varchar(45) NOT NULL COMMENT '商品名称',
  `commodity_type` varchar(45) NOT NULL COMMENT '商品类型',
  `commodity_no` varchar(45) NOT NULL COMMENT '商品编号',
  `commodity_desc` varchar(128) NOT NULL COMMENT '商品介绍',
  `price` double NOT NULL COMMENT '价格',
  `discount_price` double DEFAULT NULL COMMENT '促销价(可为空)',
  `stock` int NOT NULL COMMENT '库存',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0 未上架 1 上架',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 1 已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`commodity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_commodity`
--

LOCK TABLES `tb_commodity` WRITE;
/*!40000 ALTER TABLE `tb_commodity` DISABLE KEYS */;
INSERT INTO `tb_commodity` VALUES (1,'奥利奥','食品','CN206421681742','夹心饼干',5,3.5,95,1,0,'2022-05-01 10:55:11','2022-05-02 12:45:58'),(2,'喜之郎果冻','食品','CN206421699035','水果味果冻',1.5,NULL,994,1,0,'2022-05-01 10:55:11','2022-05-02 13:33:27'),(3,'雕牌洗衣服','日用品','CN206421708315','洗衣服必备',15,12.8,86,1,0,'2022-05-01 10:55:11','2022-05-02 13:26:12'),(4,'猫山王榴莲','水果','CN206421855587','榴莲之王',199,NULL,31,1,1,'2022-05-01 11:14:47','2022-05-02 15:03:30'),(5,'精选食盐','调料','CN206421867586','烹饪必备',3,2.8,198,1,0,'2022-05-01 11:17:14','2022-05-02 13:33:27'),(6,'旺旺雪饼','食品','CN206424181495','老少皆宜的零食，咔吱咔吱脆',12.8,9.9,497,1,0,'2022-05-01 16:24:11','2022-05-02 13:32:45'),(7,'水蜜桃','食品','CN206434383494','水嘟嘟的水蜜桃',12,9.9,80,0,0,'2022-05-02 15:04:27','2022-05-02 15:04:27');
/*!40000 ALTER TABLE `tb_commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_commodity_car`
--

DROP TABLE IF EXISTS `tb_commodity_car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_commodity_car` (
  `car_id` int NOT NULL AUTO_INCREMENT COMMENT '主键 序号',
  `car_no` varchar(45) NOT NULL,
  `user_id` int NOT NULL COMMENT '所属用户Id',
  `commodity_id` int NOT NULL COMMENT '商品Id',
  `num` int NOT NULL COMMENT '数量',
  `unit_price` double NOT NULL COMMENT '单价',
  `total_price` double NOT NULL COMMENT '总价',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 1 已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_commodity_car`
--

LOCK TABLES `tb_commodity_car` WRITE;
/*!40000 ALTER TABLE `tb_commodity_car` DISABLE KEYS */;
INSERT INTO `tb_commodity_car` VALUES (1,'CCN137622950705',10,5,9,2.8,25.2,1,'2022-05-01 21:29:44','2022-05-02 01:13:44'),(2,'CCN137622950711',10,1,3,3.5,10.5,1,'2022-05-02 09:51:48','2022-05-02 09:51:48'),(3,'CCN137622950706',10,4,1,199,199,1,'2022-05-02 09:56:01','2022-05-02 09:56:01'),(4,'CCN137622950710',10,1,1,3.5,3.5,1,'2022-05-02 10:02:06','2022-05-02 10:02:06'),(5,'CCN137622950711',10,1,2,3.5,7,1,'2022-05-02 12:45:55','2022-05-02 12:45:58'),(6,'CCN137622950702',10,2,1,1.5,1.5,1,'2022-05-02 12:48:21','2022-05-02 12:48:21'),(7,'CCN137622950706',10,2,1,1.5,1.5,1,'2022-05-02 12:48:25','2022-05-02 12:48:25'),(8,'CCN137622950705',10,2,2,1.5,3,1,'2022-05-02 12:48:55','2022-05-02 12:49:00'),(9,'CCN137622950707',10,3,2,12.8,25.6,1,'2022-05-02 12:55:30','2022-05-02 13:25:33'),(11,'CCN137622950710',10,2,1,1.5,1.5,1,'2022-05-02 13:24:19','2022-05-02 13:24:19'),(12,'CCN137622428777',10,6,1,9.9,9.9,1,'2022-05-02 13:25:45','2022-05-02 13:25:45'),(13,'CCN137622463281',10,6,1,9.9,9.9,1,'2022-05-02 13:32:39','2022-05-02 13:32:39'),(14,'CCN137622466071',10,5,1,2.8,2.8,1,'2022-05-02 13:33:12','2022-05-02 13:33:12'),(15,'CCN137622466634',10,2,1,1.5,1.5,1,'2022-05-02 13:33:19','2022-05-02 13:33:19'),(16,'CCN137622935312',10,6,1,9.9,9.9,0,'2022-05-02 15:07:03','2022-05-02 15:07:03');
/*!40000 ALTER TABLE `tb_commodity_car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pay_order`
--

DROP TABLE IF EXISTS `tb_pay_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pay_order` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '主键 订单号',
  `user_id` int NOT NULL COMMENT '所属用户Id',
  `order_no` varchar(45) NOT NULL COMMENT '订单编号',
  `order_price` double NOT NULL COMMENT '订单价钱',
  `pay_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 1 已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pay_order`
--

LOCK TABLES `tb_pay_order` WRITE;
/*!40000 ALTER TABLE `tb_pay_order` DISABLE KEYS */;
INSERT INTO `tb_pay_order` VALUES (4,10,'PON1651425232792',25.2,'2022-05-02 01:13:44',0,'2022-05-02 01:13:44',NULL),(5,10,'PON1651456343557',10.5,'2022-05-02 09:52:16',0,'2022-05-02 09:52:16',NULL),(6,10,'PON1651456561305',199,'2022-05-02 09:56:01',1,'2022-05-02 09:56:01',NULL),(7,10,'PON1651465436028',3.5,'2022-05-02 12:23:52',0,'2022-05-02 12:23:52',NULL),(9,10,'PON1651466767777',7,'2022-05-02 12:45:58',0,'2022-05-02 12:45:58',NULL),(10,10,'PON1651466906083',1.5,'2022-05-02 12:48:21',0,'2022-05-02 12:48:21',NULL),(11,10,'PON1651466931727',1.5,'2022-05-02 12:48:43',0,'2022-05-02 12:48:43',NULL),(12,10,'PON1651466950848',3,'2022-05-02 12:49:00',0,'2022-05-02 12:49:00',NULL),(13,10,'PON1651469062358',1.5,'2022-05-02 13:24:19',0,'2022-05-02 13:24:19',NULL),(14,10,'PON1651469177017',35.5,'2022-05-02 13:26:12',0,'2022-05-02 13:26:12',NULL),(15,10,'PON1651469570262',9.9,'2022-05-02 13:32:45',0,'2022-05-02 13:32:45',NULL),(16,10,'PON1651469615887',4.3,'2022-05-02 13:33:27',0,'2022-05-02 13:33:27',NULL);
/*!40000 ALTER TABLE `tb_pay_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pay_order_relation`
--

DROP TABLE IF EXISTS `tb_pay_order_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pay_order_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int NOT NULL COMMENT '订单Id',
  `combo_id` int DEFAULT NULL COMMENT '套餐Id',
  `car_id` int DEFAULT NULL COMMENT '商品购物车Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单信息关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pay_order_relation`
--

LOCK TABLES `tb_pay_order_relation` WRITE;
/*!40000 ALTER TABLE `tb_pay_order_relation` DISABLE KEYS */;
INSERT INTO `tb_pay_order_relation` VALUES (2,4,NULL,1),(3,5,NULL,2),(4,6,NULL,3),(7,7,NULL,4),(8,11,NULL,7),(9,14,NULL,12),(10,14,NULL,9),(11,15,NULL,13),(12,16,NULL,15),(13,16,NULL,14);
/*!40000 ALTER TABLE `tb_pay_order_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键 用户Id',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `nick_name` varchar(45) NOT NULL COMMENT '用户昵称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `phone_number` varchar(11) NOT NULL COMMENT '手机号码',
  `user_role` tinyint(1) NOT NULL DEFAULT '2' COMMENT '用户角色 ：1 管理员 2 普通用户',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除：0 未删除 1 已删除',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'zhangsan','张三','123456','15915640611',2,'第一个用户',0,'2022-04-16 16:22:42','2022-04-16 16:22:42'),(2,'zhangsanfeng','张三丰','qwerty','13226879809',1,'武林大侠',0,'2022-04-17 13:54:56','2022-04-30 18:46:29'),(3,'luohao','罗灏','123456','13059381566',1,'大佬',0,'2022-04-30 21:31:17','2022-04-30 21:31:17'),(4,'admin','admin','admin','13047865438',1,'创始人',0,'2022-04-30 21:31:17','2022-04-30 21:31:17'),(5,'tom','汤姆','tom123','13713640611',2,'喵喵喵',0,'2022-04-30 21:31:17','2022-04-30 21:31:17'),(6,'szpt','深职院','szpt789','15915672648',2,NULL,0,'2022-04-30 21:31:17','2022-04-30 21:31:17'),(7,'kity','HelloKity','kity456','13227974209',2,NULL,0,'2022-04-30 21:37:16','2022-04-30 21:37:16'),(8,'panda','胖达','china666','15913642685',2,'国宝',1,'2022-04-30 21:37:16','2022-04-30 21:37:16'),(9,'luckyGirl','幸运女孩','123456','13072485049',2,'',0,'2022-05-01 00:25:43','2022-05-01 00:25:43'),(10,'badBoy','坏小孩','123456','13268714032',2,'',0,'2022-05-01 00:28:43','2022-05-01 00:28:43'),(11,'Jack','杰克','123456','13713640897',2,'',0,'2022-05-01 09:02:39','2022-05-01 09:02:39'),(12,'luoke','小洛克','qwerty','15917995383',2,'',0,'2022-05-01 09:08:33','2022-05-01 09:08:33');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-02 15:35:25
