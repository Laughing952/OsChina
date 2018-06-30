package com.laughing.android.eventbus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laughing.android.eventbus.R;
import com.laughing.android.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 这里创建了SecondActivity来发布消息
 * 作者：Laughing on 2017/8/15 14:25
 * 邮箱：719240226@qq.com
 */
public class SecondActivity extends AppCompatActivity {

    private Button bt_message;
    private TextView tv_message;
    private Button bt_subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }

    private void initListener() {
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                finish();
            }
        });

        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("欢迎关注刘望舒的博客"));
                finish();
            }
        });
    }

    private void initView() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText("SecondActivity");
        bt_message = (Button) findViewById(R.id.bt_message);
        bt_message.setText("发送事件");
        bt_subscription = (Button) findViewById(R.id.bt_subscription);
        bt_subscription.setText("发送粘性事件");
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
//    private Button bt_message;
//    private TextView tv_message;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//        initListener();
//
//    }
//
//    private void initView() {
//        tv_message = (TextView) findViewById(R.id.tv_message);
//        tv_message.setText("SecondActivity");
//        bt_message = (Button) findViewById(R.id.bt_message);
//        bt_message.setText("发送事件");
//    }
//
//    private void initListener() {
//        bt_message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post(new MessageEvent("欢迎关注刘望舒的博客"));
//                finish();
//            }
//        });
//    }
}
