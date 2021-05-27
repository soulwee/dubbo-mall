# 开发笔记
## 开发流程
dubbo整合，添加提供者，消费者

### mybatis配置问题

如果映射xml文件放置java目录下那么这么配置pom.xml即可

`<directory>src/main/java</directory>`

如果是放在resources的mapper目录下，应该这样配

`<directory>src/main/resources</directory>`

且在yml文件中要指明文件路径

`mybatis:
  mapper-locations: classpath:mapper/*.xml`
  
- ctrl+F9 重新编译
- alt+shift 鼠标多个光标 