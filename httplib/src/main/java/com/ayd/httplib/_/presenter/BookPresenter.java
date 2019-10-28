package com.ayd.httplib._.presenter;

import android.annotation.SuppressLint;

import com.ayd.httplib._.base.BasePresenter;
import com.ayd.httplib._.contact.Contact;
import com.ayd.httplib._.model.BookModel;
import com.ayd.httplib._.net.ApiService;
import com.ayd.httplib._.net.RetrofitHelper;
import com.ayd.httplib._.net.RxSchedulers;
import com.ayd.httplib._.view.BookContract;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2019年10月23日 9:39
 */

public class BookPresenter extends BasePresenter<BookContract.View> implements BookContract.Presenter {
    @SuppressLint("CheckResult")
    @Override
    public void getBook(String p, String tag, String start, String end) {

        RetrofitHelper.getInstance().initBaseUrl(Contact.BASE_URL).getServer(ApiService.class)
                .getBooks(p, tag, start, end)
                .compose(RxSchedulers.<BookModel>applySchedulers())
                .compose(mView.<BookModel>bindToLife())
                .subscribe(new Consumer<BookModel>() {
                    @Override
                    public void accept(BookModel bookModel) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
