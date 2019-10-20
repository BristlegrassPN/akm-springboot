/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : akm-springboot

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 20/10/2019 23:56:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_article
-- ----------------------------
DROP TABLE IF EXISTS `demo_article`;
CREATE TABLE `demo_article`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号（uuid）',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `public_status` tinyint(4) NULL DEFAULT 0 COMMENT '发布状态（1启用，0未启用）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 999114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, '1', 'string', '1991-06-17', '2019-08-10 22:11:17', '2019-08-22 17:06:36');
INSERT INTO `demo_user` VALUES (2, 'string', 'string', '1991-06-17', '2019-08-10 22:29:28', '2019-08-11 00:47:43');
INSERT INTO `demo_user` VALUES (3, 'ceshi', '测试', '1991-06-17', '2019-08-10 22:52:58', NULL);
INSERT INTO `demo_user` VALUES (4, 'ceshi2', '测试2', '1991-06-17', '2019-08-10 22:53:07', NULL);
INSERT INTO `demo_user` VALUES (5, 'ceshi3', '', '1991-06-17', '2019-08-10 22:53:07', '2019-08-13 22:19:57');
INSERT INTO `demo_user` VALUES (6, 'ceshi4', '测试4', '1991-06-17', '2019-08-10 22:53:26', NULL);
INSERT INTO `demo_user` VALUES (7, 'string', '别墅', '1991-06-17', '2019-08-11 00:43:22', '2019-08-13 22:20:21');
INSERT INTO `demo_user` VALUES (999111, 'string', 'string', '1991-06-17', '2019-08-11 01:21:26', '2019-08-19 18:43:45');
INSERT INTO `demo_user` VALUES (999112, 'string', 'string', '1991-06-17', '2019-10-17 21:50:40', NULL);
INSERT INTO `demo_user` VALUES (999113, '111', '111', '1991-06-17', '2019-10-17 21:50:52', NULL);

-- ----------------------------
-- Table structure for sys_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_api`;
CREATE TABLE `sys_api`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父id，0表示根节点',
  `type` tinyint(4) NOT NULL DEFAULT 2 COMMENT '类型：1目录，2uri',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'api接口名称',
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'service接口的权限验证规则，如：/user/op/save、/user/op/**',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `seq` int(11) NULL DEFAULT 0 COMMENT '排序（倒序）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  `del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志(默认0,删除1)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api
-- ----------------------------
INSERT INTO `sys_api` VALUES ('1184012076797005824', '1184015271476727808', 2, '查询权限', '/sys/user/view/**', '', 10, '2019-10-15 15:44:11', '2019-10-15 16:31:42', 0);
INSERT INTO `sys_api` VALUES ('1184012153326276608', '1184015271476727808', 2, '操作权限', '/sys/user/op/**', '', 9, '2019-10-15 15:44:29', '2019-10-15 16:31:45', 0);
INSERT INTO `sys_api` VALUES ('1184013370299387904', '0', 1, '资源管理', NULL, '', 90, '2019-10-15 15:49:20', '2019-10-17 18:57:45', 0);
INSERT INTO `sys_api` VALUES ('1184013443590656000', '1184013370299387904', 2, '查询权限', '/sys/resource/view/**', '', 10, '2019-10-15 15:49:37', '2019-10-15 16:31:24', 0);
INSERT INTO `sys_api` VALUES ('1184013488184496128', '1184013370299387904', 2, '操作权限', '/sys/resource/op/**', '', 9, '2019-10-15 15:49:48', '2019-10-15 16:31:27', 0);
INSERT INTO `sys_api` VALUES ('1184013908491505664', '0', 1, '角色管理', NULL, '', 80, '2019-10-15 15:51:28', '2019-10-17 18:57:45', 0);
INSERT INTO `sys_api` VALUES ('1184013977244536832', '1184013908491505664', 2, '查询权限', '/sys/role/view/**', '', 10, '2019-10-15 15:51:44', '2019-10-15 16:31:33', 0);
INSERT INTO `sys_api` VALUES ('1184014048031805440', '1184013908491505664', 2, '操作权限', '/sys/role/op/**', '', 9, '2019-10-15 15:52:01', '2019-10-15 16:31:36', 0);
INSERT INTO `sys_api` VALUES ('1184014181075128320', '0', 1, '数据字典管理', NULL, '', 60, '2019-10-15 15:52:33', '2019-10-17 18:57:45', 0);
INSERT INTO `sys_api` VALUES ('1184014256035729408', '1184014181075128320', 2, '查询权限', '/sys/dict/view/**', '', 10, '2019-10-15 15:52:51', '2019-10-15 16:31:50', 0);
INSERT INTO `sys_api` VALUES ('1184014315359965184', '1184014181075128320', 2, '操作权限', '/sys/dict/op/**', '', 9, '2019-10-15 15:53:05', '2019-10-15 16:31:53', 0);
INSERT INTO `sys_api` VALUES ('1184015271476727808', '0', 1, '用户管理', NULL, '', 70, '2019-10-15 15:56:53', '2019-10-17 18:57:45', 0);
INSERT INTO `sys_api` VALUES ('1184015523596341248', '0', 1, 'Api管理', NULL, '', 100, '2019-10-15 15:57:53', '2019-10-17 18:57:45', 0);
INSERT INTO `sys_api` VALUES ('1184015685500669952', '1184015523596341248', 2, '查询权限', '/sys/api/view/**', '', 10, '2019-10-15 15:58:32', '2019-10-15 16:31:16', 0);
INSERT INTO `sys_api` VALUES ('1184015759681130496', '1184015523596341248', 2, '操作权限', '/sys/api/op/**', '', 9, '2019-10-15 15:58:49', '2019-10-15 16:31:19', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码（英文，多个单词下划线分隔）',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称（用于给用户展示）',
  `value` int(11) NOT NULL COMMENT '值（业务表保存该值）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `seq` int(11) NULL DEFAULT 0 COMMENT '排序（倒序）',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'sex', '性别', '男', 1, NULL, 0, '2019-08-14 15:56:23', '2019-08-30 10:15:05');
INSERT INTO `sys_dict` VALUES (2, 'sex', '性别', '女', 2, NULL, 0, '2019-08-14 15:57:55', '2019-08-30 10:15:05');
INSERT INTO `sys_dict` VALUES (3, 'resource_type', '资源类型', '目录', 1, NULL, 0, '2019-08-30 10:12:26', '2019-08-30 10:17:01');
INSERT INTO `sys_dict` VALUES (4, 'resource_type', '资源类型', '菜单', 2, NULL, 0, '2019-08-19 19:04:48', '2019-08-30 10:16:57');
INSERT INTO `sys_dict` VALUES (5, 'resource_type', '资源类型', '按钮', 3, NULL, 0, '2019-08-19 19:05:05', '2019-08-30 10:16:48');
INSERT INTO `sys_dict` VALUES (6, 'resource_type', '资源类型', '其他', 4, NULL, 0, '2019-08-19 19:05:20', '2019-08-30 10:16:45');
INSERT INTO `sys_dict` VALUES (7, 'client_type', '客户端类型', '后台管理系统', 1, NULL, 0, '2019-08-19 19:06:33', '2019-08-30 10:16:40');
INSERT INTO `sys_dict` VALUES (8, 'client_type', '客户端类型', 'web系统', 2, NULL, 0, '2019-08-19 19:06:54', '2019-08-30 10:16:37');
INSERT INTO `sys_dict` VALUES (9, 'client_type', '客户端类型', 'app', 3, NULL, 0, '2019-08-19 19:08:29', '2019-08-30 10:16:34');
INSERT INTO `sys_dict` VALUES (10, 'api_type', 'api记录类型', '目录', 1, '', 0, '2019-10-11 19:52:45', '2019-10-17 18:39:02');
INSERT INTO `sys_dict` VALUES (11, 'api_type', 'api记录类型', 'uri', 2, NULL, 0, '2019-10-17 18:38:40', '2019-10-17 18:39:12');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父id，0表示根节点',
  `type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '资源类型1:目录,2:菜单,3:按钮,4:其他',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `uri` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单uri',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源编码，前端根据该code控制资源的显示隐藏',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源图标class',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '排序（倒序）',
  `client_type` tinyint(4) NOT NULL COMMENT '应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用(默认1,禁用0)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  `del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志(默认0,删除1)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '前端资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1164368483723644928', '0', 2, '测试', NULL, '1', 'el-icon-star-on', '', 0, 2, 1, '2019-08-22 10:47:34', '2019-08-30 11:04:00', 0);
INSERT INTO `sys_resource` VALUES ('1167003012795863040', '0', 1, '系统管理', NULL, '', 'el-icon-star-on', '', 100, 1, 1, '2019-08-29 17:16:14', '2019-10-15 16:16:01', 0);
INSERT INTO `sys_resource` VALUES ('1167003348218548224', '1167003012795863040', 2, 'Api接口管理', '/layout/api', 'sys_api', 'el-icon-star-on', '接口管理', 100, 1, 1, '2019-08-29 17:17:34', '2019-10-15 16:00:51', 0);
INSERT INTO `sys_resource` VALUES ('1167003541898924032', '1167003012795863040', 2, '系统资源管理', '/layout/resource', 'sys_menu', 'el-icon-star-on', '', 90, 1, 1, '2019-08-29 17:18:20', '2019-08-30 11:10:06', 0);
INSERT INTO `sys_resource` VALUES ('1167003860695388160', '1167003012795863040', 2, '角色管理', '/layout/role', 'sys_role', 'el-icon-star-on', '', 80, 1, 1, '2019-08-29 17:19:36', '2019-10-15 16:12:42', 0);
INSERT INTO `sys_resource` VALUES ('1167004033748176896', '1167003012795863040', 2, '用户管理', '/layout/user', 'sys_user', 'el-icon-star-on', '', 70, 1, 1, '2019-08-29 17:20:18', '2019-10-15 16:13:30', 0);
INSERT INTO `sys_resource` VALUES ('1182617613847629824', '0', 1, '项目管理', '/layout/user', 'sys_user', 'el-icon-star-on', '', 0, 1, 1, '2019-10-11 19:23:05', '2019-10-11 19:24:33', 0);
INSERT INTO `sys_resource` VALUES ('1182618054442487808', '1182617613847629824', 2, '文档平台', '/layout/test1', 'sys_user', 'el-icon-star-on', '', 0, 1, 1, '2019-10-11 19:24:50', '2019-10-20 22:15:23', 0);
INSERT INTO `sys_resource` VALUES ('1182618097920643072', '1182617613847629824', 2, '安全交底', '/layout/test2', 'sys_user', 'el-icon-star-on', '', 0, 1, 1, '2019-10-11 19:25:01', '2019-10-20 22:15:30', 0);
INSERT INTO `sys_resource` VALUES ('1182618242573799424', '1182618097920643072', 3, '项目进度', '/layout/user', 'sys_pr', 'el-icon-star-on', '', 0, 1, 1, '2019-10-11 19:25:35', NULL, 0);
INSERT INTO `sys_resource` VALUES ('1182618316951392256', '1182618097920643072', 3, '编辑', '/layout/user', 'edit', 'el-icon-star-on', '', 0, 1, 1, '2019-10-11 19:25:53', '2019-10-20 22:15:37', 1);
INSERT INTO `sys_resource` VALUES ('1184016665428496384', '1167003012795863040', 2, '数据字典管理', '/layout/dict', 'sys_role', 'el-icon-star-on', '', 0, 1, 1, '2019-10-15 16:02:25', NULL, 0);
INSERT INTO `sys_resource` VALUES ('1185844175229292544', '1182618097920643072', 3, '编辑', '/edit', 'edit', 'el-icon-star-on', '', 0, 1, 1, '2019-10-20 17:04:17', NULL, 0);

-- ----------------------------
-- Table structure for sys_resource_api
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_api`;
CREATE TABLE `sys_resource_api`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `resource_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_resource表id',
  `api_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_api表id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源和api关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_resource_api
-- ----------------------------
INSERT INTO `sys_resource_api` VALUES ('1185837142945959936', '1167003541898924032', '1184013443590656000', '2019-10-20 16:36:21', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185837142945959937', '1167003541898924032', '1184013488184496128', '2019-10-20 16:36:21', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185837162915041280', '1167004033748176896', '1184012076797005824', '2019-10-20 16:36:26', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185837162915041281', '1167004033748176896', '1184012153326276608', '2019-10-20 16:36:26', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185837170083106816', '1184016665428496384', '1184014256035729408', '2019-10-20 16:36:27', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185837170083106817', '1184016665428496384', '1184014315359965184', '2019-10-20 16:36:27', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185842194246930432', '1167003348218548224', '1184015685500669952', '2019-10-20 16:56:25', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185842194246930433', '1167003348218548224', '1184015759681130496', '2019-10-20 16:56:25', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185857353661157376', '1167003860695388160', '1184013977244536832', '2019-10-20 17:56:39', NULL);
INSERT INTO `sys_resource_api` VALUES ('1185857353661157377', '1167003860695388160', '1184014048031805440', '2019-10-20 17:56:39', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `seq` int(11) NOT NULL DEFAULT 0 COMMENT '排序（倒序）',
  `client_type` tinyint(4) NOT NULL COMMENT '应用类型,对应字典表client_type,由于前端分离,后台系统可能要对接多个客户端,如web，app',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用(默认1,禁用0)',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  `del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志(默认0,删除1)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '111', '3', 1, 1, 1, '2019-08-22 18:06:14', '2019-10-15 16:13:42', 1);
INSERT INTO `sys_role` VALUES ('1164384808021921792', '超级管理员', '拥有系统所有权限', 999, 1, 1, '2019-08-22 11:54:07', '2019-10-20 17:52:22', 0);
INSERT INTO `sys_role` VALUES ('1164384882701504512', '管理员', '拥有除权限管理所有权限', 998, 1, 1, '2019-08-22 11:54:07', '2019-08-28 11:26:38', 0);
INSERT INTO `sys_role` VALUES ('1164488227080507392', '122', '12', 12, 1, 1, '2019-08-22 18:43:23', '2019-08-23 09:22:50', 1);
INSERT INTO `sys_role` VALUES ('1164488393455964160', '12333', '', 12, 1, 1, '2019-08-22 18:44:03', '2019-10-15 16:13:39', 1);
INSERT INTO `sys_role` VALUES ('1164489000409501696', '34', '3', 3, 1, 1, '2019-08-22 18:46:27', '2019-08-22 19:49:14', 1);
INSERT INTO `sys_role` VALUES ('1164502777158832128', '12', '12', 12, 1, 1, '2019-08-22 19:41:12', '2019-08-22 19:52:12', 1);
INSERT INTO `sys_role` VALUES ('1164503733330120704', '12', '', 12, 1, 1, '2019-08-22 19:45:00', '2019-08-22 19:51:47', 1);
INSERT INTO `sys_role` VALUES ('1164503861029900288', '1', '', 1, 1, 1, '2019-08-22 19:45:30', '2019-08-22 19:48:07', 1);
INSERT INTO `sys_role` VALUES ('1164504018614095872', '12', '12', 12, 1, 1, '2019-08-22 19:46:08', '2019-08-22 19:51:45', 1);
INSERT INTO `sys_role` VALUES ('1164504096531681280', '12', '12', 12, 1, 1, '2019-08-22 19:46:26', '2019-08-22 19:51:00', 1);
INSERT INTO `sys_role` VALUES ('1164824314609012736', '测试', '', 1, 1, 1, '2019-08-23 16:58:52', '2019-08-23 16:59:28', 1);
INSERT INTO `sys_role` VALUES ('1164828140086890496', '踩踩踩', '12', 12, 1, 1, '2019-08-23 17:14:04', '2019-08-23 17:15:42', 1);
INSERT INTO `sys_role` VALUES ('1164828400809021440', '12', '1', 12, 1, 1, '2019-08-23 17:15:06', '2019-08-23 17:15:41', 1);
INSERT INTO `sys_role` VALUES ('1164828574583230464', '12', '', 12, 1, 1, '2019-08-23 17:15:48', '2019-08-23 17:17:15', 1);
INSERT INTO `sys_role` VALUES ('1164828955958710272', '123', '1', 1, 1, 1, '2019-08-23 17:17:19', '2019-08-23 17:17:38', 1);
INSERT INTO `sys_role` VALUES ('1164829050569625600', '4', '', 4, 1, 1, '2019-08-23 17:17:41', '2019-10-15 16:13:41', 1);
INSERT INTO `sys_role` VALUES ('1183309188466610176', '项目经理', '', 0, 1, 1, '2019-10-13 17:11:10', '2019-10-20 17:53:10', 0);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_role表id',
  `resource_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_resource表id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和前端资源关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('1164828172945068032', '1164828140086890496', '1164363555865235456', '2019-08-23 17:14:12', NULL);
INSERT INTO `sys_role_resource` VALUES ('1164828592388050944', '1164828574583230464', '1164363555865235456', '2019-08-23 17:15:52', NULL);
INSERT INTO `sys_role_resource` VALUES ('1164829005568937984', '1164828955958710272', '1164363555865235456', '2019-08-23 17:17:31', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618674922655744', '1164488393455964160', '1182617613847629824', '2019-10-11 19:27:18', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618674922655745', '1164488393455964160', '1182618054442487808', '2019-10-11 19:27:18', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618674922655746', '1164488393455964160', '1182618097920643072', '2019-10-11 19:27:18', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618674922655747', '1164488393455964160', '1182618316951392256', '2019-10-11 19:27:18', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618674922655748', '1164488393455964160', '1182618242573799424', '2019-10-11 19:27:18', NULL);
INSERT INTO `sys_role_resource` VALUES ('1182618695772540928', '1164829050569625600', '1167003348218548224', '2019-10-11 19:27:23', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744256', '1164384808021921792', '1164368483723644928', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744257', '1164384808021921792', '1167003012795863040', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744258', '1164384808021921792', '1167003348218548224', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744259', '1164384808021921792', '1167003541898924032', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744260', '1164384808021921792', '1167003860695388160', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744261', '1164384808021921792', '1167004033748176896', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744262', '1164384808021921792', '1184016665428496384', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744263', '1164384808021921792', '1182617613847629824', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744264', '1164384808021921792', '1182618097920643072', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744265', '1164384808021921792', '1182618242573799424', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744266', '1164384808021921792', '1182618316951392256', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1184019753979744267', '1164384808021921792', '1182618054442487808', '2019-10-15 16:14:42', NULL);
INSERT INTO `sys_role_resource` VALUES ('1185940550839308288', '1183309188466610176', '1182617613847629824', '2019-10-20 23:27:15', NULL);
INSERT INTO `sys_role_resource` VALUES ('1185940550839308289', '1183309188466610176', '1182618097920643072', '2019-10-20 23:27:15', NULL);
INSERT INTO `sys_role_resource` VALUES ('1185940550839308290', '1183309188466610176', '1182618242573799424', '2019-10-20 23:27:15', NULL);
INSERT INTO `sys_role_resource` VALUES ('1185940550839308291', '1183309188466610176', '1182618054442487808', '2019-10-20 23:27:15', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `password` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码加盐',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用(默认1,禁用0)',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  `del` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志(默认0,删除1)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '5bf0dd481a93ef89bb6bfb50f8b6b397', '934a451d3e7caca3b5e8957cf62e9436', 'yanxiaojun617', '闫小军', '18688498342', 1, '2020-03-10 22:09:29', '2019-08-25 21:28:19', '2019-10-10 22:51:59', 0);
INSERT INTO `sys_user` VALUES ('1166186974026010624', '8e928c702f3ecac55cf3e002c631b1a1', '491fc7a7c36c674f7b42d1dbd03325d0', '12', '12', '12', 1, NULL, '2019-08-27 11:13:35', '2019-10-13 17:06:38', 1);
INSERT INTO `sys_user` VALUES ('1166193342090973184', 'b610eb15fa88e90dc1030cf3ef644d69', 'f12c716e5d9d32cbfdc203d82eb78760', '1', '1', '13', 1, NULL, '2019-08-27 11:38:54', '2019-08-27 15:53:51', 0);
INSERT INTO `sys_user` VALUES ('1166193557011304448', '4e03639ee76ee80e7dc1712cc53b6ae4', 'a4ffcdbfd265153eb9401fbc0c6baa40', '6', '6', '', 1, NULL, '2019-08-27 11:39:45', '2019-08-28 14:47:16', 1);
INSERT INTO `sys_user` VALUES ('1166239412535824384', 'a7bf88562e6a71c761f350e9240f87de', 'c0b36a2d2633d945c1a48f5d18df1cf8', '4', '张三', '', 1, NULL, '2019-08-27 14:41:58', '2019-10-13 14:31:06', 0);
INSERT INTO `sys_user` VALUES ('1166370457520312320', '5c2a72b46641c2d1fd039e88bc9d39da', 'fd211f0fddd181b1c4774162f413b37a', 'yanxiaojun', '小军', '18688498342', 1, NULL, '2019-08-27 23:22:41', '2019-10-10 22:50:42', 0);
INSERT INTO `sys_user` VALUES ('1166618676292620288', '85937259bdd8aa4196c8c0dca4c0cdf0', '0e0d795d618470e32bdbbe3d33907765', 'lisi', '李四', '', 1, '2019-08-28 16:17:48', '2019-08-28 15:49:01', '2019-10-13 17:06:27', 1);
INSERT INTO `sys_user` VALUES ('1182622013265678336', 'dd605889a1f13dc0c3e3ce408ca30d83', '1b842edff1d87165c96eb10bd86b1cab', '123456', '测试', '18688498342', 1, NULL, '2019-10-11 19:40:34', '2019-10-13 17:06:24', 1);
INSERT INTO `sys_user` VALUES ('1183307967865425920', 'dbeeada8a08ba7128e326923463c8b0c', '9df143d53a943b0f503c8c8b7955018f', 'admin', '管理员', '18688498342', 1, NULL, '2019-10-13 17:06:19', NULL, 0);
INSERT INTO `sys_user` VALUES ('1183309277562015744', '8bd1debd2a1beb5d94677d708216b722', '805e7cf9111261a8a5542c4660b662e1', 'project', '项目经理', '18688498342', 1, NULL, '2019-10-13 17:11:31', '2019-10-13 17:11:51', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_user表id',
  `role_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sys_role表id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动更新）',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间（自动更新）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1166543191294480384', '1', '1164384808021921792', '2019-08-28 10:49:04', NULL);
INSERT INTO `sys_user_role` VALUES ('1166543191294480385', '1', '1164384882701504512', '2019-08-28 10:49:04', NULL);
INSERT INTO `sys_user_role` VALUES ('1166569988564324352', '1165277302964228000', '1164384882701504512', '2019-08-28 12:35:33', NULL);
INSERT INTO `sys_user_role` VALUES ('1166602381052481536', '1165277302964228096', '1164384882701504512', '2019-08-28 14:44:16', NULL);
INSERT INTO `sys_user_role` VALUES ('1166602381052481537', '1165277302964228096', '1164384808021921792', '2019-08-28 14:44:16', NULL);
INSERT INTO `sys_user_role` VALUES ('1166613375443144704', '1166603271616466944', '1', '2019-08-28 15:27:57', NULL);
INSERT INTO `sys_user_role` VALUES ('1166626072599597056', '1166617090778599424', '1164384882701504512', '2019-08-28 16:18:25', NULL);
INSERT INTO `sys_user_role` VALUES ('1166626072599597057', '1166617090778599424', '1164384808021921792', '2019-08-28 16:18:25', NULL);
INSERT INTO `sys_user_role` VALUES ('1167764584254279680', '1166618676292620288', '1164829050569625600', '2019-08-31 19:42:27', NULL);
INSERT INTO `sys_user_role` VALUES ('1167764584254279681', '1166618676292620288', '1164384808021921792', '2019-08-31 19:42:27', NULL);
INSERT INTO `sys_user_role` VALUES ('1167764584254279682', '1166618676292620288', '1164384882701504512', '2019-08-31 19:42:27', NULL);
INSERT INTO `sys_user_role` VALUES ('1183307968771395584', '1183307967865425920', '1164384808021921792', '2019-10-13 17:06:19', NULL);
INSERT INTO `sys_user_role` VALUES ('1183309361481650176', '1183309277562015744', '1183309188466610176', '2019-10-13 17:11:51', NULL);
INSERT INTO `sys_user_role` VALUES ('1185039015263473664', '1166370457520312320', '1164384808021921792', '2019-10-18 11:44:52', NULL);
INSERT INTO `sys_user_role` VALUES ('1185039015263473665', '1166370457520312320', '1164384882701504512', '2019-10-18 11:44:52', NULL);
INSERT INTO `sys_user_role` VALUES ('1185039015263473666', '1166370457520312320', '1183309188466610176', '2019-10-18 11:44:52', NULL);

SET FOREIGN_KEY_CHECKS = 1;
