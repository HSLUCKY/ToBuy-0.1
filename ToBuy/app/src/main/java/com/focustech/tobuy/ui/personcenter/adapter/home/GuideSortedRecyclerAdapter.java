package com.focustech.tobuy.ui.personcenter.adapter.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.tobuy.R;
import com.focustech.tobuy.ui.personcenter.activity.home.GuideSortedActivity;
import com.focustech.tobuy.ui.personcenter.adapter.search.SearchRecyclerAdapter;
import com.focustech.tobuy.ui.personcenter.component.home.GuideSortedComponent;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GuideSortedRecyclerAdapter   extends RecyclerView.Adapter<RecyclerView.ViewHolder>   {

    private GuideSortedActivity context;
    private GuideSortedComponent guideSortedComponent;

    public boolean DATA_CHANGE_FLAG = true;
    public int now = -1;

    public boolean LOAD_DATA = false;

    public GuideSortedRecyclerAdapter(Context context){
        this.context = (GuideSortedActivity) context;
        guideSortedComponent = new GuideSortedComponent(context);
    }

    @Override
    public int getItemCount() {
        return context.ruler.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (now < position){
            DATA_CHANGE_FLAG = true;
            now = position;
        }else {
            DATA_CHANGE_FLAG = false;
        }
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GuideSortedComponent.SimpleGoods simpleGoods = guideSortedComponent.getSimpleGoods();
       return new SimpleGoodsViewHolder(simpleGoods.getOutLinearLayout(), simpleGoods);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA) {
            return;
        }

        if (position < context.ruler.size()){
            SimpleGoodsViewHolder sgvh =  (SimpleGoodsViewHolder) holder;

            sgvh.getSimpleGoods().setCardId(context.datas.get(position).getId());
            sgvh.getSimpleGoods().setTypeId(1);
            byte[] pic = context.imageses.get(position).get(0);
            if (pic != null){
                sgvh.getSimpleGoods().getGoodsPic().setImageBitmap(BitmapFactory.decodeByteArray(pic, 0, pic.length));
            }else {
                sgvh.getSimpleGoods().getGoodsPic().setImageResource(R.drawable.neko);
            }
            sgvh.getSimpleGoods().getTitleTx().setText(context.datas.get(0).getTitle());
            sgvh.getSimpleGoods().getPriceTx().setText(String.valueOf(context.datas.get(position).getPrice()+"￥"));
            sgvh.getSimpleGoods().getTipTx().setText(context.datas.get(position).getTip());
        }

        if (position == getItemCount()-1) {
            LOAD_DATA = true;
        }
    }

    public void updata(){
        notifyDataSetChanged();
    }

    class SimpleGoodsViewHolder extends RecyclerView.ViewHolder {

        private GuideSortedComponent.SimpleGoods simpleGoods;

        public SimpleGoodsViewHolder(View itemView, GuideSortedComponent.SimpleGoods simpleGoods) {
            super(itemView);
            this.simpleGoods = simpleGoods;
        }

        public GuideSortedComponent.SimpleGoods getSimpleGoods() {
            return simpleGoods;
        }
    }

}
