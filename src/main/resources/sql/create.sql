/*

Source Server         : localhost
Source Host           : localhost:3306
Source Database       : qq_chat
Target Server Type    : MYSQL

*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `msg`
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  msg_id int(11) NOT NULL AUTO_INCREMENT,
  msg_content varchar(2000) DEFAULT NULL,
  msg_sendfrom int(11) DEFAULT NULL,
  msg_sendto int(11) DEFAULT NULL,
  msg_sendtime datetime DEFAULT NULL,
  PRIMARY KEY (msg_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  u_id int(11) NOT NULL auto_increment,
  u_name varchar(50) DEFAULT NULL,
  u_pwd varchar(50) DEFAULT NULL,
  PRIMARY KEY (u_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '小茗同学', '123');
INSERT INTO `users` VALUES (2, '马化腾', '123');
INSERT INTO `users` VALUES (3, '吴阳阳', '123');
