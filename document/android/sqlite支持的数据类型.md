android sqlite支持的数据类型 
=======================
[摘自](http://blog.csdn.net/qwjhappy/article/details/7355414)

Sqlite3支持的数据类型 ：NULL、INTEGER、REAL、TEXT、BLOB
----------------------------------------------
 但实际上，sqlite3也接受如下的数据类型：
    smallint 16 位元的整数。
    interger 32 位元的整数。
    decimal(p,s) p 精确值和 s 大小的十进位整数，精确值p是指全部有几个数(digits)大小值，s是指小数点後有几位数。如果没有特别指定，则系统会设为 p=5; s=0 。
    float  32位元的实数。
    double  64位元的实数。
    char(n)  n 长度的字串，n不能超过 254。
    varchar(n) 长度不固定且其最大长度为 n 的字串，n不能超过 4000。
    graphic(n) 和 char(n) 一样，不过其单位是两个字元 double-bytes， n不能超过127。这个形态是为了支援两个字元长度的字体，例如中文字。
    vargraphic(n) 可变长度且其最大长度为 n 的双字元字串，n不能超过 2000
    date  包含了 年份、月份、日期。
    time  包含了 小时、分钟、秒。
    timestamp 包含了 年、月、日、时、分、秒、千分之一秒。


SQLite包含了如下时间/日期函数：
---------------------
datetime().......................产生日期和时间
date()...........................产生日期
time()...........................产生时间
strftime().......................对以上三个函数产生的日期和时间进行格式化

datetime()的用法是：datetime(日期/时间,修正符,修正符...)
date()和time()的语法与datetime()相同。

在时间/日期函数里可以使用如下格式的字符串作为参数：
---------------------------------
YYYY-MM-DD
YYYY-MM-DD HH:MM
YYYY-MM-DD HH:MM:SS
YYYY-MM-DD HH:MM:SS.SSS
HH:MM
HH:MM:SS
HH:MM:SS.SSS
now
其中now是产生现在的时间。

举例（写这个笔记的时间是2006年10月17日晚8点到10点，测试环境：SQLite 2.8.17，WinXP，北京时间）：


例1.select datetime('now');
结果：2006-10-17 12:55:54

例2.select datetime('2006-10-17');
结果：2006-10-17 12:00:00

例3.select datetime('2006-10-17 00:20:00','+1 hour','-12 minute');
结果：2006-10-17 01:08:00

例4.select date('2006-10-17','+1 day','+1 year');
结果：2007-10-18

例5.select datetime('now','start of year');
结果：2006-01-01 00:00:00

例6.select datetime('now','start of month');
结果：2006-10-01 00:00:00

例7.select datetime('now','start of day');
结果：2006-10-17 00:00:00

例8.select datetime('now','+10 hour','start of day','+10 hour');
结果：2006-10-17 10:00:00

例9.select datetime('now','localtime');
结果：2006-10-17 21:21:47

例10.select datetime('now','+8 hour');
结果：2006-10-17 21:24:45


例3中的+1 hour和-12 minute表示可以在基本时间上（datetime函数的第一个参数）增加或减少一定时间。

例5中的start of year表示一年开始的时间。

从例8可以看出，尽管第2个参数加上了10个小时，但是却被第3个参数“start of day”把时间归零到00:00:00，随后的第4个参数在00:00:00
的基础上把时间增加了10个小时变成了10:00:00。

例9把格林威治时区转换成本地时区。

例10把格林威治时区转换成东八区。

strftime()函数可以把YYYY-MM-DD HH:MM:SS格式的日期字符串转换成其它形式的字符串。
strftime()的语法是strftime(格式, 日期/时间, 修正符, 修正符, ...)

它可以用以下的符号对日期和时间进行格式化：
---------------------------
%d 月份, 01-31
%f 小数形式的秒，SS.SSS
%H 小时, 00-23
%j 算出某一天是该年的第几天，001-366
%m 月份，00-12
%M 分钟, 00-59
%s 从1970年1月1日到现在的秒数
%S 秒, 00-59
%w 星期, 0-6 (0是星期天)
%W 算出某一天属于该年的第几周, 01-53
%Y 年, YYYY
%% 百分号

strftime()的用法举例如下：


例11.select strftime('%Y.%m.%d %H:%M:%S','now','localtime');
结果：2006.10.17 21:41:09


sqlite函数篇：
=============
算术函数
-------
abs(X) 返回给定数字表达式的绝对值。
max(X,Y[,...]) 返回表达式的最大值。
min(X,Y[,...]) 返回表达式的最小值。
random(*) 返回随机数。
round(X[,Y]) 返回数字表达式并四舍五入为指定的长度或精度。
字符处理函数
--------
length(X) 返回给定字符串表达式的字符个数。
lower(X) 将大写字符数据转换为小写字符数据后返回字符表达式。
upper(X) 返回将小写字符数据转换为大写的字符表达式。
substr(X,Y,Z) 返回表达式的一部分。
randstr()  
quote(A)  
like(A,B) 确定给定的字符串是否与指定的模式匹配。
glob(A,B)  
条件判断函数
--------
coalesce(X,Y[,...])  
ifnull(X,Y)  
nullif(X,Y)  
集合函数
----------
avg(X) 返回组中值的平均值。
count(X) 返回组中项目的数量。
max(X) 返回组中值的最大值。
min(X) 返回组中值的最小值。
sum(X) 返回表达式中所有值的和。

其他函数
----------
typeof(X) 返回数据的类型。
last_insert_rowid() 返回最后插入的数据的ID。
sqlite_version(*) 返回SQLite的版本。
change_count() 返回受上一语句影响的行数。
last_statement_change_count()
