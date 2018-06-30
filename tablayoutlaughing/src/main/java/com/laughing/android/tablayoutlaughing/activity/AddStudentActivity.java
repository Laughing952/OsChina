package com.laughing.android.tablayoutlaughing.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laughing.android.tablayoutlaughing.LitePalBean.Student;
import com.laughing.android.tablayoutlaughing.R;
import com.laughing.android.tablayoutlaughing.util.ToastUtils;

/**
 * 作者：Laughing on 2017/9/2 14:34
 * 邮箱：719240226@qq.com
 */

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext = this;
    private EditText mEtMore;
    private Button mBtCommit, bt_go_on, bt_back;
    private RadioGroup mRgGrade;
    private RadioButton mRb6;
    private RadioButton mRb5;
    private RadioButton mRb4;
    private RadioButton mRb3;
    private RadioButton mRb2;
    private RadioButton mRb1;
    private TextView mTvgrade;
    private EditText mEtphone2;
    private EditText mEtphone1;
    private EditText mEtname;
    private String checkGradeId, checkTypeId, checkSexId;
    private RadioButton mGradeRadioButton, mTypeRadioButton, mSexRadioButton;
    private RadioGroup mRgsextype;
    private RadioButton mRb3sextype;
    private RadioButton mRb1sextype;
    private RadioButton mRb2sextype;
    private TextView mTvsextype;
    private RadioGroup mRgtuoguantype;
    private RadioButton mRb3tuoguantype;
    private RadioButton mRb2tuoguantype;
    private RadioButton mRb1tuoguantype;
    private TextView mTvtuoguantype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().setTitle("大乐教育    添加学生信息");
        setContentView(R.layout.activity_add);


        initView();
        initListener();


    }

    private void initListener() {
        bt_back.setOnClickListener(this);
        bt_go_on.setOnClickListener(this);
        mBtCommit.setOnClickListener(this);

    }

    private void initView() {
        mEtMore = (EditText) findViewById(R.id.et_more);
        bt_back = (Button) findViewById(R.id.bt_back);
        bt_go_on = (Button) findViewById(R.id.bt_go_on);
        mBtCommit = (Button) findViewById(R.id.bt_commit);
        mRgGrade = (RadioGroup) findViewById(R.id.rg_grade);
        mRb6 = (RadioButton) findViewById(R.id.rb6);
        mRb5 = (RadioButton) findViewById(R.id.rb5);
        mRb4 = (RadioButton) findViewById(R.id.rb4);
        mRb3 = (RadioButton) findViewById(R.id.rb3);
        mRb2 = (RadioButton) findViewById(R.id.rb2);
        mRb1 = (RadioButton) findViewById(R.id.rb1);
        mTvgrade = (TextView) findViewById(R.id.tv_grade);
        mEtphone2 = (EditText) findViewById(R.id.et_phone2);
        mEtphone1 = (EditText) findViewById(R.id.et_phone1);
        mEtname = (EditText) findViewById(R.id.et_name);

        //性别
        mRgsextype = (RadioGroup) findViewById(R.id.rg_sex_type);
        mRb3sextype = (RadioButton) findViewById(R.id.rb3_sex_type);
        mRb2sextype = (RadioButton) findViewById(R.id.rb2_sex_type);
        mRb1sextype = (RadioButton) findViewById(R.id.rb1_sex_type);
        mTvsextype = (TextView) findViewById(R.id.tv_sex_type);
        //托管类型
        mRgtuoguantype = (RadioGroup) findViewById(R.id.rg_tuoguan_type);
        mRb3tuoguantype = (RadioButton) findViewById(R.id.rb3_tuoguan_type);
        mRb2tuoguantype = (RadioButton) findViewById(R.id.rb2_tuoguan_type);
        mRb1tuoguantype = (RadioButton) findViewById(R.id.rb1_tuoguan_type);
        mTvtuoguantype = (TextView) findViewById(R.id.tv_tuoguan_type);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                back();
                break;
            case R.id.bt_go_on:
                goOn();
                break;
            case R.id.bt_commit:
                commit();
                goOn();
                break;
            default:

                break;
        }
    }

    /**
     * 清除界面所有内容
     */
    private void goOn() {
        mEtname.setText("");
        mEtphone1.setText("");
        mEtphone2.setText("");
        mEtMore.setText("");

    }

    private void back() {
        finish();
    }

    /**
     * 提交信息
     */
    private void commit() {
        String name = mEtname.getText().toString().trim();
        String phone1 = mEtphone1.getText().toString().trim();
        String phone2 = mEtphone2.getText().toString().trim();
        //托管类型
        type();
        //性别
        sex();
        //年级
        grade();

        String more = mEtMore.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            //添加一条数据
            Student student = new Student();
            student.setName(name);
            student.setPhone1(TextUtils.isEmpty(phone1) ? "" : phone1);
            student.setPhone2(TextUtils.isEmpty(phone2) ? "" : phone2);
            student.setType(TextUtils.isEmpty(checkTypeId) ? "" : checkTypeId);
            student.setSex(TextUtils.isEmpty(checkSexId) ? "" : checkSexId);
            student.setGrade(TextUtils.isEmpty(checkGradeId) ? "" : checkGradeId);
            student.setMore(TextUtils.isEmpty(more) ? "" : more);
            student.save();//记得要保存
            ToastUtils.showToastPlus(mContext, "添加成功");

        } else {
            ToastUtils.showToastPlus(mContext, "学生姓名不能为空");
        }
    }

    private void grade() {
        mGradeRadioButton = (RadioButton) findViewById(mRgGrade.getCheckedRadioButtonId());
        checkGradeId = mGradeRadioButton.getText().toString();
        switch (checkGradeId) {
            case "一年级":
                checkGradeId = "0";
                break;
            case "二年级":
                checkGradeId = "1";
                break;
            case "三年级":
                checkGradeId = "2";
                break;
            case "四年级":
                checkGradeId = "3";
                break;
            case "五年级":
                checkGradeId = "4";
                break;
            case "六年级":
                checkGradeId = "5";
                break;

            default:

                break;
        }
    }

    private void sex() {
        mSexRadioButton = (RadioButton) findViewById(mRgsextype.getCheckedRadioButtonId());
        checkSexId = mSexRadioButton.getText().toString();
        switch (checkSexId) {
            case "女":
                checkSexId = "0";
                break;
            case "男":
                checkSexId = "1";
                break;
            case "待确定":
                checkSexId = "2";
                break;

            default:

                break;
        }
    }

    private void type() {
        mTypeRadioButton = (RadioButton) findViewById(mRgtuoguantype.getCheckedRadioButtonId());
        checkTypeId = mTypeRadioButton.getText().toString();
        switch (checkTypeId) {
            case "全托":
                checkTypeId = "0";
                break;
            case "午托":
                checkTypeId = "1";
                break;
            case "晚托":
                checkTypeId = "2";
                break;

            default:

                break;
        }
    }

}
