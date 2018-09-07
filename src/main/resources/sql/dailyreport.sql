/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.40 : Database - weeklyreport
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dailyreport` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dailyreport`;

-- ----------------------------
-- Table structure for t_dr_user
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_user`;
CREATE TABLE `t_dr_user`  (
  `f_uid` int(11) NOT NULL,
  `f_username` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_realname` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_jurisdiction` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`f_uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_daily
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_daily`;
CREATE TABLE `t_dr_daily`  (
  `f_did` int(11) NOT NULL AUTO_INCREMENT,
  `f_uid` int(11) NOT NULL,
  `f_pid` int(11) NOT NULL,
  `f_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_date` date NOT NULL,
  `f_man_hours` int(11) NOT NULL,
  PRIMARY KEY (`f_did`) USING BTREE,
  INDEX `f_uid`(`f_uid`) USING BTREE,
  INDEX `f_pid`(`f_pid`) USING BTREE,
  CONSTRAINT `t_dr_daily_ibfk_1` FOREIGN KEY (`f_uid`) REFERENCES `t_dr_user` (`f_uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_dr_daily_ibfk_2` FOREIGN KEY (`f_pid`) REFERENCES `t_dr_project` (`f_pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_dr_project
-- ----------------------------
DROP TABLE IF EXISTS `t_dr_project`;
CREATE TABLE `t_dr_project`  (
  `f_pid` int(11) NOT NULL AUTO_INCREMENT,
  `f_projectname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `f_validity` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`f_pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;