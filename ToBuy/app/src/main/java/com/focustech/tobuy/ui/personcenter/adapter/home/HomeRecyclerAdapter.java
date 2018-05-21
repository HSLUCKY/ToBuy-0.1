package com.focustech.tobuy.ui.personcenter.adapter.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.focustech.tobuy.EBApplication;
import com.focustech.tobuy.R;
import com.focustech.tobuy.bean.service.resource.ResourceResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.ui.personcenter.activity.home.HomeActivity;
import com.focustech.tobuy.ui.personcenter.component.home.GuideComponent;
import com.focustech.tobuy.ui.personcenter.component.home.GoodsComponent;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;

/**
 * RecycleView  重写适配器
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //const
    private Context context;
    private static Map<String, Integer> screenSize;
    //data
    private ArrayList<GoodsTable> datas;

    //component
    private GuideComponent guideComponent;
    private GoodsComponent homeComponentFr;
    private GoodsComponent homeComponentSe;

    public static boolean DATA_CHANGE_FLAG = true;
    public static int now = -1;

    public boolean LOAD_DATA = false;

    public int dataIndex;

    public HomeRecyclerAdapter(ArrayList<GoodsTable> datas, Context context) {
        this.context = context;
        this.datas = datas;
        this.screenSize = EBApplication.screenSize;

    }

    private LinearLayout initGoodsLayout() {

        LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenSize.get("SCREEN_HEIGHT") * 0.35f));
        LinearLayout goodsLayout = new LinearLayout(context);
        goodsLayout.setOrientation(LinearLayout.HORIZONTAL);
        goodsLayout.setLayoutParams(lLayoutParams);
        this.homeComponentFr = new GoodsComponent(goodsLayout.getContext());
        this.homeComponentSe = new GoodsComponent(goodsLayout.getContext());
        goodsLayout.addView(this.homeComponentFr.getBaseLinearLayout());
        goodsLayout.addView(this.homeComponentSe.getBaseLinearLayout());

        return goodsLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private LinearLayout initGuideLayout() {
        LinearLayout.LayoutParams lLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout guideLayout = new LinearLayout(context);
        guideLayout.setLayoutParams(lLayoutParams);
        guideLayout.setOrientation(LinearLayout.VERTICAL);
        this.guideComponent = new GuideComponent(guideLayout.getContext(), 3);
        guideLayout.addView(this.guideComponent.getComplexLayout().getBaseLinearLayout());

        return guideLayout;
    }

    /**
     * 返回子项个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (datas.size() > 12) {
            int goodsCount = datas.size() - 12;
            if (goodsCount % 2 == 0) {
                return goodsCount / 2 + 1;
            } else {
                return goodsCount / 2 + 2;
            }
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if (position > now) {
            DATA_CHANGE_FLAG = true;
            now = position;
        } else {
            HomeRecyclerAdapter.DATA_CHANGE_FLAG = false;
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_activity, parent, false);
        //设置RecyclerView布局
        if (viewType == 0) {
            LinearLayout temp = initGuideLayout();
            return new GuideViewHolder(temp, this.guideComponent);
        } else {
            LinearLayout temp = initGoodsLayout();
            return new GoodsViewHolder(temp, this.homeComponentFr, this.homeComponentSe);
        }

    }

    /**
     * 对RecyclerView子项数据进行赋值
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (LOAD_DATA) {
            return;
        }
        if (position == getItemCount()-1) {
            LOAD_DATA = true;
        }

        if (datas.size() > 0) {
            if (position == 0) {
                if (holder instanceof GuideViewHolder) {
                    GuideViewHolder temp = (GuideViewHolder) holder;
                    ImageView[] adv = temp.getGuideComponent().getComplexLayout().getAdvertisementLayout().getPics();
                    for (int i = 0; i < datas.size() && i < adv.length; i++) {
                        GoodsTable goodsTable = datas.get(i);
                        GuideComponent.advertiseI.put(adv[i], goodsTable.getId());
                        if (((HomeActivity) context).imageses.size() > 0) {
                            byte[] image = ((HomeActivity) context).imageses.get(i).get(0);
                            adv[i].setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                        }
                        dataIndex++;
                    }
                    GoodsComponent[] showGoods = temp.getGuideComponent().getComplexLayout().getShowLayout().getHomeComponents();
                    for (int i = adv.length; i < datas.size() && i - adv.length < showGoods.length; i++) {
                        GoodsTable goodsTable = datas.get(i);
                        showGoods[i - 3].goodsId = goodsTable.getId();
                        if (((HomeActivity) context).imageses.size() > 0) {
                            byte[] image = ((HomeActivity) context).imageses.get(i).get(0);
                            showGoods[i - 3].getHeadImageView().setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                        }
                        showGoods[i - 3].getTitleText().setText(String.valueOf(goodsTable.getTitle()));
                        showGoods[i - 3].getPcText().setText(String.valueOf(goodsTable.getPrice()) + "￥");
                        showGoods[i - 3].getcText().setText(String.valueOf(goodsTable.getReadCount()) + "*");
                        dataIndex++;
                    }
                }
            } else {
                //int index = position + 11;

                GoodsViewHolder temp = (GoodsViewHolder) holder;
                temp.initGoodsView(datas.get(dataIndex), true, dataIndex, context);
                dataIndex++;
                if (dataIndex < datas.size()) {
                    temp.initGoodsView(datas.get(dataIndex), false, dataIndex, context);
                    dataIndex++;
                } else {
                    temp.getHomeComponentSe().getBaseLinearLayout().setVisibility(View.INVISIBLE);
                }

            }
        }


    }

    public void updata() {
        this.notifyDataSetChanged();
    }


    public static class GuideViewHolder extends RecyclerView.ViewHolder {
        private GuideComponent guideComponent;

        public GuideViewHolder(View itemView, GuideComponent guideComponent) {
            super(itemView);
            this.guideComponent = guideComponent;
        }

        public GuideComponent getGuideComponent() {
            return guideComponent;
        }

        public void initGuideView(ArrayList<GoodsTable> datas) {

        }
    }

    /**
     * ViewHolder内部类
     */
    public static class GoodsViewHolder extends RecyclerView.ViewHolder {
        private GoodsComponent homeComponentFr;
        private GoodsComponent homeComponentSe;

        public GoodsViewHolder(View itemView, GoodsComponent homeComponentFr, GoodsComponent homeComponentSe) {
            super(itemView);
            this.homeComponentFr = homeComponentFr;
            this.homeComponentSe = homeComponentSe;
        }

        public GoodsComponent getHomeComponentFr() {
            return homeComponentFr;
        }

        public GoodsComponent getHomeComponentSe() {
            return homeComponentSe;
        }

        public void initGoodsView(GoodsTable data, boolean flag, int index, Context context) {
            boolean imgFlag = false;
            Bitmap staticBitmap = null;
            if (((HomeActivity) context).imageses.size() > index && !((HomeActivity) context).imageses.get(index).isEmpty()) {
                byte[] image = ((HomeActivity) context).imageses.get(index).get(0);
                staticBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            } else {
                imgFlag = true;
            }
            if (flag) {
                if (imgFlag) {
                    homeComponentFr.getHeadImageView().setImageResource(R.drawable.neko);
                } else {
                    homeComponentFr.getHeadImageView().setImageBitmap(staticBitmap);
                }
                homeComponentFr.goodsId = data.getId();
                homeComponentFr.getTitleText().setText(data.getTitle());
                homeComponentFr.getPcText().setText(String.valueOf(data.getPrice()) + "￥");
                homeComponentFr.getcText().setText(String.valueOf(data.getReadCount()) + "*");
            } else {
                if (imgFlag) {
                    homeComponentSe.getHeadImageView().setImageResource(R.drawable.neko);
                } else {
                    homeComponentSe.getHeadImageView().setImageBitmap(staticBitmap);
                }
                homeComponentSe.goodsId = data.getId();
                homeComponentSe.getTitleText().setText(data.getTitle());
                homeComponentSe.getPcText().setText(String.valueOf(data.getPrice()) + "￥");
                homeComponentSe.getcText().setText(String.valueOf(data.getReadCount()) + "*");
            }

        }
    }


}
