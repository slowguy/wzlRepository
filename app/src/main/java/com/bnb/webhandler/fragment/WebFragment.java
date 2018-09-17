package com.bnb.webhandler.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.bnb.webhandler.R;
import com.bnb.webhandler.base.MyApplication;
import com.bnb.webhandler.bean.ConfigurationBean;
import com.bnb.webhandler.event.ClearWebEvent;
import com.bnb.webhandler.event.StartActionEvent;
import com.bnb.webhandler.executor.JsExecutorHelper;
import com.bnb.webhandler.helper.ConfigurationHelper;
import com.bnb.webhandler.utils.EventUtil;
import com.bnb.webhandler.utils.ToastHelper;
import com.victor.loading.book.BookLoading;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WebFragment extends Fragment {

  public static final String TAG = "WebFragment TAG";

  private View mContentView;
  private WebView mWeb;
  private BookLoading mLoading;
  private View mBlock;

  private String mKeyword;
  private int mPlatform;
  private long mDelayTime;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventUtil.register(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mContentView = inflater.inflate(R.layout.fragment_web, container, false);
    ViewParent viewParent = mContentView.getParent();
    if (viewParent != null) {
      ((ViewGroup) viewParent).removeAllViews();
    }
    init();
    return mContentView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  private void init() {
    findView();
    initWebview();
  }

  private void findView() {
    mWeb = mContentView.findViewById(R.id.wv_content_web);
    mLoading = mContentView.findViewById(R.id.rl_loading);
    mBlock = mContentView.findViewById(R.id.finger_block);
  }

  private void initWebview() {
    WebSettings setting = mWeb.getSettings();
    setting.setBuiltInZoomControls(false);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      setting.setDisplayZoomControls(false);
    }
    setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    setting.setJavaScriptEnabled(true);
    setting.setAppCacheEnabled(true);
    setting.setDomStorageEnabled(true);
    setting.setDatabaseEnabled(true);
    setting.setUseWideViewPort(true);//将图片调整到适合WebView的大小
    mWeb.setHorizontalScrollBarEnabled(false);
    String cacheDir = MyApplication.getContext().getCacheDir().getAbsolutePath();
    setting.setAppCachePath(cacheDir);
    setting.setAllowFileAccess(true);
    mWeb.setWebViewClient(new MyWebClient());
    mWeb.setWebChromeClient(new MyWebChrome());
    mWeb.addJavascriptInterface(this, "nativeJs");
  }

  private void refreshWeb() {
    ToastHelper.showShortToast("load url!");
//    mLoading.setVisibility(View.VISIBLE);
    mWeb.loadUrl(ConfigurationHelper.getInstance().getLoadUrl(mPlatform, mKeyword));
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    EventUtil.unregister(this);
  }

  class MyWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
      super.onPageFinished(view, url);
      Log.e(TAG, "onPageFinished -> " + url);
      if (mLoading != null && mLoading.getVisibility() == View.VISIBLE) {
        mLoading.setVisibility(View.GONE);
      }
    }
  }

  class MyWebChrome extends WebChromeClient {

    @Override
    public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
//      Log.e(TAG, "title -> " + title);
    }
  }

  /**
   * 点击start按钮
   */
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onStartActionEvent(StartActionEvent event) {
//    ToastHelper.showShortToast("start action");
    ConfigurationBean configurationInfo = ConfigurationHelper.getInstance().getConfigurationInfo();
    if (configurationInfo != null) {
      mKeyword = configurationInfo.getKeyword();
      mPlatform = configurationInfo.getPlatform();
      mDelayTime = configurationInfo.getDelayTime();
      if (TextUtils.isEmpty(mKeyword)) {
        ToastHelper.showShortToast(getString(R.string.info_error));
        return;
      }
      refreshWeb();
    } else {
      ToastHelper.showShortToast(getString(R.string.info_error));
    }
  }

  /**
   * 清除WebView内容
   */
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onWebClearEvent(ClearWebEvent event) {
//    mWeb.clearView();
//    mWeb.loadUrl("");
//    mWeb.loadUrl(JsExecutorHelper.getHeight());
    mWeb.loadUrl(JsExecutorHelper.scrollJs("200"));
  }

  //------------js func----------
  @JavascriptInterface
  public void getShareHeight(final String height) {
    Log.e("wzl0917", "height -> " + height);
  }

}
