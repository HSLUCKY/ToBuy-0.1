package com.focustech.tobuy.ui.personcenter.activity.personal.function;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.focustech.tobuy.R;
import com.focustech.tobuy.constant.Event;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.community.CardActivity;
import com.focustech.tobuy.ui.personcenter.adapter.community.session.HistoryRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.personal.SimpleHistoryComponent;
import com.focustech.tobuy.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class HistoryActivity extends BaseActivity {

    private List<Integer> data = new ArrayList<>();

    private ImageView back;
    private MyTextView title;
    private ImageView function;
    private RecyclerView historyRV;

    private ImageView cardUserHead;
    private MyTextView cardUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.history_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        for (int i = 0; i < 10; i++) {
            data.add(i);
        }
    }

    @Override
    public void initViews() {
        initHistoryPage();
    }

    @Override
    public void initListeners() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardBack:
                onBackPressed();
                break;
            default:
                if (SimpleHistoryComponent.histories.keySet().contains(v)){
                    Bundle bundle = new Bundle();
                    bundle.putInt("cardId", SimpleHistoryComponent.histories.get(v).getCardId());
                    startActivity(CardActivity.class, bundle);
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
    public void onSuccess() {

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

    public void initHistoryPage(){

        cardUserHead = findViewById(R.id.cardUserHead);
        cardUserHead.setVisibility(View.INVISIBLE);
        cardUserName = findViewById(R.id.cardUserName);
        cardUserName.setText("历史记录");

        back = findViewById(R.id.cardBack);
        title = findViewById(R.id.username);
        function = findViewById(R.id.functionIV);
        historyRV = findViewById(R.id.historyRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        historyRV.setLayoutManager(linearLayoutManager);
        HistoryRecyclerAdapter historyRecyclerAdapter = new HistoryRecyclerAdapter(this, data);
        historyRV.setAdapter(historyRecyclerAdapter);
    }

}
