# SignalLoadingView
A signal rotation loading view(一个信号旋转loading view).


[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/SignalLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/SignalLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>

![gif1](https://github.com/samlss/SignalLoadingView/blob/master/screenshots/screenshot1.gif)



## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:SignalLoadingView:1.1'
}
```

布局中使用：
```
<com.iigo.library.SignalLoadingView
            android:id="@+id/slv_loading1"
            android:layout_width="100dp"
            android:layout_height="100dp"
            app:duration="1500"
            app:interpolator="LinearInterpolator"
            app:signal_color="#fbc02d" />
```

<br>

代码中使用：
```
  signalLoadingView.start(); //开始动画
  signalLoadingView.stop(); //结束动画
  
  signalLoadingView.setInterpolator(new LinearInterpolator());//设置动画插值器
  signalLoadingView.setSignalColor(Color.RED);//设置信号颜色
  signalLoadingView.setDuration(500); //设置动画执行时间
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| signal_color      | 信号颜色 |
| duration | 动画时间 |
| interpolator | 动画加速器 |

### 插值器值interpolator: <br>
* AccelerateDecelerateInterpolator
* AccelerateInterpolator
* DecelerateInterpolator
* BounceInterpolator
* CycleInterpolator
* LinearInterpolator
* AnticipateOvershootInterpolator
* AnticipateInterpolator
* OvershootInterpolator

<br>

如果不能满足你的需要，你可以下载源码自行修改。

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:SignalLoadingView:1.1'
}
```


in layout.xml：
```
<com.iigo.library.SignalLoadingView
            android:id="@+id/slv_loading1"
            android:layout_width="100dp"
            android:layout_height="100dp"
            app:duration="1500"
            app:interpolator="LinearInterpolator"
            app:signal_color="#fbc02d" />
```

<br>

in java code：
```
  signalLoadingView.start(); //start animation
  signalLoadingView.stop(); //stop animation
  
  signalLoadingView.setInterpolator(new LinearInterpolator());//set the animator interpolator
  signalLoadingView.setSignalColor(Color.RED);//set the signal color
  signalLoadingView.setDuration(500); //set the animator duration
```
<br>


Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| signal_color      | the signal color |
| duration | the animator duration |
| interpolator | the animator interpolator |

### interpolator: <br>
* AccelerateDecelerateInterpolator
* AccelerateInterpolator
* DecelerateInterpolator
* BounceInterpolator
* CycleInterpolator
* LinearInterpolator
* AnticipateOvershootInterpolator
* AnticipateInterpolator
* OvershootInterpolator

<br>

If you can not meet your needs, you can download the source code to modify it.

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/PeasLoadingView/blob/master/LICENSE)
