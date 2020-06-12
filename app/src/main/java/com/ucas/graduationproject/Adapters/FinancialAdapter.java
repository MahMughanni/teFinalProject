package com.ucas.graduationproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucas.graduationproject.Model.FinancialData;
import com.ucas.graduationproject.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FinancialAdapter extends RecyclerView.Adapter<FinancialAdapter.FinancialHolder> {

    private ArrayList<FinancialData> data;
    private Context context;

    public FinancialAdapter(ArrayList<FinancialData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public FinancialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_finacial_fragment_desgin, parent, false);
        FinancialHolder holder = new FinancialHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialHolder holder, int position) {
        FinancialData data1 = data.get(position);
        String replace = data1.getDate().replaceAll("-", "/");

        holder.tvCreditor.setText(data1.getCreditor() + "");
        holder.tvTotalBill.setText((int) data1.getTotalBill() + "");
        holder.tvDate.setText(replace);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FinancialHolder extends RecyclerView.ViewHolder {

        TextView tvCreditor, tvDate, tvTotalBill;

        public FinancialHolder(@NonNull View itemView) {
            super(itemView);

            tvCreditor = itemView.findViewById(R.id.credit_financial);
            tvTotalBill = itemView.findViewById(R.id.record_financial);
            tvDate = itemView.findViewById(R.id.date_financial);

        }
    }
}
