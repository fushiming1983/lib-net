#### **代码重构前期规划**
---
**前言**


```
因为重构工作时间可能比较长，相当于重新开发。所以现版本继续进行版本的迭代开发工作，以维持线上产品的更新。
重构会将在新的工程下面进行，预计每周进行一个功能模块的迭代开发。迭代开发需要产品，UI，测试配合同步进行。
```


**Android产品的重构前期架构预研**
 - 架构本着对程序的模块化，可扩展性，易维护的思想进行开发。尽可能达到高内聚，低耦合。
 
 - 产品的架构将会抽象出三个层次的概念： ````数据层```` ```公共模块层``` ```UI展示层```
   - ```数据层```   主要是包含2各方面： 网络数据到本地对象的实例化工作, 本地对象的落地缓存和读取
   - ```公共模块层```   主要包含有： 视频播放， 图片加载， 日志统计， 各平台分享 ...
   - ```UI展示层```   将采用Google提供的Data Binding(数据绑定库)来进行UI绘制，Data binding 符合MVVM架构模式
   

**编程规范**

 为了保证代码的可读性，统一编码规范是非常有必要的
 - 除了注释，代码中不出现中文
 - 每个类写上必要的注释，类的说明，日期，作者，联系方式
 - 方法和变量，加上必要的注释说明，方便以后维护
 
 **命名**
  - 大驼峰命名(UpperCamelCase)：每个单词的第一个字母都大写。
  - 小驼峰命名(lowerCamelCase)：除第一个单词以外，每一个单词的第一个字母大写
  
**Java类命名**
 - 大驼峰命名 UserListAdapter
 - 公共的工具类建议以Utils、Manager为后缀，如LogUtils
 - 除常见的缩写单词以外，不使用缩写，缩写的单词每个字母都大写 
 
**变量命名**
 - 成员变量命名
   - 小驼峰命名
   - 不推荐使用谷歌的前面加m的编码风格
 - 常量命名
   - 单词每个字母均大写
   - 单词之间下划线连接
 - 控件变量命名
   - 小驼峰命名
   - 建议使用 控件缩写+逻辑名称 格式，例如 `tvTitle`、`etUserName`
   - 对应的控件的id的命名控件  `缩写_逻辑名称`，单词均小写，用下划线连接，例如：`tv_title` `et_user_name`
 - 布局文件命名
   - `activity` `fragment`布局文件名以对应的类别名称为前缀，逻辑名称放在其后，以下划线连接，例如`activity_home` `fragment_chat_list`，方便查找
   - `ListView`、`GridView`的item布局文件建议以`list_item` `gird_item`为前缀，加上对应的逻辑名称，例如
`list_item_post` `grid_item_photo`
   - `Dialog`的布局文件以dialog为前缀，逻辑名称放在其后，下划线连接，例如`dialog_warnning`
   - 包含项布局命名以include开头，在加上对应的逻辑名称，例如`include_foot`
 - 资源命名
   - 图标资源以ic为前缀，例如`ic_chat`，指聊天图标
   - 背景图片以bg为前缀，例如`bg_login`，指的是登录页的背景图
   - 按钮图片以btn为前缀，例如`btn_login`，指的是登录按钮的图片，不过这只有一种状态，需要加上状态的可以在后面添加，例如`btn_login_pressed` 表示登录按钮按下的图片
   - 当使用`shape`和`selector`文件为背景或者按钮时，命名参照以上说明
 

**包管理**

```
- base:存放基础类的包，里面的类以Base为前缀，例如BaseActivity
- activity:存放activity的包，每个activity命名以Activity结尾，例如MainActivity
- fragment:存放fragment的包，每个fragment命名以Fragment结尾，例如ChatFragment
- receiver:存放receiver的包
- service:存放service的包
- adapter:存放adapter的包，每个adapter命名以Adapter结尾，例如EventItemAdapter
- common:存放一些公共常量，例如后端接口、SharedPreferenceKey、IntentExtra等
- utils:存放工具类的包，比如常见的工具类：LogUtils、DateUtils
- entity:存放实体类的包
- widget:存放自定义View的包

```

以上是一些常见的包，但不局限于此，视项目的具体情况而定


**开源框架**

```
- 网络: Retrofit + OkHttp+ RxJava
- Json数据解析: Fastjson、Gson
- 事件总线: EventBus
- 图片记载: Glide
- 数据库: GreenDao
- 日志输出: Logger、LogUtils
- Google提供的兼容Support全家桶
- 性能优化: LeakCanary 让内存泄露无所遁形

```


**原工程用到的库 (24个)**

```
1.具体作用待查:       alipay_plugin.jar
2.友盟数据统计:       Analytics_Android_SDK_2.1.jar
3.友盟数据统计:       umeng_sdk_201309121504_fb_analytics.jar
4.二维码的生成与解析:  barecode-core.jar
5.处理常用的编码方法的工具类包(DES、SHA1、MD5、Base64)： commons-codec-1.7.jar
6.xml解析：           dom4j-1.6.1.jar
7.http链接：          httpmime-4.1.2.jar
8.具体作用待查：       iresource.jar
9.微信：              libammsdk.jar
10.具体作用待查：      MMAndroid_v1.2.4.jar
11.个推消息推送:       GetuiSDK2.7.3.0.jar
12.科大讯飞：          Msc.jar
13.具体作用待查：      mzmonitorandroidsdk.jar
14.具体作用待查:       SN_Statistic_E1.1.8.jar
15.新浪微博：          weibo.sdk.android.sso.jar
16.DAC报文发送:            logclient.jar
17.防盗链(pptv):            ppbox_jni.jar 
18.具体作用待查(pptv):     streamingsdk_jni.jar 
19.具体作用待查(pptv):     MeetSDK.jar
20.具体作用待查(pptv):     APISDK.jar
21.具体作用待查(pptv):     breakpad_util_jni.jar
22.具体作用待查(pptv):     MeetSDK.jar
23.具体作用待查(pptv):     MeetSDK.jar
24.so库：                  armeabi.jar
                        libAPISDK.so
                        libdlna.so
                        libmeet.so
                        libplayer_neon.so
                        libppbox_jni-armandroid-r4-gcc44-mt-1.1.0.so
                        libppmediaextractor-jni.so
                        libppplayer_a14.so
                        libpushservant-jni.so
                        libsample.so
                        libsdk.so
                        libstreamingsdk_jni-armandroid-r4-gcc44-mt-1.1.0.so
                        libsubtitle-jni.so
                           
```



**Android Studio 常用插件**

名称 | 用途
---|---
android selector | 更具资源文件生成selector
ADB Idea         | 快捷卸载应用
...              | ...




图片