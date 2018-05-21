package com.focustech.tobuy.ui.personcenter.adapter.personal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.tobuy.ui.personcenter.component.personal.SaleGoodsComponent;

/**
 * Created by Administrator on 2018/4/30.
 */

public class SaleGoodsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    public SaleGoodsComponent saleGoodsComponent;
    public SaleGoodsComponent.SaleGoods saleGoods;

    public SaleGoodsRecyclerAdapter(Context context){
        this.context = context;
        saleGoodsComponent = new SaleGoodsComponent(context);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        saleGoods = saleGoodsComponent.getSaleGoods();
        return new SaleGoodsViewHolder(saleGoods.getOutLinearLayout(), saleGoods);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public class SaleGoodsViewHolder extends RecyclerView.ViewHolder {
        private SaleGoodsComponent.SaleGoods saleGoods;
        public SaleGoodsViewHolder(View itemView, SaleGoodsComponent.SaleGoods saleGoods) {
            super(itemView);
            this.saleGoods = saleGoods;
        }

        public SaleGoodsComponent.SaleGoods getSaleGoods() {
            return saleGoods;
        }
    }

}
