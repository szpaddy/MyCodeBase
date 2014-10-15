res/color和res/drawable两个文件中的内容详解
===================================
[摘自](http://blog.csdn.net/jiangwei0910410003/article/details/16985955)

color文件夹
------------
在这个文件夹下放的是color_selector.xml等文件，主要是用于背景色的selector,比如TextView中的textColor属性，点击改变TextView中的字体颜色，在这个文件中的文件color_selector.xml中定义如下:
		<?xml version="1.0" encoding="utf-8"?>
		<selector xmlns:android="http://schemas.android.com/apk/res/android">
		    <item 
		        android:state_selected="true" 
		        android:color="@color/pressed" />
		    <item 
		        android:state_pressed="true" 
		        android:color="@color/pressed" />
		</selector>

主要的属性是android:color引用不同的色值,而这些色值是在values/color.xml文件中定义的,比如:
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="not_pressed">#000000</color>
    <color name="pressed">#ffffff</color>
</resources>

### 在color_selector.xml中没有android:drawable属性
我不知道Android中是根据什么判断什么时候有android:drawable属性，什么时候有android:color属性的


res/drawable文件夹中的文件
-----------------------
这个文件夹中的文件drawable_selector.xml，主要是用于背景图的使用，Button中的android:background，点击改变背景，drawable_selecor.xml文件的定义:
		<?xml version="1.0" encoding="UTF-8"?>
		<selector xmlns:android="http://schemas.android.com/apk/res/android">
		    <item 
		        android:state_pressed="true" 
		        android:drawable="@drawable/info_collect_btn_pressed_bg" />
			<item 
			    android:drawable="@drawable/info_collect_btn_normal_bg" />
		</selector>

这里的info_collect_btn_normal_bg和info_collect_btn_pressed_bg是图片资源，放在drawableXX文件夹下的。
同时也发现了在drawable_selector.xml中没有android:color属性，结合上面的color_selector.xml中没有android:drawable属性，
### 知道了Android应该是根据文件夹来判断的，在color文件夹下的文件有android:color属性,在drawable文件夹下的文件有android:drawable属性

### 同时还要注意的是:像textColor和background属性引用到的文件不能乱引用，
比如textColor引用了drawable_selector.xml，background引用了color_selector.xml会报错的。所以要注意使用。

### 但是现在有个问题：假设Button现在想点击变成绿色，不点击变成红色，这时候怎么办?
首先肯定要用background的属性-->那肯定就要用drawable_selector.xml文件引用，
所以在drawable_selector_.xml中将android:drawable="@drawable/info_collect_btn_normal_bg"改成android:drawable="@color/not_pressed"即可，
### 这里可能有个误解就是android:drawable只能引用drawable资源，但是事实证明这个误解是多余的！原理可以参考attr详解中。