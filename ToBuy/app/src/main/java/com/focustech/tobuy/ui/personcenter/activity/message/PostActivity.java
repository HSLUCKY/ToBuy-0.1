package com.focustech.tobuy.ui.personcenter.activity.message;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.message.MessagePresenter;
import com.focustech.tobuy.biz.personcenter.message.view.IMessageViewe;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.Help.message.PostScrollHelper;
import com.focustech.tobuy.ui.personcenter.adapter.message.PostRecyclerAdapter;
import com.focustech.tobuy.util.PhoneParameterUtil;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 此页面第一步加载和发送对象所有的信息
 * 第二步发送信息并更新列表
 * Created by Administrator on 2018/4/20.
 */

public class PostActivity extends BaseActivity implements IMessageViewe {


    private ImageView expression;
    private ImageView camera;
    private ImageView album;
    private ImageView voice;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PostScrollHelper mPostScrollHelper;
    private PostRecyclerAdapter postRecyclerBaseAdapter;


    private LinearLayout editPadding;
    private LinearLayout postRoot;
    private LinearLayout inputBottom;
    private EditText messageContent;
    private ImageView messageBack;
    private LinearLayout sendMessage;

    private boolean haveChanged = false;


    private MyTextView friendTitle;

    /**
     * 接收方头像
     */
    public byte[] f_user_head;
    /**
     * 接收方用户信息
     */
    public UserTable f_users_info;

    /**
     * 发送方
     * 也就是app用户自己
     */
    public int m_id = BaseActivity.userTable.getId();
    /**
     * 接收方
     */
    public int t_id = -1;

    /**
     * 发送方和接收方排序之后的信息和用户id
     */
    public ArrayList<MessageTable> messages = new ArrayList<>();
    public ArrayList<Integer> ids = new ArrayList<>();

    private MessagePresenter messagePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.post_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        Bundle bundle = this.getIntent().getExtras();
        f_user_head = bundle.getByteArray("from_user_head");
        f_users_info = (UserTable)bundle.getSerializable("from_user_info");

        if (messagePresenter == null){
            presenter = messagePresenter = new MessagePresenter();
            presenter.attachView(this);
        }
        t_id = f_users_info.getId();
        messagePresenter.getUserMessagesListByFrom(BaseActivity.userTable.getId(), t_id);
    }

    @Override
    public void initViews() {
        initPostPage();
    }
    @Override
    public void initListeners() {
        messageBack.setOnClickListener(this);
        editPadding.setOnClickListener(this);
        messageContent.setOnClickListener(this);
        sendMessage.setOnClickListener(this);

        voice.setOnClickListener(this);
        album.setOnClickListener(this);
        camera.setOnClickListener(this);
        expression.setOnClickListener(this);

        messageContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        postMessage();
                    }
                    return true;
                }
                return false;
            }
        });

        setListenerToRootView();
    }
    public void  setListenerToRootView() {
        postRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                postRoot.getWindowVisibleDisplayFrame(r);
                boolean isOpen = PhoneParameterUtil.isKeyboardShown(postRoot.getRootView());
                if (isOpen){
                    inputBottom.setVisibility(View.GONE);
                    postRoot.setPadding(0, 0, 0, r.bottom - 120);
                    haveChanged = true;
                }else {
                    if (haveChanged){
                        inputBottom.setVisibility(View.VISIBLE);
                        postRoot.setPadding(0, 0, 0, 0);
                    }
                }
            }
        });
    }
    public void initPostPage() {

        friendTitle = findViewById(R.id.friendTitle);
        friendTitle.setText(f_users_info.getUsername());

        voice = findViewById(R.id.voice);
        album = findViewById(R.id.album);
        camera = findViewById(R.id.camera);
        expression = findViewById(R.id.expression);

        messageContent = findViewById(R.id.messageContent);
        messageContent.clearFocus();
        messageBack = findViewById(R.id.messageBack);
        editPadding = findViewById(R.id.editPadding);
        postRoot = findViewById(R.id.postRoot);
        inputBottom = findViewById(R.id.inputBottom);
        sendMessage = findViewById(R.id.sendMessage);

        swipeRefreshLayout = findViewById(R.id.postSRL);

        recyclerView = findViewById(R.id.postRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        postRecyclerBaseAdapter = new PostRecyclerAdapter(this);
        recyclerView.setAdapter(postRecyclerBaseAdapter);

        mPostScrollHelper = new PostScrollHelper(recyclerView, postRecyclerBaseAdapter, swipeRefreshLayout, this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messageBack:
                onBackPressed();
                break;
            case R.id.voice:
                ToastUtil.makeText(this, "语音");
                break;
            case R.id.album:
                ToastUtil.makeText(this, "相册");
                break;
            case R.id.camera:
                ToastUtil.makeText(this, "相机");
                break;
            case R.id.expression:
                ToastUtil.makeText(this, "表情");
                break;
            case R.id.sendMessage:
                postMessage();
                break;
        }
    }


    @Override
    public void loadMessages(GetMessagesesResp getMessagesesResp) {
        ArrayList<MessageTable> m_messages = getMessagesesResp.getMessageses().get(m_id);
        ArrayList<MessageTable> t_messages = getMessagesesResp.getMessageses().get(t_id);

        messages.addAll(m_messages);
        messages.addAll(t_messages);

        for (int i = 0 ; i < m_messages.size(); i++){
            ids.add(m_id);
        }

        for (int i = 0 ; i < t_messages.size(); i++){
            ids.add(t_id);
        }

        for(int i = 0; i < messages.size() - 1; i++){
            for (int j = 0; j < messages.size()-i-1; j++){
                if (messages.get(j).getDate().getTime() > messages.get(j+1).getDate().getTime()){

                    MessageTable tempMsg = messages.get(j);
                    messages.set(j, messages.get(j+1));
                    messages.set(j+1, tempMsg);

                    Integer tempId = ids.get(j);
                    ids.set(j, ids.get(j+1));
                    ids.set(j+1, tempId);
                }
            }
        }

        postRecyclerBaseAdapter.LOAD_DATA = false;
        postRecyclerBaseAdapter.updata();
    }

    @Override
    public void sendMessage(SendMessageResp sendMessageResp) {

        messageContent.setText("");
        messages.clear();
        ids.clear();
        postRecyclerBaseAdapter.LOAD_DATA = false;
        postRecyclerBaseAdapter.updata();
        messagePresenter.getUserMessagesListByFrom(BaseActivity.userTable.getId(), t_id);
    }


    public void postMessage(){
        if (!checkInput()){
            return;
        }

        SendMessageResp sendMessageResp = new SendMessageResp();

        sendMessageResp.setFrom_user_id(BaseActivity.userTable.getId());
        sendMessageResp.setTo_id(f_users_info.getId());
        sendMessageResp.setType(3);

        MessageTable messageTable = new MessageTable();
        messageTable.setDate(new Date());
        messageTable.setContent(messageContent.getText().toString());

        sendMessageResp.setMessageTable(messageTable);

        messagePresenter.postMessage(sendMessageResp);
    }

    /**
     * 内容
     */
    public boolean checkInput() {

        if (messageContent.getText().toString().trim().length() == 0) {
            ToastUtil.makeText(this, "内容不能为空");
            return false;
        }
        return true;
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
    public void setHeader() {}

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
