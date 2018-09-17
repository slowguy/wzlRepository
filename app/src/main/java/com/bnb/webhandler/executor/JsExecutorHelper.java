package com.bnb.webhandler.executor;

public class JsExecutorHelper {

  public static String scrollJs(String distance) {
//    return "javascript:(function () { try {" + "document.getElementsByTagName(\"body\")[0].scrollTo.(0," + distance + ")" + "}catch(e){console.log(e)}})()";
    return "javascript:(function () { try {" + "window.scrollTo.(0," + distance + ")" + "}catch(e){console.log(e)}})()";
  }

  public static String getHeight() {
    return "javascript:(function () { try {" + "nativeJs.getShareHeight(document.body.scrollHeight)" + "}catch(e){console.log(e)}})()";
  }
}
