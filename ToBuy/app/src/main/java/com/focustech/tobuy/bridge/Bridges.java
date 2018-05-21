
package com.focustech.tobuy.bridge;

/**
 * <与Bridge模块一一对应，用以在BridgeFactory获取某个Bridge对应的Key>
 *
 */
public class Bridges {
    /**
     * 本地缓存(sd卡存储和手机内部存储)
     */
    public static final String LOCAL_FILE_STORAGE = "com.focustech.tobuy.LOCAL_FILE_STORAGE";

    /**
     * SharedPreference缓存
     */
    public static final String SHARED_PREFERENCE = "com.focustech.tobuy.SHARED_PREFERENCE";

    /**
     * 安全
     */
    public static final String SECURITY = "com.focustech.tobuy.SECURITY";

    /**
     * 用户Session
     */
    public static final String USER_SESSION = "com.focustech.tobuy.USER_SESSION";

    /**
     * CoreService，主要维护功能模块
     */
    public static final String CORE_SERVICE = "com.focustech.tobuy.CORE_SERVICE";


    /**
     * 数据库模块
     */
    public static final String DATABASE = "com.focustech.tobuy.DATABASE";

    /**
     * http请求
     */
    public static final String HTTP = "com.focustech.tobuy.HTTP";

}
