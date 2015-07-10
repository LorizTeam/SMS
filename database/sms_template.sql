/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : smsimobiledb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2015-07-10 14:47:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sms_template`
-- ----------------------------
DROP TABLE IF EXISTS `sms_template`;
CREATE TABLE `sms_template` (
  `description` varchar(248) default NULL,
  `type` varchar(100) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_template
-- ----------------------------
INSERT INTO `sms_template` VALUES ('สวัสดี วันนี้เป็นวันปีใหม่ ท้องฟ้าสดใส จิตใจแจ่มสัน', 'A');
INSERT INTO `sms_template` VALUES ('เกิดแล้วดี', 'B');
INSERT INTO `sms_template` VALUES ('รักพ่อ ส่งเลยสิ  sms', 'C');
INSERT INTO `sms_template` VALUES ('วันแม่แล้วนะ sms ส่งเลยอย่าง ช้าดิ เอ้อ', 'D');
INSERT INTO `sms_template` VALUES ('เล่นน้ำให้สนุก สุขสันวันปีใหม่ไทย', 'E');
INSERT INTO `sms_template` VALUES ('แต๊ะเอีย ต้องจับหู แล้วแบรมือ รับตังค์', 'F');
