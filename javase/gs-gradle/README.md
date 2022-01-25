### 手工打造一个使用gradle的java项目
#### Step 1: 创建文件夹
```bash
mkdir -p src/main/java/hello  
```
#### Step 2: 添加2个文件：
```
src/main/java/hello/HelloWorld.java  
src/main/java/hello/Greeter.java  
```
#### Step 3: 新建build.gradle
在项目根目录新建build.gradle并添加一行：
```
apply plugin: 'java'  
```
执行 gradle tasks  
执行 gradle build  

#### Step 4: 声明依赖
在build.gradle文件中添加：
```
dependencies {
    implementation "joda-time:joda-time:2.2"
    testImplementation "junit:junit:4.12"
}
```

#### Step 5: 使用 jar block 对生成的JAR包进行命名。
```
jar {
    archiveBaseName = 'lambda'
    archiveVersion =  '0.1.0'
}
```

#### Step 6: 理解 gradle wrapper （不用安装gradle也能构建项目）
执行：
```bash
gradle wrapper --gradle-version 7.3.3  
```
生成4个文件，其中2个gradlew脚本。  
```
gradlew
gradlew.bat
gradle/wrapper/gradle-wrapper.jar
gradle/wrapper/gradle-wrapper.properties
```

#### Step 7: 执行程序
在 build.gradle 文件中添加下面两行：
```
apply plugin: 'application'
mainClassName = 'hello.HelloWorld'
```
运行：gradlew run
