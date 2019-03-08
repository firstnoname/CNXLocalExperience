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
import com.example.ifirst.cnxlocalexperience.Model.FavoriteFood;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class FavoriteFoodAdapter extends RecyclerView.Adapter<FavoriteFoodAdapter.FavoriteFoodHolder> {
    private Context mContext;
    private List<FavoriteFood> mData;
    RequestOptions options;

    public FavoriteFoodAdapter(Context mContext, List<FavoriteFood> mData) {
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public FavoriteFoodAdapter.FavoriteFoodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_recreation, viewGroup, false);
        final FavoriteFoodHolder viewHolder = new FavoriteFoodHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, LocalExperienceDetail.class);
                i.putExtra("name_eng", mData.get(viewHolder.getAdapterPosition()).getFf_name_eng());
                i.putExtra("name_ch", mData.get(viewHolder.getAdapterPosition()).getFf_name_ch());
                i.putExtra("img1", mData.get(viewHolder.getAdapterPosition()).getImg_1());
                i.putExtra("img2", mData.get(viewHolder.getAdapterPosition()).getImg_2());
                i.putExtra("img3", mData.get(viewHolder.getAdapterPosition()).getImg_3());
                i.putExtra("img4", mData.get(viewHolder.getAdapterPosition()).getImg_4());
                i.putExtra("img5", mData.get(viewHolder.getAdapterPosition()).getImg_5());
                i.putExtra("type", mData.get(viewHolder.getAdapterPosition()).getFf_type());
                i.putExtra("activity", mData.get(viewHolder.getAdapterPosition()).getFf_activity());
                i.putExtra("time", mData.get(viewHolder.getAdapterPosition()).getFf_time());
                i.putExtra("tel", mData.get(viewHolder.getAdapterPosition()).getFf_tel());
                i.putExtra("car_park", mData.get(viewHolder.getAdapterPosition()).getFf_car_park());
                i.putExtra("address", mData.get(viewHolder.getAdapterPosition()).getFf_address());
                i.putExtra("latitude", mData.get(viewHolder.getAdapterPosition()).getFf_latitude());
                i.putExtra("longitude", mData.get(viewHolder.getAdapterPosition()).getFf_longitude());
                i.putExtra("website", mData.get(viewHolder.getAdapterPosition()).getFf_website());
                i.putExtra("price", mData.get(viewHolder.getAdapterPosition()).getFf_price());

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFoodAdapter.FavoriteFoodHolder favoriteFoodHolder, int i) {
        favoriteFoodHolder.txtName.setText(mData.get(i).getFf_name_eng());
        favoriteFoodHolder.txtDescInfo.setText(mData.get(i).getFf_activity());
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(favoriteFoodHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class FavoriteFoodHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtDescInfo;
        ImageView imgThumbnail;

        LinearLayout view_container;

        public FavoriteFoodHolder(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            txtName = itemView.findViewById(R.id.txtNameInfo);
            txtDescInfo = itemView.findViewById(R.id.txtDescInfo);
            imgThumbnail = itemView.findViewById(R.id.imgInfo);
        }
    }
}
