package com.bnb.webhandler.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class BaseDialog extends Dialog {

  protected Context mContext;

  public BaseDialog(Context context, int themeResId) {
    super(context, themeResId);
    mContext = context;
  }

  protected boolean checkContext() {
    return mContext != null && mContext instanceof Activity && !((Activity) mContext).isFinishing();
  }

  protected ViewGroup.LayoutParams getLayoutParams() {

    return new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  @Override
  public void show() {
    if (checkContext()) {
      super.show();
    }
  }

  @Override
  public void dismiss() {
    if (isShowing() && checkContext()) {
      super.dismiss();
    }
  }

}
