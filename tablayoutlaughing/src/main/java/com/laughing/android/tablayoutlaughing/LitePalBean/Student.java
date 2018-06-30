package com.laughing.android.tablayoutlaughing.LitePalBean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * 作者：Laughing on 2017/9/2 16:06
 * 邮箱：719240226@qq.com
 * extends DataSupport
 * compile 'org.litepal.android:core:1.5.0'//引入litepal
 */

public class Student extends DataSupport implements Serializable{
    private int id;     //id（自增长）
    private String name;    //学生姓名
    private String grade;      //学生年级：0：代表一年级，1：代表二年级......5：代表六年级
    private String phone1;    //家长电话1
    private String phone2;    //家长电话2
    private String type;    //托管类型：0 全托     1：午托    2：晚托
    private String sex;    //性别 0：女     1：男     2：待确定
    private String more;    //备注信息

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
