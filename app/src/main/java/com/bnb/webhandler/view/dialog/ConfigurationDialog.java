package com.bnb.webhandler.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bnb.webhandler.R;
import com.bnb.webhandler.bean.ConfigurationBean;
import com.bnb.webhandler.utils.ToastHelper;

public class ConfigurationDialog extends BaseDialog implements OnClickListener {


  private RadioGroup mPlatforms;
  private EditText mKeyword;
  private TextView mClearCookie;
  private TextView mSure;
  private TextView mCancel;
  private ConfigurationBean mBean;

  public ConfigurationDialog(Context context) {
    super(context, R.style.dialog_configuration);
  }

  public static ConfigurationDialog create(Context context) {
    return new ConfigurationDialog(context);
  }

  public ConfigurationDialog setConfigurationInfo(ConfigurationBean bean) {
    mBean = bean;
    return this;
  }

  public ConfigurationDialog setOnActionListener(OnActionListener listener) {
    this.mListener = listener;
    return this;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(LayoutInflater.from(mContext).inflate(R.layout.dialog_configuration, null),
        getLayoutParams());
    findView();
    setupInfo();
    setListeners();
  }

  private void findView() {
    mPlatforms = findViewById(R.id.rg_platforms);
    mKeyword = findViewById(R.id.et_keyword);
    mClearCookie = findViewById(R.id.tv_clear_cookie);
    mSure = findViewById(R.id.tv_sure);
    mCancel = findViewById(R.id.tv_cancel);
  }

  private void setListeners() {
    mClearCookie.setOnClickListener(this);
    mSure.setOnClickListener(this);
    mCancel.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_clear_cookie:
        ToastHelper.showShortToast("developing...");
        break;
      case R.id.tv_sure:
        int checkedPlatform = getCheckedPlatform();
        String keyword = getKeyword();
        if (checkedPlatform == -1 || TextUtils.isEmpty(keyword)) {
          ToastHelper.showShortToast(mContext.getString(R.string.info_error));
          return;
        }
        if (mListener != null) {
          mListener.onSure(checkedPlatform, keyword);
          dismiss();
        }
        break;
      case R.id.tv_cancel:
        dismiss();
        break;
    }
  }

  private int getCheckedPlatform() {
    if (mPlatforms != null) {
      for (int i = 0; i < mPlatforms.getChildCount(); i++) {
        RadioButton child = (RadioButton) mPlatforms.getChildAt(i);
        if (child.isChecked()) {
          return i;
        }
      }
    }
    return -1;
  }

  private String getKeyword() {
    if (mKeyword == null) {
      return "";
    }
    return String.valueOf(mKeyword.getText());
  }

  private void setupInfo() {
    if (mBean != null) {
      ((RadioButton) mPlatforms.getChildAt(mBean.getPlatform())).setChecked(true);
      mKeyword.setText(mBean.getKeyword());
    }
  }

  private OnActionListener mListener;

  public interface OnActionListener {

    void onSure(int platform, String keyword);

    void onCancel();
  }
}
