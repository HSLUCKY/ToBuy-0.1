package com.focustech.tobuy.ui.personcenter.activity.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.login.view.IUserLoginView;
import com.focustech.tobuy.biz.personcenter.login.LoginPresenter;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

public class LoginActivity extends BaseActivity implements IUserLoginView, View.OnClickListener, View.OnFocusChangeListener {

    /**
     * 用户名
     */
    private EditText userName;

    /**
     * 用户密码
     */
    private EditText password;

    /**
     * 输入以外的区域
     */
    private View layout;

    /**
     * 登录
     */
    private Button login;
    private MyTextView registerTurn;

    private LoginPresenter mUserLoginPresenter;

    private UserTable userTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);

        presenter = mUserLoginPresenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        userName = findViewById(R.id.username);
        password = findViewById(R.id.passowrd);
        layout = findViewById(R.id.loginEdit);
        login = findViewById(R.id.login);
        registerTurn = findViewById(R.id.registerTurn);
    }

    @Override
    public void initListeners() {
        login.setOnClickListener(this);
        userName.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);
        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        layout.setOnClickListener(this);
        registerTurn.setOnClickListener(this);

        userName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

        userName.clearFocus();
        password.clearFocus();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                mUserLoginPresenter.login(userName.getText().toString(),
                        password.getText().toString());
                break;
            case R.id.loginEdit:
                if (userName.hasFocus()) {
                    closeKeyboard(userName);
                }
                if (password.hasFocus()) {
                    closeKeyboard(password);
                }
                break;
            case R.id.registerTurn:
                startActivity(RegisterActivity.class, null);
                break;
        }
        super.onClick(v);
    }


    @Override
    public void onEventMainThread(Event event) {
        switch (event){
            case LOGIN_SUCCESS:
                ToastUtil.makeText(this, "登录成功");
                break;
        }

        super.onEventMainThread(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.username:
                if (hasFocus) {
                    openKeyboard(v);
                }
                break;
            case R.id.passowrd:
                if (hasFocus) {
                    openKeyboard(v);
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code) {
        showToast(errorMsg);
    }

    @Override
    public void onSuccess() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HomeActivity.class, null);
            }
        }, 100);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void clearEditContent() {
        userName.setText("");
        password.setText("");
    }

    @Override
    public void showLoading() {
        ToastUtil.makeText(this, "正在登录");
    }

    @Override
    public void hideLoading() {
        hideSoftKeyboard();
    }


    /**
     * 开启软键盘
     */
    public void openKeyboard(final View editText) {
        super.showSoftKeyboard(editText);
    }

    /**
     * 关闭软键盘
     */
    public void closeKeyboard(View editText) {

        super.hideSoftKeyboard(this, editText);
    }


}
