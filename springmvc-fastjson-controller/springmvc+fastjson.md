---
title: springmvc+fastjson
tags: 接入层模板，不支持分布式
grammar_cjkRuby: true
---
# 代码结构
controller 包存放springmvc控制器
form  包存放前台提交过来的表单po
resources/messages 存放资源文件
resources/myconfig 自定义的spring配置文件
resources/servlet-content.xml 　主配置文件

# 报文格式验证
## 接收client传过来的数据;
1. application/json;charset=UTF-8　类型数据，必须要在Controller方法参数据上@RequestBody
2. application/x-www-form-urlencoded   @RequestBody参数可选
3. multipart/form-data  也是表单提交方式  key=value型式只不过有些key是文件
    必顺加上　　multipartResolver　对象　才能解析．
## 报文验工证:
    采用JSR 303验证框架+国际化资源

# Session管理
只支持单机的Session管理

# 异常处理
1. 应用了统一的异常处理机制，确保发生异常时返回给用户是友好的错误信息；避免了异常外理代码
散落在接入层的其它化码里面。
