# 贡献指南

感谢您对 BioDashboard 项目的关注！我们欢迎各种形式的贡献。

## 如何贡献

### 报告问题

如果您发现了 bug 或有功能建议，请通过 [GitHub Issues](https://github.com/chenxofhit/BioDashboard/issues) 提交：

1. 检查是否已有相关问题
2. 提供详细的问题描述
3. 提供复现步骤（如果是 bug）
4. 标注环境信息（操作系统、JDK 版本、MySQL 版本等）

### 提交代码

1. **Fork** 本仓库
2. 创建特性分支：`git checkout -b feature/my-feature`
3. 提交更改：`git commit -m 'Add some feature'`
4. 推送分支：`git push origin feature/my-feature`
5. 提交 **Pull Request**

### 代码规范

- 遵循项目现有的代码风格
- 提交前运行测试：`mvn test`
- 确保代码可以通过编译：`mvn clean package`
- 添加必要的注释

### 提交信息规范

- 使用中文或英文描述
- 格式：`<类型>: <描述>`
- 类型包括：`feat`（新功能）、`fix`（修复）、`docs`（文档）、`style`（格式）、`refactor`（重构）、`test`（测试）、`chore`（构建）

示例：
```
feat: 添加周报导出功能
fix: 修复定时任务重复执行问题
docs: 更新 README 部署说明
```

## 开发环境设置

### 必要条件

- JDK 1.8+
- Maven 3.5+
- MySQL 5.7+
- IDE（推荐 IntelliJ IDEA）

### 本地开发

```bash
# 1. 克隆仓库
git clone https://github.com/chenxofhit/BioDashboard.git
cd BioDashboard

# 2. 初始化数据库
mysql -u root -p < init.sql

# 3. 使用 IDE 打开项目
# 4. 运行 Application.java
# 5. 访问 http://localhost:8088/biodashboard
```

## 联系我们

- GitHub Issues: [https://github.com/chenxofhit/BioDashboard/issues](https://github.com/chenxofhit/BioDashboard/issues)

## 行为准则

- 尊重他人，保持友善和建设性的交流
- 接受不同的观点和经验
- 专注于对社区最有利的事情
- 对其他社区成员表示同理心

再次感谢您的贡献！
