ANDROID开发之SQLite详解
====================
[摘自](http://www.cnblogs.com/Excellent/archive/2011/11/19/2254888.html)

SQLite具备下列特点：
-----------------------
1.轻量级
使用 SQLite 只需要带一个动态库，就可以享受它的全部功能，而且那个动态库的尺寸想当小。
2.独立性
SQLite 数据库的核心引擎不需要依赖第三方软件，也不需要所谓的“安装”。
3.隔离性
SQLite 数据库中所有的信息（比如表、视图、触发器等）都包含在一个文件夹内，方便管理和维护。
4.跨平台
SQLite 目前支持大部分操作系统，不至电脑操作系统更在众多的手机系统也是能够运行，比如：Android。
5.多语言接口
SQLite 数据库支持多语言编程接口。
6.安全性
SQLite 数据库通过数据库级上的独占性和共享锁来实现独立事务处理。这意味着多个进程可以在同一时间从同一数据库读取数据，但只能有一个可以写入数据。


Android中的SQLite使用
----------------------------------
### 创建数据库类
public class DatabaseHelper extends SQLiteOpenHelper {
 
    private static final String DB_NAME = "mydata.db"; //数据库名称
    private static final int version = 1; //数据库版本
     
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(username varchar(20) not null , password varchar(60) not null );";         
        db.execSQL(sql);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
 
    }
 
}
		通过调用了SQLiteOpenHelper类的getReadableDatabase()方法来实现创建一个数据库
		


数据的添加
---------------------
### 1.使用insert方法	
ContentValues cv = new ContentValues();//实例化一个ContentValues用来装载待插入的数据cv.put("username","Jack Johnson");//添加用户名
cv.put("password","iLovePopMusic"); //添加密码
db.insert("user",null,cv);//执行插入操作

### 2.使用execSQL方式来实现
String sql = "insert into user(username,password) values ('Jack Johnson','iLovePopMuisc');//插入操作的SQL语句
db.execSQL(sql);//执行SQL语句

数据的删除
-----------------------------
同样有2种方式可以实现
### 1.使用delete方法	
String whereClause = "username=?";//删除的条件
String[] whereArgs = {"Jack Johnson"};//删除的条件参数
db.delete("user",whereClause,whereArgs);//执行删除

### 2. 使用execSQL方式的实现
String sql = "delete from user where username='Jack Johnson'";//删除操作的SQL语句
db.execSQL(sql);//执行删除操作

数据修改
-----------
同上，仍是2种方式
### 1.使用update方法		
ContentValues cv = new ContentValues();//实例化ContentValues
cv.put("password","iHatePopMusic");//添加要更改的字段及内容
String whereClause = "username=?";//修改条件
String[] whereArgs = {"Jack Johnson"};//修改条件的参数
db.update("user",cv,whereClause,whereArgs);//执行修改

### 2.使用execSQL方式的实现
String sql = "update [user] set password = 'iHatePopMusic' where username='Jack Johnson'";//修改的SQL语句
db.execSQL(sql);//执行修改

数据查询
------------------------
### 1.通过query实现查询的
public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
各参数说明：
    table：表名称
    colums：列名称数组
    selection：条件子句，相当于where
    selectionArgs：条件语句的参数数组
    groupBy：分组
    having：分组条件
    orderBy：排序类
    limit：分页查询的限制
    Cursor：返回值，相当于结果集ResultSet

实现代码	
Cursor c = db.query("user",null,null,null,null,null,null);//查询并获得游标
if(c.moveToFirst()){//判断游标是否为空
    for(int i=0;i<c.getCount();i++){
        c.move(i);//移动到指定记录
        String username = c.getString(c.getColumnIndex("username");
        String password = c.getString(c.getColumnIndex("password"));
    }
}

### 2. 通过rawQuery实现的带参数查询	
Cursor c = db.rawQuery("select * from user where username=?",new Stirng[]{"Jack Johnson"});
if(cursor.moveToFirst()) {
    String password = c.getString(c.getColumnIndex("password"));
}
