package com.focustech.tobuy.ui.personcenter.activity.message;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.message.MessagePresenter;
import com.focustech.tobuy.biz.personcenter.message.view.IMessageViewe;
import com.focustech.tobuy.biz.personcenter.user.UsersPresenter;
import com.focustech.tobuy.biz.personcenter.user.view.IUserView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.Help.message.MessageScrollHelper;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.PersonalActivity;
import com.focustech.tobuy.ui.personcenter.adapter.message.MessageRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.message.SimpleMessageComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 信息页面
 *
 * 头像   标题  /可隐藏
 *
 * RecylerList  Swi
 *
 * 图标   信息标题        日期
 *          信息内容
 *
 * foot
 *
 * 子页面
 * 返回   标题
 *
 * 图标   方框
 *          内容
 *
 *   发送方框   图标
 *
 *
 *
 */

public class MessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IMessageViewe, IUserView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MessageScrollHelper mMessageScrollHelper;
    private MessageRecyclerAdapter messageRecyclerBaseAdapter;

    private ImageView head;



    private MessagePresenter messagePresenter;
    private UsersPresenter usersPresenter;

    /**
     * 尺子
     */
    public ArrayList<Integer> ruler;
    /**
     * 用户id
     */
    public ArrayList<Integer> uId = new ArrayList<>();
    /**
     * 用户下标
     */
    public int userIndex = 0;
    /**
     * 消息下标
     */
    public int messageIndex = 0;
    /**
     * 发送方和信息的map
     */
    public HashMap<Integer, ArrayList<MessageTable>> messageses;
    /**
     * 发送方和信息的头像
     */
    public HashMap<Integer, byte[]> from_users;
    /**
     * 发送方用户信息
     */
    public HashMap<Integer, UserTable> from_users_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.message_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

        if (messagePresenter == null){
            presenter = messagePresenter = new MessagePresenter();
            presenter.attachView(this);
        }
        if (usersPresenter == null){
            presenter = usersPresenter = new UsersPresenter();
            presenter.attachView(this);
        }
        ruler = new ArrayList<>();

        if (ACTIVITY_DATA == null){
            ACTIVITY_DATA = new ArrayList<>();
        }
        if (ACTIVITY_DATA.size() == 0){
            EBApplication.ACTIVITY_CACHE.put(this.getClass().getName(), ACTIVITY_DATA);
            ACTIVITY_DATA.add(from_users);
            ACTIVITY_DATA.add(from_users_info);
            ACTIVITY_DATA.add(messageses);
        }
    }

    @Override
    public void initViews() {
        initMessagePage();
        super.initViews();
        messagePresenter.getUserMessagesList(BaseActivity.userTable.getId(), 0);
    }

    @Override
    public void initListeners() {
        head.setOnClickListener(this);
        super.initListeners();
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.head:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("userId", BaseActivity.userTable.getId());
                startActivity(PersonalActivity.class, bundle2);
                break;
            default:
                if (SimpleMessageComponent.messages.keySet().contains(v)){
                    int userId = SimpleMessageComponent.messages.get(v).userId;
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("from_user_info", from_users_info.get(userId));
                    bundle.putByteArray("from_user_head", from_users.get(userId));
                    startActivity(PostActivity.class, bundle);
                }
        }
        super.onClick(v);
    }

    @Override
    public void setHeader() {
        super.setHeader();
        super.title.setText("消息");
        super.search.setVisibility(View.INVISIBLE);
        super.search.setBackgroundColor(Color.BLACK);
    }


    public void initMessagePage(){

        head = findViewById(R.id.head);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.messageSRL);
        recyclerView = (RecyclerView) findViewById(R.id.messageRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        messageRecyclerBaseAdapter = new MessageRecyclerAdapter(this, recyclerView);
        recyclerView.setAdapter(messageRecyclerBaseAdapter);
        mMessageScrollHelper = new MessageScrollHelper(recyclerView, messageRecyclerBaseAdapter, swipeRefreshLayout, this);
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }


    @Override
    public void onRefresh() {

    }


    @Override
    public void loadMessages(GetMessagesesResp getMessagesesResp) {
        from_users = getMessagesesResp.getFrom_users();
        messageses = getMessagesesResp.getMessageses();

        Iterator<Integer> it = from_users.keySet().iterator();
        while (it.hasNext()){
            int fid = it.next();
            uId.add(fid);
            for(int i = 0; i < messageses.get(fid).size(); i++){
                ruler.add(i);
            }
        }

        UserResp userResp = new UserResp();
        userResp.setUid(BaseActivity.userTable.getId());
        usersPresenter.loadUsersInfo(userResp);
    }



    @Override
    public void loadUserInfo(UserResp userResp) {
        from_users_info = userResp.getUsers();
        messageRecyclerBaseAdapter.LOAD_DATA = false;
        messageRecyclerBaseAdapter.updata();
    }

    @Override
    public void loadUserById(UserTable userTable) {

    }

    @Override
    public void updateUserInfo(UserTable userTable) {


    }


    @Override
    public void sendMessage(SendMessageResp sendMessageResp) {

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