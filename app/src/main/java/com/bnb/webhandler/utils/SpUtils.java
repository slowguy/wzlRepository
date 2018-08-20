package com.bnb.webhandler.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.text.TextUtils;
import com.bnb.webhandler.base.MyApplication;
import java.util.concurrent.ConcurrentHashMap;

public class SpUtils {


  private static final String DEFAULT_NAME = "planetAlliance";
  private static ConcurrentHashMap<String, SharedPreferences> sMap = new
      ConcurrentHashMap<>();

  private static SharedPreferences getSharedPreferences(String name) {
    if (MyApplication.getContext() == null || TextUtils.isEmpty(name)) {
      return null;
    }
    if (!sMap.containsKey(name)) {
      SharedPreferences sharedPreferences = MyApplication.getContext()
          .getSharedPreferences(name, Context.MODE_PRIVATE);
      sMap.put(name, sharedPreferences);
    }
    return sMap.get(name);
  }

  private static Editor getEditor(String name) {
    SharedPreferences sp = getSharedPreferences(name);
    return sp == null ? null : sp.edit();
  }

  private static void handleEdit(Editor edit) {
    if (edit == null) {
      return;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      edit.apply();
    } else {
      edit.commit();
    }
  }

  /**
   * String的资源id转成String
   */
  private static String resIdToKey(int resKey) {
    try {
      return MyApplication.getContext().getString(resKey);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }


  // Boolean
  public static void setValue(String key, boolean value) {
    setValue(DEFAULT_NAME, key, value);
  }

  public static void setValue(int resKey, boolean value) {
    setValue(resIdToKey(resKey), value);
  }

  // Float
  public static void setValue(String key, float value) {
    setValue(DEFAULT_NAME, key, value);
  }

  public static void setValue(int resKey, float value) {
    setValue(resIdToKey(resKey), value);
  }

  // Integer
  public static void setValue(String key, int value) {
    setValue(DEFAULT_NAME, key, value);
  }

  public static void setValue(int resKey, int value) {
    setValue(resIdToKey(resKey), value);
  }

  // Long
  public static void setValue(String key, long value) {
    setValue(DEFAULT_NAME, key, value);
  }

  public static void setValue(int resKey, long value) {
    setValue(resIdToKey(resKey), value);
  }

  // String
  public static void setValue(String key, String value) {
    setValue(DEFAULT_NAME, key, value);
  }

  public static void setValue(int resKey, String value) {
    setValue(resIdToKey(resKey), value);
  }

  // Get

  // Boolean
  public static boolean getValue(String key, boolean defaultValue) {
    return getValue(DEFAULT_NAME, key, defaultValue);
  }

  public static boolean getValue(int resKey, boolean defaultValue) {
    return getValue(resIdToKey(resKey), defaultValue);
  }

  // Float
  public static float getValue(String key, float defaultValue) {
    return getValue(DEFAULT_NAME, key, defaultValue);
  }

  public static float getValue(int resKey, float defaultValue) {
    return getValue(resIdToKey(resKey), defaultValue);
  }

  // Integer
  public static int getValue(String key, int defaultValue) {
    return getValue(DEFAULT_NAME, key, defaultValue);
  }

  public static int getValue(int resKey, int defaultValue) {
    return getValue(resIdToKey(resKey), defaultValue);
  }

  // Long
  public static long getValue(String key, long defaultValue) {
    return getValue(DEFAULT_NAME, key, defaultValue);
  }

  public static long getValue(int resKey, long defaultValue) {
    return getValue(resIdToKey(resKey), defaultValue);
  }

  // String
  public static String getValue(String key) {
    return getValue(key, "");
  }

  public static String getValue(String key, String defaultValue) {
    return getValue(DEFAULT_NAME, key, defaultValue);
  }

  public static String getValue(int resKey, String defaultValue) {
    return getValue(resIdToKey(resKey), defaultValue);
  }

  public static boolean contains(String key) {
    return contains(DEFAULT_NAME, key);
  }

  // Delete
  public static void remove(String key) {
    remove(DEFAULT_NAME, key);
  }

  public static void clear() {
    clear(DEFAULT_NAME);
  }

  //以下所有方法提供给sp不储存在networkreference.xml里的(networkreference.xml为存sp的默认xml)；

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param value sp的value
   */
  // Boolean
  public static void setValue(String name, String key, boolean value) {
    if (TextUtils.isEmpty(key)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.putBoolean(key, value);
    handleEdit(edit);

  }

  public static void setValue(String name, int resKey, boolean value) {
    setValue(name, resIdToKey(resKey), value);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param value sp的value
   */
  // Float
  public static void setValue(String name, String key, float value) {
    if (TextUtils.isEmpty(key)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.putFloat(key, value);
    handleEdit(edit);
  }

  public static void setValue(String name, int resKey, float value) {
    setValue(name, resIdToKey(resKey), value);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param value sp的value
   */
  // Integer
  public static void setValue(String name, String key, int value) {
    if (TextUtils.isEmpty(key)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.putInt(key, value);
    handleEdit(edit);
  }

  public static void setValue(String name, int resKey, int value) {
    setValue(name, resIdToKey(resKey), value);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param value sp的value
   */
  // Long
  public static void setValue(String name, String key, long value) {
    if (TextUtils.isEmpty(key)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.putLong(key, value);
    handleEdit(edit);
  }

  public static void setValue(String name, int resKey, long value) {
    setValue(name, resIdToKey(resKey), value);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param value sp的value
   */
  // String
  public static void setValue(String name, String key, String value) {
    if (TextUtils.isEmpty(key) || value == null) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.putString(key, value.trim());
    handleEdit(edit);
  }

  public static void setValue(String name, int resKey, String value) {
    setValue(name, resIdToKey(resKey), value);
  }

  // Get

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param defaultValue 默认值
   */
  // Boolean
  public static boolean getValue(String name, String key, boolean defaultValue) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return false;
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return false;
    }
    return sp.getBoolean(key, defaultValue);
  }

  public static boolean getValue(String name, int resKey, boolean defaultValue) {
    return getValue(name, resIdToKey(resKey), defaultValue);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param defaultValue 默认值
   */
  // Float
  public static float getValue(String name, String key, float defaultValue) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return 0;
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return 0;
    }
    return sp.getFloat(key, defaultValue);
  }

  public static float getValue(String name, int resKey, float defaultValue) {
    return getValue(name, resIdToKey(resKey), defaultValue);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param defaultValue 默认值
   */
  // Integer
  public static int getValue(String name, String key, int defaultValue) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return 0;
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return 0;
    }
    return sp.getInt(key, defaultValue);
  }

  public static int getValue(String name, int resKey, int defaultValue) {
    return getValue(name, resIdToKey(resKey), defaultValue);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param defaultValue 默认值
   */
  // Long
  public static long getValue(String name, String key, long defaultValue) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return 0l;
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return 0l;
    }
    return sp.getLong(key, defaultValue);
  }

  public static long getValue(String name, int resKey, long defaultValue) {
    return getValue(name, resIdToKey(resKey), defaultValue);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   * @param defaultValue 默认值
   */
  // String
  public static String getValue(String name, String key, String defaultValue) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return "";
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return defaultValue;
    }
    return sp.getString(key, defaultValue);
  }

  public static String getValue(String name, int resKey, String defaultValue) {
    return getValue(name, resIdToKey(resKey), defaultValue);
  }


  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   */
  public static boolean contains(String name, String key) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return false;
    }
    SharedPreferences sp = getSharedPreferences(name);
    if (sp == null) {
      return false;
    }
    return sp.contains(key);
  }

  /**
   * @param name 储存sp的xml名字
   * @param key sp的key
   */
  // Delete
  public static void remove(String name, String key) {
    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(key)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.remove(key);
    handleEdit(edit);
  }

  /**
   * @param name 储存sp的xml名字
   */
  public static void clear(String name) {
    if (TextUtils.isEmpty(name)) {
      return;
    }
    Editor edit = getEditor(name);
    if (edit == null) {
      return;
    }
    edit.clear();
    handleEdit(edit);
  }


}
