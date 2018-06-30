package com.laughing.android.bluetoothlamp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 蓝牙灯泡
 * 作者：Laughing on 2017/7/24 16:49
 * 邮箱：719240226@qq.com
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mLv;
    private BluetoothAdapter mDefaultAdapter;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();
    private OutputStream mOutputStream;
    private DeviceAdapter mAdapter;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 扫描到蓝牙设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDevices.add(device);
//                System.out.print(device.getName());
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                ToastUtil.showToastPlus(getApplicationContext(), "开始扫描");

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                ToastUtil.showToastPlus(getApplicationContext(), "停止扫描");
            }

        }
    };
    private TextView mTv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTv_content = (TextView) findViewById(R.id.tv_content);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button1 = (Button) findViewById(R.id.button1);
        mLv = (ListView) findViewById(R.id.lv);
        mLv.setOverScrollMode(ListView.OVER_SCROLL_NEVER);//永远不显示listView滑动蓝色阴影
        mAdapter = new DeviceAdapter(getApplicationContext(), mDevices);
        mLv.setAdapter(mAdapter);
        mLv.setOnItemClickListener(this);
        // 注册广播接收者, 当扫描到蓝牙设备的时候, 系统会发送广播
        mDefaultAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);//开始扫描
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);//结束扫描
        registerReceiver(mBroadcastReceiver, filter);

    }

    public void clickBtn(View v) {
        switch (v.getId()) {
            case R.id.button1:
                // 蓝牙是否可用
                if (!mDefaultAdapter.isEnabled()) {
                    mDefaultAdapter.enable();// 打开蓝牙
                }
                break;
            case R.id.button2:
                // 关闭蓝牙
                if (mDefaultAdapter.isEnabled()) {
                    mDefaultAdapter.disable();
                }
                break;
            case R.id.button3:
                //开始扫描
                mDevices.clear();
                mAdapter.notifyDataSetChanged();
                mDefaultAdapter.startDiscovery();
                mTv_content.setText(mDefaultAdapter.getName());

                break;
            case R.id.button4:
                //停止扫描
                mDefaultAdapter.cancelDiscovery();
                break;
            case R.id.button5:
                sendCtrl(0);
                break;
            case R.id.button6:
                sendCtrl(1);
                break;
            case R.id.button7:
                sendCtrl(2);
                break;

            default:

                break;
        }
    }

    private void sendCtrl(int i) {
        byte[] bs = new byte[5];
        bs[0] = (byte) 0x01;
        bs[1] = (byte) 0x99;
        switch (i) {
            case 0:
                bs[2] = (byte) 0x10;
                bs[3] = (byte) 0x10;
                break;
            case 1:
                bs[2] = (byte) 0x11;
                bs[3] = (byte) 0x11;
                break;
            case 2:
                bs[2] = (byte) 0x17;
                bs[3] = (byte) 0x17;
                break;

            default:

                break;
        }
        bs[4] = (byte) 0x99;
        try {

            mOutputStream.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothDevice device = mDevices.get(position);
        conn(device);
    }

    private void conn(final BluetoothDevice device) {
        // 建立蓝牙连接是耗时操作, 类似TCP Socket, 需要放在子线程里
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 BluetoothSocket, UUID需要和蓝牙服务端保持一致
                try {
                    BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID
                            .fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    // 和蓝牙服务端建立连接
                    bluetoothSocket.connect();
                    // 获取输出流, 往蓝牙服务端写指令信息
                    mOutputStream = bluetoothSocket.getOutputStream();
                    // 提示用户
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToastPlus(getApplicationContext(), "连接成功----");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
