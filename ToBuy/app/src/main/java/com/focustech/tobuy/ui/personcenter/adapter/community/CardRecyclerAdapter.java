package com.focustech.tobuy.ui.personcenter.adapter.community;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.bean.table.entity.CardTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.ui.personcenter.activity.community.CardActivity;
import com.focustech.tobuy.ui.personcenter.activity.community.CommunityActivity;
import com.focustech.tobuy.ui.personcenter.component.community.CardComponent;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

public class CardRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CardActivity context;
    private CardComponent cardComponent;

    public boolean DATA_CHANGE_FLAG = true;
    public int now = -1;

    public boolean LOAD_DATA;

    public CardRecyclerAdapter(Context context) {
        this.context = (CardActivity)context;
        this.cardComponent = new CardComponent(context);
    }

    @Override
    public int getItemCount() {
        return (context).ruler.size() + 1;
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
            CardComponent.DetailCard detailCard = cardComponent.getDetailCard(context.images.size());
            return new CardViewHolder(detailCard.getOutLinearLayout(), detailCard);
        } else {
            MessageComponent reply = cardComponent.getReply(context.uId.get(context.uMark));
            return new ReplyViewHolder(reply.getMessageLayout(), reply);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA) {
            return;
        }
        if (position == 0) {

            if (holder instanceof CardViewHolder) {
                CardViewHolder cardViewHolder = ((CardViewHolder) holder);
                CardActivity instance = ((CardActivity) context);

                /**
                 * 初始化发帖人信息以及发帖日期
                 */
                byte[] head = instance.baseUserInfo.getUserHead();
                cardViewHolder.getDetailCard().getHead().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
                cardViewHolder.getDetailCard().getName().setText(instance.baseUserInfo.getUsername());
                cardViewHolder.getDetailCard().getDate().setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(instance.cardTable.getDate()));

                /**
                 * 初始化帖子信息
                 */
                CardTable cardTable = instance.cardTable;
                cardViewHolder.getDetailCard().getTitleTx().setText(cardTable.getTitle());
                cardViewHolder.getDetailCard().getContentTx().setText(cardTable.getContent());
                for (int i = 0; i < instance.images.size(); i++){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(instance.images.get(i), 0, instance.images.get(i).length);
                    cardViewHolder.getDetailCard().getPics()[i].setImageBitmap(bitmap);
                    cardViewHolder.getDetailCard().getPics()[i].setScaleType(ImageView.ScaleType.FIT_XY);
                }

            }

        }
        if (position > 0){
            if (!(holder instanceof ReplyViewHolder)){
                return;
            }
            ReplyViewHolder replyViewHolder = ((ReplyViewHolder)holder);
            CardActivity instance = ((CardActivity)context);

            //当前用户消息数从第uMark个id为users.get(uMark)的用户读取消息
            int msgSize = instance.messageses.get(instance.uId.get(instance.uMark)).size();
            //当前用户消息未读取数
            int msgCount = instance.uMessageCount.get(instance.uMark);
            //计算出当前读取到的消息下标
            int msgIndex = msgSize - msgCount;

            if (msgIndex < msgSize){
                byte[] head = instance.users.get(instance.uId.get(instance.uMark));
                MessageComponent messageComponent = replyViewHolder.getReply();

                int id = instance.uId.get(instance.uMark);
                System.out.println(id);
                messageComponent.setUserId(id);

                messageComponent.getHeadPic().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
                MessageTable messageTable =instance.messageses.get(instance.uId.get(instance.uMark)).get(msgIndex);
                messageComponent.getMessageContent().setText(messageTable.getContent());
                messageComponent.getMessageTime().setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(messageTable.getDate()));

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
        if (position == ((CardActivity)context).ruler.size()){
            LOAD_DATA = true;
        }
    }

    public void updata() {
        this.notifyDataSetChanged();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        private CardComponent.DetailCard detailCard;

        public CardViewHolder(View itemView, CardComponent.DetailCard detailCard) {
            super(itemView);
            this.detailCard = detailCard;
        }

        public CardComponent.DetailCard getDetailCard() {
            return detailCard;
        }
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder {
        private MessageComponent reply;

        public ReplyViewHolder(View itemView, MessageComponent reply) {
            super(itemView);
            this.reply = reply;
        }

        public MessageComponent getReply() {
            return reply;
        }
    }
}
