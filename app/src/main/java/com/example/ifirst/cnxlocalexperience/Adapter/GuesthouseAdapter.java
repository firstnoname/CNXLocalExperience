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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.GuestHouseActivity.GuesthouseDetailActivity;
import com.example.ifirst.cnxlocalexperience.Model.Guesthouse;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class GuesthouseAdapter extends RecyclerView.Adapter<GuesthouseAdapter.GuesthouseHolder> {
    private Context mContext;
    private List<Guesthouse> mData;
    RequestOptions options;

    public GuesthouseAdapter(Context mContext, List<Guesthouse> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public GuesthouseAdapter.GuesthouseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_guesthouse, viewGroup, false);
        final GuesthouseHolder viewHolder = new GuesthouseHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, GuesthouseDetailActivity.class);
                i.putExtra("name_eng", mData.get(viewHolder.getAdapterPosition()).getGh_name_eng());
                i.putExtra("name_ch", mData.get(viewHolder.getAdapterPosition()).getGh_name_ch());
                i.putExtra("img1", mData.get(viewHolder.getAdapterPosition()).getImg_1());
                i.putExtra("img2", mData.get(viewHolder.getAdapterPosition()).getImg_2());
                i.putExtra("img3", mData.get(viewHolder.getAdapterPosition()).getImg_3());
                i.putExtra("img4", mData.get(viewHolder.getAdapterPosition()).getImg_4());
                i.putExtra("img5", mData.get(viewHolder.getAdapterPosition()).getImg_5());
                i.putExtra("gh_address", mData.get(viewHolder.getAdapterPosition()).getGh_address());
                i.putExtra("gh_overview", mData.get(viewHolder.getAdapterPosition()).getGh_overview());
                i.putExtra("gh_price", mData.get(viewHolder.getAdapterPosition()).getGh_access());
                i.putExtra("gh_neighborhood", mData.get(viewHolder.getAdapterPosition()).getGh_neighborhood());
                i.putExtra("gh_breakfast", mData.get(viewHolder.getAdapterPosition()).getGh_breakfast());
                i.putExtra("gh_airport", mData.get(viewHolder.getAdapterPosition()).getGh_address());
                i.putExtra("gh_public_transportation", mData.get(viewHolder.getAdapterPosition()).getGh_public_transportation());
                i.putExtra("gh_shopping", mData.get(viewHolder.getAdapterPosition()).getGh_shopping());
                i.putExtra("gh_hospital", mData.get(viewHolder.getAdapterPosition()).getGh_hospital());
                i.putExtra("gh_convenience_store", mData.get(viewHolder.getAdapterPosition()).getGh_convenience_store());
                i.putExtra("gh_cash_withdrawal", mData.get(viewHolder.getAdapterPosition()).getGh_cash_withdrawal());
                i.putExtra("gh_thai_cuisine", mData.get(viewHolder.getAdapterPosition()).getGh_thai_cuisine());
                i.putExtra("gh_asian_cuisine", mData.get(viewHolder.getAdapterPosition()).getGh_asian_cuisine());
                i.putExtra("gh_cafe", mData.get(viewHolder.getAdapterPosition()).getGh_cafe());
                i.putExtra("gh_languages", mData.get(viewHolder.getAdapterPosition()).getGh_languages());
                i.putExtra("gh_internet_access", mData.get(viewHolder.getAdapterPosition()).getGh_internet_access());
                i.putExtra("gh_thing_todo", mData.get(viewHolder.getAdapterPosition()).getGh_things_todo());
                i.putExtra("gh_dining", mData.get(viewHolder.getAdapterPosition()).getGh_dining());
                i.putExtra("gh_services", mData.get(viewHolder.getAdapterPosition()).getGh_services());
                i.putExtra("gh_access", mData.get(viewHolder.getAdapterPosition()).getGh_access());
                i.putExtra("gh_getting_around", mData.get(viewHolder.getAdapterPosition()).getGh_getting_around());
                i.putExtra("gh_most_popular_landmarks", mData.get(viewHolder.getAdapterPosition()).getGh_most_popular_landmarks());
                i.putExtra("gh_closest_landmarks", mData.get(viewHolder.getAdapterPosition()).getGh_closest_landmarks());
                i.putExtra("gh_children_extra_bed", mData.get(viewHolder.getAdapterPosition()).getGh_children_extra_bed());
                i.putExtra("gh_other", mData.get(viewHolder.getAdapterPosition()).getGh_other());
                i.putExtra("gh_website", mData.get(viewHolder.getAdapterPosition()).getGh_website());
                i.putExtra("gh_latitude", mData.get(viewHolder.getAdapterPosition()).getGh_latitude());
                i.putExtra("gh_longitude", mData.get(viewHolder.getAdapterPosition()).getGh_longitude());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuesthouseAdapter.GuesthouseHolder guesthouseHolder, int i) {
        guesthouseHolder.txtName.setText(mData.get(i).getGh_name_eng());
        guesthouseHolder.txtDescInfo.setText(mData.get(i).getGh_overview());
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(guesthouseHolder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class GuesthouseHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescInfo;
        ImageView imgThumbnail;
        LinearLayout view_container;

        public GuesthouseHolder(@NonNull View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            txtName = itemView.findViewById(R.id.txtNameInfo);
            txtDescInfo = itemView.findViewById(R.id.txtDescInfo);
            imgThumbnail = itemView.findViewById(R.id.imgInfo);
        }
    }
}
