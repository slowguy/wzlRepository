package com.bnb.webhandler.bean;

public class ConfigurationBean {

  private int platform;
  private String keyword;
  private long delayTime = 2 * 60 * 1000;

  public ConfigurationBean() {

  }

  public int getPlatform() {
    return platform;
  }

  public void setPlatform(int platform) {
    this.platform = platform;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public long getDelayTime() {
    return delayTime;
  }

  public void setDelayTime(long delayTime) {
    this.delayTime = delayTime;
  }
}
