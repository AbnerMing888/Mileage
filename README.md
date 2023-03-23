# Mileage
一个自定义的平行四边形里程或者油量进度条

## 快速开始

1、在你的根项目下的build.gradle文件下，引入maven。

```groovy
allprojects {
    repositories {
        maven { url "https://gitee.com/AbnerAndroid/almighty/raw/master" }
    }
}
```
2、在你需要使用的Module中build.gradle文件下，引入依赖。

```groovy
dependencies {
    implementation 'com.vip:mprogress:1.0.1'
}
```
3、XML引入即可

```xml
    <com.vip.mprogress.MileageProgress
        android:id="@+id/mp_view"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:mp_default_progress="80"
        app:mp_warn_progress="50" />

```

4、属性介绍

| 属性 | 类型 | 概述 |
|  ----  |  ----  |  ----  |
| mp_background | color | 整体的背景颜色 |
| mp_angle_width | dimension | 平行四边形角度的宽（倾斜度） |
| mp_anamorphism_color | reference | 渐变颜色（传递颜色数组，定义array资源） |
| mp_max_progress | Int | 最大进度 |
| mp_default_progress | Int | 默认进度 |
| mp_warn_progress | Int | 告警进度（低于多少就改变进度颜色） |
| mp_warn_background | color | 告警颜色 |
| mp_is_dim_progress | boolean | 是否模糊颜色（进度前进头的颜色） |

5、方法介绍

| 方法 | 参数 | 概述 |
|  ----  |  ----  |  ----  |
| changeProgress | Int | 传递进度值 |


## 文章介绍

[一个超简单的渐变平行四边形进度条](https://juejin.cn/post/7213582724878909500)


## 欢迎关注作者

微信搜索【Android干货铺】，或扫描下面二维码关注，查阅更多技术文章！

<img src="image/abner.jpg" width="200px" />

## License

```
Copyright (C) AbnerMing, Mileage Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
