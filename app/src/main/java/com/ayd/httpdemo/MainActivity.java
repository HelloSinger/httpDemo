package com.ayd.httpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ayd.httpdemo.adapter.ListViewAdaapter;
import com.ayd.httpdemo.bean.GzhDataBean;
import com.ayd.httpdemo.presenter.GZHPresenter;
import com.ayd.httpdemo.view.GZHViewContract;
import com.ayd.httplib.wrap.base.BaseActivity;
import com.ayd.httplib.wrap.base.BaseContract;

import org.jetbrains.annotations.Nullable;


public class MainActivity extends BaseActivity implements GZHViewContract.GzhView, View.OnClickListener {

    private Button btn_show;
    private ListView lv_show;
    private ListViewAdaapter listViewAdaapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(this);
        lv_show = findViewById(R.id.lv_show);
        listViewAdaapter = new ListViewAdaapter(this);

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected BaseContract.BasePresenter initPresenter() {
        return new GZHPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    public void setData(GzhDataBean data) {
        listViewAdaapter.setDataBeans(data.getData());
        lv_show.setAdapter(listViewAdaapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                ((GZHPresenter) mPresenter).getData();
                break;
        }
    }
}
