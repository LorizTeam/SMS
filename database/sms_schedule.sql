/*
Navicat MySQL Data Transfer

Source Server         : Database
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : smsimobiledb

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2015-07-10 14:46:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sms_schedule`
-- ----------------------------
DROP TABLE IF EXISTS `sms_schedule`;
CREATE TABLE `sms_schedule` (
  `no` int(11) NOT NULL auto_increment,
  `custid` varchar(10) default NULL,
  `message` varchar(200) default NULL,
  `sending` varchar(50) default NULL,
  `datetime` datetime default NULL,
  `unit` int(10) default NULL,
  `cost` double(10,0) default NULL,
  `username` varchar(100) default NULL,
  PRIMARY KEY  (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_schedule
-- ----------------------------
