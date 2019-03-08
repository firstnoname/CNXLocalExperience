package com.example.ifirst.cnxlocalexperience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.LocalExperienceActivity.LocalExperienceDetail;
import com.example.ifirst.cnxlocalexperience.Model.LocalExperience;
import com.example.ifirst.cnxlocalexperience.Promotion.PromotionDetail;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=94rCjYxvzEE

public class DashboardSlideRecyclerAdapter extends RecyclerView.Adapter<DashboardSlideRecyclerAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private List<LocalExperience> mData;
    RequestOptions options;

    public DashboardSlideRecyclerAdapter(Context mContext, List<LocalExperience> mData) {
        this.mData = mData;
        this.mContext = mContext;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_dashboard_slide, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.txtSlide.setText(mData.get(i).getLe_name_eng());
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(viewHolder.imgSlide);
        //Log.d("image", mData.get(i).getImg_1());

        viewHolder.imgSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, LocalExperienceDetail.class);
                i.putExtra("name_eng", mData.get(viewHolder.getAdapterPosition()).getLe_name_eng());
                i.putExtra("name_ch", mData.get(viewHolder.getAdapterPosition()).getLe_name_ch());
                i.putExtra("img1", mData.get(viewHolder.getAdapterPosition()).getImg_1());
                i.putExtra("img2", mData.get(viewHolder.getAdapterPosition()).getImg_2());
                i.putExtra("img3", mData.get(viewHolder.getAdapterPosition()).getImg_3());
                i.putExtra("img4", mData.get(viewHolder.getAdapterPosition()).getImg_4());
                i.putExtra("img5", mData.get(viewHolder.getAdapterPosition()).getImg_5());
                i.putExtra("type", mData.get(viewHolder.getAdapterPosition()).getLe_type());
                i.putExtra("activity", mData.get(viewHolder.getAdapterPosition()).getLe_activity());
                i.putExtra("time", mData.get(viewHolder.getAdapterPosition()).getLe_time());
                i.putExtra("tel", mData.get(viewHolder.getAdapterPosition()).getLe_tel());
                i.putExtra("car_park", mData.get(viewHolder.getAdapterPosition()).getLe_car_park());
                i.putExtra("address", mData.get(viewHolder.getAdapterPosition()).getLe_address());
                i.putExtra("latitude", mData.get(viewHolder.getAdapterPosition()).getLe_latitude());
                i.putExtra("longitude", mData.get(viewHolder.getAdapterPosition()).getLe_longitude());
                i.putExtra("website", mData.get(viewHolder.getAdapterPosition()).getLe_website());
                i.putExtra("price", mData.get(viewHolder.getAdapterPosition()).getLe_price());

                mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSlide;
        TextView txtSlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSlide = itemView.findViewById(R.id.imgDashboardSlide);
            txtSlide = itemView.findViewById(R.id.txtDashboardSlide);
        }
    }
}
