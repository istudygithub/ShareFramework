package com.ray.balloon.view.main;


import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.corelibs.base.BaseActivity;
import com.corelibs.common.AppManager;
import com.corelibs.utils.ToastMgr;
import com.ray.balloon.R;
import com.ray.balloon.presenter.MainPresenter;

import butterknife.Bind;
import carbon.widget.Toolbar;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        toolbar.setText("balloon");
    }

    @Override
    protected MainPresenter createPresenter() {
        return null;
    }

    @Override
    public void getDataSuccess() {

    }


    private boolean isBackPressed;

    /**
     * 双击退出
     */
    private void doublePressBackToast() {
        if (!isBackPressed) {
            isBackPressed = true;
            ToastMgr.show("双击退出程序");
        } else {
            AppManager.getAppManager().appExit();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressed = false;
            }
        }, 2000);
    }

    private boolean isShow = false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isShow) {
                isShow = false;
                return false;
            } else {
                doublePressBackToast();
            }
            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }
}
