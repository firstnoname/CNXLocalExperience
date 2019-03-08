package com.example.ifirst.cnxlocalexperience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.ifirst.cnxlocalexperience.FullScreenActivity;
import com.example.ifirst.cnxlocalexperience.MainActivity;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private String[] imageUrls;

    public ImagePagerAdapter(Context mContext, String[] imageUrls) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(mContext);
        Glide.with(mContext)
                .load(imageUrls[position])
                .into(imageView);

        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display full screen images when touched.
                Intent intent = new Intent(mContext,FullScreenActivity.class);
                intent.putExtra("imageUrls", imageUrls[position]);
                mContext.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
