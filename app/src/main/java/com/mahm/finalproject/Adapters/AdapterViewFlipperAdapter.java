package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahm.finalproject.Model.AdsData;
import com.mahm.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterViewFlipperAdapter extends BaseAdapter {

    private ArrayList<AdsData> androidVersions;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public AdapterViewFlipperAdapter(Context context, ArrayList<AdsData> androidVersions) {
        this.mContext = context;
        this.androidVersions = androidVersions;
    }

    @Override
    public int getCount() {
        return androidVersions.size();
    }

    @Override
    public Object getItem(int position) {
        return androidVersions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (layoutInflater != null) {
            View v = layoutInflater.inflate(R.layout.row_view_item, parent, false);
            AdsData version = androidVersions.get(position);

            TextView textView = v.findViewById(R.id.textView);
            ImageView imageView = v.findViewById(R.id.imageView);
            textView.setText(version.getTitle());

            Picasso.with(mContext).load(version.getImage()).into(imageView);

            return v;
        }
        return null;
    }
}
