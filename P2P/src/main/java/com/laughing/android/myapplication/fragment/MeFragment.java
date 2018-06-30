package com.laughing.android.myapplication.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.activity.BarChartActivity;
import com.laughing.android.myapplication.activity.BaseActivity;
import com.laughing.android.myapplication.activity.ChongzhiActivity;
import com.laughing.android.myapplication.activity.LineChartActivity;
import com.laughing.android.myapplication.activity.LoginActivity;
import com.laughing.android.myapplication.activity.PieChartActivity;
import com.laughing.android.myapplication.activity.TiXianActivity;
import com.laughing.android.myapplication.activity.ToggleActivity;
import com.laughing.android.myapplication.activity.UserInfoActivity;
import com.laughing.android.myapplication.bean.Login;
import com.laughing.android.myapplication.utils.BitmapUtils;
import com.laughing.android.myapplication.utils.SharedPreferenceUtil;
import com.laughing.android.myapplication.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public class MeFragment extends BaseFragment implements View.OnClickListener {


    private TextView mTextView11;
    private ImageView imageView1;
    private ImageView mChongzhi;
    private ImageView mTixian;
    private TextView mLl_touzi;
    private TextView mLl_touzi_zhiguan;
    private TextView mLl_zichang;
    private TextView mLlMoneySafe;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        TextView title_tv = (TextView) mView.findViewById(R.id.title_tv);
        ImageView title_left = (ImageView) mView.findViewById(R.id.title_left);
        ImageView title_right = (ImageView) mView.findViewById(R.id.title_right);
        mChongzhi = (ImageView) mView.findViewById(R.id.chongzhi);//充值按钮
        mTixian = (ImageView) mView.findViewById(R.id.tixian);//充值按钮
        title_tv.setText("我的资产");
        title_left.setVisibility(View.INVISIBLE);
        title_right.setVisibility(View.VISIBLE);
        mTextView11 = (TextView) mView.findViewById(R.id.textView11);
        imageView1 = (ImageView) mView.findViewById(R.id.imageView1);
        mLl_touzi = (TextView) mView.findViewById(R.id.ll_touzi);//投资
        mLl_touzi_zhiguan = (TextView) mView.findViewById(R.id.ll_touzi_zhiguan);//
        mLl_zichang = (TextView) mView.findViewById(R.id.ll_zichang);//资产管理
        mLlMoneySafe = (TextView) mView.findViewById(R.id.ll_money_safe);//资产安全
        mChongzhi.setOnClickListener(this);
        title_right.setOnClickListener(this);
        mTixian.setOnClickListener(this);
        mLl_touzi.setOnClickListener(this);
        mLl_touzi_zhiguan.setOnClickListener(this);
        mLl_zichang.setOnClickListener(this);
        mLlMoneySafe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right://设置
                ((BaseActivity) getActivity()).gotoActivity(UserInfoActivity.class, null);
                break;
            case R.id.chongzhi://充值
                ((BaseActivity) getActivity()).gotoActivity(ChongzhiActivity.class, null);
                break;
            case R.id.tixian://提现
                ((BaseActivity) getActivity()).gotoActivity(TiXianActivity.class, null);
                break;

            case R.id.ll_touzi://
                ((BaseActivity) getActivity()).gotoActivity(LineChartActivity.class, null);
                break;
            case R.id.ll_touzi_zhiguan://
                ((BaseActivity) getActivity()).gotoActivity(BarChartActivity.class, null);
                break;
            case R.id.ll_zichang://
                ((BaseActivity) getActivity()).gotoActivity(PieChartActivity.class, null);
                break;
            case R.id.ll_money_safe://
                ((BaseActivity) getActivity()).gotoActivity(ToggleActivity.class, null);
                break;

            default:

                break;
        }
    }

    @Override
    protected void initData(String content) {
        isLogin();

    }

    private void isLogin() {
        String uf_acc = SharedPreferenceUtil.getString(getActivity(), "UF_ACC", "");
        if (TextUtils.isEmpty(uf_acc)) {
            //未登录
            showLoginDialog();
        } else {
            //已登录--处理信息
            doUser();
        }
    }

    /**
     * 用户已登录
     */
    private void doUser() {
        Login login = ((BaseActivity) getActivity()).getLogin();
        mTextView11.setText(login.UF_ACC);
        Picasso.with(getActivity()).load(login.UF_AVATAR_URL).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Bitmap zoomBitmap = BitmapUtils.zoomBitmap(source, UIUtils.dp2px(62), UIUtils.dp2px(62));//xml中宽高是62dp
                Bitmap circleBitMap = BitmapUtils.circleBitmap(zoomBitmap);
                //1:transform当中处理完图片之后，需要调用recylce方法回收
                source.recycle();
                return circleBitMap;
            }

            @Override
            public String key() {
                //2:重写key方法的返回值，不能是null
                return "";
            }
        }).into(imageView1);

    }

    /**
     * 显示对话框提示未登录
     */
    private void showLoginDialog() {
        // 跳转到登录页面
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示信息");
        builder.setMessage("请您点击登录按钮进行登录");
        // 登录
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 跳转到登录页面
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                ((BaseActivity) getActivity()).gotoActivity(LoginActivity.class, null);

            }
        });
        builder.setCancelable(false);
        builder.create().show();
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("登录");
//        builder.setMessage("必须先登录...go...");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                ((BaseActivity) getActivity()).gotoActivity(LoginActivity.class, null);
//            }
//        });
//        builder.setCancelable(false);
//        builder.create().show();
    }


}


