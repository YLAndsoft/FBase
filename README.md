### 鉴于BaseProject封装暴露出来的方法太多，经过优化，去掉了没必要的几个方法。现已整理完成。
* 1.去掉了Base类里面的几个没必要展示的方法
* 2.增加了网络请求封装的Base类
* 3.去掉了一部分工具类
<br>
Add it in your root build.gradle at the end of repositories:<br>

```java
Add it in your root build.gradle at the end of repositories:
allprojects {
  repositories {
    ...
    maven { url 'https://www.jitpack.io' }
  }
}

Step 2. Add the dependency
dependencies {
  implementation 'com.github.YLAndsoft:FBase:0.0.4'
}
```
<h4>混淆配置<h4/>

``` java
-keep class org.xutils.** { *; }
-keep class * extends java.lang.annotation.Annotation { *; }
#EventBus 3.0 混淆配置
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

#Glide混淆配置
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#GSON 混淆配置
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}

#百度统计混淆配置
-keep  class  com.baidu.mobstat.**  {  *; }
#permissiongen混淆配置
-keep  class  kr.co.namee.permissiongen.**  {  *; }
#alertview 混淆配置
-keep  class  com.bikoo.alertview.**  {  *; }
# base工程不做混淆
-keep class m.b.** {*;}
# 不混淆资源类
-keepclassmembers class **.R$* { *; }

异常：Android Error:Execution failed for task ':app:transformClassesAndResourcesWithProguardForRelease'
原因：Proguard 文件启用时报错，解决方式有两种，一种是如果你要禁用 proguard 文件，直接在项目的 build 文件中将 minifyEnabled 设置为 false，如果不想禁用 proguard 文件，那么可以将 
-ignorewarnings -keep class * { public private *; } 
添加到 Proguard 文件中，然后重新编译打包，这样就能解决打包报错的问题了！

```

### [详细使用见WIKI](https://github.com/YLAndsoft/FBase/wiki) 

#### [下载体验demo](https://project-1256156566.cos.ap-chengdu.myqcloud.com/demo.apk) 

```java 
 有疑问或者建议的同伴可以QQ好友或者邮箱留言我
 QQ:347933430
 邮箱：347933430@qq.com
 群：7458095
```
