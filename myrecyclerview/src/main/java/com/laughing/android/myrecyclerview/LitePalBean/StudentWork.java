package com.laughing.android.myrecyclerview.LitePalBean;

import org.litepal.crud.DataSupport;
import java.io.Serializable;

/**
 * 作者：Laughing on 2017/9/2 16:06
 * 邮箱：719240226@qq.com
 * extends DataSupport
 * compile 'org.litepal.android:core:1.5.0'//引入litepal
 */

public class StudentWork extends DataSupport implements Serializable{
    private int id;     //id（自增长）
    private String name;    //学生姓名
    private String over;      //0:未完完，1：表示完成


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }
}
