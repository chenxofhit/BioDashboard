# 更新日志

所有重要的更改都会记录在此文件中。

格式基于 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.0.0/)，
并且该项目遵循 [语义化版本](https://semver.org/lang/zh-CN/)。

## [未发布]

### 新增
- 开源项目标准化：添加 LICENSE、CONTRIBUTING.md
- 优化数据库初始化脚本 init.sql
- 完善 README.md 文档

### 变更
- 简化部署流程，数据库配置更通用
- 清理敏感信息（测试数据、云存储密钥等）

## [1.0.0] - 2024-02-15

### 新增
- 周报管理功能：自动生成周报模板、历史对照、周报汇总
- 组织架构管理：扁平化部门-主管-员工结构
- 定时任务：周报提醒、统计报告邮件通知
- 权限管理：基于角色的访问控制
- 系统监控：日志管理、在线用户、数据库监控

### 技术栈
- Spring Boot 2.0+
- MyBatis-Plus
- Apache Shiro
- Quartz
- Bootstrap + AdminLTE
