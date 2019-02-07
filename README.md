
### 项目说明

此项目基于springboot做周边组件监控和代码自动生成

> 目前完成功能：
+ 获取jvm配置信息
+ 获取已加载的jar包
+ 获取已加载的class （非bootstrap classload加载）
+ spring配置信息
+ spring容器中的实力
+ 数据库基本信息查看
+ 通过数据库实体信息生成mybatis plus代码
+ 生成接口文档
+ 通过json生成java实体类

### 启动方式
> 添加pom信息到pom.xml中
```xml
<dependency>
    <groupId>io.github.tanghuibo</groupId>
    <artifactId>spring-takeaway-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
<!--oss快照仓库-->
<repositories>
    <repository>
        <id>oss</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>

```
> 启动项目
----------
访问 http://127.0.0.1:{port}/{content-path}/spring-takeaway/index.html即可开始使用<br>
示例 http://127.0.0.1:8081/spring-takeaway-ui/index.html

### 首页展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/%E9%A6%96%E9%A1%B5.jpg)

### SPRING信息展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/spring%E4%BF%A1%E6%81%AF%E9%A1%B5.jpg)

### JVM基础信息展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/jvm%E4%BF%A1%E6%81%AF%E9%A1%B5.jpg)


### 数据库信息展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/%E6%95%B0%E6%8D%AE%E5%BA%93%E9%A1%B5%E9%9D%A2.jpg)

### 接口文档信息页展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3%E4%BF%A1%E6%81%AF%E9%A1%B5.jpg)

### JSON转java实体信息页展示

![cmd-markdown-logo](https://raw.githubusercontent.com/tanghuibo/spring-takeaway-ui/master/screenshot/json%E8%BD%ACjava%E4%BF%A1%E6%81%AF%E9%A1%B5.jpg)