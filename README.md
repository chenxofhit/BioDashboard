# BioDashboard 周报系统

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.0+-green.svg" alt="Spring Boot">
  <img src="https://img.shields.io/badge/MySQL-5.7+-blue.svg" alt="MySQL">
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License">
  <img src="https://img.shields.io/badge/PRs-welcome-brightgreen.svg" alt="PRs Welcome">
</p>

<p align="center">
  适合中小型公司和科研院校实验室的周报管理系统<br>
  <em>让周报不再枯燥，让科研协作更高效！</em> :rocket:
</p>

---

## 项目简介

BioDashboard 是一款专为**实验室/部门-PI/主管-学生/员工**扁平化管理模式设计的周报系统。

项目起源于 [中南大学生物信息实验室](http://bioinformatics.csu.edu.cn)，已在多个高校实验室和企业团队中使用，包括：
- 中南大学生物信息实验室
- 湖南科技大学单细胞计算生物研究组
- 第四范式（业界公司）
- 江苏理工大学毕业设计原型

## 功能特性

### 周报管理
- :memo: **智能周报模板** - 周一自动生成本周周报草稿
- :mag: **历史对照** - 查看上周周报内容作为参考
- :closed_book: **周报汇总** - PI/主管可查看组内所有周报

### 组织管理
- :office: **扁平化架构** - 实验室(部门) → PI(主管) → 学生(员工)
- :busts_in_silhouette: **成员管理** - PI 可增删查改小组成员
- :lock: **权限控制** - 基于角色的细粒度权限管理

### 定时通知
- :alarm_clock: **周一 7:00** - 自动生成本周周报并邮件通知
- :bell: **周日 19:00** - 提醒未完成周报的同学
- :chart_with_upwards_trend: **周一 8:30** - 发送上周统计报告给 PI

## 技术栈

| 类别 | 技术 |
|------|------|
| **后端框架** | Spring Boot 2.0+ |
| **安全框架** | Apache Shiro |
| **持久层** | MyBatis / MyBatis-Plus |
| **数据库** | MySQL 5.7+ |
| **连接池** | Alibaba Druid |
| **缓存** | Ehcache |
| **任务调度** | Quartz |
| **数据库迁移** | Flyway |
| **前端框架** | Bootstrap + AdminLTE |
| **表格组件** | Bootstrap Table |

## 快速开始

### 环境要求

- JDK 1.8+
- MySQL 5.7+
- Maven 3.5+

### 1. 克隆项目

```bash
git clone https://github.com/chenxofhit/BioDashboard.git
cd BioDashboard
```

### 2. 数据库初始化（自动）

项目已集成 **Flyway** 数据库迁移工具，启动时会自动检查并初始化数据库：

- :white_check_mark: **首次启动** - 自动创建所有表结构和基础数据
- :repeat: **后续启动** - 自动跳过已执行的脚本，不会重复初始化
- :arrow_up: **版本升级** - 自动执行新的数据库迁移脚本

**手动初始化（可选）**

如需手动导入数据，可执行：
```bash
mysql -u root -p biodashboard < init.sql
```

### 3. 配置数据库

编辑 `src/main/resources/application-dev.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/biodashboard?useUnicode=true&characterEncoding=utf8
    username: root          # 修改为你的数据库用户名
    password: root          # 修改为你的数据库密码
```

### 4. 启动项目

**开发模式（推荐）**

```bash
mvn spring-boot:run
```

**或使用 JAR 包方式**

```bash
# Linux/Mac
mvn clean package -DskipTests
./start.sh start

# Windows
mvn clean package -DskipTests
start.bat start
```

### 5. 访问系统

- 地址：http://localhost:8088/biodashboard
- 账号：`admin`
- 密码：`1`

## 邮件配置（可选）

如需启用邮件通知功能，编辑 `src/main/resources/application.properties`：

```properties
# SMTP 服务器配置
spring.mail.host=smtp.qq.com
spring.mail.username=your-email@qq.com
spring.mail.password=your-auth-code
spring.mail.port=587
spring.mail.protocol=smtp
spring.mail.default-encoding=UTF-8

# 发件人
biodashboard.mail.sender=your-email@qq.com
```

## 系统截图

| 登录页面 | 周报列表 | 填写周报 |
|---------|---------|---------|
| ![登录](https://tva1.sinaimg.cn/large/006tNbRwgy1gaekv4c2vuj30np0ei74x.jpg) | ![列表](https://tva1.sinaimg.cn/large/006tNbRwgy1gael2aknjtj30x20io419.jpg) | ![填写](https://tva1.sinaimg.cn/large/006tNbRwgy1gael5yeqtij30wy0ehmym.jpg) |

| 部门管理 | 角色管理 | 定时任务 |
|---------|---------|---------|
| ![部门](https://tva1.sinaimg.cn/large/006tNbRwgy1gaeln2205ej31240eb41g.jpg) | ![角色](https://tva1.sinaimg.cn/large/006tNbRwgy1gaem6qec6uj311x0d5acc.jpg) | ![任务](https://tva1.sinaimg.cn/large/006tNbRwgy1gaelks6pl8j311s0h7acs.jpg) |

## 常见问题

### Q: 为什么用户不能自己创建周报？

**A:** 为了保证周报格式统一便于管理，周报由系统定时任务自动生成，而非用户手动创建。每周一早上会自动为所有成员生成本周周报任务。

### Q: 如何为新成员生成本周周报？

**A:** 
1. 在「系统工具」→「计划任务」中找到 `ThisWeekReportTemplateGenerateJob`
2. 点击「运行一次」为新成员生成周报
3. 开启定时任务后，系统会在每周一自动为所有成员派发周报

## 生产环境部署

### 方式一：使用启动脚本（推荐）

#### Linux/Mac

```bash
# 1. 编译打包
mvn clean package -DskipTests

# 2. 复制启动脚本
cp target/biodashboard-1.0.0.jar .

# 3. 赋予执行权限
chmod +x start.sh

# 4. 启动应用
./start.sh start

# 其他命令
./start.sh stop      # 停止应用
./start.sh restart   # 重启应用
./start.sh status    # 查看状态
./start.sh log       # 查看实时日志
```

#### Windows

```bash
# 1. 编译打包
mvn clean package -DskipTests

# 2. 复制启动脚本
copy target\biodashboard-1.0.0.jar .

# 3. 启动应用
start.bat start

# 其他命令
start.bat stop       # 停止应用
start.bat restart    # 重启应用
start.bat status     # 查看状态
```

### 方式二：直接运行 JAR

```bash
# 编译打包
mvn clean package -DskipTests

# 运行（使用生产环境配置）
java -jar target/biodashboard-1.0.0.jar --spring.profiles.active=prod

# 或指定自定义配置
java -jar target/biodashboard-1.0.0.jar \
  --spring.profiles.active=prod \
  --server.port=8080 \
  --spring.datasource.password=yourpassword
```

### 生产环境配置

编辑 `src/main/resources/application-prod.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/biodashboard?useUnicode=true&characterEncoding=utf8
    username: root
    password: your_production_password    # 修改生产环境密码

server:
  port: 8088                            # 可根据需要修改端口

bio:
  projectRootURL: http://your-domain.com/biodashboard/  # 修改为你的域名
```

### 后台运行（Linux）

```bash
# 使用 nohup
nohup java -jar biodashboard-1.0.0.jar --spring.profiles.active=prod > app.log 2>&1 &

# 或使用 systemd（推荐）
# 创建 /etc/systemd/system/biodashboard.service
```

### 使用 systemd 服务（推荐）

创建服务文件 `/etc/systemd/system/biodashboard.service`：

```ini
[Unit]
Description=BioDashboard Weekly Report System
After=syslog.target

[Service]
User=biodashboard
Group=biodashboard
WorkingDirectory=/opt/biodashboard
ExecStart=/usr/bin/java -jar biodashboard-1.0.0.jar --spring.profiles.active=prod
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

启动服务：

```bash
sudo systemctl daemon-reload
sudo systemctl start biodashboard
sudo systemctl enable biodashboard  # 开机自启
sudo systemctl status biodashboard  # 查看状态
```

## 项目结构

```
BioDashboard/
├── src/
│   └── main/
│       ├── java/com/bio/
│       │   ├── common/          # 公共工具类
│       │   ├── sys/             # 系统管理模块
│       │   ├── bio/             # 周报业务模块
│       │   └── job/             # 定时任务模块
│       └── resources/
│           ├── mapper/          # MyBatis 映射文件
│           ├── templates/       # Thymeleaf 模板
│           └── static/          # 静态资源
├── init.sql                     # 数据库初始化脚本（手动导入用）
├── src/main/resources/db/migration/  # Flyway 自动迁移脚本
├── start.sh                     # Linux/Mac 启动脚本
├── start.bat                    # Windows 启动脚本
├── pom.xml                      # Maven 配置
└── README.md
```

## 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交你的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开一个 Pull Request

## 开发计划

- [ ] 微信小程序端
- [ ] 移动端 APP (iOS/Android)
- [ ] UI 界面优化
- [ ] 数据导出功能 (PDF/Excel)
- [ ] 钉钉/飞书集成

## 许可证

[MIT License](LICENSE)

## 致谢

感谢以下单位和个人对项目的支持：

- 中南大学生物信息实验室
- 湖南科技大学单细胞计算生物研究组
- 所有贡献者和用户

---

<p align="center">
  <sub>Built with :heart: by <a href="https://github.com/chenxofhit">@chenxofhit</a></sub>
</p>
