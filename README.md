# testall 持续更新中···

## 打算把用到过的和学习过的，所有前后端技术都集成到这个项目里，并在issues里配以介绍说明，以备以后使用。

已包含的技术功能点：

- [Spring Boot](https://github.com/x113773/testall/blob/master/src/main/java/com/ansel/testall/Application.java)
这个项目就是就是基于Spring Boot框架；

- [Ansible](https://github.com/x113773/testall/tree/master/src/main/java/com/ansel/testall/ansible)
是一个自动化运维工具，可以实现批量系统配置、批量程序部署、批量运行命令等功能。这里的样例实现了如下功能：通过指定本机和目标主机（均为Linux）的一些配置信息，实现自动启停Tomcat、weblogic，并删除同名旧项目，部署新项目。具体的配置说明及实现说明，请看[这里](https://github.com/x113773/testall/issues/4)
 
- [AOP](https://github.com/x113773/testall/tree/master/src/main/java/com/ansel/testall/aop)
面向切面编程，这里是一个Spring AOP 的实例，通过Advice可以获得方法的一些信息，并在方法执行前后执行一些操作。通过Introduction可以为某个对象增加新的 方法，详见[这里](https://github.com/x113773/testall/issues/12)
 
- [CORS](https://github.com/x113773/testall/tree/master/src/main/java/com/ansel/testall/cors)
跨域资源共享，这里是Springboot开启全局支持CORS的配置，什么是CORS，请看[这里](http://www.ruanyifeng.com/blog/2016/04/cors.html)

- [HTTPS](https://github.com/x113773/testall/tree/master/src/main/java/com/ansel/testall/https)
Springboot开启https协议，详见[这里](https://github.com/x113773/testall/issues/1)

- [MyBatis](https://github.com/x113773/testall/tree/master/src/main/java/com/ansel/testall/mybatis)
Springboot集成MyBatis的两种方式，详见[这里](https://github.com/x113773/testall/issues/9)
