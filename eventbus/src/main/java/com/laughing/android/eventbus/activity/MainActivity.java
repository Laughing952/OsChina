package com.laughing.android.eventbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laughing.android.eventbus.R;
import com.laughing.android.eventbus.Utils.ToastUtils;
import com.laughing.android.eventbus.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_message;
    private Button mBt_subscription;
    private Button mBt_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initData() {


    }

    private void initListener() {
        mBt_subscription.setOnClickListener(this);
        mBt_message.setOnClickListener(this);

    }

    private void initView() {
        tv_message = (TextView) findViewById(R.id.tv_message);
        tv_message.setText("MainActivity");
        mBt_subscription = (Button) findViewById(R.id.bt_subscription);
        mBt_subscription.setText("订阅事件");
        mBt_message = (Button) findViewById(R.id.bt_message);
        mBt_message.setText("跳转到SecondActivity");
        //注册事件
//        EventBus.getDefault().register(this);//非粘性事件注册
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_message:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;

            case R.id.bt_subscription:
                // 注册事件
                if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
                    EventBus.getDefault().register(MainActivity.this);
                } else {
                    ToastUtils.showToastPlus(MainActivity.this, "请勿重复注册事件");
                }
                break;

            default:

                break;
        }
    }

    /**
     * 这里我们的ThreadMode设置为MAIN，事件的处理会在UI线程中执行，用TextView来展示收到的事件消息
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        tv_message.setText(messageEvent.getMessage());
    }

    /**
     * 在MainActivity中新写一个方法用来处理粘性事件
     *
     * @param messageEvent
     */
    @Subscribe(sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent) {
        tv_message.setText(messageEvent.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }


}
