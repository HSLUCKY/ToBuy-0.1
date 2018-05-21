package com.focustech.tobuy.ui.personcenter.activity.publish;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.card.PublishCardResp;
import com.focustech.tobuy.bean.service.tag.TagsResp;
import com.focustech.tobuy.bean.table.entity.CardTable;
import com.focustech.tobuy.bean.table.entity.TagTable;
import com.focustech.tobuy.biz.personcenter.publish.PublishPresenter;
import com.focustech.tobuy.biz.personcenter.publish.view.IPublishView;
import com.focustech.tobuy.biz.personcenter.tag.TagsPresenter;
import com.focustech.tobuy.biz.personcenter.tag.view.ITagView;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.adapter.publish.PublishRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.publish.PublishComponent;
import com.focustech.tobuy.util.ToastUtil;
import com.focustech.tobuy.view.MyCheckBoxView;
import com.focustech.tobuy.view.MyTextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 社区帖子
 */

public class PublishActivity extends BaseActivity implements IPublishView, ITagView {

    private ArrayList<TagTable> data = new ArrayList<>();

    private RecyclerView publishRecyclerView;
    private PublishRecyclerAdapter publishRecyclerAdapter;
    private MyTextView titleTx;
    private ImageView back;

    /**
     * 发送功能标签
     */
    private ImageView publishinputPic;
    private ImageView publishinputSend;
    /**
     * 帖子内容
     */
    private EditText publishTitleET;
    private int sessionType = 0;

    /**
     * 打开页面加载标签
     */
    private TagsPresenter tagsPresenter;

    /**
     * 发布帖子
     */
    private PublishPresenter publishPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.publish_activity);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initData() {

        if (tagsPresenter == null) {
            presenter = tagsPresenter = new TagsPresenter();
            presenter.attachView(this);
        }
        if (publishPresenter == null){
            presenter = publishPresenter = new PublishPresenter();
            presenter.attachView(this);
        }

        ACTIVITY_DATA = (ArrayList<Object>)EBApplication.ACTIVITY_CACHE.get(this.getClass().getName());
        if (ACTIVITY_DATA == null){
            ACTIVITY_DATA = new ArrayList<>();
        }
        if (ACTIVITY_DATA.size() == 0){
            ACTIVITY_DATA.add(data);
            EBApplication.ACTIVITY_CACHE.put(this.getClass().getName(), ACTIVITY_DATA);
        }else {
            data = (ArrayList<TagTable>) ACTIVITY_DATA.get(0);
        }

    }

    @Override
    public void initViews() {
        initPublishPart();
        if (data == null || data.size() == 0){
            tagsPresenter.getAllTags();
        }
    }

    @Override
    public void initListeners() {
        publishinputPic.setOnClickListener(this);
        publishinputSend.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publishinputPic:
                ToastUtil.makeText(this, "表情图片");
                break;
            case R.id.publishinputSend:
                postCard();
                break;
            case R.id.messageBack:
                super.onBackPressed();
                break;
            default:
                if (MyCheckBoxView.getViewIds().keySet().contains(v)) {
                    MyCheckBoxView.getViewIds().get(v).switchStatus();
                }
                if (PublishComponent.sessions.keySet().contains(v)) {
                    sessionType = switchParts(v);
                }
        }
    }

    @Override
    public void onEventMainThread(Event event) {
        super.onEventMainThread(event);
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

    public void initPublishPart() {
        publishRecyclerView = findViewById(R.id.publishRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        publishRecyclerAdapter = new PublishRecyclerAdapter(this, data);
        publishRecyclerView.setLayoutManager(linearLayoutManager);
        publishRecyclerView.setAdapter(publishRecyclerAdapter);

        titleTx = findViewById(R.id.friendTitle);
        titleTx.setText("发帖");

        publishinputPic = findViewById(R.id.publishinputPic);
        publishinputSend = findViewById(R.id.publishinputSend);

        back = findViewById(R.id.messageBack);

        publishTitleET = findViewById(R.id.publishTitleET);

    }

    public int switchParts(View v) {
        int key = PublishComponent.sessions.get(v);
        sessionType = key;
        Iterator<LinearLayout> iterator = PublishComponent.sessions.keySet().iterator();
        while (iterator.hasNext()) {
            LinearLayout temp = iterator.next();
            if (PublishComponent.sessions.get(temp) != key) {
                temp.setBackgroundColor(Color.WHITE);
            } else {
                temp.setBackgroundColor(0xFFFF9999);
            }
        }

        return key;
    }


    public void postCard() {
        if (!checkInput()) {
            return;
        }
        /**
         * 初始化响应体
         * 获取当前APP用户
         */
        PublishCardResp publishCardResp = new PublishCardResp();
        publishCardResp.setId(BaseActivity.userTable.getId());

        /**
         * 初始化帖子
         *  标题，内容，日期，持有者id，版区，标签列表
         */
        CardTable cardTable = new CardTable();
        cardTable.setTitle(publishTitleET.getText().toString());
        cardTable.setContent(publishRecyclerAdapter.editLayout.getPtEditText().getText().toString());
        cardTable.setDate(new Date());
        cardTable.setUserId(BaseActivity.userTable.getId());
        cardTable.setType(sessionType);

        publishCardResp.setTagIds(new ArrayList<Integer>());
        ArrayList<MyCheckBoxView> tagIds = publishRecyclerAdapter.myCheckBoxViews;
        for (int i = 0; i < tagIds.size(); i++) {
            if (tagIds.get(i).isCHECK_FLAG()) {
                publishCardResp.getTagIds().add(tagIds.get(i).getTagId());
            }
        }
        publishCardResp.setCardTable(cardTable);

        publishPresenter.publishCard(publishCardResp);
    }


    /**
     * 检验标题
     * 内容
     */
    public boolean checkInput() {

        if (publishTitleET.getText().length() == 0) {
            ToastUtil.makeText(this, "标题不能为空");
            return false;
        }
        if (publishRecyclerAdapter.editLayout.getPtEditText().getText().length() == 0) {
            ToastUtil.makeText(this, "内容不能为空");
            return false;
        }
        return true;
    }


    @Override
    public void loadAllTags(TagsResp tagsResp) {
        ArrayList<TagTable> tags = tagsResp.getTags();
        for(int i = 0; i < tags.size(); i++){
            data.add(tags.get(i));
        }
        publishRecyclerAdapter.LOAD_DATA = false;
        publishRecyclerAdapter.updata();
    }

    @Override
    public void onSuccess() {
        /**
         * 上传完毕清空
         */
        ToastUtil.makeText(this, "发送成功");
        publishTitleET.setText("");
        publishRecyclerAdapter.editLayout.getPtEditText().setText("");
        sessionType= 0;


        /**
         * 跳转操作
         * 清空社区缓存
         * 跳转到社区界面
         */
        EBApplication.ACTIVITY_CACHE.put(CommunityActivity.class.getName(), null);
        startActivity(CommunityActivity.class, null);

    }

}
