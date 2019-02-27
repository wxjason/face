/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.207
Source Server Version : 50722
Source Host           : 192.168.2.207:3306
Source Database       : face

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-02-27 17:24:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad_person
-- ----------------------------
DROP TABLE IF EXISTS `ad_person`;
CREATE TABLE `ad_person` (
  `id` varchar(32) NOT NULL COMMENT '人员名称',
  `person_name` varchar(50) DEFAULT NULL COMMENT '人员名称',
  `person_image` varchar(100) DEFAULT NULL COMMENT '人员人脸图名称',
  `del` tinyint(1) DEFAULT NULL COMMENT '逻辑删除1未删,0删除',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad_person
-- ----------------------------

-- ----------------------------
-- Table structure for ad_snap_record
-- ----------------------------
DROP TABLE IF EXISTS `ad_snap_record`;
CREATE TABLE `ad_snap_record` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `person_name` varchar(50) DEFAULT NULL COMMENT '人员名称',
  `person_image` varchar(100) DEFAULT NULL COMMENT '人员库人脸图名称',
  `snap_image` varchar(100) DEFAULT NULL COMMENT '抓拍图片',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad_snap_record
-- ----------------------------

-- ----------------------------
-- Table structure for excel_text_temp
-- ----------------------------
DROP TABLE IF EXISTS `excel_text_temp`;
CREATE TABLE `excel_text_temp` (
  `id` varchar(50) NOT NULL,
  `text` text COMMENT '导入文本',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Excel导入过渡表';

-- ----------------------------
-- Records of excel_text_temp
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) DEFAULT '0' COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `menu_url` varchar(100) DEFAULT NULL COMMENT 'url地址',
  `menu_index` int(11) DEFAULT NULL COMMENT '菜单排序号',
  `menu_type` tinyint(4) DEFAULT NULL COMMENT '类型（1：菜单  2：按钮  3：接口）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', 'lft-icon-xtgl', null, '99', '1', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', null, '/system/user', '1', '1', null);
INSERT INTO `sys_menu` VALUES ('3', '2', '添加用户', null, '/system/user/add', '2', '2', null);
INSERT INTO `sys_menu` VALUES ('4', '2', '编辑用户', null, '/system/user/update', '3', '2', null);
INSERT INTO `sys_menu` VALUES ('5', '2', '删除用户', null, '/system/user/delete', '4', '2', null);
INSERT INTO `sys_menu` VALUES ('6', '2', '启用用户', null, '/system/user/enable', '5', '2', null);
INSERT INTO `sys_menu` VALUES ('7', '2', '停用用户', null, '/system/user/disable', '6', '2', null);
INSERT INTO `sys_menu` VALUES ('8', '2', '密码重置', null, '/system/user/password/reset', '7', '2', null);
INSERT INTO `sys_menu` VALUES ('9', '1', '角色管理', null, '/system/role', '2', '1', null);
INSERT INTO `sys_menu` VALUES ('10', '9', '添加角色', null, '/system/role/add', '2', '2', null);
INSERT INTO `sys_menu` VALUES ('11', '9', '编辑角色', null, '/system/role/update', '3', '2', null);
INSERT INTO `sys_menu` VALUES ('12', '9', '删除角色', null, '/system/role/delete', '4', '2', null);
INSERT INTO `sys_menu` VALUES ('13', '1', '操作日志', null, '/system/operation/log', '3', '1', null);
INSERT INTO `sys_menu` VALUES ('14', '13', '批量导出', null, '/system/operation/log/export', '2', '2', null);
INSERT INTO `sys_menu` VALUES ('16', '0', '首页', 'lft-icon-home', '/admin/home', '1', '1', null);
INSERT INTO `sys_menu` VALUES ('17', '2', '用户列表', null, '/system/user/list/page', '1', '2', null);
INSERT INTO `sys_menu` VALUES ('18', '9', '角色列表', null, '/system/role/list/page', '1', '2', null);
INSERT INTO `sys_menu` VALUES ('19', '0', '人员管理', 'lft-icon-grzx', '/face/admin/person', '2', '1', null);
INSERT INTO `sys_menu` VALUES ('20', '19', '添加人员', null, '/face/admin/person/add', '2', '2', null);
INSERT INTO `sys_menu` VALUES ('21', '19', '编辑人员', null, '/face/admin/person/update', '3', '2', null);
INSERT INTO `sys_menu` VALUES ('22', '19', '删除人员', null, '/face/admin/person/delete', '4', '2', null);
INSERT INTO `sys_menu` VALUES ('23', '19', '人员列表', null, '/face/admin/person/list/page', '1', '2', null);

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作人id',
  `remote_ip` varchar(50) DEFAULT NULL COMMENT '操作IP地址',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `menu_sub_id` int(11) DEFAULT NULL COMMENT '功能ID',
  `result_code` int(1) DEFAULT NULL COMMENT '结果状态码:200为成功',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES ('03703e4fcdee49ee9c64be26708bd999', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 17:07:58');
INSERT INTO `sys_operation_log` VALUES ('7da1059886d9429a8431adec085daa7e', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 17:08:05');
INSERT INTO `sys_operation_log` VALUES ('95e399b8b9a74b22a4deefae19898869', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 16:25:55');
INSERT INTO `sys_operation_log` VALUES ('a39a5348c7a54a18ae06d5e45d8f6753', '1', '192.168.2.16', '9', '18', '200', '2019-02-27 16:25:58');
INSERT INTO `sys_operation_log` VALUES ('b7d59014f0a24bab8c3f6561ae4133dd', '1', '192.168.2.16', '2', '3', '500', '2019-02-27 16:26:09');
INSERT INTO `sys_operation_log` VALUES ('c686bf1e136c49638dc92e7d0cb0461c', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 16:26:05');
INSERT INTO `sys_operation_log` VALUES ('df715e2a30df43b6a28c22f128d44076', '1', '192.168.2.16', '9', '18', '200', '2019-02-27 17:08:00');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '角色id',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色备注',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', null, '0', '2019-01-02 18:20:21', '0', '2019-01-02 18:21:02');
INSERT INTO `sys_role` VALUES ('9fc67265a1ef4ed9b37ac08dd69d740c', '管理员', null, '1', '2019-02-27 12:57:47', '1', '2019-02-27 12:59:09');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单角色关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('06d9fc5bba21482cbbecf8fec7893500', '9fc67265a1ef4ed9b37ac08dd69d740c', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '13');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('20', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('22e0766349224822a739331693b340cb', '9fc67265a1ef4ed9b37ac08dd69d740c', '15');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('288a21f5f8244e65a3ea0f1963b32cf8', '9fc67265a1ef4ed9b37ac08dd69d740c', '2');
INSERT INTO `sys_role_menu` VALUES ('2a6de5fd73184ea1bb77e446a1bedc0e', '9fc67265a1ef4ed9b37ac08dd69d740c', '3');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('3d6d6af88f254bca8ce40f0cd38c0641', '9fc67265a1ef4ed9b37ac08dd69d740c', '6');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('46a0de1e4bff4b598c4f59c1b5e28379', '9fc67265a1ef4ed9b37ac08dd69d740c', '18');
INSERT INTO `sys_role_menu` VALUES ('46fda750b551419f8987376998c32d10', '9fc67265a1ef4ed9b37ac08dd69d740c', '17');
INSERT INTO `sys_role_menu` VALUES ('48f8ec63a1b04f7b8990dfbe873d8556', '9fc67265a1ef4ed9b37ac08dd69d740c', '16');
INSERT INTO `sys_role_menu` VALUES ('4a7ae3a4bde440ad92b60fed6c8a039d', '9fc67265a1ef4ed9b37ac08dd69d740c', '9');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('6028855fd6c846e588e1985d7f248835', '9fc67265a1ef4ed9b37ac08dd69d740c', '11');
INSERT INTO `sys_role_menu` VALUES ('6c0ddeb23beb4568b0fe84b21518046d', '9fc67265a1ef4ed9b37ac08dd69d740c', '14');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('77ad70858412456d95a58ab646c77b21', '9fc67265a1ef4ed9b37ac08dd69d740c', '12');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('803964d7cf3b4bfaba391b467309b4dd', '9fc67265a1ef4ed9b37ac08dd69d740c', '13');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('9ecdaa72adb74e6aad407a6ee78d764a', '9fc67265a1ef4ed9b37ac08dd69d740c', '5');
INSERT INTO `sys_role_menu` VALUES ('b4164d60343b4ec59975372e1835830c', '9fc67265a1ef4ed9b37ac08dd69d740c', '1');
INSERT INTO `sys_role_menu` VALUES ('bd08612c21964be394a35696a2b7a984', '9fc67265a1ef4ed9b37ac08dd69d740c', '8');
INSERT INTO `sys_role_menu` VALUES ('e941775936d94a1bbc99572795b0c246', '9fc67265a1ef4ed9b37ac08dd69d740c', '4');
INSERT INTO `sys_role_menu` VALUES ('e991acf23ec346ecba23c8db821bdbaf', '9fc67265a1ef4ed9b37ac08dd69d740c', '7');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '所属角色ID',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态1启用0停用',
  `del` tinyint(1) DEFAULT '1' COMMENT '0删除1未删',
  `create_user_id` varchar(32) DEFAULT NULL COMMENT '创建用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(32) DEFAULT NULL COMMENT '修改用户id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0829596656744ec6990b4be24f07c64c', '9fc67265a1ef4ed9b37ac08dd69d740c', 'wxjason', '$2a$10$fDzRCzxbRYDfMXXXlTj8V.xw2hSbwCzxXSeb0wa8HYxg69eVpjHbm', '吴鑫键', '15523991770', 'wxjason@hotmail.com', '1', '1', '1', '2019-02-27 12:58:53', null, null);
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', '$2a$10$85shpaOqGIF9agF3UktRo.LJUTY2heKGUoJ6qvWm.FkPiNNbI6Cri', 'admin', '10000', '10000@189.com', '1', '1', '0', '2019-01-02 11:29:49', '0', '2019-01-16 13:15:28');
