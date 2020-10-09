package com.trainme.jerald.frontend.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.showimage.PreviewActivity;
import com.trainme.jerald.frontend.dependencies.response.model.AdsModel;
import java.util.List;


public class ImageSlideAdapter extends PagerAdapter {

    Context mContext;
    List<AdsModel> products;

    public ImageSlideAdapter(Context mContext, List<AdsModel> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vp_image, container, false);

        ImageView mImageView = view
                .findViewById(R.id.image_display);
        mImageView.setOnClickListener(v -> {
            //Do something
            mContext.startActivity(PreviewActivity.createIntent(mContext, products.get(position).getImage()));
        });
        Glide.with(mContext).load((products.get(position)).getImage()).into(mImageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}


