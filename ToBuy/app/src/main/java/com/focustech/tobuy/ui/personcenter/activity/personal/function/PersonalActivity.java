package com.focustech.tobuy.ui.personcenter.activity.personal.function;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.service.resource.UserHeadResp;
import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.resource.ResourcePresenter;
import com.focustech.tobuy.biz.personcenter.resource.view.IResourceView;
import com.focustech.tobuy.biz.personcenter.user.UsersPresenter;
import com.focustech.tobuy.biz.personcenter.user.view.IUserView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.message.PostActivity;
import com.focustech.tobuy.view.MyTextView;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/29.
 */

public class PersonalActivity extends BaseActivity implements IResourceView, IUserView {

    private LinearLayout personalZoneBackground;
    private LinearLayout personalZoneGlass;
    private ImageView personalHead;
    private MyTextView personalWords;
    private MyTextView personalCard;
    private MyTextView personalBrowse;
    private MyTextView personalMessage;
    private MyTextView personalVisitor;
    private MyTextView personalSend;

    /**
     * 发送方和信息的头像
     */
    public byte[] head;
    /**
     * 发送方用户信息
     */
    public UserTable users_info;

    public int userId = -1;


    private ResourcePresenter resourcePresenter;
    private UsersPresenter usersPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.personal_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

        /**
         * 此处需要通过接收的参数初始化该界面的值
         */
        Bundle bundle = this.getIntent().getExtras();
        userId = bundle.getInt("userId");


        if (resourcePresenter == null) {
            presenter = resourcePresenter = new ResourcePresenter();
            presenter.attachView(this);
        }
        if (usersPresenter == null) {
            presenter = usersPresenter = new UsersPresenter();
            presenter.attachView(this);
        }
    }

    @Override
    public void initViews() {
        initPersonalPage();

        if (userId != 0) {
            resourcePresenter.loadUserHead(userId);
            usersPresenter.loadUserById(userId);
        }
    }

    @Override
    public void initListeners() {
        personalSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalSend:
                if (users_info != null) {
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("from_user_head", head);
                    bundle.putSerializable("from_user_info", users_info);
                    startActivity(PostActivity.class, bundle);
                }
                break;
        }
    }

    public void initPersonalPage() {
        personalZoneBackground = findViewById(R.id.personalZoneBackground);
        personalSend = findViewById(R.id.personalSend);
        personalZoneGlass = findViewById(R.id.personalZoneGlass);
        personalHead = findViewById(R.id.personalHead);
        personalWords = findViewById(R.id.personalWords);
        personalCard = findViewById(R.id.personalCard);
        personalBrowse = findViewById(R.id.personalBrowse);
        personalMessage = findViewById(R.id.personalMessage);
        personalVisitor = findViewById(R.id.personalVisitor);


    }


    @Override
    public void loadHead(UserHeadResp userHeadResp) {
        /**
         * 此处初始化用户头像
         */
        byte[] head = userHeadResp.getHead();
        this.head = head;
        personalHead.setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
    }


    @Override
    public void loadUserById(UserTable userTable) {
        /**
         * 此处初始化用户信息
         */
        users_info = userTable;
        userTable.setId(userId);
        personalWords.setText("个性签名\n" + userTable.getPs());
        personalCard.setText("帖子\n" + String.valueOf(userTable.getCardCount()));
        personalBrowse.setText("浏览\n" + String.valueOf(userTable.getBrowseCount()));
        personalMessage.setText("留言\n" + String.valueOf(userTable.getMessageCount()));
        personalVisitor.setText("访客\n" + String.valueOf(userTable.getHomeCount()));
    }

    @Override
    public void updateUserInfo(UserTable userTable) {

    }


    @Override
    public void loadResources(ResourceResp resourceResp) {

    }

    @Override
    public void loadUserInfo(UserResp userResp) {

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


}
