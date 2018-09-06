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




# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
