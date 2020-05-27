package com.ayd.httpdemo.presenter;

import com.ayd.httpdemo.api.MyApi;
import com.ayd.httpdemo.bean.GzhDataBean;
import com.ayd.httpdemo.view.GZHViewContract;
import com.ayd.httplib.wrap.base.BasePresenter;
import com.ayd.httplib.wrap.net.RetrofitHelper;
import com.ayd.httplib.wrap.net.RxSchedulers;

import io.reactivex.functions.Consumer;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2020年05月26日 11:29
 */

public class GZHPresenter extends BasePresenter<GZHViewContract.GzhView> implements GZHViewContract.GzhPresenter {
    @Override
    public void getData() {
        RetrofitHelper.getInstance().initBaseUrl("https://www.wanandroid.com/").getServer(MyApi.class)
                .getDatas()
                .compose(RxSchedulers.<GzhDataBean>applySchedulers())
                .compose(mView.<GzhDataBean>bindToLife())
                .subscribe(new Consumer<GzhDataBean>() {
                    @Override
                    public void accept(GzhDataBean dataBean) throws Exception {
                        mView.setData(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }
}
