package com.focustech.tobuy.ui.personcenter.activity.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.service.message.SendMessageResp;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.service.resource.UserHeadResp;
import com.focustech.tobuy.bean.service.user.UserResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;
import com.focustech.tobuy.biz.personcenter.message.MessagePresenter;
import com.focustech.tobuy.biz.personcenter.message.view.IMessageViewe;
import com.focustech.tobuy.biz.personcenter.resource.ResourcePresenter;
import com.focustech.tobuy.biz.personcenter.resource.view.IResourceView;
import com.focustech.tobuy.biz.personcenter.user.UsersPresenter;
import com.focustech.tobuy.biz.personcenter.user.view.IUserView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.personal.function.PersonalActivity;
import com.focustech.tobuy.ui.personcenter.adapter.home.GoodsDetailRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyPTEditTextView;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 商品细节页
 */

public class GoodsDetailActivity extends BaseActivity implements IMessageViewe, IResourceView, IUserView {

    private MyTextView goodsDetailTV;

    private ImageView cardBack;
    private ImageView cardUserHead;
    private MyTextView cardUserName;

    private RecyclerView goodsDetailRV;
    private GoodsDetailRecyclerAdapter goodsDetailRecyclerAdapter;

    private LinearLayout goodsDetailRoot;
    private LinearLayout goodsinput;
    private LinearLayout goodsBuy;


    //原始框框中间的一部分用于触发弹出输入框
    private LinearLayout alertinput;
    //输入框
    private MyPTEditTextView alertComment;
    //左边的预留图标
    private LinearLayout alertpic;
    //发送按钮
    private LinearLayout alertSend;
    //弹出窗口根布局
    private LinearLayout alterRoot;

    private boolean haveChanged = false;



    /**
     * 帖子数据和帖子图片数据  需要传送
     */
    public GoodsTable data;
    public ArrayList<byte[]> images = new ArrayList<>();
    private UserTable userTable;
    private byte[] head;


    /**
     *  留言数据    动态加载无需传送
     *
     *  每次绑定数据 uMessageCount(uMark) 用户对应的消息数 - 1
     *  对应的消息为 消息size - uMessageCount(uMark) 从 0 到
     *  当消息数 = 0 uMark++\
     *  这里的uId对应的是用户id
     */
    public HashMap<Integer, byte[]> users = new HashMap<>();
    //用户标记
    public int uMark = 0;

    public HashMap<Integer, ArrayList<MessageTable>>  messageses =  new HashMap<>();

    //每个用户的信息数列表
    public ArrayList<Integer> uMessageCount = new ArrayList<>();
    //用户id表
    public ArrayList<Integer> uId = new ArrayList<>();

    public int goodsId = -1;


    private MessagePresenter messagePresenter;
    private ResourcePresenter resourcePresenter;
    private UsersPresenter usersPresenter;

    /**
     * 长度可调节的尺子，可以根据需求对数据长度进行伸缩
     */
    public ArrayList<Integer> ruler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.goodsdetail_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

        ruler = new ArrayList<>();
        ruler.add(1);
        ruler.add(1);


        Bundle bundle = this.getIntent().getExtras();
        goodsId = bundle.getInt("goodsId");
        int from = bundle.getInt("from");

        if (ACTIVITY_DATA == null){
            ACTIVITY_DATA = new ArrayList<>();
        }

        /**
         * 这里==0是为了区分第一次和第二次进入
         * goodsId != ((GoodsTable)this.ACTIVITY_DATA.get(0)).getId()
         * 是为了区分
         */
        if (this.ACTIVITY_DATA.size() == 0 || goodsId != ((GoodsTable)this.ACTIVITY_DATA.get(0)).getId()) {
            if (from == 0) {
                ArrayList<Object> preActivityData = ((ArrayList<Object>) (EBApplication.ACTIVITY_CACHE.get(HomeActivity.class.getName())));
                ArrayList<GoodsTable> goodsTables = (ArrayList<GoodsTable>) preActivityData.get(0);
                for (int i = 0; i < goodsTables.size(); i++) {
                    if (goodsTables.get(i).getId() == goodsId) {
                        data = goodsTables.get(i);
                        images = ((ArrayList<ArrayList<byte[]>>) preActivityData.get(1)).get(i);
                        this.ACTIVITY_DATA.add(data);
                        this.ACTIVITY_DATA.add(images);
                    }
                }
            }else if (from == 1){
                data = (GoodsTable)bundle.getSerializable("datas");
                images = (ArrayList<byte[]>)bundle.getSerializable("images");
            }
        }else {
            data = (GoodsTable) this.ACTIVITY_DATA.get(0);
            images = (ArrayList<byte[]>) this.ACTIVITY_DATA.get(1);
        }
        if (messagePresenter == null){
            presenter = messagePresenter = new MessagePresenter();
            presenter.attachView(this);
        }
        if (resourcePresenter == null){
            presenter = resourcePresenter = new ResourcePresenter();
            presenter.attachView(this);
        }
        if (usersPresenter == null){
            presenter = usersPresenter = new UsersPresenter();
            presenter.attachView(this);
        }

    }

    @Override
    public void initViews() {
        alertComment = findViewById(R.id.alertComment);
        alertpic = findViewById(R.id.alertpic);
        alertSend = findViewById(R.id.alertSend);
        initGoodsDetailPage();

        messagePresenter.getGoodstMessagesList(goodsId, 0);
        usersPresenter.loadUserById(data.getUserId());
        resourcePresenter.loadUserHead(data.getUserId());
    }

    @Override
    public void initListeners() {
        cardBack.setOnClickListener(this);
        cardUserName.setOnClickListener(this);
        goodsBuy.setOnClickListener(this);
        alertSend.setOnClickListener(this);
        initPopEditListener();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cardBack:
                onBackPressed();
                break;
            case R.id.cardUserName:
            case R.id.cardUserHead:
                if (userTable != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", data.getUserId());
                    startActivity(PersonalActivity.class, bundle);
                }
                break;
            case R.id.alertSend:
                postMessage();
                break;
            default:
                if (MessageComponent.messages.keySet().contains(v)) {
                    int userId = MessageComponent.messages.get(v).getUserId();
                    Bundle bundle = new Bundle();
                    bundle.putInt("userId", userId);
                    startActivity(PersonalActivity.class,bundle);
                }
        }

    }

    public void initGoodsDetailPage() {

        goodsDetailRoot = findViewById(R.id.goodsDetailRoot);

        //FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(EBApplication.screenSize.get("SCREEN_HEIGHT")*0.4));
        //layoutParams.gravity = Gravity.BOTTOM;
        alterRoot = findViewById(R.id.alertRoot);
        //alterRoot.setLayoutParams(layoutParams);
        alertComment = findViewById(R.id.alertComment);
        alertpic = findViewById(R.id.alertpic);
        alertSend = findViewById(R.id.alertSend);
        goodsBuy = findViewById(R.id.goodsBuy);
        goodsinput = findViewById(R.id.goodsinput);


        cardBack = findViewById(R.id.cardBack);
        cardUserHead = findViewById(R.id.cardUserHead);
        cardUserName = findViewById(R.id.cardUserName);

        goodsDetailTV = findViewById(R.id.goodsDetailTV);
        goodsDetailRV = findViewById(R.id.goodsDetailRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        goodsDetailRV.setLayoutManager(linearLayoutManager);
        goodsDetailRecyclerAdapter = new GoodsDetailRecyclerAdapter(this, data, goodsDetailRV);
        goodsDetailRV.setAdapter(goodsDetailRecyclerAdapter);


    }


    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
    }


    public void postMessage(){
        if (!checkInput()){
            return;
        }

        SendMessageResp sendMessageResp = new SendMessageResp();
        sendMessageResp.setType(2);
        sendMessageResp.setFrom_user_id(BaseActivity.userTable.getId());
        sendMessageResp.setTo_id(data.getId());

        MessageTable messageTable = new MessageTable();
        messageTable.setContent(alertComment.getText().toString());
        messageTable.setDate(new Date());
        sendMessageResp.setMessageTable(messageTable);

        messagePresenter.postMessage(sendMessageResp);
    }


    /**
     * 内容
     */
    public boolean checkInput() {

        if (alertComment.getText().toString().trim().length() == 0) {
            ToastUtil.makeText(this, "内容不能为空");
            return false;
        }
        return true;
    }



    @Override
    public void sendMessage(SendMessageResp sendMessageResp) {

        /**
         * 发送成功清空操作
         */
        initSendSuccessStatus();
        /**
         * 重新加载留言列表初始化操作
         */
        initMessagesStatus();
        /**
         * 更新留言列表
         */
        goodsDetailRecyclerAdapter.LOAD_DATA = false;
        goodsDetailRecyclerAdapter.updata();
        /**
         * 重新加载留言
         */
        messagePresenter.getGoodstMessagesList(goodsId, 0);
    }

    public void initMessagesStatus(){
        /**
         * 清空用户和消息缓存以及每个用户信息数、用户id列表
         * 用户标记从0 开始
         */
        messageses.clear();
        users.clear();

        uMessageCount.clear();
        uId.clear();
        uMark = 0;
        ruler.clear();
        ruler.add(1);
        ruler.add(1);
    }
    public void initSendSuccessStatus(){
        ToastUtil.makeText(this, "发送成功");
        goodsDetailTV.setText("");
        alertComment.setText("");
        alterRoot.setVisibility(View.GONE);
    }





    @Override
    public void loadMessages(GetMessagesesResp getMessagesesResp) {
        users = getMessagesesResp.getFrom_users();
        HashMap<Integer, ArrayList<MessageTable>> msgses = getMessagesesResp.getMessageses();
        Iterator<Integer> it = msgses.keySet().iterator();
        while (it.hasNext()){
            int fId = it.next();
            uId.add(fId);
            messageses.put(fId, getMessagesesResp.getMessageses().get(fId));
            uMessageCount.add(getMessagesesResp.getMessageses().get(fId).size());
        }

        for (int i = 0; i < uMessageCount.size(); i++){
            for (int j = 0; j < uMessageCount.get(i); j++){
                ruler.add(1);
            }
        }
        goodsDetailRecyclerAdapter.LOAD_DATA = false;
        goodsDetailRecyclerAdapter.updata();
    }


    @Override
    public void loadUserById(UserTable userTable) {
        this.userTable = userTable;
        this.cardUserName.setText(userTable.getUsername());
        data.setUserId(userTable.getId());
    }

    @Override
    public void updateUserInfo(UserTable userTable) {

    }

    @Override
    public void loadHead(UserHeadResp userHeadResp) {
        byte[] head = userHeadResp.getHead();
        this.head = head;

        this.cardUserHead.setImageBitmap(BitmapFactory.decodeByteArray(head, 0,head.length));
    }























    @Override
    public void onSuccess() {
    }
    @Override
    public void onError(String errorMsg, String code) {

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


    public void initPopEditListener() {

        goodsDetailRV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (alterRoot.getVisibility() == View.VISIBLE) {
                    alertComment.clearFocus();
                    goodsDetailTV.setText(alertComment.getText().toString());
                    alterRoot.setVisibility(View.GONE);
                    hideSoftKeyboard();
                }
                return false;
            }
        });

        goodsinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alterRoot.getVisibility() == View.GONE) {
                    alertComment.setText(goodsDetailTV.getText().toString());
                    alterRoot.setVisibility(View.VISIBLE);
                }
            }
        });
        goodsBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alterRoot.getVisibility() == View.GONE) {
                    alertComment.setText(goodsDetailTV.getText().toString());
                    alterRoot.setVisibility(View.VISIBLE);
                }
            }
        });
        goodsDetailRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                goodsDetailRoot.getWindowVisibleDisplayFrame(r);
                boolean isOpen = r.bottom < EBApplication.screenSize.get("SCREEN_HEIGHT");
                if (isOpen) {
                    goodsDetailRoot.setPadding(0, 0, 0,
                            EBApplication.screenSize.get("SCREEN_HEIGHT") / 5);
                    haveChanged = true;
                } else {
                    if (haveChanged) {
                        goodsDetailRoot.setPadding(0, 0, 0, 0);
                    }
                }
            }
        });
        alertComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        goodsDetailTV.setText(alertComment.getText().toString());
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                        alterRoot.setVisibility(View.GONE);
                        postMessage();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void loadResources(ResourceResp resourceResp) {

    }

    @Override
    public void loadUserInfo(UserResp userResp) {

    }

}
