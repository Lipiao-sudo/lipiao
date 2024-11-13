# lipiao
开发环境：Intellij idea + Tomcat9.0 + Mysql8.0 + jdk17  + Maven
软件架构：SpringBoot+ Spring + Mybatisplus+ Elementui + Jwt+ Redis+ swagger+ 阿里云OSS/VOD
项目描述：随着互联网技术的不断进步，人们对于在线娱乐的需求日益增长，特别是对优质电影视频等内容。为了满足这一市场需求，特别开发了星辰影视平台。该平台采用前后端分离架构，结合了当前企业中最实用的前沿技术，如JWT、Spring Security、Redis、knife4j（swagger2）、阿里云0SS存储和阿里云视频点播(VOD)等技术。
本项目功能如下：
1、利用Nginx和阿里云0SS服务来存储和管理图片文件，确保了系统的高可用性和扩展性。同时通过集成阿里云视频点播(V0D)服务提供视频的一个在线获取，为用户提供了稳定流畅的视频播放体验，同时支持视频的上传、转码、存储和分发等。
2、采用Redis作为缓存解决方案，提高系统性能，同时使用Redis、JWT等对用户登录生成一个Token存储在客户端，通过Redis的高速缓存能力，快速验证用户Token，提高系统的响应速度和扩展性。
3、通过knife4j（swagger2）提供了一个强大的API文档和测试平台，方便前后端开发者进行接口的调试和文档的查阅。
