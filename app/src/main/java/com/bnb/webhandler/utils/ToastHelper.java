package com.bnb.webhandler.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.bnb.webhandler.base.MyApplication;

public class ToastHelper {


  private static final Handler sHandler = new Handler(Looper.getMainLooper());
  private static ShowRunnable showRunnable = new ShowRunnable();
  private static Object sLock = new Object();
  private static Runnable sCancleRunnable = new Runnable() {

    @Override
    public void run() {
      synchronized (sLock) {
        cancleToast();
      }
    }
  };

  public static void showLongToast(CharSequence text) {
    showToast(text, true);
  }

  public static void showShortToast(CharSequence text) {
    showToast(text, false);
  }


  private static void showToast(CharSequence text, boolean showLength) {
    synchronized (sLock) {
      sHandler.removeCallbacks(showRunnable);
      sHandler.removeCallbacks(sCancleRunnable);
      showRunnable.message = text;
      showRunnable.showLong = showLength;
      sHandler.post(showRunnable);
    }
  }

  public static void cancleToast() {
    synchronized (sLock) {
      sHandler.removeCallbacks(showRunnable);
      sHandler.removeCallbacks(sCancleRunnable);
      sHandler.post(sCancleRunnable);
    }
  }


  private static class ShowRunnable implements Runnable {

    private CharSequence message;
    private boolean showLong;

    @Override
    public void run() {
      synchronized (sLock) {
        show();
      }
    }

    private void show() {
      final Context context = MyApplication.getContext();
      if (context == null) {
        return;
      }
      if (showLong) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
      } else {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
      }
    }
  }

}
