package com.focustech.tobuy.ui.personcenter.adapter.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.ui.personcenter.activity.home.GoodsDetailActivity;
import com.focustech.tobuy.ui.personcenter.component.home.GoodsDetailComponent;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GoodsDetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private GoodsTable data;
    private GoodsDetailComponent goodsDetailComponent;


    public boolean DATA_CHANGE_FLAG = true;
    public int now = -1;

    public boolean LOAD_DATA;

    public GoodsDetailRecyclerAdapter(Context context, GoodsTable data, RecyclerView goodsDetailRV) {
        this.context = context;
        this.data = data;
        goodsDetailComponent = new GoodsDetailComponent(context);
    }

    @Override
    public int getItemCount() {
        return ((GoodsDetailActivity)context).ruler.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            GoodsDetailComponent.GoodsShowLayout goodsShowLayout = goodsDetailComponent.getGoodsShowLayout(((GoodsDetailActivity) context).images.size());
            return new GoodsShowViewHolder(goodsShowLayout.getOutLinearLayout(), goodsShowLayout);
        } else if (viewType == 1) {
            GoodsDetailComponent.IntroduceGuide introduceGuide = goodsDetailComponent.getIntroduceGuide();
            return new IntroduceGuideViewHolder(introduceGuide.getGuideLinearLayout(), introduceGuide);
        } else {
            GoodsDetailComponent.IntroduceLayout introduceLayout = goodsDetailComponent.getIntroduceLayout();
            return new IntroduceViewHolder(introduceLayout.getMessageComponent().getMessageLayout(), introduceLayout);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (LOAD_DATA){
            return;
        }

        if (position == 0) {
            if (holder instanceof GoodsShowViewHolder) {

                ArrayList<byte[]> images = ((GoodsDetailActivity) context).images;
                ImageView[] imageViews = ((GoodsShowViewHolder) holder).getGoodsShowLayout().getShowPics();
                for (int i = 0; i < imageViews.length && i < images.size(); i++) {
                    byte[] image = images.get(i);
                    if (image != null) {
                        imageViews[i].setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                    }
                }

                GoodsShowViewHolder temp = ((GoodsShowViewHolder) holder);
                temp.getGoodsShowLayout().getDateTx().setText("\t\t\t\t\t\t\t\t" + String.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(data.getDate())));
                temp.getGoodsShowLayout().getTitleTx().setText(data.getTitle());
                temp.getGoodsShowLayout().getPriceTx().setText(String.valueOf(data.getPrice()) + "￥");
                temp.goodsShowLayout.getGoodsTipTx().setText(data.getTip());
            }
        }
        if (position > 1){
            if (!(holder instanceof IntroduceViewHolder)){
                return;
            }
            IntroduceViewHolder ivhTemp = ((IntroduceViewHolder)holder);
            GoodsDetailActivity instance = ((GoodsDetailActivity)context);
            //当前用户消息数从第uMark个id为users.get(uMark)的用户读取消息
            int msgSize = instance.messageses.get(instance.uId.get(instance.uMark)).size();
            //当前用户消息未读取数
            int msgCount = instance.uMessageCount.get(instance.uMark);
            //计算出当前读取到的消息下标
            int msgIndex = msgSize - msgCount;

            if (msgIndex < msgSize){
                byte[] head = instance.users.get(instance.uId.get(instance.uMark));

                MessageComponent messageComponent = ivhTemp.getIntroduceLayout().getMessageComponent();
                messageComponent.getHeadPic().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));

                int id = instance.uId.get(instance.uMark);
                System.out.println(id);
                messageComponent.setUserId(id);

                MessageTable messageTable =instance.messageses.get(instance.uId.get(instance.uMark)).get(msgIndex);
                messageComponent.getMessageContent().setText(messageTable.getContent());
                String dataStr = String.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(messageTable.getDate()));
                System.out.println(dataStr);
                messageComponent.getMessageTime().setText(dataStr);

                /**
                 * 消息数--    设置新的消息数
                 */
                msgCount--;
                instance.uMessageCount.set(instance.uMark, msgCount);
                /**
                 * 当msgSize - msgCount = msgSize的时候下标越界消息读完用户++
                 */
                if (msgCount == 0){
                    instance.uMark++;
                }
            }
        }
        if (position == ((GoodsDetailActivity)context).ruler.size()  - 1){
            LOAD_DATA = true;
        }

    }

    public void updata() {
        notifyDataSetChanged();
    }

    class GoodsShowViewHolder extends RecyclerView.ViewHolder {

        GoodsDetailComponent.GoodsShowLayout goodsShowLayout;

        public GoodsShowViewHolder(View itemView, GoodsDetailComponent.GoodsShowLayout goodsShowLayout) {
            super(itemView);
            this.goodsShowLayout = goodsShowLayout;
        }

        public GoodsDetailComponent.GoodsShowLayout getGoodsShowLayout() {
            return goodsShowLayout;
        }
    }

    class IntroduceGuideViewHolder extends RecyclerView.ViewHolder {

        private GoodsDetailComponent.IntroduceGuide introduceGuide;

        public IntroduceGuideViewHolder(View itemView, GoodsDetailComponent.IntroduceGuide introduceGuide) {
            super(itemView);
            this.introduceGuide = introduceGuide;
        }

        public GoodsDetailComponent.IntroduceGuide getIntroduceGuide() {
            return introduceGuide;
        }
    }

    class IntroduceViewHolder extends RecyclerView.ViewHolder {

        private GoodsDetailComponent.IntroduceLayout introduceLayout;

        public IntroduceViewHolder(View itemView, GoodsDetailComponent.IntroduceLayout introduceLayout) {
            super(itemView);
            this.introduceLayout = introduceLayout;
        }

        public GoodsDetailComponent.IntroduceLayout getIntroduceLayout() {
            return introduceLayout;
        }
    }
}
