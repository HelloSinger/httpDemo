package com.ayd.httpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayd.httpdemo.R;
import com.ayd.httpdemo.bean.GzhDataBean;

import java.util.List;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2020年05月26日 11:03
 */

public class ListViewAdaapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<GzhDataBean.DataBean> dataBeans;

    public ListViewAdaapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    public void setDataBeans(List<GzhDataBean.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeans == null ? 0 : dataBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list_layout, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(dataBeans.get(position).getName());
        holder.tv_content.setText(dataBeans.get(position).getLink());
        return convertView;
    }


    class MyViewHolder {
        private TextView tv_name, tv_content;

        public MyViewHolder(View itemView) {
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_content=itemView.findViewById(R.id.tv_content);
        }
    }
}
