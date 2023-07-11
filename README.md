# API接口开放平台

# 项目介绍

基于 React + Spring Boot + Dubbo + Gateway+Elastic Stack 的API 接口聚合搜索平台。管理员可以接入并发布接口，可视化各接口调用情况；用户可以开通接口调用权限、浏览接口及在线调试，并通过客户端 SDK 轻松调用接口。并且提供聚合搜索功能，用户可以在同一个页面集中搜索出不同来源、不同类型的内容，提升用户的检索效率和搜索体验。

# 项目架构

![img.png](api-backend%2Fdoc%2Fimg.png)

## 前端架构

```tsx
├── config                   # umi 配置，包含路由，构建等配置
├── mock                     # 本地模拟数据
├── public
│   └── favicon.png          # Favicon
├── src
│   ├── assets               # 本地静态资源
│   ├── components           # 业务通用组件
│   ├── e2e                  # 集成测试用例
│   ├── layouts              # 通用布局
│   ├── models               # 全局 dva model
│   ├── pages                # 业务页面入口和常用模板
│   ├── services             # 后台接口服务
│   ├── utils                # 工具库
│   ├── locales              # 国际化资源
│   ├── global.less          # 全局样式
│   └── global.ts            # 全局 JS
├── README.md
└── package.json
```

## 后端架构

- API-backend：后端管理系统
- api-interface：在线接口系统
- api-clientSDK：客户端调用SDK系统
- api-common：公共服务系统

# ****技术框架****

- 前端技术
    - React 18
    - Ant Design Pro 5.x 脚手架
    - Ant Design & Procomponents 组件库
    - Umi 4 前端框架
    - OpenAPI 前端代码生成
    - AntV 数据可视化框架
- 后端技术
    - Java8,Spring Boot2.7.9
    - MySQL8.0 数据库
    - MyBatis-Plus 及 MyBatis X 自动生成
    - API 签名认证（Http 调用）
    - Spring Boot Starter（SDK 开发）
    - Dubbo 分布式（RPC、Nacos）
    - Swagger + Knife4j 接口文档生成
    - pring Cloud Gateway 微服务网关
    - Hutool、Apache Common Utils、Gson 等工具库
    - Elastic Stack

# TODO

- [ ] 
- [ ] 
- [ ]