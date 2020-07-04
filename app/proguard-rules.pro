# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 实体类防混淆
-keepclassmembers class * extends com.wangj.core.entity.BaseVO { *;}


# ------Alibaba ARouter防混淆------------------------------------------------------
-keep public class com.alibaba.android.arouter.routes.*{*;}
-keep public class com.alibaba.android.arouter.facade.*{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# If you use the byType method to obtain Service, add the following rules to protect the interface:
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口（此为上一行英文翻译）
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# If single-type injection is used, that is, no interface is defined to implement IProvider, the following rules need to be added to protect the implementation
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现（此为上一行英文翻译）
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider


# -------ButterKnife防混淆----------------------------------------------------------
-keep class butterknife.* { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# -------EventBus防混淆--------------------------------------------------------------
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}