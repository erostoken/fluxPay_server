# FluxPay Server

FluxPay 后端服务 —— API 调用计费平台，采用 **Java 多模块 Maven** 工程组织，基于 Spring Boot 3.4.3 构建。

---

## 项目结构

```
server/
├── pom.xml                  # 根聚合 POM，统一管理版本与插件
├── fluxpay-common/          # 公共模块：全局响应体、枚举、异常定义、工具类
├── fluxpay-core/            # 核心业务模块：实体、Mapper、Service，供上层模块复用
├── fluxpay-gateway/         # API 网关：请求鉴权、限流、按次计量（Spring Cloud Gateway）
├── fluxpay-admin/           # 管理后台 BFF：内部管理员操作界面的后端服务
└── fluxpay-website/         # 官方网站 BFF：对外展示与企业注册入口的后端服务
```

### 模块依赖关系

```
fluxpay-common
    ↑
fluxpay-core ──────────────┐
    ↑                       ↑
fluxpay-admin          fluxpay-website

fluxpay-common
    ↑
fluxpay-gateway
```

---

## 技术栈

| 分类 | 技术 | 版本 |
|------|------|------|
| 语言 / 运行时 | Java | 17 |
| 框架 | Spring Boot | 3.4.3 |
| 微服务网关 | Spring Cloud Gateway | 2024.0.1 |
| ORM | MyBatis-Plus | 3.5.9 |
| 数据库 | MySQL | 8.x |
| 缓存 / 限流 | Redis | — |
| 认证授权 | Spring Security + JJWT | 0.12.6 |
| 对象映射 | MapStruct | 1.6.3 |
| 工具库 | Hutool | 5.8.35 |
| 构建工具 | Maven | 3.x |

---

## 快速启动

### 前置条件

- JDK 17+
- Maven 3.8+
- MySQL 8.x（创建对应数据库）
- Redis 6.x+

### 1. 克隆仓库

```bash
git clone <repo-url>
cd server
```

### 2. 配置数据库与 Redis

分别修改各可部署模块的 `src/main/resources/application.properties`：

```properties
# 数据库
spring.datasource.url=jdbc:mysql://localhost:3306/fluxpay?useUnicode=true&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=your_password

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### 3. 编译整个工程

```bash
mvn clean install -DskipTests
```

### 4. 分别启动各服务

```bash
# 管理后台（默认端口 8080）
cd fluxpay-admin
mvn spring-boot:run

# 官方网站（默认端口 8080，需在配置中区分端口）
cd fluxpay-website
mvn spring-boot:run

# API 网关（默认端口 8080，需在配置中区分端口）
cd fluxpay-gateway
mvn spring-boot:run
```

---

## 模块说明与目录结构

### fluxpay-common

纯 Java 库，不依赖任何内部模块，被所有其他模块引用。

---

### fluxpay-core

业务核心层，依赖 `fluxpay-common`，供 `fluxpay-admin` 和 `fluxpay-website` 复用。

---

### fluxpay-gateway

基于 Spring Cloud Gateway（WebFlux 响应式），依赖 `fluxpay-common`，独立部署。


---

### fluxpay-admin

Spring MVC + Spring Security，依赖 `fluxpay-common` + `fluxpay-core`，独立部署。

---

### fluxpay-website

Spring MVC，依赖 `fluxpay-common` + `fluxpay-core`，独立部署。

---

## 开发规范

- **代码风格**：Lombok 简化 POJO，MapStruct 做 DTO/Entity 转换，禁止手写 getter/setter
- **版本管理**：所有依赖版本在根 `pom.xml` 的 `<dependencyManagement>` 中统一声明，子模块不写版本号
- **可部署模块**：仅 `fluxpay-admin`、`fluxpay-website`、`fluxpay-gateway` 配置了 `spring-boot-maven-plugin`，`fluxpay-common` 和 `fluxpay-core` 作为普通 jar 库被引用
