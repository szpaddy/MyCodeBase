ActionBar风格自定义
===============
[摘自](http://blog.csdn.net/klpchan/article/details/10027841)
按照项目中的需求和网上论坛常见的内容，自定义标题栏风格包括以下几项：
---------------------------------------------------------------------------------
      Q1.1 修改标题栏字体大小、颜色、样式
      Q1.2 自定义ActionBar背景颜色及AB高度
      Q1.3 修改ActionBar上菜单项字体样式
      Q2 在ActonBar上显示与Launch界面不同的Title的icon
      Q3 在含有菜单键的手机上强制显示溢出菜单
      Q4 防止应用启动时ActionBar上默认图标和标题的闪烁
      本文将以上问题打包解答，问题虽多，但是解决思路类路类似。
      
### Q1.x 自定义标题栏选项内容基本风格
从API14也就是ICS之后，Theme.DeviceDefault作为新的主题，使用了新的文件，如上图所示。本文中以API14为最低版本，默认使用的主题是android/framework/base/core/res/res/values/themes_device_defaults.xml中的Theme.DeviceDefault。  
     <style name="Theme.DeviceDefault" parent="Theme.Holo" >
     Theme.DeviceDefault的父类是Theme.Holo，这里体现了对以前版本的继承关系，Theme.DeviceDefaul中的大部分属性值，都是在Theme.Holo属性值的基础上进行再定义。Theme.DeviceDefault主题定义了对于所有组件的默认定义方式，其中包括Actionbar的属性集。
     
<!-- Action bar styles -->  
<item name="actionDropDownStyle">@android:style/Widget.DeviceDefault.Spinner.DropDown.ActionBar</item>  
<item name="actionButtonStyle">@android:style/Widget.DeviceDefault.ActionButton</item>  
<item name="actionOverflowButtonStyle">@android:style/Widget.DeviceDefault.ActionButton.Overflow</item>  
<item name="actionBarTabStyle">@style/Widget.DeviceDefault.ActionBar.TabView</item>  
<item name="actionBarTabBarStyle">@style/Widget.DeviceDefault.ActionBar.TabBar</item>  
<item name="actionBarTabTextStyle">@style/Widget.DeviceDefault.ActionBar.TabText</item>  
<item name="actionModeStyle">@style/Widget.DeviceDefault.ActionMode</item>  
<item name="actionModeCloseButtonStyle">@style/Widget.DeviceDefault.ActionButton.CloseMode</item>  
<item name="actionBarStyle">@android:style/Widget.DeviceDefault.ActionBar</item>  
<item name="actionModePopupWindowStyle">@android:style/Widget.DeviceDefault.PopupWindow.ActionMode</item>

      在这些属性集中，有个item比较重要，<item name="actionBarStyle">@android:style/Widget.DeviceDefault.ActionBar</item>，属性名是actionBarStyle，属性值是系统默认风格Widget.DeviceDefault.ActionBar，这个风格是个继承自Widget.Holo.Actionbar的子风格，定义在Styles_device_defaults.xml中，源码如下：      
        <stylename="Widget.DeviceDefault.ActionBar"parent="Widget.Holo.ActionBar"></style>
<style name="Widget.Holo.ActionBar" parent="Widget.ActionBar">  
    <item name="android:titleTextStyle">@android:style/TextAppearance.Holo.Widget.ActionBar.Title</item>  
    <item name="android:subtitleTextStyle">@android:style/TextAppearance.Holo.Widget.ActionBar.Subtitle</item>  
    <item name="android:background">@android:drawable/ab_transparent_dark_holo</item>  
    <item name="android:backgroundStacked">@android:drawable/ab_stacked_transparent_dark_holo</item>  
    <item name="android:backgroundSplit">@android:drawable/ab_bottom_transparent_dark_holo</item>  
    <item name="android:divider">?android:attr/dividerVertical</item>  
    <item name="android:progressBarStyle">@android:style/Widget.Holo.ProgressBar.Horizontal</item>  
    <item name="android:indeterminateProgressStyle">@android:style/Widget.Holo.ProgressBar</item>  
    <item name="android:progressBarPadding">32dip</item>  
    <item name="android:itemPadding">8dip</item>  
</style> 

我们需要在App主题中使用自己定义的actionBarStyle，在actionBarStyle中使用自己定义的titleTextStyle，后者需继承自TextAppearance.Holo.Widget.ActionBar.Title并且修改字体大小。
<style name="MyTheme" parent="@android:style/Theme.DeviceDefault"></style>   //定义自己的APP主题 继承自Theme.DeviceDefault  
    <style name="MyTheme.WithActionBar" parent="@style/MyTheme">                              
        <item name="android:actionBarStyle">@style/MyActionBarStyle</item>      //在APP主题中使用自定义的actionBarStyle  
    </style>  
    <style name="MyActionBarStyle" parent="android:style/Widget.DeviceDefault.ActionBar"> //自定义的actionBarStyle，需继承自Widget.DeviceDefault.ActionBar  
        <item name="android:titleTextStyle">@style/MyTitleTextStyle</item>   //在MyActionBarStyle中使用自定义的titleTextStyle属性  
    </style>  
    <style name="MyTitleTextStyle" parent="@android:style/TextAppearance.Holo.Widget.ActionBar.Title"> //自定义titleTextStyle属性，父类同源码  
        <item name="android:textSize">30sp</item>                                                                                         
        <item name="android:textColor">#FFFF00</item>       //自定义title大小及颜色  
    </style>  

修改过程如源码注释，在整个自定义过程中需主要：
==================================
### a  我们需要采用和源码同样的层次结构来构建自定义风格和属性，如actionBarStyle是Theme.DeviceDefault下的一个属性，那么MyActionBarStyle也就应该是MyTheme下的一个属性，其它同理。
### b  每一个属性或者风格在定义时，需要继承自和源码一样的父类，如MyActionBarStyle父类需要和actionBarStyle的父类一样，继承自Widget.DeviceDefault.ActionBar，我们可以改变的，只是该父类中的item属性值。
### c  对于不同层级的空间属性和风格来说，ThemeDeviceDefault类型的多是以Holo类型为父类       
      
   按照上面的分析思路，照猫画虎，可以解决前三个问题 : 修改标题栏字体大小、颜色、样式；自定义ActionBar背景颜色及AB高度 ；修改ActionBar上菜单项字体样式；
   
   Q4 防止应用启动时ActionBar上默认图标和标题的闪烁
   ==================================
android:actionBarStyle
顶部标题栏那一带的属性设置，style类型引用。这个属性很重要，如果想要把标题栏去掉换成customView或者Tab类型，你会发现Activity启动时先显示标题栏再执行你代码里的替换功能，那么必须在theme属性中指定不显示标题与logo。

### 最根本原因：默认图标始终会在屏幕内容完全加载完成前出现
### 如果调用API禁止显示图标，那么就第二屏的图标也会消失（不可行）
### 解决方案
<style name="MyActionBarStyle" parent="android:style/Widget.DeviceDefault.ActionBar">  
    <item name="android:displayOptions">showCustom</item>    //添加该行，表示默认显示的是CustomView。  
</style>

### 让ActionBar浮在布局显示的上面，腾出空间（在setContentView之前调用）：
    requestWindowFeature(Window.FEATURE_ACTION_BAR);  
    requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);  