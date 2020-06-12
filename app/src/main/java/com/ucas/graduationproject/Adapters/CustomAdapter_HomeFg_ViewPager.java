package com.ucas.graduationproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ucas.graduationproject.Model.ActivitiesData;
import com.ucas.graduationproject.R;

import java.util.List;

public class CustomAdapter_HomeFg_ViewPager extends PagerAdapter {

    private List<ActivitiesData> data;
    private Context mCntext;
    private LayoutInflater layoutInflater;


    public CustomAdapter_HomeFg_ViewPager(List<ActivitiesData> data, Context mCntext) {
        this.data = data;
        this.mCntext = mCntext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(mCntext);

        View view = layoutInflater.inflate(R.layout.homefg_item_vp_cardview, container, false);
        ImageView img_vp;
        TextView title, description;

        img_vp = view.findViewById(R.id.item_img_Vp);
        title = view.findViewById(R.id.item_Vp_tv_title);
        description = view.findViewById(R.id.item_Vp_description);

//        img_vp.set(data.get(position).getImg());
        title.setText(data.get(position).getTitle());
        description.setText(data.get(position).getDescription());

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
