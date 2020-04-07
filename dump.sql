DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `version` varchar(45) DEFAULT NULL,
  `createtime` varchar(45) DEFAULT NULL,
  `modifytime` varchar(45) DEFAULT NULL,
  `publish` varchar(45) DEFAULT NULL,
  `application` varchar(45) DEFAULT NULL,
  `secret` varchar(45) DEFAULT NULL,
  `cipher` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `qrid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hardware`
--

DROP TABLE IF EXISTS `hardware`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hardware` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `factory` varchar(45) CHARACTER SET armscii8 COLLATE armscii8_general_ci DEFAULT NULL,
  `support` varchar(45) DEFAULT NULL,
  `buytime` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `instruction` varchar(45) DEFAULT NULL,
  `record` varchar(45) DEFAULT NULL,
  `intime` varchar(45) DEFAULT NULL,
  `qrid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hardware`
--

LOCK TABLES `hardware` WRITE;
/*!40000 ALTER TABLE `hardware` DISABLE KEYS */;
/*!40000 ALTER TABLE `hardware` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `software`
--

DROP TABLE IF EXISTS `software`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `software` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `version` varchar(45) DEFAULT NULL,
  `publishtime` varchar(45) DEFAULT NULL,
  `function` varchar(45) DEFAULT NULL,
  `relation` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `instruction` varchar(45) DEFAULT NULL,
  `qrid` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `software`
--

LOCK TABLES `software` WRITE;
/*!40000 ALTER TABLE `software` DISABLE KEYS */;
/*!40000 ALTER TABLE `software` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2020-03-30  9:43:19
