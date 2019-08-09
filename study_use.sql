/*
Navicat MySQL Data Transfer

Source Server         : 12
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : study_use

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-08-09 22:07:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `commmentator` int(11) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `like_count` bigint(20) unsigned DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('24', '17', '1', '13', '1565079009836', '1565079009836', '0', '这是一个很好的评论，虽然说的都是废话，但却简单易懂');
INSERT INTO `comment` VALUES ('30', '17', '1', '13', '1565083519659', '1565083519659', '0', '这是一个很好的评论，虽然说的都是废话，但却简单易懂');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(255) DEFAULT NULL,
  `view_count` int(255) DEFAULT NULL,
  `like_count` int(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('16', '标题16', '补充', '1563014052834', '1563014052834', '13', '0', '22', '0', '标签');
INSERT INTO `question` VALUES ('17', '标题17', '补充1', '1563014056531', '1564456039652', '13', '2', '111', '0', '标签');
INSERT INTO `question` VALUES ('18', '标题18修改过后', '补充', '1563014059785', '1564217915345', '13', '0', '0', '0', '标签');
INSERT INTO `question` VALUES ('19', '标题19', '补充', '1563014063127', '1563014063127', '13', '0', '47', '0', '标签');
INSERT INTO `question` VALUES ('21', '标题20', '20', '1564217532949', '1564217532949', '13', '0', '2', '0', '标签');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', '52613558', 'hqw-1997', '7b683ffd-f24e-4080-997b-4f23390ba951', '1564106194815', '1565315782575', 'https://avatars3.githubusercontent.com/u/52613558?v=4');
