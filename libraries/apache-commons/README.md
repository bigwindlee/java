#### 要点
- Java Application Plugin: 创建可执行的 JVM 应用程序，轻松地在本地启动应用程序。
```
在build.gradle中添加：
plugins {
    id 'application'
}
application {
    mainClass = 'org.gradle.sample.Main'
}
```
- Enable JUnit5, 熟悉`package org.junit.jupiter.api.*`
- 熟悉`package org.apache.commons.lang3.StringUtils`对字符串功能的增强。
- Debugging when running tests
```
    // Edit Configurations... > Add New Configuration > Remote JVM Debug
    debugOptions {
        enabled = true
        port = 4455
        server = true
        suspend = true
    }
```    
