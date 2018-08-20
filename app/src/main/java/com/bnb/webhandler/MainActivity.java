package com.bnb.webhandler;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.bnb.webhandler.base.BaseActivity;
import com.bnb.webhandler.bean.ConfigurationBean;
import com.bnb.webhandler.event.StartActionEvent;
import com.bnb.webhandler.fragment.WebFragment;
import com.bnb.webhandler.helper.ConfigurationHelper;
import com.bnb.webhandler.utils.EventUtil;
import com.bnb.webhandler.view.dialog.ConfigurationDialog;
import com.bnb.webhandler.view.dialog.ConfigurationDialog.OnActionListener;

public class MainActivity extends BaseActivity implements OnClickListener {


  private TextView mStart;
  private TextView mConfig;
  private WebFragment mWebFragment;


  @Override
  protected int setView() {
    return R.layout.activity_main;
  }

  @Override
  public void findView() {
    super.findView();
    mStart = findViewById(R.id.tv_action_start);
    mConfig = findViewById(R.id.tv_action_config);

    mStart.setOnClickListener(this);
    mConfig.setOnClickListener(this);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    init();
  }

  private void init() {
//    EventUtil.register(this);
    initFragment();
  }

  private void initFragment() {
    mWebFragment = new WebFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.fl_container, mWebFragment);
    transaction.show(mWebFragment);
    transaction.commit();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_action_start:
        //TODO post url keyword
        EventUtil.post(new StartActionEvent());
        break;
      case R.id.tv_action_config:
        ConfigurationDialog.create(this)
            .setConfigurationInfo(ConfigurationHelper.getInstance().getConfigurationInfo())
            .setOnActionListener(new OnActionListener() {
              @Override
              public void onSure(final int platform, final String keyword) {
                ConfigurationBean bean = new ConfigurationBean();
                bean.setPlatform(platform);
                bean.setKeyword(keyword);
                ConfigurationHelper.getInstance().saveConfigurationInfo(bean);
              }

              @Override
              public void onCancel() {

              }
            })
            .show();
        break;
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
//    EventUtil.unregister(this);
  }
}
