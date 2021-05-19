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

