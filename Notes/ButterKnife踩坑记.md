[TOC]

# ButterKnife踩坑记

## 集成 ButterKnife 到工程

如果你的 project 是一个单 module 的结构（或者虽然是多 module 模式，但是只需要在 app module），那么集成很简单，网上文档也很丰富。

## app Module 中使用 ButterKnife

这个是最简单的。

在 app 的 build.gradle 文件中添加

```groovy
implementation com.jakewharton:butterknife:10.2.1
annotationProcessor com.jakewharton:butterknife-compiler:10.2.1
```

编译后即可在代码中使用

```java
@BindView(R.id.xxx) / @BindDrawable(R.drawable.xxx) / ...
```

寻找对应资源。

## library Module 中使用 ButterKnife

（1）在 Project 的 build.gradle 文件中添加 butterknife-gradle-plugin，添加后如下：

```groovy
dependencies {
        classpath 'com.android.tools.build:gradle:3.5.2'

        // ButterKnife插件声明
        classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
```

（2）然后在然后在对应 Module 的 build.gradle 中增加：

```groovy
apply plugin: 'com.android.library'
// butterknife-gradle-plugin的使用
apply plugin: 'com.jakewharton.butterknife'
```

同时添加：

```groovy
implementation com.jakewharton:butterknife:10.2.1
annotationProcessor com.jakewharton:butterknife-compiler:10.2.1
```

（3）完成后，需要重新 build 一下，即可在 Module 的代码中使用

```java
@BindView(R2.id.xxx) / @BindDrawable(R2.drawable.xxx) / ...
```

来寻找对应资源了。

## 模块化工程中的小技巧

### 初级版

将依赖写在Base Module 中进行传递依赖：

```groovy
api com.jakewharton:butterknife:10.2.1
```

将 compiler 放在各个 Module 的 build.gradle 中：

```groovy
annotationProcessor com.jakewharton:butterknife-compiler:10.2.1
```

### 中级版

将 gradle 依赖的库和版本放在 Project 的 gradle.properties 文件中统一管理：

```properties
# ButterKnife编译时绑定
JakeWharton_ButterKnife=com.jakewharton:butterknife:10.2.1
JakeWharton_ButterKnifeCompiler=com.jakewharton:butterknife-compiler:10.2.1
```

然后在基础 Module 中使用：

```java
// ButterKnife公共依赖
api JakeWharton_ButterKnife
```

在各个 Module 中使用 compiler：

```java
// Butter Knife注解处理编译器（依赖已在公共库中添加）
annotationProcessor JakeWharton_ButterKnifeCompiler
```

### 高级版（所谓的）

屹通框架模式中依赖库的管理方式和上边方式的结合。



个人项目中使用 **中级版** 即可，完全用不到所谓的高级版。因为所谓的高级版其实是基于已有的私有 Maven 库。



## ButterKnife 从入门到放弃

### 版本问题

AndroidStudio 版本、gradle 版本、build gradle版本、Butterknife 版本真叫人头疼——升级了 Android Studio 后建议升级 gradle 版本，可能需要升级 gradle，升级了 gradle 版本就可能导致 Butterknife 编译报错，再升级 Butterknife 版本，（如 10.2.1）可能你的工程要适配 androidx，....吐不完的槽点！

有些同学可能觉得我做技术本来就是要不断进步，拥抱变化，接受挑战的，这里我不否认。但是别忘了，我们使用 Butterknife 的初衷只是为了简化开发（注意，我不是在否认 Butterknife 的技术思想，其实底层原理我还没搞明白）。

#### 我的止步版本

```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-5.4.1-all.zip
```

```groovy
dependencies {
        classpath 'com.android.tools.build:gradle:3.5.3'

        // ButterKnife插件声明
        classpath 'com.jakewharton:butterknife-gradle-plugin:9.0.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
```

```properties
# ButterKnife版本
JakeWharton_ButterKnife=com.jakewharton:butterknife:9.0.0
JakeWharton_ButterKnifeCompiler=com.jakewharton:butterknife-compiler:9.0.0
```

### 便捷跳转问题

如果使用 findViewbyId(R.id.xxx)，点击 xxx，会跳转到对应资源，但是



跳到了 R2 文件中；

