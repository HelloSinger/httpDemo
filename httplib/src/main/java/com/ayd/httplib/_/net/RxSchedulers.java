package com.ayd.httplib._.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.operators.observable.ObservableTimeInterval;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2019年10月22日 14:15
 */

public class RxSchedulers {

    static final ObservableTransformer observableTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return (upstream).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) observableTransformer;
    }
}
