package com.focustech.tobuy.ui.personcenter.adapter.message;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.ui.base.BaseActivity;
import com.focustech.tobuy.ui.personcenter.activity.message.PostActivity;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;

import java.util.List;
import java.util.Map;

/**
 * RecycleView  重写适配器
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //const
    private PostActivity context;

    public static boolean DATA_CHANGE_FLAG = true;
    public static int now = -1;

    public boolean LOAD_DATA;

    public PostRecyclerAdapter(Context context) {
        this.context = (PostActivity) context;
    }

    /**
     * 返回子项个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return context.messages.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (position > now){
            now = position;
        }else {
            PostRecyclerAdapter.DATA_CHANGE_FLAG = false;
        }

        return position;
    }


    /**
     * 加载item布局 创建ViweHolder实例
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageComponent messageComponent = new MessageComponent(context, context.ids.get(viewType));
        return new PostViewHolder(messageComponent.getMessageLayout(), messageComponent);
    }

    /**
     * 对RecyclerView子项数据进行赋值
     *
     * @param holder
     * @param position
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA){
            return;
        }

        if (position < getItemCount()){
            PostViewHolder pvh = (PostViewHolder)holder;

            if (context.ids.get(position) == context.m_id){
                pvh.getMessageComponent().getHeadPic().setImageDrawable(BaseActivity.user_head.getDrawable());
            }else {
                byte[] head = context.f_user_head;
                pvh.getMessageComponent().getHeadPic().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
            }
            MessageTable messageTable = context.messages.get(position);
            pvh.getMessageComponent().getMessageTime().setText(String.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(messageTable.getDate())));
            pvh.getMessageComponent().getMessageContent().setText(messageTable.getContent());

        }


        if (position == getItemCount()-1){
            LOAD_DATA = true;
        }
    }



    public void updata() {
        this.notifyDataSetChanged();
    }

    /**
     * ViewHolder内部类
     */
    class PostViewHolder extends RecyclerView.ViewHolder {

        private MessageComponent messageComponent;

        public PostViewHolder(View itemView, MessageComponent messageComponent) {
            super(itemView);
            this.messageComponent = messageComponent;
        }

        public MessageComponent getMessageComponent() {
            return messageComponent;
        }
    }


}
