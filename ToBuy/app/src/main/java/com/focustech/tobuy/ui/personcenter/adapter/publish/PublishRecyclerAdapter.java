package com.focustech.tobuy.ui.personcenter.adapter.publish;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.table.entity.TagTable;
import com.focustech.tobuy.ui.personcenter.activity.publish.PublishActivity;
import com.focustech.tobuy.ui.personcenter.component.publish.PublishComponent;
import com.focustech.tobuy.view.MyCheckBoxView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/4/23.
 */

public class PublishRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     *  存储输入控件引用便于获取控件中数据
     *  以及标签的选中状态，并且标签列表的顺序和加载的顺序一致
     */
    public PublishComponent.EditLayout editLayout;
    public ArrayList<MyCheckBoxView> myCheckBoxViews = new ArrayList<>();
    //====================================================

    private Context context;
    private PublishComponent publishComponent;
    private ArrayList<TagTable> data;
    private int dataIndex;

    public boolean DATA_CHANGE_FLAG = true;
    public int now = -1;

    public boolean LOAD_DATA = false;



    public PublishRecyclerAdapter(Context context, ArrayList<TagTable> data) {
        this.context = context;
        this.publishComponent = new PublishComponent(context);
        this.data = data;
    }

    @Override
    public int getItemCount() {
        if (data.size() % 2 != 0) {
            return data.size() / 2 + 3;
        }
        return data.size() / 2 + 2;
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
            editLayout = publishComponent.getEditLayout();
            return new EditLayoutViewHolder(editLayout.getOutLinearLayout(), editLayout);
        } else if (viewType == 1) {
            PublishComponent.TitleLayout titleLayout = publishComponent.getTitleLayout();
            return new TitleLayoutViewHolder(titleLayout.getOutLinearLayout(), titleLayout);
        } else {
            PublishComponent.CheckBoxLine checkBoxLine = publishComponent.getCheckBoxLine();
            return new CheckBoxLineViewHolder(checkBoxLine.getOutLinearLayout(), checkBoxLine);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA) {
            return;
        }
        if (position == getItemCount() - 1) {
            LOAD_DATA = true;
        }
        if (position > 1) {
            if (holder instanceof CheckBoxLineViewHolder) {
                CheckBoxLineViewHolder temp = (CheckBoxLineViewHolder) holder;
                int index = (position - 2) * 2;
                if (index >= 0 && index < data.size()) {
                    MyCheckBoxView myCheckBoxView = publishComponent.getMyCheckBoxView();
                    myCheckBoxView.getLeftPic().setImageResource(R.drawable.select);
                    myCheckBoxView.getMidTx().setText(data.get(dataIndex).getContent());
                    myCheckBoxView.getRightPic().setImageResource(R.drawable.select);
                    myCheckBoxView.setCheckListener((PublishActivity) context);
                    myCheckBoxView.setTagId(data.get(dataIndex).getId());
                    temp.getCheckBoxLine().getLeftLinearLayout().addView(myCheckBoxView.getOutLinearLayout());
                    dataIndex++;

                    myCheckBoxViews.add(myCheckBoxView);
                    if (index + 1 < data.size()) {
                        MyCheckBoxView myCheckBoxView2 = publishComponent.getMyCheckBoxView();
                        myCheckBoxView2.getLeftPic().setImageResource(R.drawable.select);
                        myCheckBoxView2.getMidTx().setText(data.get(dataIndex).getContent());
                        myCheckBoxView2.getRightPic().setImageResource(R.drawable.select);
                        myCheckBoxView2.setCheckListener((PublishActivity) context);
                        myCheckBoxView2.setTagId(data.get(dataIndex).getId());
                        temp.getCheckBoxLine().getRightLinearLayout().addView(myCheckBoxView2.getOutLinearLayout());
                        myCheckBoxViews.add(myCheckBoxView2);
                        dataIndex++;
                    }
                }
            }
        }

    }

    public void updata() {
        this.notifyDataSetChanged();
    }


    public class EditLayoutViewHolder extends RecyclerView.ViewHolder {
        private PublishComponent.EditLayout editLayout;

        public EditLayoutViewHolder(View itemView, PublishComponent.EditLayout editLayout) {
            super(itemView);
            this.editLayout = editLayout;
        }

        public PublishComponent.EditLayout getEditLayout() {
            return editLayout;
        }
    }

    public class TitleLayoutViewHolder extends RecyclerView.ViewHolder {
        private PublishComponent.TitleLayout titleLayout;

        public TitleLayoutViewHolder(View itemView, PublishComponent.TitleLayout titleLayout) {
            super(itemView);
            this.titleLayout = titleLayout;
        }

        public PublishComponent.TitleLayout geTitleLayout() {
            return titleLayout;
        }
    }

    public class CheckBoxLineViewHolder extends RecyclerView.ViewHolder {
        private PublishComponent.CheckBoxLine checkBoxLine;

        public CheckBoxLineViewHolder(View itemView, PublishComponent.CheckBoxLine checkBoxLine) {
            super(itemView);
            this.checkBoxLine = checkBoxLine;
        }

        public PublishComponent.CheckBoxLine getCheckBoxLine() {
            return checkBoxLine;
        }
    }

    public Context getContext() {
        return context;
    }

    public boolean isDATA_CHANGE_FLAG() {
        return DATA_CHANGE_FLAG;
    }

    public int getNow() {
        return now;
    }

    public PublishComponent getPublishComponent() {
        return publishComponent;
    }
}