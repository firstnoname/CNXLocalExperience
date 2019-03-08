package com.example.ifirst.cnxlocalexperience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.LocalExperienceActivity.LocalExperienceDetail;
import com.example.ifirst.cnxlocalexperience.Model.LocalExperience;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class LocalExperienceAdapter extends RecyclerView.Adapter<LocalExperienceAdapter.LocalExperienceHolder> {

    private Context mContext;
    private List<LocalExperience> mData;
    RequestOptions options;

    public LocalExperienceAdapter(Context mContext, List<LocalExperience> mData) {
        this.mContext = mContext;
        this.mData = mData;

        //Request option for glide.
        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public LocalExperienceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_local_experience, viewGroup, false);

        final LocalExperienceHolder viewHolder = new LocalExperienceHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

//                Log.d("Position", String.valueOf(viewHolder.getAdapterPosition()));
//                Toast.makeText(mContext, String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalExperienceHolder localExperienceHolder, int i) {
        localExperienceHolder.txtName.setText(mData.get(i).getLe_name_eng());
        localExperienceHolder.txtDescInfo.setText(mData.get(i).getLe_activity());

        //Load image from the internet and set it into ImageView using Glide.
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(localExperienceHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class LocalExperienceHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtDescInfo;
        ImageView imgThumbnail;
        LinearLayout view_container;

        public LocalExperienceHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            txtName = itemView.findViewById(R.id.txtNameInfo);
            txtDescInfo = itemView.findViewById(R.id.txtDescInfo);
            imgThumbnail = itemView.findViewById(R.id.imgInfo);
        }
    }
}
