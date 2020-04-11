# ButterKnife踩坑记
## 集成 ButterKnife 到工程

集成很简单，网上文档也很丰富。

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

将依赖写在基础 Module 中进行传递依赖：

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

### 高级版

屹通框架模式中依赖库的管理方式和上边方式的结合。
