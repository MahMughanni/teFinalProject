package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahm.finalproject.Model.ActivitiesData;

import com.mahm.finalproject.R;

import java.util.List;

public class C_RvAdapter_HomeFg_ListNews extends RecyclerView.Adapter<C_RvAdapter_HomeFg_ListNews.mViewHolder> {

    private Context mContext;
    private List<ActivitiesData> data;
    OnClickItemListener mOnClickItemListener;


    public C_RvAdapter_HomeFg_ListNews(Context mContext, List<ActivitiesData> data, OnClickItemListener mOnClickItemListener) {
        this.mContext = mContext;
        this.data = data;
        this.mOnClickItemListener = mOnClickItemListener;
    }

    public C_RvAdapter_HomeFg_ListNews(Context mContext, List<ActivitiesData> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.custom_activities_desgin, parent, false);

        return new mViewHolder(view, mOnClickItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        holder.img_vp.setImageResource(data.get(position).getImg());
        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());

    }
    

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, description;
        private ImageView img_vp;
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


    public interface OnClickItemListener {
        void ClickItemListener(int position);
    }
}



