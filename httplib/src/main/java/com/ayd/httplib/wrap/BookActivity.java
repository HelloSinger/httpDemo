package com.ayd.httplib.wrap;

import com.ayd.httplib.wrap.base.BaseActivity;
import com.ayd.httplib.wrap.base.BaseContract;
import com.ayd.httplib.wrap.model.BookModel;
import com.ayd.httplib.wrap.view.BookContract;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2019年10月23日 10:37
 */

public class BookActivity extends BaseActivity implements BookContract.View {
    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getActivityLayoutID() {
        return 0;
    }

    @Override
    public void setBook(BookModel book) {

    }
}
