package com.mahm.finalproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahm.finalproject.Model.Item_HolidaysFg;
import com.mahm.finalproject.R;

import java.util.List;

public class C_RvAdapter_HolidaysFg extends RecyclerView.Adapter<C_RvAdapter_HolidaysFg.mViewHolder> {

    private Context mContext;
    private List<Item_HolidaysFg> data;


    public C_RvAdapter_HolidaysFg(Context mContext, List<Item_HolidaysFg> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.holidaysfg_item_rvlist, parent, false);

        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.description.setText(data.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class mViewHolder extends RecyclerView.ViewHolder {

        private TextView title, description;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.holidaysItem_tv_title);
            description = itemView.findViewById(R.id.holidaysItem_tv_description);

        }
    }
}
