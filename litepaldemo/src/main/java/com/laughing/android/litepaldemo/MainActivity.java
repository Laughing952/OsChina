package com.laughing.android.litepaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.laughing.android.litepaldemo.LitePalBean.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreate_database;
    private Button mAdd_data;
    private Button mUpdate_data;
    private Button mDelete_data;
    private Button mQuery_data;
    private FrameLayout fl_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        fl_content.setOnClickListener(this);
        mCreate_database.setOnClickListener(this);
        mAdd_data.setOnClickListener(this);
        mUpdate_data.setOnClickListener(this);
        mDelete_data.setOnClickListener(this);
        mQuery_data.setOnClickListener(this);

    }

    private void initView() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        mCreate_database = (Button) findViewById(R.id.create_database);
        mAdd_data = (Button) findViewById(R.id.add_data);
        mUpdate_data = (Button) findViewById(R.id.update_data);
        mDelete_data = (Button) findViewById(R.id.delete_data);
        mQuery_data = (Button) findViewById(R.id.query_data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database:
                Connector.getDatabase();//创建数据库
                break;
            case R.id.add_data:
                //添加一条数据
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.setDes("Des");
                book.save();
                break;
            case R.id.update_data:
                //更新书的价格
                Book book1 = new Book();
                book1.setPrice(10.00);
//                book1.setToDefault("pages");//用来把字段值改为数据库初始值
                book1.setPress("电子工业出版社");
                book1.updateAll("name=? and author=?", "The Da Vinci Code", "Dan Brown");
                break;
            case R.id.delete_data:
                //删除价格低于15元的书
                DataSupport.deleteAll(Book.class, "price<?", "15");
                break;
            case R.id.query_data:
//                List<Book> books = DataSupport.findAll(Book.class);
//                Book books1 = DataSupport.findFirst(Book.class);//第一条
//                Book books2 = DataSupport.findLast(Book.class);//最后一条
//                //按字段查找
//                List<Book> books = DataSupport.select("name","author").find(Book.class);
                //查询条件书大于400页
//                List<Book> books = DataSupport.where("pages>?","400").find(Book.class);
                //排序（降序排列）
//                List<Book> books = DataSupport.order("price desc").find(Book.class);
                //分页
//                List<Book> books = DataSupport.limit(3).find(Book.class);
                //从第2条数据开始查找3条数据，即（数据库中的第2,3,4）3条数据
//                List<Book> books = DataSupport.limit(3).offset(1).find(Book.class);
                List<Book> books = DataSupport.select("name", "author")
                        .where("pages>?", "400")
                        .order("price desc")
                        .limit(3)
                        .offset(2)
                        .find(Book.class);
                //支持原生sql查询
//                Cursor cursor = DataSupport.findBySQL("select * from book where pages>? and price <?", "400", "20");


                for (Book boo : books) {
                    Log.e("MainActivity", "book name ----- " + boo.getName());
                    Log.e("MainActivity", "book author ----- " + boo.getAuthor());
//                    Log.e("MainActivity", "book pages ----- " + boo.getPages());
//                    Log.e("MainActivity", "book price ----- " + boo.getPrice());
//                    Log.e("MainActivity", "book press -----" + boo.getPress());
//                    Log.e("MnActivity", "book des -----" + boo.getDes());

                }
                break;

            default:

                break;
        }
    }
}
