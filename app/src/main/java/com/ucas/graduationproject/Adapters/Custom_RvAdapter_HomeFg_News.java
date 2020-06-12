package com.ucas.graduationproject.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucas.graduationproject.Model.ActivitiesData;

import com.ucas.graduationproject.Model.AdsData;
import com.ucas.graduationproject.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Custom_RvAdapter_HomeFg_News extends RecyclerView.Adapter<Custom_RvAdapter_HomeFg_News.mViewHolder> {

    private Context mContext;
    private ArrayList<ActivitiesData> data;
    private OnClickItemListener mOnClickItemListener;
    private ImageView img_vp;
    private ArrayList<AdsData> adsData = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public Custom_RvAdapter_HomeFg_News(Context mContext, ArrayList<ActivitiesData> data, OnClickItemListener mOnClickItemListener) {
        this.mContext = mContext;
        this.data = data;
        this.mOnClickItemListener = mOnClickItemListener;

    }


    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.homefg_item_rv, parent, false);

        return new mViewHolder(view, mOnClickItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        ActivitiesData activitiesData = data.get(position);

        holder.title.setText(activitiesData.getTitle());
        holder.description.setText(activitiesData.getDescription());

        String img = activitiesData.getImg();

        if (activitiesData.getImg().contains("$")) {
            img_vp.setVisibility(View.GONE);
            holder.sliderView.setVisibility(View.VISIBLE);

            segmentation(img, list);
            setUpSliderView(holder.sliderView);

        } else {
            img_vp.setVisibility(View.VISIBLE);
            holder.sliderView.setVisibility(View.GONE);

            Picasso.with(mContext)
                    .load(activitiesData.getImg())
                    .placeholder(mContext.getResources().getDrawable(R.drawable.default_image))
                    .error(mContext.getResources().getDrawable(R.drawable.default_image))
                    .into(img_vp);


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickItemListener.ClickItemListener(activitiesData, list);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder {

        private SliderView sliderView;
        private TextView title, description;

        OnClickItemListener mOnClickItemListener;


        public mViewHolder(@NonNull View itemView, OnClickItemListener mOnClickItemListener) {
            super(itemView);

            img_vp = itemView.findViewById(R.id.item_img_Vp);
            sliderView = itemView.findViewById(R.id.item_img_slider_Vp);
            title = itemView.findViewById(R.id.item_Vp_tv_title);
            description = itemView.findViewById(R.id.item_Vp_description);
            this.mOnClickItemListener = mOnClickItemListener;

        }

    }

    public interface OnClickItemListener {
        void ClickItemListener(ActivitiesData data, List<String> list);
    }

    private void setUpSliderView(SliderView sliderView) {

        for (int i = 0; i < list.size(); i++) {
            adsData.add(new AdsData(list.get(i)));
        }

        SliderAdapterHomeFragment sliderAdapter = new SliderAdapterHomeFragment(mContext, adsData);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    private void segmentation(String s, List<String> list) {
        StringTokenizer st = new StringTokenizer(s, "$");
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
    }
}



