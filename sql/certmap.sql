/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.18-log : Database - certmap
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`certmap` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `certmap`;

/*Table structure for table `cm_coupon` */

DROP TABLE IF EXISTS `cm_coupon`;

CREATE TABLE `cm_coupon` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '卡券编号',
  `money` double(5,2) unsigned NOT NULL COMMENT '卡券的面额',
  `begin_time` datetime NOT NULL COMMENT '卡券的开始使用时间',
  `dead_line` datetime NOT NULL COMMENT '卡券的截止使用时间',
  `scope` varchar(150) NOT NULL COMMENT '使用范围',
  `scope_num` varchar(30) DEFAULT NULL,
  `overlying` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可叠加 0-否 1-是',
  `is_share` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可以分享 0-否 1-是',
  `used` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已使用 0-否 1-是',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_coupon_template` */

DROP TABLE IF EXISTS `cm_coupon_template`;

CREATE TABLE `cm_coupon_template` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT COMMENT '卡券编号',
  `money` double(5,2) unsigned NOT NULL COMMENT '卡券的面额',
  `begin_time` datetime NOT NULL COMMENT '卡券的开始使用时间',
  `dead_line` datetime NOT NULL COMMENT '卡券的截止使用时间',
  `scope` varchar(150) NOT NULL COMMENT '使用范围',
  `scope_num` varchar(30) DEFAULT NULL,
  `overlying` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可叠加 0-否 1-是',
  `is_share` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可以分享 0-否 1-是',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用 0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_course` */

DROP TABLE IF EXISTS `cm_course`;

CREATE TABLE `cm_course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `course_type` tinyint(1) NOT NULL COMMENT '课程类型：0-直播 1-录播 2-点播',
  `father_id` int(11) unsigned NOT NULL,
  `link` varchar(150) NOT NULL,
  `price` decimal(5,2) NOT NULL COMMENT '课程的价格',
  `discount_price` decimal(5,2) DEFAULT NULL COMMENT '课程的折扣价',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_order` */

DROP TABLE IF EXISTS `cm_order`;

CREATE TABLE `cm_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_num` varchar(30) NOT NULL COMMENT '订单编号',
  `pay_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态：0-未支付 1-已支付 2-退款',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `goods_type` tinyint(2) NOT NULL COMMENT '商品类型',
  `goods_id` int(11) NOT NULL COMMENT '商品编号',
  `pay_type` tinyint(1) NOT NULL COMMENT '支付类型',
  `original_price` double(5,2) NOT NULL COMMENT '商品原价',
  `coupon_price` double(5,2) NOT NULL COMMENT '卡券抵扣金额',
  `actual_price` double(5,2) NOT NULL COMMENT '商品的实际支付金额',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用：0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_order_flow_expert` */

DROP TABLE IF EXISTS `cm_order_flow_expert`;

CREATE TABLE `cm_order_flow_expert` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '专家账户明细编号',
  `user_id` int(11) NOT NULL COMMENT '专家用户编号',
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `operation` tinyint(1) NOT NULL COMMENT '操作类型：0-收入 1-支出',
  `money` decimal(5,2) NOT NULL COMMENT '金额',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用：0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_order_flow_platform` */

DROP TABLE IF EXISTS `cm_order_flow_platform`;

CREATE TABLE `cm_order_flow_platform` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '平台流水明细编号',
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `money` decimal(5,2) NOT NULL COMMENT '金额',
  `operation` tinyint(1) NOT NULL COMMENT '操作类型：0-收入 1-支出',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用：0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_share_proportion` */

DROP TABLE IF EXISTS `cm_share_proportion`;

CREATE TABLE `cm_share_proportion` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '分成比例编号',
  `goods_type` tinyint(2) NOT NULL COMMENT '商品类型，与cm_order表的goods_type保持一致',
  `goods_id` int(11) NOT NULL COMMENT '商品编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_share_proportion_default` */

DROP TABLE IF EXISTS `cm_share_proportion_default`;

CREATE TABLE `cm_share_proportion_default` (
  `id` int(1) unsigned NOT NULL AUTO_INCREMENT COMMENT '默认分成比例编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cm_standard` */

DROP TABLE IF EXISTS `cm_standard`;

CREATE TABLE `cm_standard` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '标准解读编号',
  `title` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标题',
  `user_id` int(11) NOT NULL COMMENT '专家编号（用户编号）',
  `price` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '标准解读的价格',
  `discount_price` decimal(5,2) DEFAULT NULL COMMENT '折扣价',
  `thumbnail` varchar(150) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '缩略图',
  `type` tinyint(1) NOT NULL COMMENT '标准解读的类型',
  `intro` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标准解读的简介',
  `standard_number` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '标准号',
  `document_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '文档名称',
  `document_id` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '上传百度云返回的文档编号',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `cm_user` */

DROP TABLE IF EXISTS `cm_user`;

CREATE TABLE `cm_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `real_name` varchar(50) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `role` tinyint(1) NOT NULL DEFAULT '1' COMMENT '会员角色（1：用户  2：专家）',
  `email` varchar(50) NOT NULL COMMENT '电子邮箱',
  `union_id` varchar(50) DEFAULT '' COMMENT '微信unionId',
  `sex` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别0-女 1-男 2-其他',
  `head_img` varchar(150) NOT NULL DEFAULT '' COMMENT '用户头像',
  `access_token` varchar(150) NOT NULL DEFAULT '' COMMENT '登录token',
  `qq_open_id` varchar(50) DEFAULT NULL COMMENT 'qq的openId',
  `balance` decimal(5,2) NOT NULL DEFAULT '0.00' COMMENT '钱包余额',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0：已停用 1：已启动)专家账户未审核通过时，状态为停用',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `cm_wallet_unit` */

DROP TABLE IF EXISTS `cm_wallet_unit`;

CREATE TABLE `cm_wallet_unit` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT COMMENT '钱包充值套餐编号',
  `price` double(5,2) NOT NULL DEFAULT '0.00' COMMENT '套餐的金额',
  `discount_price` double(5,2) NOT NULL DEFAULT '0.00' COMMENT '折扣价（实际支付金额）',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可用0-否 1-是',
  `cst_create` datetime NOT NULL COMMENT '数据的创建时间',
  `cst_modify` datetime NOT NULL COMMENT '数据的修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
