-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: java-transacao-trabalho2
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `codigo_cli` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_cli` varchar(45) NOT NULL,
  `cpf_cli` varchar(14) NOT NULL,
  `ultimacompra_cli` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo_cli`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,'Mateus Cirino','0808080','2020-04-12 01:09:21');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `codigo_ped` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data_ped` datetime NOT NULL,
  `obs_ped` varchar(255) DEFAULT NULL,
  `codcliente_ped` int(10) unsigned NOT NULL,
  `codvendedor_ped` int(10) unsigned NOT NULL,
  PRIMARY KEY (`codigo_ped`),
  KEY `codcliente_ped` (`codcliente_ped`),
  KEY `codvendedor_ped` (`codvendedor_ped`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`codcliente_ped`) REFERENCES `cliente` (`codigo_cli`),
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`codvendedor_ped`) REFERENCES `vendedor` (`codigo_vend`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,'2020-04-11 20:26:30','Meu segundo teste simulando uma view',2,1),(3,'2020-04-12 00:55:33','Massa',2,1),(4,'2020-04-12 00:58:51','',2,1),(5,'2020-04-12 01:09:21','jacinto',2,1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_produto`
--

DROP TABLE IF EXISTS `pedido_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_produto` (
  `id_pedprod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quantidade_pedprod` decimal(18,2) NOT NULL,
  `valor_pedprod` decimal(18,2) NOT NULL,
  `valortotal_pedprod` decimal(18,2) NOT NULL,
  `codproduto_pedprod` int(10) unsigned NOT NULL,
  `codpedido_pedprod` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_pedprod`),
  KEY `codproduto_pedprod` (`codproduto_pedprod`),
  KEY `codpedido_pedprod` (`codpedido_pedprod`),
  CONSTRAINT `pedido_produto_ibfk_1` FOREIGN KEY (`codproduto_pedprod`) REFERENCES `produto` (`codigo_prod`),
  CONSTRAINT `pedido_produto_ibfk_2` FOREIGN KEY (`codpedido_pedprod`) REFERENCES `pedido` (`codigo_ped`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_produto`
--

LOCK TABLES `pedido_produto` WRITE;
/*!40000 ALTER TABLE `pedido_produto` DISABLE KEYS */;
INSERT INTO `pedido_produto` VALUES (6,0.10,2.00,0.20,3,2),(7,0.10,44.00,4.40,4,2),(8,0.10,200.00,20.00,5,2),(9,0.10,30.00,3.00,6,2),(10,0.10,1.00,0.10,7,2),(11,2.00,2.00,4.00,3,3),(12,1.00,44.00,44.00,4,3),(13,1.00,30.00,30.00,6,4),(14,1.00,1.00,1.00,7,4),(15,3.00,2.00,6.00,3,4),(16,0.40,1.00,0.40,7,5),(17,0.40,30.00,12.00,6,5),(18,0.40,200.00,80.00,5,5);
/*!40000 ALTER TABLE `pedido_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `codigo_prod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao_prod` varchar(45) NOT NULL,
  `saldo_prod` decimal(12,2) NOT NULL,
  `unidade_prod` varchar(2) NOT NULL,
  `preco_prod` double NOT NULL,
  PRIMARY KEY (`codigo_prod`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (3,'Feijao',4.40,'Kg',2),(4,'Banana',0.90,'kg',44),(5,'Peru',1.00,'Kg',200),(6,'Peru',0.00,'Kg',30),(7,'Feijao',0.50,'Kg',1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_movimento`
--

DROP TABLE IF EXISTS `produto_movimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_movimento` (
  `id_prodmov` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tipo_prodmov` enum('E','S') NOT NULL,
  `data_prodmov` datetime NOT NULL,
  `descricao_prodmov` varchar(45) DEFAULT NULL,
  `codproduto_prodmov` int(10) unsigned NOT NULL,
  `quantidade_prodmov` double NOT NULL,
  PRIMARY KEY (`id_prodmov`),
  KEY `codproduto_prodmov` (`codproduto_prodmov`),
  CONSTRAINT `produto_movimento_ibfk_1` FOREIGN KEY (`codproduto_prodmov`) REFERENCES `produto` (`codigo_prod`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_movimento`
--

LOCK TABLES `produto_movimento` WRITE;
/*!40000 ALTER TABLE `produto_movimento` DISABLE KEYS */;
INSERT INTO `produto_movimento` VALUES (21,'S','2020-04-10 23:08:16','Saída',3,1),(22,'S','2020-04-10 23:08:16','Saída',4,1),(23,'S','2020-04-10 23:08:16','Saída',5,1),(24,'S','2020-04-10 23:08:16','Saída',6,1),(25,'S','2020-04-10 23:08:16','Saída',7,1),(26,'E','2020-04-10 23:44:03','Entrada',3,1),(27,'E','2020-04-10 23:44:03','Entrada',4,1),(28,'E','2020-04-10 23:44:03','Entrada',5,1),(29,'E','2020-04-10 23:44:03','Entrada',6,1),(30,'E','2020-04-10 23:44:03','Entrada',7,1),(31,'S','2020-04-11 19:46:01','Saída',3,1),(32,'S','2020-04-11 19:46:01','Saída',4,1),(33,'S','2020-04-11 19:46:01','Saída',5,1),(34,'S','2020-04-11 19:46:01','Saída',6,1),(35,'S','2020-04-11 19:46:01','Saída',7,1),(36,'S','2020-04-11 20:26:30','Saída',3,0.1),(37,'S','2020-04-11 20:26:30','Saída',4,0.1),(38,'S','2020-04-11 20:26:30','Saída',5,0.1),(39,'S','2020-04-11 20:26:30','Saída',6,0.1),(40,'S','2020-04-11 20:26:30','Saída',7,0.1),(41,'E','2020-04-11 20:42:57','Entrada',3,1),(42,'E','2020-04-11 20:42:57','Entrada',4,1),(43,'E','2020-04-11 20:42:57','Entrada',5,1),(44,'E','2020-04-11 20:42:57','Entrada',6,1),(45,'E','2020-04-11 20:42:57','Entrada',7,1),(46,'S','2020-04-12 00:55:33','Saída',3,2),(47,'S','2020-04-12 00:55:33','Saída',4,1),(48,'S','2020-04-12 00:58:51','Saída',6,1),(49,'S','2020-04-12 00:58:51','Saída',7,1),(50,'S','2020-04-12 00:58:51','Saída',3,3),(51,'S','2020-04-12 01:09:21','Saída',7,0.4),(52,'S','2020-04-12 01:09:21','Saída',6,0.4),(53,'S','2020-04-12 01:09:21','Saída',5,0.4);
/*!40000 ALTER TABLE `produto_movimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor`
--

DROP TABLE IF EXISTS `vendedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor` (
  `codigo_vend` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_vend` varchar(45) NOT NULL,
  `porcentualcomissao_vend` decimal(3,2) NOT NULL,
  PRIMARY KEY (`codigo_vend`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
INSERT INTO `vendedor` VALUES (1,'Jacinto',0.50);
/*!40000 ALTER TABLE `vendedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedor_comissao`
--

DROP TABLE IF EXISTS `vendedor_comissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendedor_comissao` (
  `id_vendcom` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `porcentualcomissao_vendcom` decimal(3,2) NOT NULL,
  `valorcomissao_vendcom` decimal(18,2) NOT NULL,
  `codvend_vendcom` int(10) unsigned NOT NULL,
  `codpedido_vendcom` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_vendcom`),
  KEY `codpedido_vendcom` (`codpedido_vendcom`),
  KEY `codVendVendedorComissaoVendedor` (`codvend_vendcom`),
  CONSTRAINT `codVendVendedorComissaoVendedor` FOREIGN KEY (`codvend_vendcom`) REFERENCES `vendedor` (`codigo_vend`),
  CONSTRAINT `vendedor_comissao_ibfk_1` FOREIGN KEY (`codpedido_vendcom`) REFERENCES `pedido` (`codigo_ped`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor_comissao`
--

LOCK TABLES `vendedor_comissao` WRITE;
/*!40000 ALTER TABLE `vendedor_comissao` DISABLE KEYS */;
INSERT INTO `vendedor_comissao` VALUES (3,0.50,24.00,1,3),(4,0.50,18.50,1,4),(5,0.50,46.20,1,5);
/*!40000 ALTER TABLE `vendedor_comissao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 16:55:34
