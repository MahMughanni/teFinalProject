package com.ucas.graduationproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.graduationproject.Model.Item_NoteFg;
import com.ucas.graduationproject.R;

import java.util.List;

public class Custom_RvAdapter_NoteFg extends RecyclerView.Adapter<Custom_RvAdapter_NoteFg.mViewHolder> {

    private Context mContext;
    List<Item_NoteFg> data;


    public Custom_RvAdapter_NoteFg(Context mContext, List<Item_NoteFg> data) {
        this.mContext = mContext;
        this.data = data;

    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.notefg_item_rvlist, parent, false);

        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {

        holder.mNoteItemSubTitle.setText(data.get(position).getSubTitle());
        holder.mNoteItemStudName.setText(data.get(position).getName());
        holder.mNoteItemNoteTitle.setText(data.get(position).getNoteTilte());
        holder.mNoteItemDescription.setText(data.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        private TextView mNoteItemSubTitle;
        private TextView mNoteItemStudName;
        private TextView mNoteItemNoteTitle;
        private TextView mNoteItemDescription;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);

            mNoteItemSubTitle = itemView.findViewById(R.id.noteItem_subTilte);
            mNoteItemStudName = itemView.findViewById(R.id.noteItem_studName);
            mNoteItemNoteTitle = itemView.findViewById(R.id.noteItem_noteTitle);
            mNoteItemDescription = itemView.findViewById(R.id.noteItem_description);
        }
    }
}
