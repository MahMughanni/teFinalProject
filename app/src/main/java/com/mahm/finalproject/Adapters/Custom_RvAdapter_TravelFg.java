package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahm.finalproject.Model.Item_TravelFg;
import com.mahm.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custom_RvAdapter_TravelFg extends RecyclerView.Adapter<Custom_RvAdapter_TravelFg.mViewHodler> {


    private Context mContext;
    private ArrayList<Item_TravelFg> data;
    private ImageView travel_img;


    public Custom_RvAdapter_TravelFg(Context mContext, ArrayList<Item_TravelFg> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public mViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.travelfg_item_rv, parent, false);

        return new mViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHodler holder, int position) {

        Picasso.with(mContext).load(data.get(position).getImg()).into(travel_img);
        holder.tv_title.setText(data.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mViewHodler extends RecyclerView.ViewHolder {

        private TextView tv_title;

        public mViewHodler(@NonNull View itemView) {
            super(itemView);

            travel_img = itemView.findViewById(R.id.travelFg_img);
            tv_title = itemView.findViewById(R.id.travelFg_tv_title);
        }
    }
}
