package com.laughing.android.litepaldemo.LitePalBean;

import org.litepal.crud.DataSupport;

/**
 * 进行数据库操作必须继承DataSupport
 */
public class Book extends DataSupport {

    private int id;

    private String author;

    private double price;

    private int pages;

    private String name;

    private String press;

    private String des;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
