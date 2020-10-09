package com.trainme.jerald.frontend.components.showimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;

import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.joanzapata.iconify.widget.IconTextView;
import com.trainme.jerald.frontend.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewActivity extends AppCompatActivity {
    public static final String URL_KEY_PREVIEW = "key_url_img";
    public static final String RESOURCE_KEY = "key_res_id";

    @BindView(R.id.previewImg)
    ImageView imgContainer;
    @BindView(R.id.actionRotate)
    IconTextView actionRotate;

    String imgUrl;
    int res_id;

    public static Intent createIntent(Context context, String url) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra(URL_KEY_PREVIEW, url);

        return intent;
    }

    public static Intent createIntent(Context context, int resource_id) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra(RESOURCE_KEY, resource_id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        ButterKnife.bind(this);

//        SimpleTarget target = new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                imgContainer.setImage(ImageSource.bitmap(resource));
//            }
//        };

        if (getIntent() != null) {
            if (getIntent().getStringExtra(URL_KEY_PREVIEW) != null) {
                imgUrl = getIntent().getStringExtra(URL_KEY_PREVIEW);

                Glide.with(getApplicationContext())
                        .load(imgUrl)
                        .into(imgContainer);

            } else if (getIntent().getIntExtra(RESOURCE_KEY, 0) != 0) {
                res_id = getIntent().getIntExtra(RESOURCE_KEY, R.drawable.placeholder);
                Glide.with(getApplicationContext())
                        .load(res_id)
                        .into(imgContainer);
            }
        }

//        actionRotate.setOnClickListener(v -> {
//            imgContainer.setOrientation((imgContainer.getOrientation() + 90) % 360);
//        });
    }
}
