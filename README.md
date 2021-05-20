# 在线视频系统

## 核心业务功能:
文件上传, 断点续传, 技术秒传, 加密视频播放
## 通用业务功能:
权限设计, 图形验证码设计, 单点登录, 阿里云部署
## 项目框架:
前端:
Vue 4.5 , BootStrap
后端:
Spring Boot, Spring Cloud, Redis, MySQL
工程化:
阿里云部署, Maven, Git

## 项目模块说明

* admin

  后台管理, vue cli 项目

* business

  核心业务模块, spring boot 项目

* doc

  项目相关文档, 含项目数据库初始化脚本

* eureka

  注册中心

* file

  文件模块, spring boot 项目

* server

  公共jar模块, 被business, file, system依赖

* web

  网站模块, vue cli 项目

## 项目初始化

* 须在本地安装idea, node.js, jdk1.8, mysql5.7
* 刷新maven依赖
* 安装vue cli `npm install -g @vue.cli`
* 下载node模块 `cd web` 与 `npm install`
* 新建数据库, 修改各个spring boot 项目里的yml配置文件

## 项目启动

- 启动注册中心: EurekaApplication :8761/
- 启动业务模块: BusinessApplication [devtools] :9002/
- 启动文件模块: FileApplication [devtools] :9003/
- 启动路由模块: GatewayApplication [devtools] :9000/
- 启动系统模块: SystemApplication [devtools] :9001/
- 启动控台管理: admin\package.json
- 启动前端网站: web\package.json

## 页面访问

* 控制台地址: http://localhost:8080/login

  初始用户名/密码: test/test

* 网站地址: http://localhost:8081

  自己注册, 短信验证码后台日志看

  初始用户名/密码: 12345678901/test

## 资源配置

- 所有资源在 /doc/db/resource.json

- 需要到控台上配置进去, 并在角色管理中配置权限, 再重新登录

- OSS对象存储

  file模块中application.yml 配置: accessKeyId, accessKeySecret, endpoint, domain, bucket

- VOD视频点播

  file模块中application.yml 配置: accessKeyId, accessKeySecret

- Redis配置

  gateway和server模块中application.yml 配置 password (默认test123)

  

  

