package com.bnb.webhandler.helper;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.bnb.webhandler.base.constant.Constants;
import com.bnb.webhandler.bean.ConfigurationBean;
import com.bnb.webhandler.utils.FastJsonUtil;
import com.bnb.webhandler.utils.SpUtils;
import java.util.Map;
import java.util.TreeMap;

public class ConfigurationHelper {

  private static ConfigurationHelper instance;

  private Map<Integer, String> configUrl;

  private ConfigurationHelper() {
    configUrl = new TreeMap<>();
    configUrl.put(Constants.PLATFORM_BAIDU, Constants.PLATFORM_BAIDU_URL);
    configUrl.put(Constants.PLATFORM_SOUGOU, Constants.PLATFORM_SOUGOU_URL);
    configUrl.put(Constants.PLATFORM_GOOGLE, Constants.PLATFORM_GOOGLE_URL);
  }

  public static ConfigurationHelper getInstance() {
    synchronized (ConfigurationHelper.class) {
      if (instance == null) {
        synchronized (ConfigurationHelper.class) {
          instance = new ConfigurationHelper();
          return instance;
        }
      }
      return instance;
    }
  }

  public ConfigurationBean getConfigurationInfo() {
    String configuration = SpUtils.getValue(Constants.SP_KEY_CONFIGURATION);
    if (!TextUtils.isEmpty(configuration)) {
      return FastJsonUtil
          .parseObject(configuration, ConfigurationBean.class);
    }
    return null;
  }

  public void saveConfigurationInfo(ConfigurationBean bean) {
    if (bean != null) {
      String jsonStr = JSON.toJSONString(bean);
      SpUtils.setValue(Constants.SP_KEY_CONFIGURATION, jsonStr);
    }
  }

  public String getPlatformUrl(){
//    if(!configUrl.isEmpty()&&configUrl.size()>){
//
//    }
    return "";
  }

}
