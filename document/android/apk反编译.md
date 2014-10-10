APK反编译
==========

### 反编译apk生成程序的源代码和图片、XML配置、语言资源等文件
[下载地址](http://code.google.com/p/android-apktool/downloads/list)
下载：apktool1.4.1.tar.bz2 和 apktool-install-windows-r04-brut1.tar.bz2（两个包都下载）

具体步骤：
将下载的两个包解压到同一个文件夹下，总共会有三个文件：aapt.exe，apktool.bat，apktool.jar

在命令行下定位到apktool.bat文件夹，输入以下命令：apktool.bat  d C:\*.apk C:\*文件夹
命令行解释：apktool   d   [apk文件 ]   [输出文件夹]

### 反编译Apk得到Java源代码
[dex2jar](https://code.google.com/p/dex2jar/downloads/list)

具体步骤：
首先将apk文件，将后缀改为zip，解压，得到其中的classes.dex，它就是java文件编译再通过dx工具打包而成的；
解压下载的dex2jar，将classes.dex复制到dex2jar.bat所在目录。在命令行下定位到dex2jar.bat所在目录

运行
dex2jar.bat    classes.dex
生成
classes.dex.dex2jar.jar  