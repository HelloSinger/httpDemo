package com.ayd.httplib.wrap.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;


import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.annotations.Nullable;


/**
 * 类描述
 */

public abstract class BaseActivity<P extends BaseContract.BasePresenter> extends RxAppCompatActivity implements BaseContract.BaseView {
    protected Activity mContext;
    protected P mPresenter;
//    private Unbinder mUnBinder;
//    private ProgressDialog mDialog;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getActivityLayoutID());
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //初始化butterknife
//        mUnBinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mContext = this;
        attachView();
        initView();
        initDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
    }
    /**
     * 挂载view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 卸载view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
    /**
     * 初始化View
     */
    protected abstract void initView();
    /**
     * 初始化View
     */
    protected abstract void initDate();
    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();
    /**
     * 设置Activity的布局ID
     *
     * @return
     */
    protected abstract int getActivityLayoutID();

    @Override
    public void showLoading() {
//        mDialog = new ProgressDialog(this);
//        mDialog.show();
    }

    @Override
    public void hideLoading() {
//        if (mDialog != null && mDialog.isShowing()) {
//            mDialog.dismiss();
//        }
    }

    @Override
    public void showSuccess(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showFailed(String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void showNoNet() {
        ToastUtils.showShort("无网络");
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }
}
