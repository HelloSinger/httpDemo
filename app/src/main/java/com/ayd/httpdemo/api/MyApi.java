package com.ayd.httpdemo.api;


import com.ayd.httpdemo.bean.GzhDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2020年05月26日 11:12
 */

public interface MyApi {

    @GET("/friend/json")
    Observable<GzhDataBean> getDatas();
}
