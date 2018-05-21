package com.focustech.tobuy.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2018/5/6.
 */

public class ObjectTo {

    /**
     * 将Object对象转成字符
     * @param object
     * @return
     */
    public static byte[] toByteArrays(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try{
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bos != null){
                    bos.close();
                }
                if (oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }


}
