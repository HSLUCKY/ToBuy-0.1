package com.focustech.tobuy.capabilities.json;

import com.focustech.tobuy.capabilities.log.EBLog;
import com.google.gson.Gson;

/**
 * <json公共解析库>
 */
public class GsonHelper {

    private static String TAG = GsonHelper.class.getName();

    private static Gson gson = new Gson();

    /**
     * 把json string 转化成类对象
     *
     * @param str
     * @param t
     * @return
     */
    public static <T> T toType(String str, Class<T> t) {
        try {
            if (str != null && !"".equals(str.trim())) {
                T res = gson.fromJson(str.trim(), t);
                return res;
            }
        } catch (Exception e) {
            EBLog.e(TAG, "exception:" + e.getMessage());
        }
        return null;
    }

    /**
     * 把类对象转化成json string
     *
     * @param t
     * @return
     */
    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

}
