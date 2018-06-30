package com.laughing.android.tablayoutlaughing.activity;

import android.content.Context;
import android.content.Intent;
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
 * 作者：Laughing on 2017/9/5 09:47
 * 邮箱：719240226@qq.com
 */

public class UpdateStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = this;
    private EditText mEtupdatemore;
    private Button mBtupdatecommit;
    private Button mBtupdateback;
    private RadioGroup mRgupdategrade;
    private RadioButton mRb6updat;
    private RadioButton mRb5updat;
    private RadioButton mRb4updat;
    private RadioButton mRb3updat;
    private RadioButton mRb2updat;
    private RadioButton mRb1updat;
    private TextView mTvupdategrade;
    private RadioGroup mRgupdatesextype;
    private RadioButton mRb3updatesextype;
    private RadioButton mRb1updatesextype;
    private RadioButton mRb2updatesextype;
    private TextView mTvupdatesextype;
    private RadioGroup mRgupdatetuoguantype;
    private RadioButton mRb3updatetuoguantype;
    private RadioButton mRb2updatetuoguantype;
    private RadioButton mRb1updatetuoguantype;
    private TextView mTvupdatetuoguantype;
    private EditText mEtupdatephone2;
    private EditText mEtupdatephone1;
    private EditText mEtupdatename;
    private Student mStudent;
    private String checkTypeId;
    private String checkSexId;
    private String checkGradeId;
    private RadioButton mGradeRadioButton, mTypeRadioButton, mSexRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().setTitle("大乐教育    修改学生信息");
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        mStudent = (Student) intent.getSerializableExtra("student");
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtupdateback.setOnClickListener(this);
        mBtupdatecommit.setOnClickListener(this);
    }

    private void initView() {
        mEtupdatemore = (EditText) findViewById(R.id.et_update_more);
        mBtupdatecommit = (Button) findViewById(R.id.bt_update_commit);
        mBtupdateback = (Button) findViewById(R.id.bt_update_back);
        mRgupdategrade = (RadioGroup) findViewById(R.id.rg_update_grade);
        mRb6updat = (RadioButton) findViewById(R.id.rb6_updat);
        mRb5updat = (RadioButton) findViewById(R.id.rb5_updat);
        mRb4updat = (RadioButton) findViewById(R.id.rb4_updat);
        mRb3updat = (RadioButton) findViewById(R.id.rb3_updat);
        mRb2updat = (RadioButton) findViewById(R.id.rb2_updat);
        mRb1updat = (RadioButton) findViewById(R.id.rb1_updat);
        mTvupdategrade = (TextView) findViewById(R.id.tv_update_grade);
        mRgupdatesextype = (RadioGroup) findViewById(R.id.rg_update_sex_type);
        mRb3updatesextype = (RadioButton) findViewById(R.id.rb3_update_sex_type);
        mRb2updatesextype = (RadioButton) findViewById(R.id.rb2_update_sex_type);
        mRb1updatesextype = (RadioButton) findViewById(R.id.rb1_update_sex_type);
        mTvupdatesextype = (TextView) findViewById(R.id.tv_update_sex_type);
        mRgupdatetuoguantype = (RadioGroup) findViewById(R.id.rg_update_tuoguan_type);
        mRb3updatetuoguantype = (RadioButton) findViewById(R.id.rb3_update_tuoguan_type);
        mRb2updatetuoguantype = (RadioButton) findViewById(R.id.rb2_update_tuoguan_type);
        mRb1updatetuoguantype = (RadioButton) findViewById(R.id.rb1_update_tuoguan_type);
        mTvupdatetuoguantype = (TextView) findViewById(R.id.tv_update_tuoguan_type);
        mEtupdatephone2 = (EditText) findViewById(R.id.et_update_phone2);
        mEtupdatephone1 = (EditText) findViewById(R.id.et_update_phone1);
        mEtupdatename = (EditText) findViewById(R.id.et_update_name);
    }

    private void initData() {
        mEtupdatename.setText(mStudent.getName());
        mEtupdatephone1.setText(mStudent.getPhone1());
        mEtupdatephone2.setText(mStudent.getPhone2());
        //托管类型
        type();
        //性别
        sex();
        //年级
        grade();
        mEtupdatemore.setText(mStudent.getMore());

    }

    /**
     * 提交信息
     */
    private void commit() {
        int id = mStudent.getId();
        String name = mEtupdatename.getText().toString().trim();
        String phone1 = mEtupdatephone1.getText().toString().trim();
        String phone2 = mEtupdatephone2.getText().toString().trim();
        //托管类型
        typeCommit();
        //性别
        sexCommit();
        //年级
        gradeCommit();

        String more = mEtupdatemore.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

//            Book book1 = new Book();
//            book1.setPrice(10.00);
////                book1.setToDefault("pages");//用来把字段值改为数据库初始值
//            book1.setPress("电子工业出版社");
//            book1.updateAll("name=? and author=?", "The Da Vinci Code", "Dan Brown");
            //添加一条数据
            Student student = new Student();
            student.setName(name);
            student.setPhone1(TextUtils.isEmpty(phone1) ? "" : phone1);
            student.setPhone2(TextUtils.isEmpty(phone2) ? "" : phone2);
            student.setType(TextUtils.isEmpty(checkTypeId) ? "" : checkTypeId);
            student.setSex(TextUtils.isEmpty(checkSexId) ? "" : checkSexId);
            student.setGrade(TextUtils.isEmpty(checkGradeId) ? "" : checkGradeId);
            student.setMore(TextUtils.isEmpty(more) ? "" : more);
            student.updateAll("id=?", id + "");
            ToastUtils.showToastPlus(mContext, "修改成功");

        } else {
            ToastUtils.showToastPlus(mContext, "学生姓名不能为空");
        }
    }

    /**
     * 提交时重新获取年级
     */
    private void gradeCommit() {
        mGradeRadioButton = (RadioButton) findViewById(mRgupdategrade.getCheckedRadioButtonId());
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

    /**
     * 提交时重新获取性别
     */
    private void sexCommit() {
        mSexRadioButton = (RadioButton) findViewById(mRgupdatesextype.getCheckedRadioButtonId());
        checkSexId = mSexRadioButton.getText().toString();
        switch (checkGradeId) {
            case "女":
                checkGradeId = "0";
                break;
            case "男":
                checkGradeId = "1";
                break;
            case "待确定":
                checkGradeId = "2";
                break;

            default:

                break;
        }


    }

    /**
     * 提交时重新获取托管类型
     */
    private void typeCommit() {
        mTypeRadioButton = (RadioButton) findViewById(mRgupdatetuoguantype.getCheckedRadioButtonId());
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

    private void grade() {
        checkGradeId = mStudent.getGrade();
        switch (checkGradeId) {
            case "0":
                mRb1updat.setChecked(true);

                break;
            case "1":
                mRb2updat.setChecked(true);

                break;
            case "2":
                mRb3updat.setChecked(true);

                break;
            case "3":
                mRb4updat.setChecked(true);

                break;
            case "4":
                mRb5updat.setChecked(true);

                break;
            case "5":
                mRb6updat.setChecked(true);

                break;

            default:

                break;
        }
    }

    private void sex() {
        checkSexId = mStudent.getSex();
        switch (checkSexId) {
            case "0":
                mRb1updatesextype.setChecked(true);
                break;
            case "1":
                mRb2updatesextype.setChecked(true);
                break;
            case "2":
                mRb3updatesextype.setChecked(true);
                break;

            default:

                break;
        }
    }

    private void type() {
        checkTypeId = mStudent.getType();
        switch (checkTypeId) {
            case "0":
                mRb1updatetuoguantype.setChecked(true);

                break;
            case "1":
                mRb2updatetuoguantype.setChecked(true);

                break;
            case "2":
                mRb3updatetuoguantype.setChecked(true);

                break;

            default:

                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_update_back:
                back();
                break;
            case R.id.bt_update_commit:
                commit();
                break;
            default:

                break;
        }
    }

    private void back() {
        finish();
    }


}
