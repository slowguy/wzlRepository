package com.bnb.webhandler.utils;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * FastJson工具类
 */
public class FastJsonUtil {

  public static <T> T parseObject(String jsonString, Class<T> clz) {
    if (!TextUtils.isEmpty(jsonString) && clz != null) {
      try {
        return JSON.parseObject(jsonString, clz);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static JSONObject parseObject(String jsonString) {
    if (!TextUtils.isEmpty(jsonString)) {
      try {
        return JSON.parseObject(jsonString);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public static String toJSONString(Object obj) {
    if (obj != null) {
      return JSON.toJSONString(obj);
    }
    return null;
  }


}
