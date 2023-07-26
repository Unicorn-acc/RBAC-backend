/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 26/07/2023 12:09:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_no` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dept_no` int NULL DEFAULT NULL COMMENT '部门id',
  `job` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职务',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '逻辑删除(0存在，1删除)',
  `rec_token` int NULL DEFAULT 0 COMMENT '随机token',
  PRIMARY KEY (`emp_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 257 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (108, 'admin', '$2a$10$LKGWHJKoQMfr/Yhy2g.HyOrNqMKVBrDOcrQXmlaQHSggWZLwj20qO', 2, '超级管理员', '12345678901', '2023-05-30 16:46:58', 0, 38598);
INSERT INTO `emp` VALUES (112, '用户1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-05-30 16:48:25', 0, 0);
INSERT INTO `emp` VALUES (115, 'user', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 09:51:23', 0, 0);
INSERT INTO `emp` VALUES (116, '用户2', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 09:51:27', 0, 0);
INSERT INTO `emp` VALUES (117, '用户3', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 09:51:33', 0, 0);
INSERT INTO `emp` VALUES (119, '用户4', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 14:25:57', 0, 0);
INSERT INTO `emp` VALUES (120, '用户5', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 14:25:58', 0, 0);
INSERT INTO `emp` VALUES (121, '用户6', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 14:26:00', 0, 0);
INSERT INTO `emp` VALUES (122, '用户7', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 14:26:01', 0, 0);
INSERT INTO `emp` VALUES (123, '用户8', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 14:26:03', 0, 0);
INSERT INTO `emp` VALUES (124, '用户9', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-02 16:02:10', 0, 0);
INSERT INTO `emp` VALUES (125, '用户10', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 2, '员工', '12345678901', '2023-06-02 16:02:13', 0, 0);
INSERT INTO `emp` VALUES (138, '用户100', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-06 14:55:58', 1, 0);
INSERT INTO `emp` VALUES (143, '用户102', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-06 22:25:37', 0, 0);
INSERT INTO `emp` VALUES (145, 'name', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-08 15:25:38', 0, 0);
INSERT INTO `emp` VALUES (146, 'defaultname2', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-08 15:41:31', 1, 0);
INSERT INTO `emp` VALUES (147, 'defaultname111', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-08 15:54:22', 1, 0);
INSERT INTO `emp` VALUES (148, 'defaultname11', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-08 16:08:33', 1, 0);
INSERT INTO `emp` VALUES (149, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-08 22:47:37', 1, 0);
INSERT INTO `emp` VALUES (150, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-12 10:07:34', 1, 0);
INSERT INTO `emp` VALUES (151, 'iuhiu', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-12 13:49:54', 0, 0);
INSERT INTO `emp` VALUES (152, '879', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-12 13:53:29', 0, 0);
INSERT INTO `emp` VALUES (153, 'http测试', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-12 14:03:48', 0, 0);
INSERT INTO `emp` VALUES (154, 'http22测试', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-12 14:03:57', 0, 0);
INSERT INTO `emp` VALUES (155, 'htt222测试', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-12 14:04:34', 0, 0);
INSERT INTO `emp` VALUES (156, 'd22me', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123456789', '2023-06-12 14:07:43', 1, 0);
INSERT INTO `emp` VALUES (157, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 11:20:21', 1, 0);
INSERT INTO `emp` VALUES (158, 'defaultnam22', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 11:25:24', 1, 0);
INSERT INTO `emp` VALUES (159, 'defaultname1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 14:28:24', 1, 0);
INSERT INTO `emp` VALUES (160, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 15:35:40', 1, 0);
INSERT INTO `emp` VALUES (161, '新用户', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 16:35:57', 1, 0);
INSERT INTO `emp` VALUES (162, 'defaultname1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-15 16:38:13', 1, 0);
INSERT INTO `emp` VALUES (163, '新用户1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '主管', '12345678901', '2023-06-21 16:26:30', 0, 21211);
INSERT INTO `emp` VALUES (164, '新用户', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-26 09:50:58', 0, 59686);
INSERT INTO `emp` VALUES (165, 'http22测2试', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-26 10:53:40', 1, 13992);
INSERT INTO `emp` VALUES (166, 'http22测12试', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '123', '2023-06-26 10:54:01', 1, 64440);
INSERT INTO `emp` VALUES (167, 'http22测12试1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 5, '员工', '12345678901', '2023-06-26 11:18:53', 1, 51706);
INSERT INTO `emp` VALUES (168, 'defaultn22', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-26 11:20:44', 0, 4973);
INSERT INTO `emp` VALUES (169, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-27 11:06:20', 1, 29329);
INSERT INTO `emp` VALUES (170, 'defaultname1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-28 13:41:06', 1, 94404);
INSERT INTO `emp` VALUES (171, 'defaultname', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-28 14:30:46', 0, 7534);
INSERT INTO `emp` VALUES (172, 'name1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 10:05:26', 0, 5352);
INSERT INTO `emp` VALUES (173, 'defaultname1', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 10:17:55', 0, 66526);
INSERT INTO `emp` VALUES (174, '用户名', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 10:25:29', 0, 55210);
INSERT INTO `emp` VALUES (175, '用户222', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 12:13:26', 0, 83912);
INSERT INTO `emp` VALUES (176, '用户32', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 12:18:25', 0, 16448);
INSERT INTO `emp` VALUES (177, '用户33', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 12:27:05', 0, 54921);
INSERT INTO `emp` VALUES (178, '用户44', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 13:32:36', 1, 85260);
INSERT INTO `emp` VALUES (179, '用户44', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 2, '员工', '12345678901', '2023-06-30 13:33:57', 0, 54139);
INSERT INTO `emp` VALUES (180, '用户55', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-06-30 13:52:44', 0, 63694);
INSERT INTO `emp` VALUES (181, '用户66', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 4, '员工', '12345678901', '2023-07-03 09:15:35', 1, 75498);
INSERT INTO `emp` VALUES (182, '用户66', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-07-03 09:17:58', 0, 18373);
INSERT INTO `emp` VALUES (183, '用户7721', '$2a$10$TJGGV37hHi6ohMFmpD2ygOntIeEefp8vLsvGFm8cA36.wIU5CERmG', 5, '员工', '12345678901', '2023-07-03 15:22:55', 0, 52507);
INSERT INTO `emp` VALUES (184, '用户7721', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 2, '员工', '12345678901', '2023-07-03 16:01:03', 1, 96058);
INSERT INTO `emp` VALUES (185, 'de11', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-07-04 14:08:55', 1, 79817);
INSERT INTO `emp` VALUES (215, 'de11111', '$2a$10$Uzx/RbbqijpLxPlQbyOrru84nsHL/OXE0MLdeiPstOd8SUATr2bly', 1, '员工', '12345678901', '2023-07-06 14:21:15', 1, 69197);
INSERT INTO `emp` VALUES (233, 'de11', '$2a$10$5GBthxgyeCUV.JIyN3veoeH0KNzXYuLDFlG24jG4c8iRY60rQediK', 1, '员工', '12345678901', '2023-07-12 14:24:28', 0, 52659);
INSERT INTO `emp` VALUES (234, 'default', '$2a$10$uIC0gZf1Fx1AMMzCjqwRPOSC2wGfaYoXfKjTX6GjVixpD.V5WKHfq', 1, '员工', '12345678901', '2023-07-12 14:31:23', 1, 5892);
INSERT INTO `emp` VALUES (235, '测试233', '$2a$10$xHrDBsDB1CgLqTh6phxIK.G8IkcnfgRBehLVZT0uVfIEzpfGsuY8W', 1, '员工', '12345678901', '2023-07-12 15:26:12', 0, 85842);
INSERT INTO `emp` VALUES (236, 'defaultn655', '$2a$10$A4w38xMUILwVWEwYMlLJCeSa291/1MyGbjlioMrkGyEdX.gfNE9by', 1, '员工', '12345678901', '2023-07-12 15:28:15', 1, 36908);
INSERT INTO `emp` VALUES (248, '用户998', '$2a$10$YFcL1giWydXmjBlZ9MRAwur84AUpEq5QmLPu/G4Unl6J3M2G5RCii', 1, '员工', '12345678901', '2023-07-12 16:01:25', 0, 22292);

-- ----------------------------
-- Table structure for emp_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `emp_id` bigint NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 526 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_role
-- ----------------------------
INSERT INTO `emp_role` VALUES (52, 122, 2, '2023-06-05 13:39:26');
INSERT INTO `emp_role` VALUES (53, 121, 2, '2023-06-05 13:39:30');
INSERT INTO `emp_role` VALUES (55, 125, 2, '2023-06-05 13:40:37');
INSERT INTO `emp_role` VALUES (61, 117, 2, '2023-06-05 13:43:59');
INSERT INTO `emp_role` VALUES (100, 119, 2, '2023-06-06 14:22:03');
INSERT INTO `emp_role` VALUES (111, 143, 48, '2023-06-08 15:19:55');
INSERT INTO `emp_role` VALUES (145, 115, 52, '2023-06-08 16:13:53');
INSERT INTO `emp_role` VALUES (147, 117, 52, '2023-06-08 16:13:53');
INSERT INTO `emp_role` VALUES (148, 119, 52, '2023-06-08 16:13:53');
INSERT INTO `emp_role` VALUES (150, 124, 52, '2023-06-08 16:13:58');
INSERT INTO `emp_role` VALUES (162, 112, 49, '2023-06-15 14:27:59');
INSERT INTO `emp_role` VALUES (166, 112, 62, '2023-06-15 14:29:22');
INSERT INTO `emp_role` VALUES (167, 116, 62, '2023-06-15 14:29:22');
INSERT INTO `emp_role` VALUES (171, 163, 66, '2023-06-21 16:27:29');
INSERT INTO `emp_role` VALUES (185, 117, 67, '2023-06-26 11:21:19');
INSERT INTO `emp_role` VALUES (186, 115, 67, '2023-06-26 11:21:25');
INSERT INTO `emp_role` VALUES (188, 168, 48, '2023-06-27 11:06:50');
INSERT INTO `emp_role` VALUES (189, 168, 49, '2023-06-27 11:06:50');
INSERT INTO `emp_role` VALUES (190, 112, 67, '2023-06-27 11:07:10');
INSERT INTO `emp_role` VALUES (191, 168, 50, '2023-06-28 13:41:23');
INSERT INTO `emp_role` VALUES (193, 171, 70, '2023-06-28 14:30:53');
INSERT INTO `emp_role` VALUES (194, 112, 70, '2023-06-29 16:27:51');
INSERT INTO `emp_role` VALUES (196, 116, 70, '2023-06-29 16:27:51');
INSERT INTO `emp_role` VALUES (197, 180, 50, '2023-07-03 09:15:58');
INSERT INTO `emp_role` VALUES (247, 108, 1, '2023-07-06 10:44:09');
INSERT INTO `emp_role` VALUES (390, 235, 109, '2023-07-12 15:26:39');
INSERT INTO `emp_role` VALUES (399, 115, 109, '2023-07-12 15:33:18');
INSERT INTO `emp_role` VALUES (473, 248, 121, '2023-07-14 11:29:21');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由地址',
  `url_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `type` int NULL DEFAULT NULL COMMENT '菜单类型',
  `pid` bigint NULL DEFAULT NULL COMMENT '父id',
  `order_num` int NULL DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '-1', '权限管理', 1, 0, -1, 1, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (2, 'emp/page', '用户管理', 1, 1, 1, 1, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (3, 'emp/save', '添加用户', 1, 2, 2, 1, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (4, 'emp/info/*', '查看用户信息', 1, 2, 2, 2, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (5, 'emp/remove/*', '删除用户', 1, 2, 2, 4, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (6, 'emp/page', '查询用户', 1, 2, 2, 5, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (7, 'role/page', '角色管理', 1, 1, 1, 2, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (8, 'role/save', '添加角色', 1, 2, 7, 1, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (9, 'role/info/*', '查看角色信息', 1, 2, 7, 2, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (10, 'role/remove/*', '删除角色', 1, 2, 7, 5, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (11, 'role/page', '查询角色', 1, 2, 7, 4, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (12, 'menu/tree', '菜单管理', 1, 1, 1, 3, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (13, 'auth/getRoleByEmpId', '分配角色', 1, 2, 2, 6, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (14, 'auth/getempByRoleId', '分配用户', 1, 2, 7, 6, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (15, 'auth/getMenuListByRoleId/*', '授权菜单', 1, 2, 7, 7, '2023-06-06 14:55:58');
INSERT INTO `menu` VALUES (26, 'auth/getNotAddedRoleByEmpId', '查看新增用户角色', 1, 2, 13, 1, '2023-06-06 20:44:36');
INSERT INTO `menu` VALUES (27, 'auth/saveEmpRole', '保存新增用户角色', 1, 2, 13, 2, '2023-06-06 20:44:36');
INSERT INTO `menu` VALUES (28, 'auth/getNotAddedEmpByRoleId', '查看新增角色用户', 1, 2, 14, 1, '2023-06-06 20:50:16');
INSERT INTO `menu` VALUES (29, 'auth/saveRoleEmp', '保存新增角色用户', 1, 2, 14, 2, '2023-06-06 20:52:49');
INSERT INTO `menu` VALUES (30, 'auth/saveRoleMenu', '保存授权菜单', 1, 2, 15, 1, '2023-06-06 20:54:19');
INSERT INTO `menu` VALUES (31, 'auth/deleteRoleEmp', '删除已分配用户', 1, 2, 14, 3, '2023-06-06 20:58:58');
INSERT INTO `menu` VALUES (32, 'auth/deleteEmpRole', '删除已分配角色', 1, 2, 13, 3, '2023-06-06 21:00:06');
INSERT INTO `menu` VALUES (33, 'emp/update', '更新用户', 1, 2, 2, 3, '2023-06-06 21:17:44');
INSERT INTO `menu` VALUES (34, 'role/update', '更新角色', 1, 2, 7, 3, '2023-06-06 21:26:32');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_deleted` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0,
  `rec_token` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', '管理员管理员管理员', '2023-05-29 10:53:56', 0, 0);
INSERT INTO `role` VALUES (2, '普通用户', '普通用户普通用户普通用户', '2023-06-05 11:00:57', 0, 0);
INSERT INTO `role` VALUES (48, '角色1', 'sd', '2023-06-05 15:23:01', 0, 0);
INSERT INTO `role` VALUES (49, '角色2', '角色角色角色', '2023-06-05 15:23:04', 0, 0);
INSERT INTO `role` VALUES (50, '角色3', '角色角色', '2023-06-05 15:23:08', 0, 0);
INSERT INTO `role` VALUES (51, '角色4', '角色角色', '2023-06-05 15:23:11', 0, 0);
INSERT INTO `role` VALUES (52, '角色5', '测试', '2023-06-05 15:23:19', 0, 0);
INSERT INTO `role` VALUES (55, '11', '111', '2023-06-06 14:14:00', 1, 0);
INSERT INTO `role` VALUES (56, '111', '11111', '2023-06-06 14:14:36', 1, 0);
INSERT INTO `role` VALUES (57, '反派角色1', '反派角色反派角色反派角色', '2023-06-06 15:01:44', 1, 0);
INSERT INTO `role` VALUES (58, '角色5', '', '2023-06-08 15:56:45', 1, 0);
INSERT INTO `role` VALUES (59, '测试角色111', '1111', '2023-06-08 16:09:09', 0, 0);
INSERT INTO `role` VALUES (60, '测试角色11', '11', '2023-06-08 16:09:12', 1, 0);
INSERT INTO `role` VALUES (61, '测试角色1111', '11', '2023-06-08 16:11:07', 1, 0);
INSERT INTO `role` VALUES (62, 'aaa1', 'sd', '2023-06-12 14:14:19', 0, 0);
INSERT INTO `role` VALUES (63, '112', '22', '2023-06-15 14:29:33', 1, 0);
INSERT INTO `role` VALUES (64, '55', '65655', '2023-06-15 14:34:52', 0, 0);
INSERT INTO `role` VALUES (65, '新角色', '新角色新角色新角色', '2023-06-15 16:36:05', 1, 0);
INSERT INTO `role` VALUES (66, '新角色', '新角色新角色新角色1', '2023-06-21 16:27:20', 0, 96326);
INSERT INTO `role` VALUES (67, '121321', '31231', '2023-06-26 10:58:30', 0, 2289);
INSERT INTO `role` VALUES (68, '12131', '2121', '2023-06-26 11:20:55', 1, 34210);
INSERT INTO `role` VALUES (69, '1111', '1', '2023-06-27 11:06:57', 1, 53387);
INSERT INTO `role` VALUES (70, '111', '11', '2023-06-28 13:41:33', 0, 24393);
INSERT INTO `role` VALUES (71, '角色1231', '角色角色角色', '2023-07-03 09:16:31', 1, 31420);
INSERT INTO `role` VALUES (72, '角色0', '11', '2023-07-03 09:18:10', 1, 9881);
INSERT INTO `role` VALUES (73, '11111', '新角色2新角色2', '2023-07-03 16:02:08', 1, 34095);
INSERT INTO `role` VALUES (90, '角色123', '11', '2023-07-06 14:24:08', 1, 81223);
INSERT INTO `role` VALUES (108, '11121212', '12121', '2023-07-12 14:25:07', 1, 21229);
INSERT INTO `role` VALUES (109, '测试23', '测试232', '2023-07-12 15:26:30', 0, 54551);
INSERT INTO `role` VALUES (121, '测试998', '测试998', '2023-07-12 16:01:38', 0, 42472);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `menu_id` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 847 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (103, 52, 6, '2023-06-06 21:06:45');
INSERT INTO `role_menu` VALUES (104, 52, 11, '2023-06-06 21:06:45');
INSERT INTO `role_menu` VALUES (105, 52, 12, '2023-06-06 21:06:45');
INSERT INTO `role_menu` VALUES (106, 51, 1, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (107, 51, 2, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (108, 51, 3, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (109, 51, 4, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (110, 51, 5, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (111, 51, 6, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (112, 51, 13, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (113, 51, 26, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (114, 51, 27, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (115, 51, 32, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (116, 51, 7, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (117, 51, 8, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (118, 51, 9, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (119, 51, 10, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (120, 51, 11, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (121, 51, 14, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (122, 51, 28, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (123, 51, 29, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (124, 51, 31, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (125, 51, 15, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (126, 51, 30, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (127, 51, 12, '2023-06-06 21:07:04');
INSERT INTO `role_menu` VALUES (252, 48, 6, '2023-06-08 16:33:16');
INSERT INTO `role_menu` VALUES (253, 48, 12, '2023-06-08 16:33:16');
INSERT INTO `role_menu` VALUES (286, 59, 3, '2023-06-12 10:10:22');
INSERT INTO `role_menu` VALUES (287, 59, 4, '2023-06-12 10:10:22');
INSERT INTO `role_menu` VALUES (288, 59, 33, '2023-06-12 10:10:22');
INSERT INTO `role_menu` VALUES (289, 59, 6, '2023-06-12 10:10:22');
INSERT INTO `role_menu` VALUES (297, 49, 1, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (298, 49, 2, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (299, 49, 3, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (300, 49, 4, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (301, 49, 33, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (302, 49, 5, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (303, 49, 6, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (304, 49, 13, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (305, 49, 26, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (306, 49, 27, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (307, 49, 32, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (308, 49, 7, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (309, 49, 8, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (310, 49, 9, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (311, 49, 34, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (312, 49, 11, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (313, 49, 10, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (314, 49, 14, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (315, 49, 28, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (316, 49, 29, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (317, 49, 31, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (318, 49, 15, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (319, 49, 30, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (320, 49, 12, '2023-06-15 14:27:54');
INSERT INTO `role_menu` VALUES (431, 62, 6, '2023-06-26 17:25:41');
INSERT INTO `role_menu` VALUES (435, 66, 3, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (436, 66, 4, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (437, 66, 33, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (438, 66, 6, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (439, 66, 9, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (440, 66, 11, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (441, 66, 12, '2023-06-27 11:07:47');
INSERT INTO `role_menu` VALUES (518, 70, 3, '2023-07-03 09:17:02');
INSERT INTO `role_menu` VALUES (519, 70, 4, '2023-07-03 09:17:02');
INSERT INTO `role_menu` VALUES (607, 1, 1, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (608, 1, 2, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (609, 1, 3, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (610, 1, 4, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (611, 1, 33, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (612, 1, 5, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (613, 1, 6, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (614, 1, 13, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (615, 1, 26, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (616, 1, 27, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (617, 1, 32, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (618, 1, 7, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (619, 1, 8, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (620, 1, 9, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (621, 1, 34, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (622, 1, 11, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (623, 1, 10, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (624, 1, 14, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (625, 1, 28, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (626, 1, 29, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (627, 1, 31, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (628, 1, 15, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (629, 1, 30, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (630, 1, 12, '2023-07-06 10:43:18');
INSERT INTO `role_menu` VALUES (657, 67, 4, '2023-07-06 14:24:24');
INSERT INTO `role_menu` VALUES (658, 67, 5, '2023-07-06 14:24:24');
INSERT INTO `role_menu` VALUES (659, 67, 6, '2023-07-06 14:24:24');
INSERT INTO `role_menu` VALUES (660, 67, 34, '2023-07-06 14:24:24');
INSERT INTO `role_menu` VALUES (661, 67, 11, '2023-07-06 14:24:24');
INSERT INTO `role_menu` VALUES (755, 109, 3, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (756, 109, 5, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (757, 109, 6, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (758, 109, 13, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (759, 109, 26, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (760, 109, 27, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (761, 109, 32, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (762, 109, 9, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (763, 109, 34, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (764, 109, 11, '2023-07-12 15:33:07');
INSERT INTO `role_menu` VALUES (809, 121, 3, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (810, 121, 6, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (811, 121, 11, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (812, 121, 10, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (813, 121, 14, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (814, 121, 28, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (815, 121, 29, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (816, 121, 31, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (817, 121, 15, '2023-07-18 09:30:11');
INSERT INTO `role_menu` VALUES (818, 121, 30, '2023-07-18 09:30:11');

SET FOREIGN_KEY_CHECKS = 1;
