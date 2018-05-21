package com.focustech.tobuy.ui.personcenter.adapter.community.session;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.card.GetSimpleCardListResp;
import com.focustech.tobuy.bean.table.entity.CardTable;
import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.component.community.SessionComponent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/22.
 */

public class HelpRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CommunityActivity context;
    private SessionComponent sessionComponent;
    public GetSimpleCardListResp data;

    public boolean LOAD_DATA = false;

    public int userIndex = 0;
    public int cardIndex = 0;

    public int now = -1;
    public boolean DATA_CHANGE_FLAG = false;

    public HelpRecyclerAdapter(Context context) {
        this.context = (CommunityActivity) context;
        this.sessionComponent = new SessionComponent(context);
    }

    @Override
    public int getItemCount() {
        return ((CommunityActivity) context).rulers[2].size();
    }

    @Override
    public int getItemViewType(int position) {

        if (now < position) {
            DATA_CHANGE_FLAG = true;
            now = position;
        } else {
            DATA_CHANGE_FLAG = false;
        }

        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            SessionComponent.GuideLayout guideLayout = sessionComponent.getGuideLayout();
            guideLayout.getBgLinearLayout().setBackgroundColor(Color.BLACK);
            return new GuideViewHolder(guideLayout.getZoneLinearLayout(), guideLayout);
        } else if (viewType > 0 && viewType < 2) {
            SessionComponent.TipLayout tipLayout = sessionComponent.getTipLayout();
            tipLayout.getTitleTx().setText("请遵守版区规定");
            tipLayout.getOutLinearLayout().setGravity(Gravity.RIGHT | Gravity.CENTER);
            return new TipViewHolder(tipLayout.getOutLinearLayout(), tipLayout);
        } else {
            int size = data.getCardsListRes().get(userIndex).get(cardIndex).size();
            if (size == 0){
                size++;
            }
            SessionComponent.SimpleCardLayout simpleCardLayout = sessionComponent.getSimpleCardLayout(size);
            return new SimpleCardViewHolder(simpleCardLayout.getOutLinearLayout(), simpleCardLayout);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA) {
            return;
        }
        if (position == ((CommunityActivity) context).rulers[2].size() - 1) {
            LOAD_DATA = true;
        }

        if (position > 1) {
            if (holder instanceof HelpRecyclerAdapter.SimpleCardViewHolder) {
                SimpleCardViewHolder instance = (SimpleCardViewHolder) holder;
                GetSimpleCardListResp.BaseUserInfo baseUserInfo = data.getBaseUserInfos().get(userIndex);
                CardTable cardTable = data.getCardsLists().get(userIndex).get(cardIndex);
                ArrayList<byte[]> images = data.getCardsListRes().get(userIndex).get(cardIndex);
                /**
                 * 用户信息初始化
                 */
                byte[] head = baseUserInfo.getUserHead();
                instance.getSimpleCardLayout().getPromulgatorPic().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
                instance.getSimpleCardLayout().getPromulgatorName().setText(baseUserInfo.getUsername());
                instance.getSimpleCardLayout().getPromulgatorTime().setText(String.valueOf((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(cardTable.getDate())));
                instance.getSimpleCardLayout().setCardId(cardTable.getId());
                instance.getSimpleCardLayout().setType(2);
                /**
                 * 帖子信息初始化
                 */
                instance.getSimpleCardLayout().getTitleTx().setText(cardTable.getTitle());
                ImageView[] showPic = instance.getSimpleCardLayout().getShowPics();
                if (data.getCardsListRes().get(userIndex).get(cardIndex).size() != 0) {
                    for (int i = 0; i < showPic.length && i < images.size(); i++) {
                        showPic[i].setImageBitmap(BitmapFactory.decodeByteArray(images.get(i), 0, images.get(i).length));
                    }
                } else {
                    showPic[0].setImageResource(R.drawable.guide1);
                }
                if (userIndex < data.getBaseUserInfos().size()) {
                    if (cardIndex == data.getCardsLists().get(userIndex).size() - 1) {
                        userIndex++;
                        cardIndex = 0;
                    } else {
                        cardIndex++;
                    }
                }
            }
        }
    }

    public void updata() {
        this.notifyDataSetChanged();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder {
        private SessionComponent.GuideLayout guideLayout;

        public GuideViewHolder(View itemView, SessionComponent.GuideLayout guideLayout) {
            super(itemView);
            this.guideLayout = guideLayout;
        }

        public SessionComponent.GuideLayout getGuideLayout() {
            return guideLayout;
        }
    }

    public class TipViewHolder extends RecyclerView.ViewHolder {
        private SessionComponent.TipLayout tipLayout;

        public TipViewHolder(View itemView, SessionComponent.TipLayout tipLayout) {
            super(itemView);
            this.tipLayout = tipLayout;
        }

        public SessionComponent.TipLayout getTipLayout() {
            return tipLayout;
        }
    }

    public class SimpleCardViewHolder extends RecyclerView.ViewHolder {
        private SessionComponent.SimpleCardLayout simpleCardLayout;

        public SimpleCardViewHolder(View itemView, SessionComponent.SimpleCardLayout simpleCardLayout) {
            super(itemView);
            this.simpleCardLayout = simpleCardLayout;
        }

        public SessionComponent.SimpleCardLayout getSimpleCardLayout() {
            return simpleCardLayout;
        }
    }

}
