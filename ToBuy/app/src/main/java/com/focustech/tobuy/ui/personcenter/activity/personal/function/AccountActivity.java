package com.focustech.tobuy.ui.personcenter.activity.personal.function;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.user.UsersPresenter;
import com.focustech.tobuy.biz.personcenter.user.view.IUserView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.SimplePersonalActivity;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyCircleImageView;
import com.focustech.tobuy.view.MyGenderCheckBoxGroupView;
import com.focustech.tobuy.view.MyTextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class AccountActivity extends BaseActivity implements IUserView {

    private MyCircleImageView accountPic;
    private MyTextView accountId;
    private LinearLayout accountNameLayout;
    private MyTextView accountName;
    private LinearLayout accountGenderGroup;
    private MyGenderCheckBoxGroupView genders;
    private MyTextView accountBirthday;
    private MyTextView accountWords;

    private ImageView back;
    private ImageView functionIV;
    private TextView functionTV;

    private AlertDialog.Builder adb;
    private EditText alertEditText;

    private ImageView cardUserHead;
    private MyTextView cardUserName;

    private UsersPresenter usersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.account_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        if (usersPresenter == null) {
            presenter = usersPresenter = new UsersPresenter();
            presenter.attachView(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initViews() {
        initAccountPage();
    }

    @Override
    public void initListeners() {
        back.setOnClickListener(this);
        initAccountPicAlert();
        initAccountNameAlert();
        initBirthdayAlert();
        initAccountWordsAlert();
        functionIV.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardBack:
                onBackPressed();
                break;
            case R.id.functionIV:
                updateUserInfo();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initAccountPage() {

        cardUserHead = findViewById(R.id.cardUserHead);
        cardUserHead.setVisibility(View.INVISIBLE);
        cardUserName = findViewById(R.id.cardUserName);
        cardUserName.setVisibility(View.INVISIBLE);
        accountNameLayout = findViewById(R.id.accountNameLayout);

        back = findViewById(R.id.cardBack);
        functionIV = findViewById(R.id.functionIV);
        functionTV = findViewById(R.id.functionTV);
        functionTV.setText("保存");
        accountPic = findViewById(R.id.accountPic);
        accountId = findViewById(R.id.accountId);
        accountName = findViewById(R.id.accountName);
        accountBirthday = findViewById(R.id.accountBirthday);
        accountWords = findViewById(R.id.accountWords);
        accountGenderGroup = findViewById(R.id.accountGenderGroup);
        genders = new MyGenderCheckBoxGroupView(this);
        for (int i = 0; i < genders.getGenders().length; i++) {
            accountGenderGroup.addView(genders.getGenders()[i].outLinearLayout);
        }


        cardUserHead.setImageDrawable(BaseActivity.user_head.getDrawable());
        cardUserName.setText(userTable.getUsername());
        accountPic.setImageDrawable(BaseActivity.user_head.getDrawable());
        accountId.setText(String.valueOf(userTable.getId()));
        accountName.setText(userTable.getUsername());
        accountBirthday.setText(new SimpleDateFormat("yyyy-MM-dd").format(userTable.getDate()));
        accountWords.setText(userTable.getPs());
        genders.setSex(userTable.getGender() - 1);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateUserInfo() {
        if (!checkForm()) {
            return;
        }
        UserTable updateUserTable = new UserTable();
        updateUserTable.setId(BaseActivity.userTable.getId());
        updateUserTable.setUsername(accountName.getText().toString().trim());
        updateUserTable.setGender(genders.getSelectedId() + 1);

        try {
            String dateStr = accountBirthday.getText().toString().trim();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            updateUserTable.setDate(simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        updateUserTable.setPs(accountWords.getText().toString().trim());

        UserTable temp = BaseActivity.userTable;
        if (temp.getUsername().equals(updateUserTable.getUsername()) && temp.getGender()== updateUserTable.getGender()&&updateUserTable.getDate()==temp.getDate()&& temp.getPs().equals(updateUserTable.getPs())){
            ToastUtil.makeText(this, "无更新内容");
            return;
        }

        usersPresenter.updateUserInfo(updateUserTable);
    }

    public boolean checkForm() {
        if (accountName.getText().toString().trim().length() == 0) {
            ToastUtil.makeText(this, "用户名不能为空");
            return false;
        }

        return true;
    }


    @Override
    public void updateUserInfo(UserTable userTable) {
        if (userTable != null) {
            ToastUtil.makeText(this, "更新成功");
            BaseActivity.userTable = userTable;
            startActivity(SimplePersonalActivity.class, null);
        }else {
            ToastUtil.makeText(this, "更新失败");
        }
    }












    public void initAccountPicAlert() {
        //本地相册
    }


    public void initAccountNameAlert() {
        accountNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertEditText = new EditText(AccountActivity.this);
                adb = new AlertDialog.Builder(AccountActivity.this);
                adb.setTitle("我的昵称")
                        .setView(alertEditText)
                        .setCancelable(false)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                accountName.setText(alertEditText.getText().toString());
                                //更新信息
                            }
                        }).setNegativeButton("取消", null).show();
                alertEditText.setText(accountName.getText());
            }
        });
    }

    public void initBirthdayAlert() {
        accountBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // 更新EditText控件日期 小于10加0
                        accountBirthday.setText(new StringBuilder()
                                .append(year)
                                .append("-")
                                .append((month + 1) < 10 ? "0"
                                        + (month + 1) : (month + 1))
                                .append("-")
                                .append((day < 10) ? "0" + day : day));
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void initAccountWordsAlert() {

        accountWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertEditText = new EditText(AccountActivity.this);
                adb = new AlertDialog.Builder(AccountActivity.this);
                adb.setTitle("个性签名")
                        .setView(alertEditText)
                        .setCancelable(false)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                accountWords.setText(alertEditText.getText().toString());
                                //更新信息
                            }
                        }).setNegativeButton("取消", null).show();
                alertEditText.setText(accountWords.getText());
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }

    @Override
    public void onError(String errorMsg, String code) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadUserInfo(UserResp userResp) {

    }

    @Override
    public void loadUserById(UserTable userTable) {

    }

}
