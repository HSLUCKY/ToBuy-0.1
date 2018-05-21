package com.focustech.tobuy.ui.personcenter.activity.community;

import android.content.Context;
import android.graphics.BitmapFactory;
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
import com.focustech.tobuy.bean.service.card.GetSimpleCardListResp;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.table.entity.CardTable;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.message.MessagePresenter;
import com.focustech.tobuy.biz.personcenter.message.view.IMessageViewe;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.Help.community.CardScrollHelper;
import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.PersonalActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.CardRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.community.CardComponent;
import com.focustech.tobuy.util.PhoneParameterUtil;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 商品帖子
 */

public class CardActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,IMessageViewe {

    private LinearLayout cardRoot;
    private LinearLayout inputBottom;
    private boolean haveChanged = false;

    private SwipeRefreshLayout cardSR;
    private RecyclerView cardRV;
    private CardScrollHelper cardScrollHelper;
    CardRecyclerAdapter cardRecyclerAdapter;

    private ImageView cardBack;
    private ImageView cardUserHead;
    private MyTextView cardUserName;

    private ImageView expression;
    private ImageView camera;
    private ImageView album;
    private ImageView voice;

    private LinearLayout sendMessage;
    private EditText messageContent;

    public int cardId = -1;
    public int type = -1;

    //========================================================================
    /**
     * 发帖人信息
     * 包含头像和基本的用户信息
     */
    public GetSimpleCardListResp.BaseUserInfo baseUserInfo;
    /**
     * 帖子信息和每张帖子对应的图片
     */
    public CardTable cardTable;
    public ArrayList<byte[]> images = new ArrayList<>();
    /**
     * frIndex  第frIndex个用户
     * seIndex  第frIndex个用户的第seIndex个帖子
     */
    public int frIndex;
    public int seIndex;

    /**
     * 列表长度尺子，可以方便的自由变换长度
     */
    public ArrayList<Integer> ruler;

    /**
     * 帖子消息
     *  计算每个用户id对应的列表存入uMessageCount
     *  uMessageCount的顺序和uId的顺序对应
     *  messageses是用来存储从服务器传来的信息数据的
     */
    public HashMap<Integer, ArrayList<MessageTable>> messageses = new HashMap<>();
    //每个用户的信息数列表
    public ArrayList<Integer> uMessageCount = new ArrayList<>();

    //用户id表
    public ArrayList<Integer> uId = new ArrayList<>();
    public HashMap<Integer, byte[]> users = new HashMap<>();
    //用户标记
    public int uMark = 0;

    private MessagePresenter messagePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.card_activity);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void initData() {
        ruler = new ArrayList<>();

        Bundle bundle = this.getIntent().getExtras();
        cardId = bundle.getInt("cardId");
        type = bundle.getInt("type");

        if (ACTIVITY_DATA == null){
            ACTIVITY_DATA = new ArrayList<>();
        }
        if(ACTIVITY_DATA.size() == 0){
            /**
             * 不同的type对应不同的版区
             */
            GetSimpleCardListResp getSimpleCardListResp = ((GetSimpleCardListResp)((ArrayList<Object>) (EBApplication.ACTIVITY_CACHE.get(CommunityActivity.class.getName()))).get(type));
            ArrayList<ArrayList<CardTable>> cardsLists = getSimpleCardListResp.getCardsLists();
            for(int i = 0; i < cardsLists.size(); i++){
                int j = 0;
                for (; j < cardsLists.get(i).size(); j++){
                    /**
                     * 此处目的，得到发帖用户
                     * 得到用户后可以通过用户的index作为帖子资源表的第一重下标
                     * 帖子的index作为资源id的第二重下标
                     * 通过传过来的帖子id遍历前一个页面的缓存数据得到帖子id对应的用户id
                     *
                     */
                    if (cardsLists.get(i).get(j).getId() == cardId){
                        /**
                         * 此处初始化帖子在缓存列表中的下标
                         */
                        frIndex = i;
                        seIndex = j;
                        baseUserInfo = getSimpleCardListResp.baseUserInfos.get(i);
                        cardTable = cardsLists.get(i).get(j);
                        images = getSimpleCardListResp.getCardsListRes().get(i).get(j);
                        break;
                    }
                }
                if (cardsLists.get(i).size() != j){
                    break;
                }
            }
            //ACTIVITY_DATA.add()

        }else {

        }

        if (messagePresenter == null){
            presenter = messagePresenter = new MessagePresenter();
            presenter.attachView(this);
        }

    }
    @Override
    public void initViews() {
        initCardPage();
        messagePresenter.getCardtMessagesList(cardId, type);
    }
    @Override
    public void initListeners() {
        cardSR.setOnRefreshListener(this);
        cardBack.setOnClickListener(this);

        voice.setOnClickListener(this);
        album.setOnClickListener(this);
        camera.setOnClickListener(this);
        expression.setOnClickListener(this);

        cardUserHead.setOnClickListener(this);
        cardUserName.setOnClickListener(this);
        sendMessage.setOnClickListener(this);

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

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                    }
                    postMessage();
            }
        });

        setListenerToRootView();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardBack:
                startActivity(CommunityActivity.class, null);
                break;
            case R.id.cardUserHead:
            case R.id.cardUserName:
                Bundle bundle3 = new Bundle();
                bundle3.putInt("userId", cardTable.getUserId());
                startActivity(PersonalActivity.class, bundle3);
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
            default:
                if (CardComponent.users.keySet().contains(v)){
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", cardTable.getUserId());
                    startActivity(PersonalActivity.class, bundle);
                }
                if (CardComponent.message.keySet().contains(v)){
                    int userId = CardComponent.message.get(v).getUserId();
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", userId);
                    startActivity(PersonalActivity.class, bundle);
                }
        }
    }



    public void initCardPage(){
        inputBottom = findViewById(R.id.inputBottom);
        cardRoot = findViewById(R.id.cardRoot);
        cardBack = findViewById(R.id.cardBack);
        cardUserHead = findViewById(R.id.cardUserHead);
        byte[] head = baseUserInfo.getUserHead();
        cardUserHead.setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
        cardUserName = findViewById(R.id.cardUserName);
        cardUserName.setText(baseUserInfo.getUsername());

        sendMessage = findViewById(R.id.sendMessage);
        messageContent = findViewById(R.id.messageContent);

        voice = findViewById(R.id.voice);
        album = findViewById(R.id.album);
        camera = findViewById(R.id.camera);
        expression = findViewById(R.id.expression);

        cardSR = findViewById(R.id.cardSR);
        cardRV = findViewById(R.id.cardRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        cardRV.setLayoutManager(linearLayoutManager);
        cardRecyclerAdapter = new CardRecyclerAdapter(this);
        cardRV.setAdapter(cardRecyclerAdapter);

        cardScrollHelper = new CardScrollHelper(this, cardRecyclerAdapter, cardRV, cardSR);

    }

    @Override
    public void loadMessages(GetMessagesesResp getMessagesesResp) {
        users = getMessagesesResp.getFrom_users();

        Iterator<Integer> it = getMessagesesResp.getMessageses().keySet().iterator();
        while (it.hasNext()){
            int fId = it.next();
            uId.add(fId);
            messageses.put(fId, getMessagesesResp.getMessageses().get(fId));
            uMessageCount.add(getMessagesesResp.getMessageses().get(fId).size());
        }
        while (it.hasNext()){
            int fId = it.next();

            ArrayList<MessageTable> messageTables = getMessagesesResp.getMessageses().get(fId);
            for(int i = 0; i < messageTables.size(); i++){
                uId.add(fId);
            }
        }

        for (int i = 0; i < uMessageCount.size(); i++){
            for (int j = 0; j < uMessageCount.get(i); j++){
                ruler.add(1);
            }
        }

        cardRecyclerAdapter.LOAD_DATA = false;
        cardRecyclerAdapter.updata();
    }

    @Override
    public void sendMessage(SendMessageResp sendMessageResp) {
        messageContent.setText("");

        initCardMsgList();


        cardRecyclerAdapter.LOAD_DATA = false;
        cardRecyclerAdapter.updata();
        messagePresenter.getCardtMessagesList(cardId, type);
    }

    public void  postMessage(){
        if (!checkInput()){
            return;
        }
        SendMessageResp sendMessageResp = new SendMessageResp();
        sendMessageResp.setFrom_user_id(BaseActivity.userTable.getId());
        sendMessageResp.setTo_id(cardId);
        sendMessageResp.setType(1);

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

    public void initCardMsgList(){
        users.clear();
        uId.clear();
        messageses.clear();
        uMessageCount.clear();
        uMark = 0;
        ruler.clear();
    }





















    @Override
    public void onRefresh() {

    }


    public void  setListenerToRootView() {
        cardRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                cardRoot.getWindowVisibleDisplayFrame(r);
                boolean isOpen = PhoneParameterUtil.isKeyboardShown(cardRoot.getRootView());
                if (isOpen){
                    inputBottom.setVisibility(View.GONE);
                    cardRoot.setPadding(0, 0, 0, r.bottom - 120);
                    haveChanged = true;
                }else {
                    if (haveChanged){
                        inputBottom.setVisibility(View.VISIBLE);
                        cardRoot.setPadding(0, 0, 0, 0);
                    }
                }
            }
        });
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
