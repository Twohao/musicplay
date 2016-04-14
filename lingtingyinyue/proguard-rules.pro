# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*
-keepattributes Signature

#-dontpreverify #不校验
#-repackageclasses ''
#-allowaccessmodification
#优化混淆后的代码
#-optimizations !code/simplification/arithmetic
#-keepattributes *Annotation*

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#不混淆UserInfo类
#-keep class com.example.yangjw.dabaodemo.UserInfo {*;}
#不混淆指定目录下的所有类
-keep class com.guocheng.lingtingyinyue.bean.** {*;}
-keep class android.support.v4.**{*;}
-keep class android.support.**{*;}
-keep class com.jakewharton.**{*;}
-keep class okhttp3**{*;}
-keep class com.google.gson**{*;}
-keep class butterknife**{*;}
-keep class com.squareup.picasso**{*;}
-keep class com.bigkoo.convenientbanner**{*;}
-keep class fm.jiecao**{*;}
-keep class com.handmark.pulltorefresh.library**{*;}


-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
    public void set*(...);
    public void get*(...);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}



