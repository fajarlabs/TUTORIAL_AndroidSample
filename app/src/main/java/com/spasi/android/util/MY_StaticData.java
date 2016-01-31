package com.spasi.android.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Spasi-Ideapad on 1/28/2016.
 */
public class MY_StaticData {
    private static Map <String,Object> data;

    public static Map<String,Object> setData(String key, Object val) {
        data = new HashMap<String,Object>();
        data.put(key,val);
        return data;
    }
}
