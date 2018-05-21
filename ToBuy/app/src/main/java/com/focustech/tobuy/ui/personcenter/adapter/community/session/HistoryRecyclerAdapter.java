package com.focustech.tobuy.ui.personcenter.adapter.community.session;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.tobuy.ui.personcenter.component.personal.SimpleHistoryComponent;

import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Integer> data;
    private SimpleHistoryComponent simpleHistoryComponent;

    public boolean DATA_CHANGE_FLAG = true;
    public int now = -1;
    public boolean LOAD_DATA = false;

    public HistoryRecyclerAdapter(Context context, List<Integer> data) {
        this.context = context;
        if (data.size() > 0) {
            this.data = data;
        }
        this.simpleHistoryComponent = new SimpleHistoryComponent(context);
    }

    @Override
    public int getItemCount() {
        return data.size();
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
        SimpleHistoryComponent.SimpleCardHistory simpleCardHistory = simpleHistoryComponent.getSimpleCardHistory();
        return new SimpleCardHistoryViewHolder(simpleCardHistory.getOutLinearLayout(), simpleCardHistory);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    public void updata() {
        this.notifyDataSetChanged();
    }

    public class SimpleCardHistoryViewHolder extends RecyclerView.ViewHolder {

        SimpleHistoryComponent.SimpleCardHistory simpleCardHistory;

        public SimpleCardHistoryViewHolder(View itemView, SimpleHistoryComponent.SimpleCardHistory simpleCardHistory) {
            super(itemView);
            this.simpleCardHistory = simpleCardHistory;
        }

        public SimpleHistoryComponent.SimpleCardHistory getSimpleCardHistory() {
            return simpleCardHistory;
        }
    }


}
