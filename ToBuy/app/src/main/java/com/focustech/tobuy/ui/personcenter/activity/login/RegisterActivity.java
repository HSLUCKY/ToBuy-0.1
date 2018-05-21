package com.focustech.tobuy.ui.personcenter.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.focustech.tobuy.R;
import com.focustech.tobuy.biz.personcenter.login.RegisterPresenter;
import com.focustech.tobuy.biz.personcenter.login.view.IUserRegisterView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/4/10.
 */

public class RegisterActivity extends BaseActivity implements IUserRegisterView {

    private ImageView appLogo;
    private EditText registerName;
    private EditText registerPassword;
    private MyTextView registerButton;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.register_activity);
        super.onCreate(savedInstanceState);

        presenter = registerPresenter = new RegisterPresenter();
        presenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {

        appLogo = findViewById(R.id.appLogo);
        registerPassword = findViewById(R.id.registerPassword);
        registerPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        registerName = findViewById(R.id.registerName);
        registerButton = findViewById(R.id.registerButton);

    }

    @Override
    public void initListeners() {
        appLogo.setOnClickListener(this);
        registerName.setOnClickListener(this);
        registerPassword.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        registerName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        registerPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appLogo:

                break;
            case R.id.registerButton:
                registerPresenter.register(registerName.getText().toString(),
                        registerPassword.getText().toString());
                break;
        }

    }

    @Override
    public void onEventMainThread(Event event) {
        switch (event) {
            case REGISTER_SUCCESS:
                ToastUtil.makeText(this,"登录成功");
                clearEdit();
                break;
        }
        super.onEventMainThread(event);
    }

    @Override
    public void onError(String errorMsg, String code) {
        ToastUtil.makeText(this, errorMsg);
    }

    @Override
    public void onSuccess() {
        startActivity(LoginActivity.class, null);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void clearEdit() {
        registerName.setText("");
        registerPassword.setText("");
    }
}
