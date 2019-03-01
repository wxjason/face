/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.207
Source Server Version : 50722
Source Host           : 192.168.2.207:3306
Source Database       : face

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-03-01 09:15:51
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
  `similar` int(11) DEFAULT NULL COMMENT '相似度',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ad_snap_record
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `stream_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('3ade3a8b54844f5480922e833b2a53e6', 'hk-2402', 'rtsp://admin:admin12345@192.168.2.94/video/1');
INSERT INTO `device` VALUES ('53aa28c030284d1996636a5fa2223f34', 'wxj-device', 'rtmp://rtmp.open.ys7.com/openlive/38332fc451ef431880569f3a4bde21a4.hd');
INSERT INTO `device` VALUES ('a70fdbb666934466b3394eb3e8e4fdda', 'kris-device', 'rtmp://rtmp.open.ys7.com/openlive/38332fc451ef431880569f3a4bde21a4.hd');

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='菜单表';

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
INSERT INTO `sys_menu` VALUES ('19', '0', '人员管理', 'lft-icon-grzx', '/face/admin/person', '3', '1', null);
INSERT INTO `sys_menu` VALUES ('20', '19', '添加人员', null, '/face/admin/person/add', '2', '2', null);
INSERT INTO `sys_menu` VALUES ('21', '19', '编辑人员', null, '/face/admin/person/update', '3', '2', null);
INSERT INTO `sys_menu` VALUES ('22', '19', '删除人员', null, '/face/admin/person/delete', '4', '2', null);
INSERT INTO `sys_menu` VALUES ('23', '19', '人员列表', null, '/face/admin/person/list/page', '1', '2', null);
INSERT INTO `sys_menu` VALUES ('24', '0', '抓拍历史', 'lft-icon-xtjk', '/face/admin/snap/record', '2', '1', null);

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
INSERT INTO `sys_operation_log` VALUES ('0112ddd3d3054588af8754521351f79d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:11');
INSERT INTO `sys_operation_log` VALUES ('02426fe0e5344afcab8420dcc512547e', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:09:32');
INSERT INTO `sys_operation_log` VALUES ('03703e4fcdee49ee9c64be26708bd999', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 17:07:58');
INSERT INTO `sys_operation_log` VALUES ('04dbde9c60674800815831296c4b8bc9', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:06:35');
INSERT INTO `sys_operation_log` VALUES ('058863e95f814c4aa3fb88f9edacd273', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:29:15');
INSERT INTO `sys_operation_log` VALUES ('067f319a130b4aae9d2e3cdff1526eee', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:18');
INSERT INTO `sys_operation_log` VALUES ('07576061ef914ae48b7d6e6ed0ccfcb3', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:01:36');
INSERT INTO `sys_operation_log` VALUES ('07f5121c56e84f63b56b552de36c257d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:42:13');
INSERT INTO `sys_operation_log` VALUES ('0a6ee52194cc4bd99f030cbb7d03eb1b', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:51:18');
INSERT INTO `sys_operation_log` VALUES ('0a875b3d3cd14f3da46667595083ff07', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:07:55');
INSERT INTO `sys_operation_log` VALUES ('0bd8f13d7e8e42d8b9acfa61594ef8da', '1', '192.168.2.16', '19', '23', '500', '2019-02-28 10:59:38');
INSERT INTO `sys_operation_log` VALUES ('0cc7f78ae761451aa2969184bbb80a87', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:34:45');
INSERT INTO `sys_operation_log` VALUES ('0d2be2025fac41a6b0502c58bba5c430', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:06:34');
INSERT INTO `sys_operation_log` VALUES ('10823fdbdcc9421892c99b4f6fff4d5f', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:39');
INSERT INTO `sys_operation_log` VALUES ('12f832fabcab42d688e804f03a7bc9ea', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:53:07');
INSERT INTO `sys_operation_log` VALUES ('132cf488fcae4542bfbd029bf566c748', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 18:24:04');
INSERT INTO `sys_operation_log` VALUES ('13a77c45d76549519cb0bfb67bb4deb4', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:32:53');
INSERT INTO `sys_operation_log` VALUES ('140bb44d55674ef6aabd8b69c74c0dc0', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:05:52');
INSERT INTO `sys_operation_log` VALUES ('1424aa7e0a2c4750ad1d750227a6b017', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 18:32:08');
INSERT INTO `sys_operation_log` VALUES ('14ce065e30ff4af9acd387b9c3ae31c6', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:08:17');
INSERT INTO `sys_operation_log` VALUES ('14d33510553341b5aea1b76f7d4d821d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:06:06');
INSERT INTO `sys_operation_log` VALUES ('15454414386b4c7bba8d4ea5c8a7e560', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:07:02');
INSERT INTO `sys_operation_log` VALUES ('15f85d06cc4c4fd294c0851700f6e134', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:55:36');
INSERT INTO `sys_operation_log` VALUES ('16273488c4204ece953b0593e26ff757', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:02:27');
INSERT INTO `sys_operation_log` VALUES ('18212d4c8e1b4a82b02e4a433311af64', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:53');
INSERT INTO `sys_operation_log` VALUES ('1830645c934144799623033c6c25cd61', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:38:01');
INSERT INTO `sys_operation_log` VALUES ('1832cc025a3e4e328506a32f338355e6', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:04:45');
INSERT INTO `sys_operation_log` VALUES ('18a220ca437b4a318bfddc2dab073001', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 15:06:35');
INSERT INTO `sys_operation_log` VALUES ('18adef22986b4065a81b15bbc740bf5a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:58:46');
INSERT INTO `sys_operation_log` VALUES ('1ee450442e4244079436ec24f8291bec', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:07:43');
INSERT INTO `sys_operation_log` VALUES ('216c6276a3724c909ecf0619e63453b3', '907825bdc5eb48ba86f973802d045b73', '192.168.2.16', '2', '17', '200', '2019-02-28 18:17:57');
INSERT INTO `sys_operation_log` VALUES ('217c2c209fec49c6a17402cde2b5035e', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:52:26');
INSERT INTO `sys_operation_log` VALUES ('22b462afd09144bf851c882ba26bbd77', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:51:09');
INSERT INTO `sys_operation_log` VALUES ('2307858f6fda4e2a9fb8ea4ab39b8e97', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:09:05');
INSERT INTO `sys_operation_log` VALUES ('232789f23148407f83f4725c7f193566', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:58:33');
INSERT INTO `sys_operation_log` VALUES ('23b0a4fb38a44e369051309d3f965d33', '1', '192.168.2.16', '19', '22', '200', '2019-02-28 15:06:06');
INSERT INTO `sys_operation_log` VALUES ('240b7129f4c04c65b7a0c018504fc19d', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:55:58');
INSERT INTO `sys_operation_log` VALUES ('24e054f00ed84c6882f8e5273bf48dcc', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:58:24');
INSERT INTO `sys_operation_log` VALUES ('27bd7c1a422144ae96d6ee743bf245a3', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:42:22');
INSERT INTO `sys_operation_log` VALUES ('27eed13eb18b4b92925c919f13405313', '1', '192.168.2.16', '9', '11', '400', '2019-02-28 18:24:02');
INSERT INTO `sys_operation_log` VALUES ('28ab6a6cd88c4f0f9d6fc5489fcd3978', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:56:37');
INSERT INTO `sys_operation_log` VALUES ('2b11fb662a56431790a3c71c4cb96205', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:30:51');
INSERT INTO `sys_operation_log` VALUES ('2b1a0d8cffb9499f80589afdf9b01d94', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:35:58');
INSERT INTO `sys_operation_log` VALUES ('2b1f5205f1a74b9ab9f6df98c253f3fe', '1', '127.0.0.1', '19', '22', '200', '2019-02-28 22:09:05');
INSERT INTO `sys_operation_log` VALUES ('2b87e3e5ed7c4c118586585b27839faf', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:16:37');
INSERT INTO `sys_operation_log` VALUES ('2c8bc6ca23e34587b5c2a7e30bf2b18a', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:33:50');
INSERT INTO `sys_operation_log` VALUES ('2d4b255d47894abea1a3d4284a9209ed', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 14:39:14');
INSERT INTO `sys_operation_log` VALUES ('2de4e365b08f468d8da13b7bea488147', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 15:10:13');
INSERT INTO `sys_operation_log` VALUES ('2f4f148fc618429f8828c957b859c319', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:42:23');
INSERT INTO `sys_operation_log` VALUES ('2fc8cde814254da08362eb6e5cbf4c27', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:03:34');
INSERT INTO `sys_operation_log` VALUES ('302735c59f6b46d7a10516d729842a60', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 18:16:11');
INSERT INTO `sys_operation_log` VALUES ('317472cf842f496b897983580bf8fc1c', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:55:59');
INSERT INTO `sys_operation_log` VALUES ('35238d8084d642c5afe3578339dc271d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:03:37');
INSERT INTO `sys_operation_log` VALUES ('35cffaf815db45b7a56d0f8de27a3410', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:11:06');
INSERT INTO `sys_operation_log` VALUES ('3772c24156a04277b5bd17d4f5124b14', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:29:43');
INSERT INTO `sys_operation_log` VALUES ('37e5ce63dd2c46e09a35d771c2ddaa1a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:54');
INSERT INTO `sys_operation_log` VALUES ('3c8fabcbb4f44d31a77397dc8ad4855c', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:07:21');
INSERT INTO `sys_operation_log` VALUES ('3f03e5b5a2564b4dac3edf5afc48a50a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:38:26');
INSERT INTO `sys_operation_log` VALUES ('3f954e4531354c438058433cddc12ce7', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:50:43');
INSERT INTO `sys_operation_log` VALUES ('421e5c81eda445368fbc60dae7da605c', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:34:45');
INSERT INTO `sys_operation_log` VALUES ('428e3c1002934d4990d17c24d1910e89', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:53:33');
INSERT INTO `sys_operation_log` VALUES ('4365b21409204659b757bae17449875a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:03:34');
INSERT INTO `sys_operation_log` VALUES ('45c5802eb5024eb4b136b8082d1e1d2f', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:49:56');
INSERT INTO `sys_operation_log` VALUES ('4817ccc9058847a7b78b861a4793eb0e', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:21:21');
INSERT INTO `sys_operation_log` VALUES ('4b6840976ce44ab399449025235c4159', '1', '192.168.2.16', '9', '11', '400', '2019-02-28 18:24:08');
INSERT INTO `sys_operation_log` VALUES ('4be5f37a767e4590b9b4e791b657937d', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 17:04:39');
INSERT INTO `sys_operation_log` VALUES ('4da9389b97764b45ab0073d4294933e4', '1', '192.168.2.16', '19', '22', '200', '2019-02-28 17:04:14');
INSERT INTO `sys_operation_log` VALUES ('5201837153534533886c4b1b3b9b051e', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:22:58');
INSERT INTO `sys_operation_log` VALUES ('52c65316abf0485b92f92db54a11259d', '1', '192.168.2.16', '19', '22', '200', '2019-02-28 17:04:17');
INSERT INTO `sys_operation_log` VALUES ('54d2dea8da4947b39aa5beefd6eabab3', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:54:20');
INSERT INTO `sys_operation_log` VALUES ('57c896420ee64bea80d30fa8e7447b92', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:39:36');
INSERT INTO `sys_operation_log` VALUES ('5aba50a7ee30443e8a6ddd617ec2ab3f', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:31:30');
INSERT INTO `sys_operation_log` VALUES ('5c7f8a82f3694a37881660af70af4aa8', '1', '192.168.2.16', '19', '23', '500', '2019-02-28 10:47:39');
INSERT INTO `sys_operation_log` VALUES ('5d2d558ddb404e85b2ce03615a6a4b62', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 14:04:48');
INSERT INTO `sys_operation_log` VALUES ('5e81f5d5ae5f48f7a1204295fb422a3a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:54:40');
INSERT INTO `sys_operation_log` VALUES ('5ee3164394634e80a1c55f001f032893', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:17');
INSERT INTO `sys_operation_log` VALUES ('5f5f1a378fd240cd8c7930d75f555f25', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:04:26');
INSERT INTO `sys_operation_log` VALUES ('60d9bad269e94487b70bfbb580ca8ed9', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:03:14');
INSERT INTO `sys_operation_log` VALUES ('611afeb0c9e64921af56ad1765aace01', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:20:42');
INSERT INTO `sys_operation_log` VALUES ('614129b8056f443895d107f58e4f3487', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:56:54');
INSERT INTO `sys_operation_log` VALUES ('646f8bcc1d5e4b37af25646c4a6e47dc', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:51:18');
INSERT INTO `sys_operation_log` VALUES ('64a744c19dae47fcb8cccdacb05af517', '1', '192.168.2.16', '19', '22', '200', '2019-02-28 15:07:35');
INSERT INTO `sys_operation_log` VALUES ('65ef03a97c9e40318d99575d63867c6d', '1', '192.168.2.16', '9', '11', '400', '2019-02-28 18:25:27');
INSERT INTO `sys_operation_log` VALUES ('66fe46d25ce84d1c9889a2e5d616833b', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:18:26');
INSERT INTO `sys_operation_log` VALUES ('6782b307244a4e9b814fbc9a8dffb380', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:31:38');
INSERT INTO `sys_operation_log` VALUES ('67ade10fd67147d8b33c811d1fec417d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:04:52');
INSERT INTO `sys_operation_log` VALUES ('67ef6ab8bfa24dea822ad7da7f867b3e', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:28:41');
INSERT INTO `sys_operation_log` VALUES ('6a097f742f7e4cb095256ce886f09f1f', '1', '192.168.2.16', '19', '23', '500', '2019-02-28 10:58:16');
INSERT INTO `sys_operation_log` VALUES ('6a7f2f03447043238411130fd8866b17', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:47:22');
INSERT INTO `sys_operation_log` VALUES ('6ae3360979ee4a09bf6842ca1b3fc841', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 17:04:53');
INSERT INTO `sys_operation_log` VALUES ('6b6f65d226944f51ae50e34152ee7afb', '1', '127.0.0.1', '19', '20', '200', '2019-02-28 22:56:26');
INSERT INTO `sys_operation_log` VALUES ('6caa5360f50143438d6fb2da6e1470ff', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 14:42:13');
INSERT INTO `sys_operation_log` VALUES ('6d13641c40f94d3da382645ac1e2e017', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:18');
INSERT INTO `sys_operation_log` VALUES ('6de8dc6cccc84afd85396dfc9cb5d63f', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:04:15');
INSERT INTO `sys_operation_log` VALUES ('6e058f4e297345068837691c9283db1a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:06:23');
INSERT INTO `sys_operation_log` VALUES ('6ed3dcbf3eb94602b16287f241ceda29', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 18:15:55');
INSERT INTO `sys_operation_log` VALUES ('6f379755cbc5445ca665337eace4c6b8', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:40:33');
INSERT INTO `sys_operation_log` VALUES ('6fa2d45610b54d6ea8b5db1ae82039b1', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:56:21');
INSERT INTO `sys_operation_log` VALUES ('6fb8988585b840e0a0dcc0e8f581c23f', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:52:41');
INSERT INTO `sys_operation_log` VALUES ('703f7c8711394f7d8d9beaf7239ef834', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:43:10');
INSERT INTO `sys_operation_log` VALUES ('708274fce8fb481a8fa13575b040f421', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 18:31:15');
INSERT INTO `sys_operation_log` VALUES ('726693a377dd4a3c80a61fd271d787cd', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:31:46');
INSERT INTO `sys_operation_log` VALUES ('72d28faa190c4a1f80d026a44e189673', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:11:06');
INSERT INTO `sys_operation_log` VALUES ('73b398da816e4de1ab93b52730653dbd', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:05:26');
INSERT INTO `sys_operation_log` VALUES ('73f4de6591994eb388096f556636e4a4', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:28:44');
INSERT INTO `sys_operation_log` VALUES ('73ffa01463b5456eb769fea2f029b267', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:13:37');
INSERT INTO `sys_operation_log` VALUES ('74029cbf6b0b494f9004e36f2e531d72', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:31:58');
INSERT INTO `sys_operation_log` VALUES ('77c949b4d8d94128b8c081f5a658afb7', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:40:25');
INSERT INTO `sys_operation_log` VALUES ('77ddde3f39f546e1842453541def0424', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:02:35');
INSERT INTO `sys_operation_log` VALUES ('78d8e6d748bf4c6782e10ad447edda21', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:06:03');
INSERT INTO `sys_operation_log` VALUES ('791e54a259ee4a8e944d858d184875e7', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:58:31');
INSERT INTO `sys_operation_log` VALUES ('7a6e34df6da04187849257446ee14f79', '1', '192.168.2.16', '19', '21', '200', '2019-02-28 15:02:35');
INSERT INTO `sys_operation_log` VALUES ('7c8d874082f041718fa54bfb61773927', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:36');
INSERT INTO `sys_operation_log` VALUES ('7d8e9aea5ad14baa85e245c75d884b13', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:04:56');
INSERT INTO `sys_operation_log` VALUES ('7da1059886d9429a8431adec085daa7e', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 17:08:05');
INSERT INTO `sys_operation_log` VALUES ('7dc28d3687b64f71927288750bf83574', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 16:43:51');
INSERT INTO `sys_operation_log` VALUES ('7f5533865f79438cb2c9d4ebd857a569', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:25:29');
INSERT INTO `sys_operation_log` VALUES ('834858aea2d14b7ea93931a80713c4cb', '1', '192.168.2.16', '19', '21', '200', '2019-02-28 15:03:53');
INSERT INTO `sys_operation_log` VALUES ('835e5f7c8f484bbf9e97c823aa5fcc50', '1', '127.0.0.1', '19', '22', '200', '2019-02-28 22:55:58');
INSERT INTO `sys_operation_log` VALUES ('87023ad03f0340878dd430f06ec1cce4', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:56:51');
INSERT INTO `sys_operation_log` VALUES ('889b1759b49249d0ab60a29543bc247b', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:24:28');
INSERT INTO `sys_operation_log` VALUES ('88c7b79576a049ea897ad04c61fce641', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:33:13');
INSERT INTO `sys_operation_log` VALUES ('88d7d47570f7416fa4ddb983ab82335c', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:07:35');
INSERT INTO `sys_operation_log` VALUES ('8d6f63fbe6804160b34e90522e14fd11', '1', '192.168.2.16', '9', '11', '400', '2019-02-28 18:23:44');
INSERT INTO `sys_operation_log` VALUES ('8d70298b07074d6c8006e104dfac2334', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:33:30');
INSERT INTO `sys_operation_log` VALUES ('8dbf8581d1e74834905b6ecdf88408f3', '1', '192.168.2.16', '19', '23', '400', '2019-02-28 09:48:26');
INSERT INTO `sys_operation_log` VALUES ('907ab8f1446d440e99134fc79d15d59b', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:56:26');
INSERT INTO `sys_operation_log` VALUES ('90fcfeeec6c8415ca11e8f0776a4e48e', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:54:57');
INSERT INTO `sys_operation_log` VALUES ('928a9f34d94b4d07ac944fc68d9a94fb', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:39:14');
INSERT INTO `sys_operation_log` VALUES ('934518f6f5c64e54bd4baa73c8eecf6a', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:34:28');
INSERT INTO `sys_operation_log` VALUES ('954c0a680b344deeb0ded160c9a1f211', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 15:10:17');
INSERT INTO `sys_operation_log` VALUES ('95b10b29da9345709a8a9ea2bda0bd38', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:03:01');
INSERT INTO `sys_operation_log` VALUES ('95e399b8b9a74b22a4deefae19898869', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 16:25:55');
INSERT INTO `sys_operation_log` VALUES ('972528bb33924ed486df242f2b0d0d30', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:00:00');
INSERT INTO `sys_operation_log` VALUES ('98c1861e170d4d9dae0ab282fcf79df9', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:22:13');
INSERT INTO `sys_operation_log` VALUES ('99366f8ef00e4ea3a33be8c0907e0a95', '1', '192.168.2.16', '19', '21', '500', '2019-02-28 15:01:47');
INSERT INTO `sys_operation_log` VALUES ('9983d6a8bdcf4a309fd1ad5b14f2149f', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 18:23:38');
INSERT INTO `sys_operation_log` VALUES ('9b1bb712215b42a8bc511849275e027d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:08:43');
INSERT INTO `sys_operation_log` VALUES ('9b4a0a91fde348e08f890e2e4eb546d0', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:41:49');
INSERT INTO `sys_operation_log` VALUES ('9ba3c9c6bcce4010a24a07dde751bdfb', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:40:40');
INSERT INTO `sys_operation_log` VALUES ('9c2d23a27c384aef95e5c5189a697013', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:09:45');
INSERT INTO `sys_operation_log` VALUES ('9c4470c3ace04e87b32e86886dfcf6aa', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:09:55');
INSERT INTO `sys_operation_log` VALUES ('a1da9520377e41f783f84acc57d35853', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 15:09:52');
INSERT INTO `sys_operation_log` VALUES ('a39a5348c7a54a18ae06d5e45d8f6753', '1', '192.168.2.16', '9', '18', '200', '2019-02-27 16:25:58');
INSERT INTO `sys_operation_log` VALUES ('a4b5ea82a315454f9c54e2a711375db5', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:03:53');
INSERT INTO `sys_operation_log` VALUES ('a616e4df46804d87a07630e89451d8d5', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:53:29');
INSERT INTO `sys_operation_log` VALUES ('a67f364e79f6433291b6e0f1c4364d60', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:09:32');
INSERT INTO `sys_operation_log` VALUES ('a726cb71acfc46a6aaef46f2e16a7be8', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:03:38');
INSERT INTO `sys_operation_log` VALUES ('a75275a61ce24bf69b847e6c86d7df05', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:10:17');
INSERT INTO `sys_operation_log` VALUES ('a7875b65e7ab4f30b991d907f0a82a94', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:48:12');
INSERT INTO `sys_operation_log` VALUES ('a78c83faec7549ff9b36c416d495fda4', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 15:10:20');
INSERT INTO `sys_operation_log` VALUES ('a7e0c77de4c64fddae9703fa837922fc', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:58:19');
INSERT INTO `sys_operation_log` VALUES ('a8b9d20c3c65499f9c0e9f01588626d4', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:04:38');
INSERT INTO `sys_operation_log` VALUES ('a915c8e2d735467385277ce70f19524b', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:00:30');
INSERT INTO `sys_operation_log` VALUES ('a984ffc379d9497b9cf157a4d6e4a109', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:13:37');
INSERT INTO `sys_operation_log` VALUES ('aa6eaf35740647439220e81566c340db', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:04:51');
INSERT INTO `sys_operation_log` VALUES ('aaec77f2b54b40659b101951b28ff92b', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:02:31');
INSERT INTO `sys_operation_log` VALUES ('ae7a45506f4f41bfbd28d03f7d171978', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:32');
INSERT INTO `sys_operation_log` VALUES ('ae8b7a58e3f749b7b16fb50cccbf5d57', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:39:23');
INSERT INTO `sys_operation_log` VALUES ('aec29754ba134ce89809694d7d011b8c', '1', '192.168.2.16', '2', '3', '200', '2019-02-28 18:16:11');
INSERT INTO `sys_operation_log` VALUES ('b1ebf3b2428a4be29103d887744bdfa0', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:03:40');
INSERT INTO `sys_operation_log` VALUES ('b1ff25b6b0f140eaaef8db099349ab17', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:00:02');
INSERT INTO `sys_operation_log` VALUES ('b360addfe087420ea315faba3da37609', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:37:53');
INSERT INTO `sys_operation_log` VALUES ('b50d70f0f0b2462dbfce0b9c785ee1ab', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:06:18');
INSERT INTO `sys_operation_log` VALUES ('b56a04f9247d4f2ebedec6bec778d4e5', '1', '192.168.2.16', '9', '11', '400', '2019-02-28 18:24:23');
INSERT INTO `sys_operation_log` VALUES ('b6660096be384bc3848653b81a6d684d', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 15:10:20');
INSERT INTO `sys_operation_log` VALUES ('b7d59014f0a24bab8c3f6561ae4133dd', '1', '192.168.2.16', '2', '3', '500', '2019-02-27 16:26:09');
INSERT INTO `sys_operation_log` VALUES ('b8bda4a9fb1741ad8f563599e96fa0c1', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:04:51');
INSERT INTO `sys_operation_log` VALUES ('b8ccda699847477cba79ecc6ad6b28ea', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 18:09:59');
INSERT INTO `sys_operation_log` VALUES ('bae0f7f7510e4a2998ef018fe5c7ddd4', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:40:52');
INSERT INTO `sys_operation_log` VALUES ('bf038835fefe4732b5864919e59c9015', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:04:42');
INSERT INTO `sys_operation_log` VALUES ('bf6beee757f24c0ebb9d89dbe8ee8746', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:01:41');
INSERT INTO `sys_operation_log` VALUES ('bfe8b6f9965e4b618294f7caed276a5a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:12:25');
INSERT INTO `sys_operation_log` VALUES ('c136daebf02f474284822a5f9d161ddf', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:15');
INSERT INTO `sys_operation_log` VALUES ('c14af4210c8847079574792a3fe632bd', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:04:39');
INSERT INTO `sys_operation_log` VALUES ('c197eb4a5c2541ea95823eb542abca2b', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:21:15');
INSERT INTO `sys_operation_log` VALUES ('c41ee3a9dcff4b14bc6ac08eeb2f6b55', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:33:49');
INSERT INTO `sys_operation_log` VALUES ('c4e9d8628fdc4fc2802f19b5955ccb83', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:01:44');
INSERT INTO `sys_operation_log` VALUES ('c53a0d44168e46c2b246441986d99fda', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 14:04:22');
INSERT INTO `sys_operation_log` VALUES ('c598501929ef478f9b55d9aba58ff210', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:58:29');
INSERT INTO `sys_operation_log` VALUES ('c686bf1e136c49638dc92e7d0cb0461c', '1', '192.168.2.16', '2', '17', '200', '2019-02-27 16:26:05');
INSERT INTO `sys_operation_log` VALUES ('c7f61ed95739475b81e8664c88da6f99', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:02:42');
INSERT INTO `sys_operation_log` VALUES ('c9196501f5fe41eb913b58884c9efa09', '1', '192.168.2.16', '19', '23', '500', '2019-02-28 11:27:30');
INSERT INTO `sys_operation_log` VALUES ('ca2a35ddbbbd42be94e34d2a0316091d', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:30:17');
INSERT INTO `sys_operation_log` VALUES ('cba6486745aa46ab9f19dd058c813dc6', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:54:22');
INSERT INTO `sys_operation_log` VALUES ('cc55fb58950c4d57ace239852ad100bf', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:01:31');
INSERT INTO `sys_operation_log` VALUES ('cd8a3eebdf08421b952de66b45520a34', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 18:25:21');
INSERT INTO `sys_operation_log` VALUES ('cda34012fc8f48ffb0eff4f32815f001', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:26:04');
INSERT INTO `sys_operation_log` VALUES ('ce2033a563884ae88e8ab0622f6c985c', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:29:15');
INSERT INTO `sys_operation_log` VALUES ('ce35d71a31ee49dba20c23b3a5e7f38e', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:56:27');
INSERT INTO `sys_operation_log` VALUES ('cfd8d8b663b441cfb11998e5841b6702', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:30:39');
INSERT INTO `sys_operation_log` VALUES ('d0ca026101a54c8684944a0e495ff58c', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:02:59');
INSERT INTO `sys_operation_log` VALUES ('d2afb480d82644658d6e2fef10abc7cf', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:37:28');
INSERT INTO `sys_operation_log` VALUES ('d345cf5c701d4c9881fc15a5e61c3ac9', '1', '192.168.2.16', '19', '21', '200', '2019-02-28 15:02:41');
INSERT INTO `sys_operation_log` VALUES ('d58f9a55435c49a0a64e22a621771592', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:10:10');
INSERT INTO `sys_operation_log` VALUES ('d6c342044c2f46c0bbc93611e3a80e1e', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:17:35');
INSERT INTO `sys_operation_log` VALUES ('d7b88437798b47389aa1ade6be097b0f', '1', '192.168.2.16', '19', '22', '200', '2019-02-28 15:04:51');
INSERT INTO `sys_operation_log` VALUES ('d91ae926e1b44de88a9b757a1950e75a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 16:44:25');
INSERT INTO `sys_operation_log` VALUES ('d9c8be64652c4247b73a53e4c38bcfac', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:51:45');
INSERT INTO `sys_operation_log` VALUES ('db52436327f14acfb2747f75a7cfede5', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 14:04:52');
INSERT INTO `sys_operation_log` VALUES ('dc0326d7a2fb4fffa8f659e5c656aa71', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:05:58');
INSERT INTO `sys_operation_log` VALUES ('dc10dc7266354dc88839383ae760af64', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:07:34');
INSERT INTO `sys_operation_log` VALUES ('dd429f62bcea495c98b4e3b0ac2ef1a0', '1', '192.168.2.16', '19', '20', '200', '2019-02-28 15:08:08');
INSERT INTO `sys_operation_log` VALUES ('de443157e2be486b83c1906c120cdead', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:50:50');
INSERT INTO `sys_operation_log` VALUES ('df715e2a30df43b6a28c22f128d44076', '1', '192.168.2.16', '9', '18', '200', '2019-02-27 17:08:00');
INSERT INTO `sys_operation_log` VALUES ('e1139aca9cff44dbafc0bc546e7249cd', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:04:40');
INSERT INTO `sys_operation_log` VALUES ('e3a73979f0334511bb9163241ffab931', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:30:42');
INSERT INTO `sys_operation_log` VALUES ('e4425f7561a94d81b13ef15ae78cea4c', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 10:29:31');
INSERT INTO `sys_operation_log` VALUES ('e4a6e10f81df48d7a8d04eb82413181f', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:26:02');
INSERT INTO `sys_operation_log` VALUES ('e4e89c015c30487085b640f00526069a', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:31:46');
INSERT INTO `sys_operation_log` VALUES ('e6f0d285626545c8a1e6b45e7177d3d4', '1', '192.168.2.16', '9', '18', '200', '2019-02-28 18:24:10');
INSERT INTO `sys_operation_log` VALUES ('e724de89cc8d44b19aadbe7262fc7606', '1', '127.0.0.1', '19', '23', '200', '2019-02-28 22:48:43');
INSERT INTO `sys_operation_log` VALUES ('ebb33a3355234c17b46db95662ac9b22', '1', '127.0.0.1', '19', '20', '200', '2019-02-28 22:33:50');
INSERT INTO `sys_operation_log` VALUES ('ebd9436eaa4646dabb50409edccea9b8', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:35:14');
INSERT INTO `sys_operation_log` VALUES ('ec12d4d5e60e42fcb1f80013c17d937c', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:39:19');
INSERT INTO `sys_operation_log` VALUES ('ec5692e5b7b3416695a74604fc98b8fd', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:07:29');
INSERT INTO `sys_operation_log` VALUES ('ee2c7b51df014fecba11c8b04224fab7', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:07:59');
INSERT INTO `sys_operation_log` VALUES ('ee3293052185444badeb9885a88bbcfe', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:08:12');
INSERT INTO `sys_operation_log` VALUES ('f176d16db4e04f80868af72e7d91de68', '1', '192.168.2.16', '2', '17', '200', '2019-02-28 15:09:50');
INSERT INTO `sys_operation_log` VALUES ('f48a8cadf1d3476aad6c9ee303a800f2', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:10:36');
INSERT INTO `sys_operation_log` VALUES ('f5696eea242847989c91dde3e65a1352', '1', '127.0.0.1', '19', '21', '200', '2019-02-28 22:17:35');
INSERT INTO `sys_operation_log` VALUES ('f6ca169643094f528f9e9ce1f0c61fce', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:42:28');
INSERT INTO `sys_operation_log` VALUES ('f6cf582ee8af4524ba1bf4b17f6fe707', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 13:57:12');
INSERT INTO `sys_operation_log` VALUES ('f7937b2816a745c4985670235a34173a', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 11:41:34');
INSERT INTO `sys_operation_log` VALUES ('f8742c33a6364a84abf3f58aac186467', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 17:06:20');
INSERT INTO `sys_operation_log` VALUES ('f889a3f601b64df487ccbad96dc10f8b', '1', '192.168.2.16', '19', '23', '500', '2019-02-28 10:58:51');
INSERT INTO `sys_operation_log` VALUES ('f8af277cf987483ea0fb03e857c52600', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:59:36');
INSERT INTO `sys_operation_log` VALUES ('f95804bdf4974c9eb89f800dc7c3b248', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:29:25');
INSERT INTO `sys_operation_log` VALUES ('faf4fb20b1644219be540079f0d9c542', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:06:03');
INSERT INTO `sys_operation_log` VALUES ('fba68f8e9eb34a82bc1c91204d858a23', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 14:59:34');
INSERT INTO `sys_operation_log` VALUES ('fc337613e47a4cf8a763836b0856e2e4', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 15:08:08');
INSERT INTO `sys_operation_log` VALUES ('fc8af23f88cd4dab8d0d7ab81a4abb21', '1', '192.168.2.16', '19', '23', '200', '2019-02-28 09:56:48');

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
INSERT INTO `sys_role_menu` VALUES ('24', '1', '24');
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
INSERT INTO `sys_user` VALUES ('907825bdc5eb48ba86f973802d045b73', '9fc67265a1ef4ed9b37ac08dd69d740c', 'wzy', '$2a$10$Pp7SdIiHvtTdZY3W43PVqeQmgqXVeFJx6vkxl1heK4LqtLMiMctwW', '吴智勇', '1', null, '1', '1', '1', '2019-02-28 18:16:10', null, null);
