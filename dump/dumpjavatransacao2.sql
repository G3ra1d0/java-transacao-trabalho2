-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: java-transacao-trabalho2
-- ------------------------------------------------------
-- Server version	5.7.21-1

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
  `ultimacompra_cli` datetime NOT NULL,
  PRIMARY KEY (`codigo_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_produto`
--

LOCK TABLES `pedido_produto` WRITE;
/*!40000 ALTER TABLE `pedido_produto` DISABLE KEYS */;
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
  PRIMARY KEY (`codigo_prod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
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
  `tipo_prodmov` varchar(1) NOT NULL,
  `data_prodmov` datetime NOT NULL,
  `descricao_prodmov` varchar(45) DEFAULT NULL,
  `codproduto_prodmov` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_prodmov`),
  KEY `codproduto_prodmov` (`codproduto_prodmov`),
  CONSTRAINT `produto_movimento_ibfk_1` FOREIGN KEY (`codproduto_prodmov`) REFERENCES `produto` (`codigo_prod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_movimento`
--

LOCK TABLES `produto_movimento` WRITE;
/*!40000 ALTER TABLE `produto_movimento` DISABLE KEYS */;
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
  `nome_vned` varchar(45) NOT NULL,
  `porcentualcomissao_vend` decimal(3,2) NOT NULL,
  PRIMARY KEY (`codigo_vend`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor`
--

LOCK TABLES `vendedor` WRITE;
/*!40000 ALTER TABLE `vendedor` DISABLE KEYS */;
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
  CONSTRAINT `vendedor_comissao_ibfk_1` FOREIGN KEY (`codpedido_vendcom`) REFERENCES `pedido` (`codigo_ped`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedor_comissao`
--

LOCK TABLES `vendedor_comissao` WRITE;
/*!40000 ALTER TABLE `vendedor_comissao` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendedor_comissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'java-transacao-trabalho2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-06 20:13:41
