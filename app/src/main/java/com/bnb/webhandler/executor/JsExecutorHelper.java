package com.bnb.webhandler.executor;

public class JsExecutorHelper {

  public static String scrollJs(String distance) {
    return "javascript:document.documentElement.scrollTop=" + distance;
  }

  public static String getHeight() {
    return "javascript:Document.";
  }
}
