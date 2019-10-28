package com.ayd.httplib._.view;

import com.ayd.httplib._.base.BaseContract;
import com.ayd.httplib._.model.BookModel;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2019年10月23日 9:40
 */

public interface BookContract {


    interface View extends BaseContract.BaseView {
        void setBook(BookModel book);
    }

    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getBook( String p, String tag, String start, String end);
    }
}
