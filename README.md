# BioDashboard 周报管理系统

The dashboard of Prof.Li's group, let it boost the research cooperation and makes research better！

项目起源于中南大学生物信息实验室 http://bioinformatics.csu.edu.cn ，试用版本请访问本文档末尾提供的试用链接。

适合中小型公司团队或者科研院校实验室的周报系统，采用“部门【实验室】-部门主管【PI】-员工【学生】”扁平化的模式管理，代码完全开源，部署简单方便。

## 功能

### 周报模块
员工可以撰写、查看自己的周报，系统提供了默认的周报撰写模板，为了优化撰写周报的体验，系统可以拉取最近一周的报告内容作为参考。

### 成员组织管理模块
部门主管可以增删查改小组及管理相应小组的成员。 

### 通知模块：
系统在后台维护了三个定时任务来支持通知功能。在周一早七点的时候给所有员工发送生成本周的周报草稿通知邮件； 在周日晚七点的时候会给未及时完成周报的员工发通知邮件； 在周一早八点半的时候会给部门主管推送上周组内周报的统计信息(未完成人数及名单)。目前仅支持邮件推送。

### 系统截图

![截图 1](https://tva1.sinaimg.cn/large/006tNbRwgy1gaekv4c2vuj30np0ei74x.jpg)
![截图 2](https://tva1.sinaimg.cn/large/006tNbRwgy1gael2aknjtj30x20io419.jpg)
![截图 3](https://tva1.sinaimg.cn/large/006tNbRwgy1gael5yeqtij30wy0ehmym.jpg)

![截图 4](https://tva1.sinaimg.cn/large/006tNbRwgy1gaeln2205ej31240eb41g.jpg)
![截图 5](https://tva1.sinaimg.cn/large/006tNbRwgy1gaem6qec6uj311x0d5acc.jpg)
![截图 5](https://tva1.sinaimg.cn/large/006tNbRwgy1gaelks6pl8j311s0h7acs.jpg)

## 技术选型

基于 Spring Boot + Mybatis + Mybatis Plus搭建的平台。

以 Spring Boot 为基础框架， Mybatis plus为数据访问层, Apache Shiro 为权限授权层， Ehcahe 对常用数据进行缓存，基于 Bootstrap 构建的 Admin LTE 作为前端框架。

1.后端
核心框架：Spring Boot
安全框架：Apache Shiro
视图框架：Spring MVC
服务端验证：Hibernate Validator
任务调度：Quartz
持久层框架：Mybatis、Mybatis plus
数据库连接池：Alibaba Druid
缓存框架：Ehcache
日志管理：SLF4J、Log4j
工具类：Apache Commons、Jackson、Xstream

2.前端
JS框架：jQuery
CSS框架：Twitter Bootstrap
数据表格：bootstrap table
对话框：layer
树结构控件：jQuery zTree
日期控件： datepicker

## QA

### SQL文件在哪里？
请添加QQ群 690853899，群文件共享里面；

### admin的登录密码？
请查看一下源代码，用户登录部分；

### 同学能自己创建周报吗？
考虑到每个人写周报的时候标题格式都不一样，不便于管理，因此同学不能自己创建周报；退而求其次，这个周报是由后台定时任务添加的，类似于派单：周一的时候会给所有的同学生成本周的周报任务，每个同学在本周周五之前填写完成. Larry Wall's Perl slogan "There's more than one way to do it " 

### 定时任务
添加一个同学或员工的时候手动在那个定时任务的地方点击“运行一次”，就可以给那个同学生成本周的周报模板；然后打开“开启定时任务”，这个任务会一直执行下去，每周都会给该同学发周报任务；

## 后续开发计划
暂无，有产品或者技术有任何想法的非常欢迎在QQ群讨论或者提交issues；由于疫情的原因有部分同学提出要加上日报功能欢迎 PR 二次开发；

## 二次开发建议
开发时建议直接使用 SpringBoot 提供的内嵌 Tomcat 容器，ApplicationEmbedded.java 中的 annotation 请取消掉注释，直接使用 Run As Java Application 的形式；部署的时候建议把 ApplicationEmbedded.java 中的 annotation 注释掉，然后使用 maven 打成 war 包部署到 Tomcat 中。

## 产品试用
访问：https://chenxofhit.com/rushboard ，点击 ‘机构注册’ 注册账号后使用！ 如果您觉得本系统对您有用，请点击右上角的 Star ~ 
