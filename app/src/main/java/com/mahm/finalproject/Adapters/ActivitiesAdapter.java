package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahm.finalproject.Model.ActivitiesData;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesHolder> {

    private ArrayList<ActivitiesData> data;
    private Context mContext;

    public ActivitiesAdapter(ArrayList<ActivitiesData> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ActivitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activities_desgin, parent, false);
        ActivitiesHolder holder = new ActivitiesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesHolder holder, int position) {
        ActivitiesData activitiesData = data.get(position);
        holder.tvTitleActivity.setText(activitiesData.getTitle());
        holder.tvDiscretionActivity.setText(activitiesData.getDescription());
        holder.ivActivity.setImageResource(activitiesData.getImg());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ActivitiesHolder extends RecyclerView.ViewHolder {

        TextView tvTitleActivity, tvDiscretionActivity;
        ImageView ivActivity;

        public ActivitiesHolder(@NonNull View itemView) {
            super(itemView);

            tvTitleActivity = itemView.findViewById(R.id.item_Vp_tv_title);
            tvDiscretionActivity = itemView.findViewById(R.id.item_Vp_description);
            ivActivity = itemView.findViewById(R.id.item_img_Vp);

        }

    }

}
