package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.mahm.finalproject.Model.ActivitiesData;

import com.mahm.finalproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Custom_RvAdapter_HomeFg_News extends RecyclerView.Adapter<Custom_RvAdapter_HomeFg_News.mViewHolder> {

    private Context mContext;
    private ArrayList<ActivitiesData> data;
    OnClickItemListener mOnClickItemListener;
    private ImageView img_vp;



    public Custom_RvAdapter_HomeFg_News(Context mContext, ArrayList<ActivitiesData> data, OnClickItemListener mOnClickItemListener) {
        this.mContext = mContext;
        this.data = data;
        this.mOnClickItemListener = mOnClickItemListener;

    }

    public Custom_RvAdapter_HomeFg_News(Context mContext, ArrayList<ActivitiesData> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.homefg_item_rv, parent, false);

        return new mViewHolder(view, mOnClickItemListener);
    }



    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, description;

        OnClickItemListener mOnClickItemListener;


        public mViewHolder(@NonNull View itemView, OnClickItemListener mOnClickItemListener) {
            super(itemView);

            img_vp = itemView.findViewById(R.id.item_img_Vp);
            title = itemView.findViewById(R.id.item_Vp_tv_title);
            description = itemView.findViewById(R.id.item_Vp_description);
            this.mOnClickItemListener = mOnClickItemListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            mOnClickItemListener.ClickItemListener(getAdapterPosition());

        }
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());
        Picasso.get().load(data.get(position).getImg()).into(img_vp);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnClickItemListener {
        void ClickItemListener(int position);
    }
}



