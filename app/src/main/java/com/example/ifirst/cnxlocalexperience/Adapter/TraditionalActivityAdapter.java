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
import com.example.ifirst.cnxlocalexperience.Model.TraditionalAct;
import com.example.ifirst.cnxlocalexperience.R;
import com.example.ifirst.cnxlocalexperience.TraditionalActivity.TraditionalActivityDetail;

import java.util.List;

public class TraditionalActivityAdapter extends RecyclerView.Adapter<TraditionalActivityAdapter.TraditionalActivityHolder> {

    private Context mContext;
    private List<TraditionalAct> mData;
    RequestOptions options;

    public TraditionalActivityAdapter(Context mContext, List<TraditionalAct> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public TraditionalActivityAdapter.TraditionalActivityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_local_experience, viewGroup, false);

        final TraditionalActivityHolder viewHolder = new TraditionalActivityHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, TraditionalActivityDetail.class);
                i.putExtra("name_eng", mData.get(viewHolder.getAdapterPosition()).getTa_name_eng());
                i.putExtra("name_ch", mData.get(viewHolder.getAdapterPosition()).getTa_name_ch());
                i.putExtra("img1", mData.get(viewHolder.getAdapterPosition()).getTa_img1());
                i.putExtra("img2", mData.get(viewHolder.getAdapterPosition()).getTa_img2());
                i.putExtra("img3", mData.get(viewHolder.getAdapterPosition()).getTa_img3());
                i.putExtra("img4", mData.get(viewHolder.getAdapterPosition()).getTa_img4());
                i.putExtra("img5", mData.get(viewHolder.getAdapterPosition()).getTa_img5());
                i.putExtra("month", mData.get(viewHolder.getAdapterPosition()).getTa_month());
                i.putExtra("type", mData.get(viewHolder.getAdapterPosition()).getTa_type());
                i.putExtra("activity", mData.get(viewHolder.getAdapterPosition()).getTa_activity());
                i.putExtra("time", mData.get(viewHolder.getAdapterPosition()).getTa_time());
                i.putExtra("tel", mData.get(viewHolder.getAdapterPosition()).getTa_tel());
                i.putExtra("car_park", mData.get(viewHolder.getAdapterPosition()).getTa_car_park());
                i.putExtra("address", mData.get(viewHolder.getAdapterPosition()).getTa_address());
                i.putExtra("latitude", mData.get(viewHolder.getAdapterPosition()).getTa_latitude());
                i.putExtra("longitude", mData.get(viewHolder.getAdapterPosition()).getTa_longitude());
                i.putExtra("website", mData.get(viewHolder.getAdapterPosition()).getTa_website());
                i.putExtra("price", mData.get(viewHolder.getAdapterPosition()).getTa_price());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TraditionalActivityAdapter.TraditionalActivityHolder traditionalActivityHolder, int i) {
        traditionalActivityHolder.tvName.setText(mData.get(i).getTa_name_eng());
        traditionalActivityHolder.tvActivity.setText(mData.get(i).getTa_activity());
        Glide.with(mContext).load(mData.get(i).getTa_img1()).apply(options).into(traditionalActivityHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TraditionalActivityHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvActivity;
        ImageView imgThumbnail;
        LinearLayout view_container;

        public TraditionalActivityHolder(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            tvName = itemView.findViewById(R.id.txtNameInfo);
            tvActivity = itemView.findViewById(R.id.txtDescInfo);
            imgThumbnail = itemView.findViewById(R.id.imgInfo);
        }
    }
}
