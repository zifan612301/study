/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - book
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `book`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `book_category_id` int(11) NOT NULL COMMENT '图书类别编号',
  `book_name` varchar(100) NOT NULL COMMENT '图书名称',
  `book_synopsis` varchar(300) NOT NULL COMMENT '图书简介',
  `book_picture` varchar(300) NOT NULL COMMENT '图书图片',
  `book_price` double(7,2) NOT NULL COMMENT '图书价格',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_book_user_id` (`user_id`),
  KEY `FK_book_book_category_id` (`book_category_id`),
  CONSTRAINT `FK_book_book_category_id` FOREIGN KEY (`book_category_id`) REFERENCES `book_category` (`id`),
  CONSTRAINT `FK_book_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`user_id`,`book_category_id`,`book_name`,`book_synopsis`,`book_picture`,`book_price`,`is_usable`,`cst_create`,`cst_modify`) values (1,1,38,'小王子手绘本','图书质量很不错，八成新，很适合小朋友看','1f166afa-32b1-4eb8-93ed-0766e5f9625d.PNG#a9c26b98-a68f-4109-b047-eafbd816ddcc.PNG#20870d97-ac99-42c7-8e6b-8f5cac93a827.PNG#61e2de53-1aca-4d6b-9ab3-1ff91faa38e0.PNG',20.30,0,'2019-04-15 12:07:16','2021-04-15 12:07:42'),(2,1,31,'java编程思想第四版','内容很实用，适合想要深入学习java的人使用，书本七成新','cde61e67-75d4-403d-8c85-42889dcee1eb.PNG#7eb46333-63b0-4612-bb62-11664f683dcf.PNG#17b72c4a-a6df-4b9c-9c98-4bd88862d4d1.PNG',50.88,1,'2019-04-15 12:13:17','2021-04-15 12:22:06'),(3,1,1,'中公国家教师资格证考试用书2019','九成新，不准备考了，试卷基本没做','52277f97-2a78-4138-b446-e00f3287ad32.PNG#a739d50e-2c49-45d7-8304-513968b8665d.PNG',60.32,1,'2019-04-15 12:25:03','2021-04-15 12:25:24'),(4,1,12,'概率论与数理统计教程书','书名:概率论与数理统计教程\n\n作者:茆诗松 等编著\n\n出版社：高等教育出版社\n\n出版日期：2004-07-01\n\nISBN：9787040143652\n\n字数：550000\n\n页码：459\n\n版次：1\n\n装帧：平装','24732f5f-3e6f-4547-b285-577de093c53b.jpg',30.40,1,'2019-04-16 13:37:19','2021-04-16 13:37:19'),(5,1,1,'中公2018教师资格证考试中学','正版书籍八成新笔记不多','7ed37c00-1b6d-4e1d-987a-d58ae2d9be68.jpg#b63b2f65-2523-4732-aa7b-04901be39428.jpg#231d781c-3def-43ec-81e3-c9ab9d2280b9.png',30.40,1,'2019-04-16 14:25:38','2021-04-16 14:25:58'),(6,1,8,'四级词汇书新东方乱序','书很好用，携带很方便，陪着我考过了四级','7522eb71-bed9-47e6-aa02-cb59c592254c.jpg',12.50,1,'2019-04-16 16:14:37','2021-04-16 16:14:37'),(7,1,1,'考研英语二基础训练 2020 试卷版',' 包含模拟题+完形填空+阅读理解+作文+翻译分类突破 华研外语','20d77fe3-f4ff-4962-a4ad-1c16f3cdc884.PNG#bec702de-153b-4cee-a2ac-29e4db4a432f.PNG',20.17,1,'2019-04-17 16:02:32','2021-04-17 16:03:26'),(8,1,18,'摄影的艺术','经典摄影教材 畅销美国35年的摄影书，书籍八成新，质量很好(原版35年畅销世界，\n被全球摄影师奉为圭皋的经典之作；世界各艺术院校摄影、设计等专业学生的美学参考书)','d7c06e55-f9c7-4cc6-8e0c-f45ee4e47287.png#da393223-56b3-477e-a79d-85dac6d4d827.png',50.00,0,'2019-04-17 16:11:21','2021-04-17 16:11:32'),(9,4,14,'假如给我三天光明','图书基本全新，内容很不错','a307f7d4-245b-424d-a0fa-5c7ed7441aca.PNG#807232d5-ab38-461b-b672-aa1db033e8cb.PNG',12.40,1,'2019-05-05 04:29:37','2021-05-05 04:29:47'),(10,3,10,'会计考试用书','书名:2016初级会计职称考试用书教材 初级会计实务 会计初级职称考试教材 2016初级会计实务\n\n定价：33.00 元\n\n作者:财政部会计资格评价中心\n\n出版社：中国财政经济出版社一\n\n出版日期：2015年12月 \n\nISBN：9787509564745\n\n字数：\n\n页码：\n\n版次：1\n\n装帧：\n\n开本：\n\n商品标识：9787509564745','b55b9b5f-9eff-49af-aeb8-6ef68761fe77.PNG',7.00,1,'2019-05-05 13:43:01','2021-05-05 13:43:01'),(11,3,2,'中级经济师2019经济基础知识','产品名称：零基础过经济师必刷1000题...\nISBN编号: 9787516419243\n书名: 零基础过经济师必刷1000题 经济基础知识\n作者: 无\n定价: 42.00元\n编者: 中华会计网校\n书名: 零基础过经济师必刷1000题 经济基础知识\n开本: 16开\n是否是套装: 否\n出版社名称: 企业管理出版社包\n册数: 1册','ee6f3686-c9b1-46ff-8cb9-a252b41db572.PNG#0b1d4fcf-0af8-48bd-8a3f-b20260b91409.PNG',12.40,1,'2019-05-05 13:57:27','2021-05-05 13:57:34'),(12,4,19,'活着','活着 新版余华作品','6620e539-78aa-4f3d-a92f-062889563215.PNG#100f343a-9fff-4174-bba0-3ecc3a294b05.PNG',12.40,1,'2019-05-05 14:19:27','2021-05-05 14:19:34');

/*Table structure for table `book_category` */

DROP TABLE IF EXISTS `book_category`;

CREATE TABLE `book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图书类别编号',
  `book_category_name` varchar(50) NOT NULL COMMENT '图书类别名',
  `book_category_father_id` int(11) NOT NULL DEFAULT '0' COMMENT '图书类别父类编号',
  `book_category_level` int(2) NOT NULL DEFAULT '0' COMMENT '图书类别级别',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `book_category` */

insert  into `book_category`(`id`,`book_category_name`,`book_category_father_id`,`book_category_level`,`is_usable`,`cst_create`,`cst_modify`) values (1,'考试教育',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(2,'经济管理',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(3,'文学艺术',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(4,'人文社科',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(5,'科学技术',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(6,'生活休闲',0,0,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(7,'公务员考试',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(8,'英语考试',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(9,'考研',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(10,'其他考试',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(11,'职称考试',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(12,'教辅书',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(13,'工具书',1,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(14,'成功励志',2,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(15,'管理',2,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(16,'经济经融',2,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(17,'收藏鉴赏',3,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(18,'艺术',3,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(19,'文学小说',3,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(20,'文化历史',3,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(21,'新闻传播',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(22,'心理',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(23,'法律',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(24,'自然科学',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(25,'社会科学',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(26,'政治军事',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(27,'宗教哲学',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(28,'语言学习',4,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(29,'汽车与交通运输',5,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(30,'医学卫生',5,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(31,'计算机与网络',5,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(32,'科技工程',5,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(33,'建筑',5,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(34,'生活时尚',6,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(35,'家庭育儿',6,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(36,'旅游地理',6,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(37,'体育保健',6,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(38,'少儿',6,1,1,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(39,'好好',0,0,0,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(40,'失望个水电费的会计',33,2,0,'2021-04-08 21:40:52','2021-04-08 21:40:52'),(41,'图书',34,2,0,'2021-04-08 21:40:52','2021-04-08 21:40:52');

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `book_id` int(11) NOT NULL COMMENT '图书编号',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_collection_user_id` (`user_id`),
  KEY `FK_collection_book_id` (`book_id`),
  CONSTRAINT `FK_collection_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_collection_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `collection` */

insert  into `collection`(`id`,`user_id`,`book_id`,`is_usable`,`cst_create`,`cst_modify`) values (1,1,11,0,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(2,2,12,0,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(3,2,5,1,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(4,2,11,0,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(5,2,7,1,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(6,11,9,1,'2021-05-06 15:13:22','2021-05-06 15:13:22'),(7,7,11,0,'2021-05-06 15:13:22','2021-04-28 08:56:20');

/*Table structure for table `leave_word` */

DROP TABLE IF EXISTS `leave_word`;

CREATE TABLE `leave_word` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `book_id` int(11) DEFAULT NULL COMMENT '图书编号',
  `leave_content` varchar(300) DEFAULT NULL COMMENT '留言内容',
  `leave_father_id` int(11) DEFAULT '0' COMMENT '回复留言编号 -0 直接针对图书评论',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_leave_word_user_id` (`user_id`),
  KEY `FK_leave_word_book_id` (`book_id`),
  CONSTRAINT `FK_leave_word_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_leave_word_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `leave_word` */

insert  into `leave_word`(`id`,`user_id`,`book_id`,`leave_content`,`leave_father_id`,`is_usable`,`cst_create`,`cst_modify`) values (1,1,8,'图片清晰吗，是正版吗',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(2,1,5,'这个对考教师资格证有用吗',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(3,1,2,'这个适合零基础的人学吗',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(4,1,2,'你是不是有',0,0,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(5,2,7,'你好',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(6,4,8,'图书怎么样',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(7,2,7,'发货单看',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(8,3,11,'很快就到饭',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(9,3,11,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(10,4,4,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(11,4,4,'黄金客户即可',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(12,4,4,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(13,4,4,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(14,4,12,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(15,4,12,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(16,4,12,'<img ',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(17,4,11,'你好',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(18,2,12,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(19,1,11,'',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(20,1,11,'这个图书怎么样',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(21,1,11,'你好',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01'),(22,2,6,'图书怎么样啊',0,1,'2021-04-28 12:11:01','2021-04-28 12:11:01');

/*Table structure for table `order_form` */

DROP TABLE IF EXISTS `order_form`;

CREATE TABLE `order_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `book_id` int(11) NOT NULL COMMENT '图书编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `receiver_id` int(11) NOT NULL COMMENT '收货地址编号',
  `order_state` int(2) NOT NULL COMMENT '订单状态 -0 发货中 -1 已收货 -2 已取消',
  `order_remark` varchar(300) NOT NULL COMMENT '备注',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_order_form_user_id` (`user_id`),
  KEY `FK_order_form_book_id` (`book_id`),
  KEY `FK_order_form_receiving_address_id` (`receiver_id`),
  CONSTRAINT `FK_order_form_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_order_form_receiving_address_id` FOREIGN KEY (`receiver_id`) REFERENCES `receiving_address` (`id`),
  CONSTRAINT `FK_order_form_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `order_form` */

insert  into `order_form`(`id`,`book_id`,`user_id`,`receiver_id`,`order_state`,`order_remark`,`is_usable`,`cst_create`,`cst_modify`) values (1,1,3,1,0,'',1,'2021-04-29 02:54:26','2021-04-29 02:54:26'),(2,8,4,2,1,'',1,'2021-05-05 04:20:57','2021-05-05 04:20:57');

/*Table structure for table `place_transaction` */

DROP TABLE IF EXISTS `place_transaction`;

CREATE TABLE `place_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '交易地点编号',
  `place_name` varchar(50) NOT NULL COMMENT '交易地点名',
  `place_father_id` int(11) NOT NULL DEFAULT '0' COMMENT '地点父类编号',
  `place_level` int(2) NOT NULL DEFAULT '1' COMMENT '地点级别 -1 园区 -2 楼栋',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `place_transaction` */

insert  into `place_transaction`(`id`,`place_name`,`place_father_id`,`place_level`,`is_usable`,`cst_create`,`cst_modify`) values (1,'一号教学楼',1,1,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(2,'图书馆',1,1,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(3,'二号教学楼',2,2,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(4,'三号教学楼',3,3,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(5,'四号教学楼',4,4,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(6,'五号教学楼',5,5,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(7,'六号教学楼',6,5,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(8,'综合楼',8,6,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(9,'晨曦园',2,7,1,'2021-04-08 22:14:55','2021-04-08 22:14:55'),(10,'十二号宿舍楼',3,8,1,'2021-04-08 22:14:55','2021-04-08 22:14:55');

/*Table structure for table `receiving_address` */

DROP TABLE IF EXISTS `receiving_address`;

CREATE TABLE `receiving_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `receiver_name` varchar(30) NOT NULL COMMENT '收货人姓名',
  `receiver_tel` varchar(30) NOT NULL COMMENT '手机号码',
  `place_name` varchar(50) NOT NULL COMMENT '交易地点',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  `is_default` int(11) DEFAULT NULL COMMENT '是否为默认地址',
  PRIMARY KEY (`id`),
  KEY `FK_receiving_address_user_id` (`user_id`),
  CONSTRAINT `FK_receiving_address_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `receiving_address` */

insert  into `receiving_address`(`id`,`user_id`,`receiver_name`,`receiver_tel`,`place_name`,`is_usable`,`cst_create`,`cst_modify`,`is_default`) values (1,1,'张三','1767465504','晨曦园',1,'2021-04-08 20:30:57','2021-04-08 20:30:57',0),(2,4,'柳柳','12345688','图书馆',1,'2021-04-08 20:30:57','2021-04-08 20:30:57',0),(3,2,'小李','123456854','综合楼',1,'2021-04-08 20:30:57','2021-04-08 20:30:57',0),(4,2,'网名','1746524128','二号教学楼',1,'2021-04-08 20:30:57','2021-04-08 20:30:57',0);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `user_student_id` varchar(255) DEFAULT NULL COMMENT '用户学号',
  `user_real_name` varchar(30) DEFAULT NULL COMMENT '用户真实姓名',
  `user_sex` char(2) DEFAULT NULL COMMENT '用户性别',
  `user_birthday` date DEFAULT NULL COMMENT '出生年月',
  `user_pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_tel` varchar(50) DEFAULT NULL COMMENT '电话',
  `user_email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `user_pic` varchar(300) DEFAULT 'default.jpg' COMMENT '头像',
  `user_role` int(2) DEFAULT '0' COMMENT '用户角色  -0 普通用户 -1 管理员 -2 超级管理员',
  `is_usable` tinyint(1) unsigned NOT NULL COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`user_name`,`user_student_id`,`user_real_name`,`user_sex`,`user_birthday`,`user_pwd`,`user_tel`,`user_email`,`user_pic`,`user_role`,`is_usable`,`cst_create`,`cst_modify`) values (1,'张三',NULL,'李四','男','1996-06-21','zhangsan','13745682594','123456789@qq.com','da4befcf-990b-42a8-a4e1-29c8956456f1.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(2,'李四',NULL,NULL,'男',NULL,'123456','14567895257','1562543587@qq.com','8d44a5d0-1b43-405d-803f-a7e6ed61df1f.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(3,'王五',NULL,NULL,'男',NULL,'123456','12345678925','4544578878@qq.com','default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(4,'柳柳',NULL,NULL,'女',NULL,'123456','18547569875','123542584@qq.com','390fdc2e-6460-4103-bdfe-5a825524bc48.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(5,'test',NULL,NULL,'女',NULL,'123456','13745682594','123456789@qq.com','default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(6,'root','1','root','女','2014-10-13','root','12345622540','123547@qq.com','390fdc2e-6460-4103-bdfe-5a825524bc48.jpg',1,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(7,'admin','000000','侯涛','男','2001-01-01','admin','17609262005',' ','default.jpg',1,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(8,'丽丽',NULL,NULL,NULL,NULL,'123456',NULL,NULL,'default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(9,'林建华',NULL,NULL,NULL,NULL,'123456',NULL,NULL,'default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(10,'你好',NULL,NULL,NULL,NULL,'123456',NULL,NULL,'default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57'),(11,'李思',NULL,NULL,NULL,NULL,'123456',NULL,NULL,'default.jpg',0,1,'2021-04-08 20:30:57','2021-04-08 20:30:57');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
