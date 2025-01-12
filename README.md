### springthin

#### 启动命令

```shell
java -Dloader.path=lib -jar  springthin-0.0.1-SNAPSHOT.jar
```

- [x] 方案1，CI/CD时，脚本删除指定jar包

- [x] 方案2，在 `maven-dependency-plugin` 插件`configuration`中添加排除配置：
    ```xml
        <excludeGroupIds>com.zdx</excludeGroupIds>
        <!--<excludeArtifactIds>common</excludeArtifactIds>-->
    ```
    
- [ ] 方案3，使用 `spring-boot-thin-layout` 插件，会在当前目录下下载`lib/repository`文件夹, 需要和 `maven-dependency-plugin` 插件配合
```shell
java -Dthin.root=. -jar app/target/*.jar
```

- [x] 方案4，`maven-jar-plugin` 启动可以不指定参数，但无法多模块打包

#### 扩展

```shell
java -Djava.ext.dirs=lib -jar  springthin-0.0.1-SNAPSHOT.jar
```

_不推荐使用此参数_:

_-Djava.ext.dirs 和 -Dloader.path 都是 Java 应用程序运行时设置的系统属性，用于指定类加载器搜索类的路径。_

_-Djava.ext.dirs 指定了一个或多个目录路径，这些路径包含了扩展类库（Extension Libraries）的实现。扩展类库包含了 JDK
标准库以外的类，通常由第三方供应商提供，并被加载到引导类加载器（Bootstrap Classloader）中。Java
运行时会优先在这些目录中查找类，如果在这些目录中找不到类，则会在系统类路径中查找。_

_-Dloader.path 用于指定 Spring Boot 应用程序加载类的路径。Spring Boot
使用自定义的类加载器来加载应用程序的类和依赖项，而不是使用传统的父子委派模型。-Dloader.path
属性允许您指定应用程序类加载器在哪些位置搜索类和资源。_

_这两个系统属性的区别在于，-Djava.ext.dirs 用于指定 JDK 扩展库的位置，而 -Dloader.path
用于指定应用程序加载类和资源的位置。如果您需要使用第三方库或自定义的扩展库，则可以使用 -Djava.ext.dirs 属性。而如果您需要使用
Spring Boot 应用程序加载器来加载应用程序和依赖项，则需要使用 -Dloader.path。_

_一般来说，-Djava.ext.dirs 已经被弃用，不再是一个推荐的选项。相反，建议使用 Maven 等构建工具来管理依赖项和库的路径，或者使用类似于
Docker 容器的隔离机制来隔离应用程序和依赖项的运行环境。-Dloader.path 是针对 Spring Boot 应用程序而设计的，如果您使用
Spring Boot，则应该使用该属性来管理应用程序的依赖项和类路径。
