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

import com.ucas.graduationproject.Model.AdsData;
import com.ucas.graduationproject.Model.Item_TravelFg;
import com.ucas.graduationproject.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Custom_RvAdapter_TravelFg extends RecyclerView.Adapter<Custom_RvAdapter_TravelFg.mViewHodler> {


    private Context mContext;
    private ArrayList<Item_TravelFg> data;
    private ImageView travel_img;
    private ArrayList<AdsData> adsData = new ArrayList<>();
    private List<String> list = new ArrayList<>();

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
        Item_TravelFg item_travelFg = data.get(position);

        String img = item_travelFg.getImg();

        if (item_travelFg.getImg().contains("$")) {
            travel_img.setVisibility(View.GONE);
            holder.sliderView.setVisibility(View.VISIBLE);

            segmentation(img, list);
            setUpSliderView(holder.sliderView);
            holder.tv_title.setText(item_travelFg.getTitle());

        } else {
            travel_img.setVisibility(View.VISIBLE);
            holder.sliderView.setVisibility(View.GONE);

            Picasso.with(mContext).load(img)
                    .placeholder(mContext.getResources().getDrawable(R.drawable.default_image))
                    .error(mContext.getResources().getDrawable(R.drawable.default_image))
                    .into(travel_img);

            holder.tv_title.setText(item_travelFg.getTitle());

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mViewHodler extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private SliderView sliderView;

        public mViewHodler(@NonNull View itemView) {
            super(itemView);

            sliderView = itemView.findViewById(R.id.travelFg_img_slider_Vp);
            travel_img = itemView.findViewById(R.id.travelFg_img);
            tv_title = itemView.findViewById(R.id.travelFg_tv_title);
        }
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
