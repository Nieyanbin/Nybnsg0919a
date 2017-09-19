package com.example.dell.nybnsg.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dell on 2017/9/9.
 */
public class Mysqldao {

    private final SQLiteDatabase db;

    public Mysqldao(Context context){
        //这行代码只是创建了帮助类的对象，并没有创建数据库
        MySql helper = new MySql(context);
        //当执行这行代码的时候，如果数据库存在直接就打开数据库，如果数据库不存在，创建并打开数据库

        db = helper.getWritableDatabase();
    }
    //添加
    public boolean add(String name,float money,String img,int num){

        /**
         * table :  表名
         * nullColumnHack : 声明表中某些字段值可以填也可以不填  然而并没有什么卵用   一般通用做法，给一个:null
         * values : ContentValues 类似我们的Map  map特点 ： key/value
         * key : 表中的列名  value : 指定列的值
         */
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("money", money);
        values.put("img", img);
        values.put("num", num);
        long result = db.insert("nyba",null, values);//insert into

        if(result != -1){
            return true;
        }else{
            return false;
        }

    }
    /**
     * 使用特殊方式修改一条数据记录
     * @param name 用户姓名
     * @param id   用户的id
     * @return  返回一个boolean的值，如果返回值 true 代表数据修改成功 否则 false 数据修改失败
     */
    public boolean update(String name,int id){

        //table : 表名
        //values : 修改的列值
        //whereClause :条件
        //whereArgs : 条件值

        ContentValues values = new ContentValues();
        values.put("name", name);
        int result = db.update("nyba", values, "id = ?", new String[]{String.valueOf(id)});

        if(result > 0){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 使用特殊的方式删除一条数据记录
     * @param id 删除用户id
     * @return  返回一个boolean的值，如果返回值 true 代表数据删除成功 否则 false 数据删除失败
     */
    public boolean delete(int id){
        //参数1：表名
        //参数2：条件
        //参数3：条件值
        int result = db.delete("nyba", "id = ?", new String[]{String.valueOf(id)});

        if(result > 0){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 使用特殊的方式查询所有的数据
     */
    public String findAll(){

        /**
         * distinct ： true 去除重复数据   false 不去除重复数据  默认的情况 false
         * table : 表名
         * columns : 查询的那些列   指定你需要数据库给你返回那些的信息 name,phone,
         * 查询所有的列信息 ： null 等价于 *
         * selection : 查询的条件
         * selectionArgs : 查询的条件值
         * groupBy : 分组查询
         * having : 子查询语句
         * orderBy : 排序    升序/降序
         * limit : 分页
         */
        Cursor cursor = db.query(false, "nyba", null, null, null, null, null, null, null);


        //创建一个容器
        StringBuffer sb = new StringBuffer();

        while(cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex("name"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String img = cursor.getString(cursor.getColumnIndex("img"));
            int num = cursor.getInt(cursor.getColumnIndex("num"));
           sb.append(name+money+img+num);
        }


        return sb.toString();

    }
}
