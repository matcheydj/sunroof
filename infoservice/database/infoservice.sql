/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.5.8 : Database - infoservice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`infoservice` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `infoservice`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `orgid` int(11) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `security_group` */

DROP TABLE IF EXISTS `security_group`;

CREATE TABLE `security_group` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `name` varchar(50) default NULL COMMENT '名称',
  `describe` varchar(100) default NULL COMMENT '描述',
  `menu_group_text` varchar(200) default NULL COMMENT '菜单权限id组以逗号隔开',
  `is_locked` bit(1) default NULL COMMENT '是否锁定',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `security_group` */

insert  into `security_group`(`id`,`code`,`name`,`describe`,`menu_group_text`,`is_locked`) values (1,'001','系统管理员',NULL,NULL,'');

/*Table structure for table `security_menu_function` */

DROP TABLE IF EXISTS `security_menu_function`;

CREATE TABLE `security_menu_function` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `menu_id` int(11) NOT NULL COMMENT '菜单Id',
  `group_id` int(11) NOT NULL COMMENT '用户组id',
  `pomition_text` varchar(100) default NULL COMMENT '操作功能组（以逗号隔开）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `security_menu_function` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `menu_pid` int(11) NOT NULL COMMENT '父节点id',
  `menu_name` varchar(30) NOT NULL COMMENT '菜单名称',
  `page_url` varchar(200) default NULL COMMENT '菜单页面路径',
  `title` varchar(100) default NULL COMMENT '提示信息',
  `order_num` int(11) NOT NULL COMMENT '排序',
  `function_group` varchar(1000) default NULL COMMENT '菜单功能组（以逗号隔开）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
