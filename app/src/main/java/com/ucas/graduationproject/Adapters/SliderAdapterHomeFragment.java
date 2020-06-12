package com.ucas.graduationproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucas.graduationproject.Model.AdsData;
import com.ucas.graduationproject.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapterHomeFragment extends
        SliderViewAdapter<SliderAdapterHomeFragment.SliderAdapterVH> {

    private Context context;
    private List<AdsData> mSliderItems;

    public SliderAdapterHomeFragment(Context context,List<AdsData> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        AdsData sliderItem = mSliderItems.get(position);

        viewHolder.textViewDescription.setText(sliderItem.getTitle());

        Picasso.with(context).load(sliderItem.getImage())
                .placeholder(context.getResources().getDrawable(R.drawable.default_image))
                .error(context.getResources().getDrawable(R.drawable.default_image))
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageView);
            textViewDescription = itemView.findViewById(R.id.textView);
        }
    }

}
