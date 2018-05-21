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
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.ui.personcenter.activity.message.MessageActivity;
import com.focustech.tobuy.ui.personcenter.component.message.MessageComponent;
import com.focustech.tobuy.ui.personcenter.component.message.SimpleMessageComponent;

import java.util.List;
import java.util.Map;

/**
 * RecycleView  重写适配器
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //const
    private MessageActivity context;

    public static boolean DATA_CHANGE_FLAG = true;
    public static int now = -1;

    public boolean LOAD_DATA = false;

    public MessageRecyclerAdapter(Context context, RecyclerView recyclerView) {
        this.context = (MessageActivity) context;

    }

    /**
     * 返回子项个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return context.ruler.size();
    }


    @Override
    public int getItemViewType(int position) {

        if (position > now){
            now = position;
        }else {
            MessageRecyclerAdapter.DATA_CHANGE_FLAG = false;
        }

        return position;
    }

    private SimpleMessageComponent initMessageLayout(){
        return new SimpleMessageComponent(context);
    }

    /**
     * 加载item布局 创建ViweHolder实例
     *
     * @param parent
     * @param viewType
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        SimpleMessageComponent messageComponent = initMessageLayout();
        return new MessageComponentViewHolder(messageComponent.getOutLinearLayout(), messageComponent);
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
        if (LOAD_DATA) {
            return;
        }

        if (context.userIndex < context.uId.size()){
            MessageComponentViewHolder mcv = (MessageComponentViewHolder)holder;
            /**
             * 绑定消息数据
             */
            byte[] head = context.from_users.get(context.uId.get(context.userIndex));
            mcv.getMessageComponent().getHead().setImageBitmap(BitmapFactory.decodeByteArray(head, 0, head.length));
            MessageTable messageTable = context.messageses.get(context.uId.get(context.userIndex)).get(context.messageIndex);
            mcv.getMessageComponent().setUserId(context.uId.get(context.userIndex));
            mcv.getMessageComponent().getTitle().setText(context.from_users_info.get(context.uId.get(context.userIndex)).getUsername());
            mcv.getMessageComponent().getContent().setText(messageTable.getContent());
            mcv.getMessageComponent().getDate().setText(String.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(messageTable.getDate())));

            context.messageIndex++;
            if (context.messageIndex == context.messageses.get(context.uId.get(context.userIndex)).size()){
                context.userIndex++;
                context.messageIndex = 0;
            }
        }


        if (position == getItemCount()-1) {
            LOAD_DATA = true;
        }
    }

    public void updata() {
        this.notifyDataSetChanged();
    }

    /**
     * ViewHolder内部类
     */
    class MessageComponentViewHolder extends RecyclerView.ViewHolder {

        private SimpleMessageComponent messageComponent;

        public MessageComponentViewHolder(View itemView, SimpleMessageComponent messageComponent) {
            super(itemView);
            this.messageComponent = messageComponent;
        }

        public SimpleMessageComponent getMessageComponent() {
            return messageComponent;
        }
    }


}
