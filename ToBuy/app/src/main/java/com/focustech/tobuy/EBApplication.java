package com.focustech.tobuy;

import android.app.Activity;
import android.app.Application;

import com.focustech.tobuy.bridge.BridgeFactory;
import com.focustech.tobuy.bridge.BridgeLifeCycleSetKeeper;
import com.focustech.tobuy.bridge.Bridges;
import com.focustech.tobuy.bridge.cache.localstorage.LocalFileStorageManager;
import com.focustech.tobuy.util.PhoneParameterUtil;
import com.focustech.tobuy.util.ToastUtil;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <应用初始化> <功能详细描述>
 *
 */
public class EBApplication extends Application {
    /**
     * app实例
     */
    public static EBApplication ebApplication = null;

    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();

    /**
     * 当前avtivity名称
     */
    public static String currentActivityName = "";

    /**
     * 当前设备屏幕参数
     * SCREEN_WIDTH
     * SCREEN_HEIGHT
     *
     */
    public static HashMap<String, Integer> screenSize;

    /**
     * 输入法高度
     */
    public static int inputHeight;

    /*************************************************************************************/
    /**
     *      窗口数据缓存
     *      key     Activity Name
     *      value   Activity data
     */
    /*************************************************************************************/
    public static HashMap<String, Object> ACTIVITY_CACHE;


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        ebApplication = this;
        BridgeFactory.init(this);
        BridgeLifeCycleSetKeeper.getInstance().initOnApplicationCreate(this);

        LocalFileStorageManager manager = BridgeFactory.getBridge(Bridges.LOCAL_FILE_STORAGE);

        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttpDownloader(new File(manager.getCacheImgFilePath(this)))).build();
        Picasso.setSingletonInstance(picasso);

        this.screenSize = PhoneParameterUtil.getScreenSize(this);
        ACTIVITY_CACHE = new HashMap<>();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        onDestory();
    }

    /**
     * 退出应用，清理内存
     */
    private void onDestory() {
        BridgeLifeCycleSetKeeper.getInstance().clearOnApplicationQuit();
        ToastUtil.destory();
    }


    /**
     * <添加> <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void addActivity(Activity activity) {
        activitys.add(activity);
    }

    /**
     * <删除>
     * <功能详细描述>
     *
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void deleteActivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }
}
