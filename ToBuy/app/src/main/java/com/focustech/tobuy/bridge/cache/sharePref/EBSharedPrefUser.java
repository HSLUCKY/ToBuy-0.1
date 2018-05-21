package com.focustech.tobuy.bridge.cache.sharePref;

import android.content.Context;

import com.focustech.tobuy.capabilities.cache.BaseSharedPreference;

/**
 * <用户信息缓存>
 *
 */
public class EBSharedPrefUser extends BaseSharedPreference {

    /**
     * 用户实体
     */
    public static final String USER_Entity = "user_entity";



    public EBSharedPrefUser(Context context, String fileName) {
        super(context,fileName);
    }
}
