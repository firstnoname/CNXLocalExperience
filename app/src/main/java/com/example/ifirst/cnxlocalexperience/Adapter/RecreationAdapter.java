package com.example.ifirst.cnxlocalexperience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.LocalExperienceActivity.LocalExperienceDetail;
import com.example.ifirst.cnxlocalexperience.Model.Recreation;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class RecreationAdapter extends RecyclerView.Adapter<RecreationAdapter.RecreationHolder>  {
    private Context mContext;
    private List<Recreation> mData;
    RequestOptions options;

    public RecreationAdapter(Context mContext, List<Recreation> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }


    @NonNull
    @Override
    public RecreationAdapter.RecreationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_recreation, viewGroup, false);

        final RecreationHolder viewHolder = new RecreationHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, LocalExperienceDetail.class);
                i.putExtra("name_eng", mData.get(viewHolder.getAdapterPosition()).getRe_name_eng());
                i.putExtra("name_ch", mData.get(viewHolder.getAdapterPosition()).getRe_name_ch());
                i.putExtra("img1", mData.get(viewHolder.getAdapterPosition()).getImg_1());
                i.putExtra("img2", mData.get(viewHolder.getAdapterPosition()).getImg_2());
                i.putExtra("img3", mData.get(viewHolder.getAdapterPosition()).getImg_3());
                i.putExtra("img4", mData.get(viewHolder.getAdapterPosition()).getImg_4());
                i.putExtra("img5", mData.get(viewHolder.getAdapterPosition()).getImg_5());
                i.putExtra("type", mData.get(viewHolder.getAdapterPosition()).getRe_type());
                i.putExtra("activity", mData.get(viewHolder.getAdapterPosition()).getRe_activity());
                i.putExtra("time", mData.get(viewHolder.getAdapterPosition()).getRe_time());
                i.putExtra("tel", mData.get(viewHolder.getAdapterPosition()).getRe_tel());
                i.putExtra("car_park", mData.get(viewHolder.getAdapterPosition()).getRe_car_park());
                i.putExtra("address", mData.get(viewHolder.getAdapterPosition()).getRe_address());
                i.putExtra("latitude", mData.get(viewHolder.getAdapterPosition()).getRe_latitude());
                i.putExtra("longitude", mData.get(viewHolder.getAdapterPosition()).getRe_longitude());
                i.putExtra("website", mData.get(viewHolder.getAdapterPosition()).getRe_website());
                i.putExtra("price", mData.get(viewHolder.getAdapterPosition()).getRe_price());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecreationAdapter.RecreationHolder recreationHolder, int i) {
        recreationHolder.txtName.setText(mData.get(i).getRe_name_eng());
        recreationHolder.txtDescInfo.setText(mData.get(i).getRe_activity());

        //Load image from the internet and set it into ImageView using Glide.
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(recreationHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class RecreationHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescInfo;
        ImageView imgThumbnail;

        LinearLayout view_container;

        public RecreationHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            txtName = itemView.findViewById(R.id.txtNameInfo);
            txtDescInfo = itemView.findViewById(R.id.txtDescInfo);
            imgThumbnail = itemView.findViewById(R.id.imgInfo);
        }
    }

}
