package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahm.finalproject.Model.Item_HomeFg;
import com.mahm.finalproject.R;

import java.util.List;

public class CustomAdapter_HomeFg_ListNews extends BaseAdapter {

    List<Item_HomeFg> data;
    Context mContext;

    public CustomAdapter_HomeFg_ListNews(List<Item_HomeFg> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.homefg_item_list , null,false);
        ImageView img_vp;
        TextView title, description;

        img_vp = view.findViewById(R.id.item_img_Vp);
        title = view.findViewById(R.id.item_Vp_tv_title);
        description = view.findViewById(R.id.item_Vp_description);

        img_vp.setImageResource(data.get(position).getImg());
        title.setText(data.get(position).getTitle());
        description.setText(data.get(position).getDescription());

        return view;
    }
}
