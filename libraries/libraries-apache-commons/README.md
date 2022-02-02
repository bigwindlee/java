#### 要点
- Java Application Plugin: 创建可执行的 JVM 应用程序，轻松地在本地启动应用程序。
在build.gradle中添加：
plugins {
    id 'application'
}
application {
    mainClass = 'org.gradle.sample.Main'
}