<a name="index">**导航**</a>

<a href="#0">BioDashboard 周报管理系统</a>

&emsp;<a href="#1">目录</a>

&emsp;&emsp;<a href="#2">周报模块</a>

&emsp;&emsp;<a href="#3">组织及成员管理模块</a>

&emsp;&emsp;<a href="#4">通知模块</a>

&emsp;&emsp;<a href="#5">系统截图</a>

&emsp;<a href="#6">QA</a>

&emsp;&emsp;<a href="#7">用户能自己创建周报吗？</a>

&emsp;&emsp;<a href="#8">定时任务</a>

&emsp;<a href="#9">独立部署</a>

&emsp;&emsp;<a href="#10">SQL文件在哪里？</a>

&emsp;&emsp;<a href="#11">管理员admin的登录密码？</a>

&emsp;&emsp;<a href="#12">配置邮件客户端</a>

&emsp;&emsp;<a href="#13">配置本地和线上的数据库</a>

&emsp;&emsp;<a href="#14">Eclipse 或者 IDEA 支持</a>

&emsp;<a href="#15">后续开发计划</a>

&emsp;&emsp;<a href="#16">当前技术选型</a>

&emsp;<a href="#17">产品试用</a>

# <a name="0">BioDashboard 周报管理系统</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

The dashboard of Prof.Li's group, let it boost the research cooperation and makes research better！:rocket:	:rocket:	:rocket:	

项目起源于:cn:中南大学生物信息实验室http://bioinformatics.csu.edu.cn ，试用请访问本文档末尾提供的试用链接:arrow_heading_down:	。

适合中小型公司:office:	或者科研院校实验室:school:	的周报系统，采用“实验室[部门]-PI[部门主管]-学生[员工]”扁平化的模式管理，代码完全开源，部署简单方便。下文为了叙述方便，统一采用 “实验室-PI-学生”的视角来展开，不失一般性。

## <a name="1">功能</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">周报模块</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
同学可以在系统提供的模板上查看、编辑自己的周报；为了优化撰写周报的体验，系统可以拉取最近一周的报告内容作为对照。

### <a name="3">组织及成员管理模块</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
PI 可以增删查改小组及管理相应小组的成员。 

### <a name="4">通知模块</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
系统在后台维护了三个定时任务来支持通知功能。在周一早七点的时候给所有同学发送生成本周的周报草稿通知邮件； 在周日晚七点的时候会给未及时完成周报的同学发通知邮件； 在周一早八点半的时候会给PI推送上周组内周报的统计信息(未完成人数及名单)。目前仅支持邮件推送。

### <a name="5">系统截图</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![截图 1](https://tva1.sinaimg.cn/large/006tNbRwgy1gaekv4c2vuj30np0ei74x.jpg)
![截图 2](https://tva1.sinaimg.cn/large/006tNbRwgy1gael2aknjtj30x20io419.jpg)
![截图 3](https://tva1.sinaimg.cn/large/006tNbRwgy1gael5yeqtij30wy0ehmym.jpg)

![截图 4](https://tva1.sinaimg.cn/large/006tNbRwgy1gaeln2205ej31240eb41g.jpg)
![截图 5](https://tva1.sinaimg.cn/large/006tNbRwgy1gaem6qec6uj311x0d5acc.jpg)
![截图 5](https://tva1.sinaimg.cn/large/006tNbRwgy1gaelks6pl8j311s0h7acs.jpg)

## <a name="6">QA</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="7">用户能自己创建周报吗？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
这是留言里面问的最多的一个问题，答案是不能：） 考虑到每个人写周报的时候标题格式都不一样，不便于管理，因此同学不能自己创建周报；退而求其次，这个周报是由后台定时任务添加的，类似于派单：周一的时候会给所有的同学生成本周的周报任务，每个同学在本周周五之前填写完成. 我比较欣赏的一句话：Larry Wall's Perl slogan "There's more than one way to do it"； 

### <a name="8">定时任务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
添加一个同学的时候手动在生成周报模板的定时任务的地方点击“运行一次”，就可以给那个同学生成本周的周报模板（之前发送过的不会重发，利用业务逻辑保证的幂等性）；然后打开“开启定时任务”，这个任务会一直在每周的制定时间给该同学派发周报任务；




## <a name="9">独立部署</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="10">SQL文件在哪里？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

请添加QQ群 690853899，群文件共享里面；

### <a name="11">管理员admin的登录密码？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

请查看一下源代码，用户登录部分；

### <a name="12">配置邮件客户端</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

application.properties 邮件客户端修改成适合自己团队使用的配置；

### <a name="13">配置本地和线上的数据库</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
这个部分就不说了，建库脚本在 QQ 群共享中，请加入后获取；

### <a name="14">Eclipse 或者 IDEA 支持</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
开发时建议直接使用 Spring Boot 提供的内嵌 Tomcat 容器，ApplicationEmbedded.java 中的 annotation 请取消掉注释，直接使用 Run As Java Application 的形式；部署的时候建议把 ApplicationEmbedded.java 中的 annotation 注释掉，然后使用 maven 打成 war 包部署到 Tomcat 中。



## <a name="15">后续开发计划</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

希望有产品经验的，UI设计经验的同学加入，同时寻微信小程序，iOS，Android 三端开发爱好者共同完成，代码将完全开源； 

### <a name="16">当前技术选型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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



## <a name="17">产品试用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
欢迎访问：https://chenxofhit.com/rushboard 点击‘机构注册’注册账号后使用！烟花易冷，落笔成殇，如果您觉得本系统对您有所启示或帮助，请点击右上角的Star :hearts:，愿盛世繁华:fireworks:	如你所愿:sparkling_heart:	:sparkling_heart:	:sparkling_heart:	。 在个人的博客文章中我对这个项目的前前后后做了一个回顾，地址是：https://chenxofhit.com/blog/index.php/46.html， 欢迎阅读和指点！ 
