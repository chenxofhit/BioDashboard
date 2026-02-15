-- ========================================================
-- BioDashboard 周报系统 - 数据库初始化脚本
-- 
-- 使用方法:
-- 1. 创建数据库: CREATE DATABASE biodashboard CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 2. 导入数据: mysql -u root -p biodashboard < init.sql
-- 3. 默认管理员账号: admin / 1
--
-- 支持 MySQL 5.7+ / 8.0+
-- ========================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 创建数据库（如果不存在）
-- ----------------------------
CREATE DATABASE IF NOT EXISTS biodashboard CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE biodashboard;

-- ----------------------------
-- 通知通告表
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify`;
CREATE TABLE `oa_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `files` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '附件',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `oa_notify_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知通告';

-- ----------------------------
-- 通知通告发送记录表
-- ----------------------------
DROP TABLE IF EXISTS `oa_notify_record`;
CREATE TABLE `oa_notify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `notify_id` bigint(20) DEFAULT NULL COMMENT '通知通告ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '接受人',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '阅读标记',
  `read_date` date DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`id`),
  KEY `oa_notify_record_notify_id` (`notify_id`),
  KEY `oa_notify_record_user_id` (`user_id`),
  KEY `oa_notify_record_read_flag` (`is_read`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='通知通告发送记录';

-- ----------------------------
-- 随机提示语表
-- ----------------------------
DROP TABLE IF EXISTS `placeholder`;
CREATE TABLE `placeholder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `contributor` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

BEGIN;
INSERT INTO `placeholder` VALUES (1, '科研虐我千百遍，我待科研如初恋!', 'Bot机器人');
INSERT INTO `placeholder` VALUES (2, '人生苦短，除了 Python还该写点什么', 'Bot机器人');
INSERT INTO `placeholder` VALUES (3, '这位大爷，您又来了，一日不见如隔三秋', 'Bot机器人');
INSERT INTO `placeholder` VALUES (4, '衣带渐宽终不悔，沉迷科研不觉累', 'Bot机器人');
INSERT INTO `placeholder` VALUES (5, '转眼一想，也就是三五年，在人生中也只是一瞬', 'Bot机器人');
INSERT INTO `placeholder` VALUES (6, '不要嘲笑我，正在通往 Nature 的道路上猥琐发育ing', 'Bot机器人');
INSERT INTO `placeholder` VALUES (7, '那天阳光正好，微风和煦，一位白球鞋的男生笑容好灿烂', 'Bot机器人');
INSERT INTO `placeholder` VALUES (8, '春困秋乏夏打盹，睡不醒的冬三月', 'Bot机器人');
INSERT INTO `placeholder` VALUES (9, '实验室的网络贼鸡儿慢，各种 404，Google也打不开，我好难啊！', 'Bot机器人');
INSERT INTO `placeholder` VALUES (10, '炼丹炉好用论文好发毕业快，DeepLearning真TA喵香！', 'Bot机器人');
INSERT INTO `placeholder` VALUES (11, '要不是老板要看，我真的不想写这个XX周报', 'Bot机器人');
INSERT INTO `placeholder` VALUES (12, '摸鱼时风光无限，周报时如坐针毡', 'Bot机器人');
INSERT INTO `placeholder` VALUES (13, '不吃学习的苦，就要吃生活的苦', 'Bot机器人');
INSERT INTO `placeholder` VALUES (14, '求抱抱，求摸头，好运即将来临！', 'Bot机器人');
INSERT INTO `placeholder` VALUES (15, '2020 年即将来临，贫困科研民工听说要被消灭了，真的慌', 'Bot机器人');
INSERT INTO `placeholder` VALUES (16, '实验结果又不太好，先投个 Bioinformatics 压压惊吧', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (17, '究天人之际，通古今之变，成一家之言', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (18, '虽然看了这么多文献，写了这么多批注，似乎仍然写不好这一篇论文', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (19, '老板很久没跟我聊过往了，看到TA心很慌，欲言却又止啊', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (20, '老板又找我聊读博的事情，我要不要上钩呢？', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (21, '这个月我要是再喝奶茶我就不是人了，我发 shi', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (22, '科研搞不动，头发还蹭蹭掉，真是惨淡的人生呢', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (23, '编辑什么时候给答复啊，真是等不及了', 'Bot 机器人');
INSERT INTO `placeholder` VALUES (24, '锦鲤保佑我投稿百发百中吧', 'Bot 机器人');
COMMIT;

-- ----------------------------
-- 周报表
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author_id` bigint(20) NOT NULL COMMENT '作者ID',
  `author_name` varchar(100) DEFAULT NULL COMMENT '作者姓名',
  `parent_dept_id` bigint(20) NOT NULL COMMENT '上级部门 ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `r_from_date` datetime NOT NULL COMMENT '起始时间',
  `r_to_date` datetime NOT NULL COMMENT '终止时间',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '周报题目',
  `content` text COMMENT '周报内容',
  `status` tinyint(255) NOT NULL DEFAULT '0' COMMENT '状态0：系统生成，1：人工修改',
  `r_create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `r_chg_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`author_id`),
  KEY `idx_title` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='周报';

-- ----------------------------
-- 周报汇总表
-- ----------------------------
DROP TABLE IF EXISTS `report_summary`;
CREATE TABLE `report_summary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `r_from_date` datetime NOT NULL COMMENT '起始时间',
  `r_to_date` datetime NOT NULL COMMENT '终止时间',
  `title` varchar(100) DEFAULT NULL COMMENT '周报题目',
  `count` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `r_create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_author_id` (`dept_id`),
  KEY `idx_title` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='周报汇总';

-- ----------------------------
-- 系统配置表
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `k` varchar(100) DEFAULT NULL COMMENT '键',
  `v` varchar(1000) DEFAULT NULL COMMENT '值',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 部门管理表
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- 字典表
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

BEGIN;
INSERT INTO `sys_dict` VALUES (1, '正常', '0', 'del_flag', '删除标记', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (3, '显示', '1', 'show_hide', '显示/隐藏', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (4, '隐藏', '0', 'show_hide', '显示/隐藏', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (5, '是', '1', 'yes_no', '是/否', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (6, '否', '0', 'yes_no', '是/否', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (7, '红色', 'red', 'color', '颜色值', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (8, '绿色', 'green', 'color', '颜色值', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (9, '蓝色', 'blue', 'color', '颜色值', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (10, '黄色', 'yellow', 'color', '颜色值', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (11, '橙色', 'orange', 'color', '颜色值', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (12, '默认主题', 'default', 'theme', '主题方案', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (13, '天蓝主题', 'cerulean', 'theme', '主题方案', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (14, '橙色主题', 'readable', 'theme', '主题方案', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (15, '红色主题', 'united', 'theme', '主题方案', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (16, 'Flat主题', 'flat', 'theme', '主题方案', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (17, '国家', '1', 'sys_area_type', '区域类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (18, '省份、直辖市', '2', 'sys_area_type', '区域类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (19, '地市', '3', 'sys_area_type', '区域类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (20, '区县', '4', 'sys_area_type', '区域类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (21, '公司', '1', 'sys_office_type', '机构类型', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (22, '部门', '2', 'sys_office_type', '机构类型', 70, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (23, '小组', '3', 'sys_office_type', '机构类型', 80, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (24, '其它', '4', 'sys_office_type', '机构类型', 90, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (25, '综合部', '1', 'sys_office_common', '快捷通用部门', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (26, '开发部', '2', 'sys_office_common', '快捷通用部门', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (27, '人力部', '3', 'sys_office_common', '快捷通用部门', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (28, '一级', '1', 'sys_office_grade', '机构等级', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (29, '二级', '2', 'sys_office_grade', '机构等级', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (30, '三级', '3', 'sys_office_grade', '机构等级', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (31, '四级', '4', 'sys_office_grade', '机构等级', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (32, '所有数据', '1', 'sys_data_scope', '数据范围', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (33, '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (34, '所在公司数据', '3', 'sys_data_scope', '数据范围', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (35, '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (36, '所在部门数据', '5', 'sys_data_scope', '数据范围', 50, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (37, '仅本人数据', '8', 'sys_data_scope', '数据范围', 90, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (38, '按明细设置', '9', 'sys_data_scope', '数据范围', 100, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (39, '系统管理', '1', 'sys_user_type', '用户类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (40, '部门经理', '2', 'sys_user_type', '用户类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (41, '普通用户', '3', 'sys_user_type', '用户类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (42, '基础主题', 'basic', 'cms_theme', '站点主题', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (43, '蓝色主题', 'blue', 'cms_theme', '站点主题', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (44, '红色主题', 'red', 'cms_theme', '站点主题', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (45, '文章模型', 'article', 'cms_module', '栏目模型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (46, '图片模型', 'picture', 'cms_module', '栏目模型', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (47, '下载模型', 'download', 'cms_module', '栏目模型', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (48, '链接模型', 'link', 'cms_module', '栏目模型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (49, '专题模型', 'special', 'cms_module', '栏目模型', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (50, '默认展现方式', '0', 'cms_show_modes', '展现方式', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (51, '首栏目内容列表', '1', 'cms_show_modes', '展现方式', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (52, '栏目第一条内容', '2', 'cms_show_modes', '展现方式', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (53, '发布', '0', 'cms_del_flag', '内容状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (54, '删除', '1', 'cms_del_flag', '内容状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (55, '审核', '2', 'cms_del_flag', '内容状态', 15, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (56, '首页焦点图', '1', 'cms_posid', '推荐位', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (57, '栏目页文章推荐', '2', 'cms_posid', '推荐位', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (58, '咨询', '1', 'cms_guestbook', '留言板分类', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (59, '建议', '2', 'cms_guestbook', '留言板分类', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (60, '投诉', '3', 'cms_guestbook', '留言板分类', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (61, '其它', '4', 'cms_guestbook', '留言板分类', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (62, '公休', '1', 'oa_leave_type', '请假类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (63, '病假', '2', 'oa_leave_type', '请假类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (64, '事假', '3', 'oa_leave_type', '请假类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (65, '调休', '4', 'oa_leave_type', '请假类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (66, '婚假', '5', 'oa_leave_type', '请假类型', 60, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (67, '接入日志', '1', 'sys_log_type', '日志类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (68, '异常日志', '2', 'sys_log_type', '日志类型', 40, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (69, '请假流程', 'leave', 'act_type', '流程类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (70, '审批测试流程', 'test_audit', 'act_type', '流程类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (71, '分类1', '1', 'act_category', '流程分类', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (72, '分类2', '2', 'act_category', '流程分类', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (73, '增删改查', 'crud', 'gen_category', '代码生成分类', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (74, '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (75, '树结构', 'tree', 'gen_category', '代码生成分类', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (76, '=', '=', 'gen_query_type', '查询方式', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (77, '!=', '!=', 'gen_query_type', '查询方式', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (78, '&gt;', '&gt;', 'gen_query_type', '查询方式', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (79, '&lt;', '&lt;', 'gen_query_type', '查询方式', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (80, 'Between', 'between', 'gen_query_type', '查询方式', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (81, 'Like', 'like', 'gen_query_type', '查询方式', 60, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (82, 'Left Like', 'left_like', 'gen_query_type', '查询方式', 70, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (83, 'Right Like', 'right_like', 'gen_query_type', '查询方式', 80, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (84, '文本框', 'input', 'gen_show_type', '字段生成方案', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (85, '文本域', 'textarea', 'gen_show_type', '字段生成方案', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (86, '下拉框', 'select', 'gen_show_type', '字段生成方案', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (87, '复选框', 'checkbox', 'gen_show_type', '字段生成方案', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (88, '单选框', 'radiobox', 'gen_show_type', '字段生成方案', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (89, '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', 60, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (90, '人员选择', 'userselect', 'gen_show_type', '字段生成方案', 70, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (91, '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', 80, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (92, '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', 90, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (93, 'String', 'String', 'gen_java_type', 'Java类型', 10, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (94, 'Long', 'Long', 'gen_java_type', 'Java类型', 20, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (95, '仅持久层', 'dao', 'gen_category', '代码生成分类', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (96, '男', '1', 'sex', '性别', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (97, '女', '2', 'sex', '性别', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (98, 'Integer', 'Integer', 'gen_java_type', 'Java类型', 30, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (99, 'Double', 'Double', 'gen_java_type', 'Java类型', 40, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (100, 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', 50, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (104, 'Custom', 'Custom', 'gen_java_type', 'Java类型', 90, 0, 1, NULL, 1, NULL, NULL, '1');
INSERT INTO `sys_dict` VALUES (105, '会议通告', '1', 'oa_notify_type', '通知通告类型', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (106, '奖惩通告', '2', 'oa_notify_type', '通知通告类型', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (107, '活动通告', '3', 'oa_notify_type', '通知通告类型', 30, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (108, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (109, '发布', '1', 'oa_notify_status', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (110, '未读', '0', 'oa_notify_read', '通知通告状态', 10, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (111, '已读', '1', 'oa_notify_read', '通知通告状态', 20, 0, 1, NULL, 1, NULL, NULL, '0');
INSERT INTO `sys_dict` VALUES (112, '草稿', '0', 'oa_notify_status', '通知通告状态', 10, 0, 1, NULL, 1, NULL, '', '0');
INSERT INTO `sys_dict` VALUES (113, '删除', '0', 'del_flag', '删除标记', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (118, '关于', 'about', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES (119, '交流', 'communication', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
INSERT INTO `sys_dict` VALUES (120, '文章', 'article', 'blog_type', '博客类型', NULL, NULL, NULL, NULL, NULL, NULL, '', '');
COMMIT;

-- ----------------------------
-- 文件上传表
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- 系统日志表
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- 菜单管理表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

BEGIN;
INSERT INTO `sys_menu` VALUES (2, 3, '系统菜单', 'sys/menu/', 'sys:menu:menu', 1, 'fa fa-th-list', 2, '2017-08-09 22:55:15', NULL);
INSERT INTO `sys_menu` VALUES (3, 0, '系统管理', NULL, NULL, 0, 'fa fa-desktop', 1, '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES (6, 3, '用户管理', 'sys/user/', 'sys:user:user', 1, 'fa fa-user', 0, '2017-08-10 14:12:11', NULL);
INSERT INTO `sys_menu` VALUES (7, 3, '角色管理', 'sys/role', 'sys:role:role', 1, 'fa fa-paw', 1, '2017-08-10 14:13:19', NULL);
INSERT INTO `sys_menu` VALUES (12, 6, '新增', '', 'sys:user:add', 2, '', 0, '2017-08-14 10:51:35', NULL);
INSERT INTO `sys_menu` VALUES (13, 6, '编辑', '', 'sys:user:edit', 2, '', 0, '2017-08-14 10:52:06', NULL);
INSERT INTO `sys_menu` VALUES (14, 6, '删除', NULL, 'sys:user:remove', 2, NULL, 0, '2017-08-14 10:52:24', NULL);
INSERT INTO `sys_menu` VALUES (15, 7, '新增', '', 'sys:role:add', 2, '', 0, '2017-08-14 10:56:37', NULL);
INSERT INTO `sys_menu` VALUES (20, 2, '新增', '', 'sys:menu:add', 2, '', 0, '2017-08-14 10:59:32', NULL);
INSERT INTO `sys_menu` VALUES (21, 2, '编辑', '', 'sys:menu:edit', 2, '', 0, '2017-08-14 10:59:56', NULL);
INSERT INTO `sys_menu` VALUES (22, 2, '删除', '', 'sys:menu:remove', 2, '', 0, '2017-08-14 11:00:26', NULL);
INSERT INTO `sys_menu` VALUES (24, 6, '批量删除', '', 'sys:user:batchRemove', 2, '', 0, '2017-08-14 17:27:18', NULL);
INSERT INTO `sys_menu` VALUES (25, 6, '停用', NULL, 'sys:user:disable', 2, NULL, 0, '2017-08-14 17:27:43', NULL);
INSERT INTO `sys_menu` VALUES (26, 6, '重置密码', '', 'sys:user:resetPwd', 2, '', 0, '2017-08-14 17:28:34', NULL);
INSERT INTO `sys_menu` VALUES (27, 91, '系统日志', 'common/log', 'common:log', 1, 'fa fa-warning', 0, '2017-08-14 22:11:53', NULL);
INSERT INTO `sys_menu` VALUES (28, 27, '刷新', NULL, 'sys:log:list', 2, NULL, 0, '2017-08-14 22:30:22', NULL);
INSERT INTO `sys_menu` VALUES (29, 27, '删除', NULL, 'sys:log:remove', 2, NULL, 0, '2017-08-14 22:30:43', NULL);
INSERT INTO `sys_menu` VALUES (30, 27, '清空', NULL, 'sys:log:clear', 2, NULL, 0, '2017-08-14 22:31:02', NULL);
INSERT INTO `sys_menu` VALUES (48, 77, '代码生成', 'common/generator', 'common:generator', 1, 'fa fa-code', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (55, 7, '编辑', '', 'sys:role:edit', 2, '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (56, 7, '删除', '', 'sys:role:remove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (57, 91, '运行监控', 'druid/index.html', '', 1, 'fa fa-caret-square-o-right', 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (61, 2, '批量删除', '', 'sys:menu:batchRemove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (62, 7, '批量删除', '', 'sys:role:batchRemove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (72, 77, '计划任务', 'common/job', 'common:taskScheduleJob', 1, 'fa fa-hourglass-1', 4, NULL, NULL);
INSERT INTO `sys_menu` VALUES (73, 3, '部门管理', 'sys/dept', 'system:sysDept:sysDept', 1, 'fa fa-users', 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (74, 73, '增加', 'sys/dept/add', 'system:sysDept:add', 2, NULL, 1, NULL, NULL);
INSERT INTO `sys_menu` VALUES (75, 73, '刪除', 'sys/dept/remove', 'system:sysDept:remove', 2, NULL, 2, NULL, NULL);
INSERT INTO `sys_menu` VALUES (76, 73, '编辑', 'sys/dept/edit', 'system:sysDept:edit', 2, NULL, 3, NULL, NULL);
INSERT INTO `sys_menu` VALUES (77, 0, '系统工具', '', '', 0, 'fa fa-gear', 4, NULL, NULL);
INSERT INTO `sys_menu` VALUES (91, 0, '系统监控', '', '', 0, 'fa fa-video-camera', 5, NULL, NULL);
INSERT INTO `sys_menu` VALUES (92, 91, '在线用户', 'sys/online', '', 1, 'fa fa-user', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (97, 0, '图表管理', '', '', 0, 'fa fa-bar-chart', 7, NULL, NULL);
INSERT INTO `sys_menu` VALUES (98, 97, '百度chart', 'chart/graph_echarts.html', '', 1, 'fa fa-area-chart', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (181, 193, '我的周报', 'bio/report/', 'bio:report:report', 1, 'fa fa-inbox', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (183, 181, '新增', NULL, 'bio:report:add', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (184, 181, '编辑', NULL, 'bio:report:edit', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (185, 181, '删除', NULL, 'bio:report:remove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (186, 181, '批量删除', NULL, 'bio:report:batchRemove', 2, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (187, 3, 'Placeholder', 'bio/placeholder', NULL, 1, 'fa fa-file-code-o', 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (188, 187, '查看', NULL, 'bio:placeholder:placeholder', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (189, 187, '新增', NULL, 'bio:placeholder:add', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (190, 187, '修改', NULL, 'bio:placeholder:edit', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (191, 187, '删除', NULL, 'bio:placeholder:remove', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (192, 187, '批量删除', NULL, 'bio:placeholder:batchRemove', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (193, 0, '周报', NULL, NULL, 0, 'fa fa-newspaper-o', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (194, 193, '组内周报', 'bio/summary', 'bio:summary:summary', 1, 'fa fa-bars', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (195, 194, '查看', NULL, 'bio:summary:summary', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (196, 194, '新增', NULL, 'bio:summary:add', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (197, 194, '修改', NULL, 'bio:summary:edit', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (198, 194, '删除', NULL, 'bio:summary:remove', 2, NULL, 6, NULL, NULL);
INSERT INTO `sys_menu` VALUES (199, 194, '批量删除', NULL, 'bio:summary:batchRemove', 2, NULL, 6, NULL, NULL);
COMMIT;

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='角色';

BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '系统开发人员', 2, '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES (2, 'PI', NULL, '实验室负责人', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '学生', NULL, ' 学生', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- 角色与菜单对应关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=829 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

BEGIN;
INSERT INTO `sys_role_menu` VALUES (721, 1, 20);
INSERT INTO `sys_role_menu` VALUES (722, 1, 21);
INSERT INTO `sys_role_menu` VALUES (723, 1, 22);
INSERT INTO `sys_role_menu` VALUES (724, 1, 61);
INSERT INTO `sys_role_menu` VALUES (725, 1, 12);
INSERT INTO `sys_role_menu` VALUES (726, 1, 13);
INSERT INTO `sys_role_menu` VALUES (727, 1, 14);
INSERT INTO `sys_role_menu` VALUES (728, 1, 24);
INSERT INTO `sys_role_menu` VALUES (729, 1, 25);
INSERT INTO `sys_role_menu` VALUES (730, 1, 26);
INSERT INTO `sys_role_menu` VALUES (731, 1, 15);
INSERT INTO `sys_role_menu` VALUES (732, 1, 55);
INSERT INTO `sys_role_menu` VALUES (733, 1, 56);
INSERT INTO `sys_role_menu` VALUES (734, 1, 62);
INSERT INTO `sys_role_menu` VALUES (735, 1, 74);
INSERT INTO `sys_role_menu` VALUES (736, 1, 75);
INSERT INTO `sys_role_menu` VALUES (737, 1, 76);
INSERT INTO `sys_role_menu` VALUES (738, 1, 188);
INSERT INTO `sys_role_menu` VALUES (739, 1, 189);
INSERT INTO `sys_role_menu` VALUES (740, 1, 190);
INSERT INTO `sys_role_menu` VALUES (741, 1, 191);
INSERT INTO `sys_role_menu` VALUES (742, 1, 192);
INSERT INTO `sys_role_menu` VALUES (743, 1, 48);
INSERT INTO `sys_role_menu` VALUES (744, 1, 72);
INSERT INTO `sys_role_menu` VALUES (745, 1, 28);
INSERT INTO `sys_role_menu` VALUES (746, 1, 29);
INSERT INTO `sys_role_menu` VALUES (747, 1, 30);
INSERT INTO `sys_role_menu` VALUES (748, 1, 57);
INSERT INTO `sys_role_menu` VALUES (749, 1, 92);
INSERT INTO `sys_role_menu` VALUES (750, 1, 98);
INSERT INTO `sys_role_menu` VALUES (751, 1, 185);
INSERT INTO `sys_role_menu` VALUES (752, 1, 186);
INSERT INTO `sys_role_menu` VALUES (753, 1, 2);
INSERT INTO `sys_role_menu` VALUES (754, 1, 6);
INSERT INTO `sys_role_menu` VALUES (755, 1, 7);
INSERT INTO `sys_role_menu` VALUES (756, 1, 73);
INSERT INTO `sys_role_menu` VALUES (757, 1, 187);
INSERT INTO `sys_role_menu` VALUES (758, 1, 3);
INSERT INTO `sys_role_menu` VALUES (759, 1, 77);
INSERT INTO `sys_role_menu` VALUES (760, 1, 27);
INSERT INTO `sys_role_menu` VALUES (761, 1, 91);
INSERT INTO `sys_role_menu` VALUES (762, 1, 97);
INSERT INTO `sys_role_menu` VALUES (763, 1, 194);
INSERT INTO `sys_role_menu` VALUES (764, 1, 195);
INSERT INTO `sys_role_menu` VALUES (765, 1, 196);
INSERT INTO `sys_role_menu` VALUES (766, 1, 197);
INSERT INTO `sys_role_menu` VALUES (767, 1, 198);
INSERT INTO `sys_role_menu` VALUES (768, 1, 199);
INSERT INTO `sys_role_menu` VALUES (769, 1, -1);
INSERT INTO `sys_role_menu` VALUES (770, 1, 193);
INSERT INTO `sys_role_menu` VALUES (771, 1, 181);
INSERT INTO `sys_role_menu` VALUES (772, 3, 184);
INSERT INTO `sys_role_menu` VALUES (773, 3, 195);
INSERT INTO `sys_role_menu` VALUES (774, 3, -1);
INSERT INTO `sys_role_menu` VALUES (775, 3, 193);
INSERT INTO `sys_role_menu` VALUES (776, 3, 181);
INSERT INTO `sys_role_menu` VALUES (777, 3, 194);
INSERT INTO `sys_role_menu` VALUES (813, 2, 12);
INSERT INTO `sys_role_menu` VALUES (814, 2, 13);
INSERT INTO `sys_role_menu` VALUES (815, 2, 14);
INSERT INTO `sys_role_menu` VALUES (816, 2, 24);
INSERT INTO `sys_role_menu` VALUES (817, 2, 25);
INSERT INTO `sys_role_menu` VALUES (818, 2, 26);
INSERT INTO `sys_role_menu` VALUES (819, 2, 75);
INSERT INTO `sys_role_menu` VALUES (820, 2, 76);
INSERT INTO `sys_role_menu` VALUES (821, 2, 195);
INSERT INTO `sys_role_menu` VALUES (822, 2, 6);
INSERT INTO `sys_role_menu` VALUES (823, 2, 74);
INSERT INTO `sys_role_menu` VALUES (824, 2, 73);
INSERT INTO `sys_role_menu` VALUES (825, 2, -1);
INSERT INTO `sys_role_menu` VALUES (826, 2, 3);
INSERT INTO `sys_role_menu` VALUES (827, 2, 193);
INSERT INTO `sys_role_menu` VALUES (828, 2, 194);
COMMIT;

-- ----------------------------
-- 定时任务表
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `sys_task` VALUES (2, '0 0 7 ? * MON', 'run1', '1', '每周一七点给老师生成统计信息和周报总表，并邮件通知给老师', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bio.job.jobs.ThisWeekReportTemplateGenerateJob', '2017-05-19 18:30:56', '1', 'group1', '2017-05-19 18:31:07', NULL, '', 'ThisWeekReportTemplateGenerateJob');
INSERT INTO `sys_task` VALUES (11, '0 0  19 ? * SUN', NULL, NULL, '周报完成提示定时任务', NULL, 'com.bio.job.jobs.WeekReportNotFinishedNotificationJob', NULL, '1', 'group1', NULL, NULL, NULL, 'WeekReportNotFinishedNotificationJob');
INSERT INTO `sys_task` VALUES (12, '0 30 7 ? * MON', NULL, NULL, '上周周报完成情况统计定时任务', NULL, 'com.bio.job.jobs.LastWeekReportStatisticsJob', NULL, '1', 'group1', NULL, NULL, NULL, 'LastWeekReportStatisticsJob');
COMMIT;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `pic_id` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 默认管理员账号: admin / 1
-- 密码使用 MD5 加密: 33808479d49ca8a3cdc93d4f976d1e3d
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', '33808479d49ca8a3cdc93d4f976d1e3d', 0, 'admin@example.com', '13800138000', 1, 1, '2017-08-15 21:40:39', '2017-08-15 21:41:00', NULL);
COMMIT;

-- ----------------------------
-- 用户与角色对应关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
