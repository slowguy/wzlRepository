package com.bnb.webhandler.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bnb.webhandler.R;
import com.bnb.webhandler.base.MyApplication;
import com.bnb.webhandler.event.StartActionEvent;
import com.bnb.webhandler.utils.EventUtil;
import com.bnb.webhandler.utils.ToastHelper;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WebFragment extends Fragment {

  public static final String TAG = "WebFragment TAG";

  private View mContentView;
  private WebView mWeb;

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
  }

  private void initWebview() {
    mWeb.getSettings().setBuiltInZoomControls(false);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      mWeb.getSettings().setDisplayZoomControls(false);
    }
    mWeb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    mWeb.getSettings().setJavaScriptEnabled(true);
    mWeb.getSettings().setAppCacheEnabled(true);
    mWeb.getSettings().setDomStorageEnabled(true);
    mWeb.getSettings().setDatabaseEnabled(true);
    mWeb.getSettings().setUseWideViewPort(true);//将图片调整到适合WebView的大小
    mWeb.setHorizontalScrollBarEnabled(false);
    String cacheDir = MyApplication.getContext().getCacheDir().getAbsolutePath();
    mWeb.getSettings().setAppCachePath(cacheDir);
    mWeb.getSettings().setAllowFileAccess(true);
    mWeb.setWebViewClient(new MyWebClient());
    mWeb.setWebChromeClient(new MyWebChrome());
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onStartActionEvent(StartActionEvent event) {
    ToastHelper.showShortToast("start action");

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
    }

  }

  class MyWebChrome extends WebChromeClient {

    @Override
    public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
      Log.e(TAG, "title -> " + title);
    }
  }

}
