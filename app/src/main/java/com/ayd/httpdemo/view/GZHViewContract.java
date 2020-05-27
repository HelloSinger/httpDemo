package com.ayd.httpdemo.view;

import com.ayd.httpdemo.bean.GzhDataBean;
import com.ayd.httplib.wrap.base.BaseContract;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2020年05月26日 11:29
 */

public interface GZHViewContract {

    interface GzhView extends BaseContract.BaseView {
        void setData(GzhDataBean data);
    }

    interface GzhPresenter extends BaseContract.BasePresenter<GzhView> {
        void getData();
    }
}
