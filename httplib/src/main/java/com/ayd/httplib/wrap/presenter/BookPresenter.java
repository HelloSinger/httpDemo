package com.ayd.httplib.wrap.presenter;

import android.annotation.SuppressLint;

import com.ayd.httplib.wrap.base.BasePresenter;
import com.ayd.httplib.wrap.contact.Contact;
import com.ayd.httplib.wrap.model.BookModel;
import com.ayd.httplib.wrap.net.ApiService;
import com.ayd.httplib.wrap.net.RetrofitHelper;
import com.ayd.httplib.wrap.net.RxSchedulers;
import com.ayd.httplib.wrap.view.BookContract;

import io.reactivex.functions.Consumer;

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
                        mView.setBook(bookModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
