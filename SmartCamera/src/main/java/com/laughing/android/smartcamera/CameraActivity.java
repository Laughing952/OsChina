package com.laughing.android.smartcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tutk.IOTC.AVIOCTRLDEFs;
import com.tutk.IOTC.Camera;
import com.tutk.IOTC.IRegisterIOTCListener;
import com.tutk.IOTC.Monitor;

/**
 * Created by Laughing on 2017/7/23.
 */
public class CameraActivity extends AppCompatActivity {

    private Camera mCamera;
    private MyIotcListener mMyIotcListener;
    private TextView mTvStatus;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Camera.CONNECTION_STATE_CONNECTING:
                    // 连接中
                    mTvStatus.setText("连接中");

                    break;
                case Camera.CONNECTION_STATE_CONNECTED:
                    // 连接成功
                    mTvStatus.setText("连接成功");
                    // 显示摄像头画面
                    showCamera();
                    break;
                case Camera.CONNECTION_STATE_CONNECT_FAILED:
                    // 连接失败
                    mTvStatus.setText("连接失败");

                    break;
                case Camera.CONNECTION_STATE_TIMEOUT:
                    // 超时
                    mTvStatus.setText("连接超时");
                    break;

                default:

                    break;
            }
            super.handleMessage(msg);
        }
    };
    private Monitor mMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noTitle();
        setContentView(R.layout.activity_camera);
//        initValue();
        initView();

    }

    // 显示画面, 需要通过 Monitor 显示, 数据从 Camera 获取
    protected void showCamera() {
        // 判断是否连击成功
        if (mCamera != null && mCamera.isChannelConnected(Camera.DEFAULT_AV_CHANNEL)) {
            // 设置最大焦距
            mMonitor.setMaxZoom(1.0f);
            // 挂载摄像头
            mMonitor.attachCamera(mCamera, Camera.DEFAULT_AV_CHANNEL);
            // 开始显示
            mCamera.startShow(Camera.DEFAULT_AV_CHANNEL, true);
        }
    }

    private void initValue() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String uid = intent.getStringExtra("uid");
        String pwd = intent.getStringExtra("pwd");
        conn(name, uid, pwd);
    }

    /**
     * 发送连接请求 连接摄像头
     *
     * @param name
     * @param uid
     * @param pwd
     */
    private void conn(String name, String uid, String pwd) {
        // 初始化摄像头, 初始化AV通道
        Camera.init();
        // 创建Camera实例对象
        mCamera = new Camera();
        // 注册回调接口, 摄像头返回的信息通过回调方法传递给我们
        mMyIotcListener = new MyIotcListener();
        mCamera.registerIOTCListener(mMyIotcListener);
        // 连接摄像头, 参数为uid
        mCamera.connect(uid);
        // 开启摄像头
        // 参数1: 通道号, 后面还会用到, 要保证一致
        /**
         * int i
         * java.lang.String s
         * java.lang.String s1
         * 开启摄像头
         * 参数1: 通道号, 后面还会用到, 要保证一致
         */
        mCamera.start(Camera.DEFAULT_AV_CHANNEL, name, pwd);
        // 发送测试指令, 测试是否连接成功
        // 参数1: 通道号
        // 参数2: 指令类型
        // 参数3: 指令参数
        mCamera.sendIOCtrl(Camera.DEFAULT_AV_CHANNEL, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_GETSUPPORTSTREAM_REQ,
                AVIOCTRLDEFs.SMsgAVIoctrlGetSupportStreamReq.parseContent());
    }

    /**
     * 去掉标题栏
     */
    private void noTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
    }


    private void initView() {
        ImageButton ibtop = (ImageButton) findViewById(R.id.ib_top);
        ImageButton ibleft = (ImageButton) findViewById(R.id.ib_left);
        ImageButton ibright = (ImageButton) findViewById(R.id.ib_right);
        ImageButton ibbottom = (ImageButton) findViewById(R.id.ib_bottom);
        View vcenter = (View) findViewById(R.id.v_center);
        mTvStatus = (TextView) findViewById(R.id.tv_status);
        mMonitor = (Monitor) findViewById(R.id.monitor);
    }

    private void sendPtz(int ptz) {
        byte b = (byte) ptz;
        // 方向, 速度, 距离, 触摸点个数, 外接设备, 通道号
        mCamera.sendIOCtrl(Camera.DEFAULT_AV_CHANNEL, AVIOCTRLDEFs.IOTYPE_USER_IPCAM_PTZ_COMMAND,
                AVIOCTRLDEFs.SMsgAVIoctrlPtzCmd.parseContent(b, (byte) 0, (byte) 0, (byte) 0,
                        (byte) 0, (byte) Camera.DEFAULT_AV_CHANNEL));
    }

    // 点击按钮, 发送不同指令
    public void sendCtrl(View v) {
        switch (v.getId()) {
            case R.id.ib_left:
                ToastUtil.showToastPlus(this, "left");
                sendPtz(AVIOCTRLDEFs.AVIOCTRL_PTZ_LEFT);

                break;
            case R.id.ib_top:
                ToastUtil.showToastPlus(this, "top");
                sendPtz(AVIOCTRLDEFs.AVIOCTRL_PTZ_UP);
                break;
            case R.id.ib_right:
                ToastUtil.showToastPlus(this, "right");
                sendPtz(AVIOCTRLDEFs.AVIOCTRL_PTZ_RIGHT);
                break;
            case R.id.ib_bottom:
                ToastUtil.showToastPlus(this, "bottom");
                sendPtz(AVIOCTRLDEFs.AVIOCTRL_PTZ_DOWN);
                break;

            default:

                break;
        }
    }

    private class MyIotcListener implements IRegisterIOTCListener {
        // 返回通道的信息
        @Override
        public void receiveChannelInfo(Camera camera, int channel, int responseCode) {
            System.out.println("conn status: " + responseCode);
            // 这个回调方法是在子线程里调用的
            // 11-19 02:58:01.555: E/AndroidRuntime(537):
            // android.view.ViewRoot$CalledFromWrongThreadException: Only the
            // original thread that created a view hierarchy can touch its
            // views.
            // mTvStatus.setText(responseCode + "");
            mHandler.sendEmptyMessage(responseCode);

        }

        // 返回摄像头捕捉到的画面信息
        @Override
        public void receiveFrameData(Camera camera, int i, Bitmap bitmap) {

        }

        // 返回摄像头其他信息
        @Override
        public void receiveFrameInfo(Camera camera, int i, long l, int i1, int i2, int i3, int i4) {

        }

        // 返回指令结过信息
        @Override
        public void receiveIOCtrlData(Camera camera, int i, int i1, byte[] bytes) {

        }

        // 返回当前会话信息
        @Override
        public void receiveSessionInfo(Camera camera, int i) {

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源, 断开连接,把之前的方法取消就行
        mCamera.stopShow(Camera.DEFAULT_AV_CHANNEL);
        mMonitor.deattachCamera();
        mCamera.stop(Camera.DEFAULT_AV_CHANNEL);
        mCamera.disconnect();
        mCamera.unregisterIOTCListener(mMyIotcListener);
        Camera.uninit();
    }
}
