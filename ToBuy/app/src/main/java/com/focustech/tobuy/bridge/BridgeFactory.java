
package com.focustech.tobuy.bridge;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.focustech.tobuy.bridge.cache.localstorage.LocalFileStorageManager;
import com.focustech.tobuy.bridge.cache.sharePref.EBSharedPrefManager;
import com.focustech.tobuy.bridge.http.OkHttpManager;
import com.focustech.tobuy.bridge.security.SecurityManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * <中间连接层>
 * <p>
 * 这玩意就是将管理类弄成一个HashMap键值对就是对应的管理了类名字和实体
 * 在EBApplication的initData中就初始化了App的管理环境，将这些管理类对应的实体都放到
 * 这个桥工厂类里，通过get管理类的键值获得实体，免去了冗余的代码和复杂的单例调用
 * <p>
 * 这些管理类都封装了capabilities底层包中的Util方法，通过一个桥简化底层调用，实现解耦和
 * <p>
 * 要说问题的话，就是在封装底层的时候也相应的弱化了底层的功能，使得功能扩展不得不扩展桥中管理类的方法
 * 以及底层的Util方法
 * <p>
 * 这就像砖头和大楼的区别，没有好坏之分，只有应用不同而已
 */
public class BridgeFactory {

    private static BridgeFactory model;

    private HashMap<String, Object> mBridges;

    private BridgeFactory() {
        mBridges = new HashMap<String, Object>();
    }

    public static void init(Context context) {
        model = new BridgeFactory();
        model.iniLocalFileStorageManager();
        model.initPreferenceManager();
        model.initSecurityManager();
        model.initUserSession();
        model.initCoreServiceManager(context);
        model.initOkHttpManager();
    }

    public static void destroy() {
        model.mBridges = null;
        model = null;
    }

    /**
     * 初始化本地存储路径管理类
     */
    private void iniLocalFileStorageManager() {
        LocalFileStorageManager localFileStorageManager = new LocalFileStorageManager();
        model.mBridges.put(Bridges.LOCAL_FILE_STORAGE, localFileStorageManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(localFileStorageManager);
    }

    /**
     * 初始化SharedPreference管理类
     */
    private void initPreferenceManager() {
        EBSharedPrefManager ebSharedPrefManager = new EBSharedPrefManager();
        model.mBridges.put(Bridges.SHARED_PREFERENCE, ebSharedPrefManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(ebSharedPrefManager);
    }

    /**
     * 网络请求管理类
     */
    private void initOkHttpManager() {
        OkHttpManager mOkHttpManager = new OkHttpManager();
        model.mBridges.put(Bridges.HTTP, mOkHttpManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(mOkHttpManager);
    }

    /**
     * 初始化安全模块
     */
    private void initSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        model.mBridges.put(Bridges.SECURITY, securityManager);
        BridgeLifeCycleSetKeeper.getInstance().trustBridgeLifeCycle(securityManager);
    }

    /**
     * 初始化用户信息模块
     */
    private void initUserSession() {
    }

    /**
     * 初始化Tcp服务
     *
     * @param context
     */
    private void initCoreServiceManager(Context context) {
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(10000);
                    while (true){
                        final Socket socket = serverSocket.accept();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(socket.getInetAddress().getAddress());
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (serverSocket != null) {
                            serverSocket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
    }


    private void initDBManager() {
    }

    /**
     * 通过bridgeKey {@link Bridges}来获取对应的Bridge模块
     *
     * @param bridgeKey {@link Bridges}
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <V extends Object> V getBridge(String bridgeKey) {
        final Object bridge = model.mBridges.get(bridgeKey);
        if (bridge == null) {
            throw new NullPointerException("-no defined bridge-");
        }
        return (V) bridge;
    }
}
