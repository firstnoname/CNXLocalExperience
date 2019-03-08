package com.example.ifirst.cnxlocalexperience.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.Model.LocalExperience;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardRecyclerHolder> {
    private Context mContext;
    private List<LocalExperience> mData;
    RequestOptions options;

    public DashboardRecyclerAdapter(Context mContext, List<LocalExperience> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public DashboardRecyclerAdapter.DashboardRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.adapter_dashboard_slide, viewGroup, false);

        final DashboardRecyclerHolder viewHolder = new DashboardRecyclerHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerAdapter.DashboardRecyclerHolder dashboardRecyclerHolder, int i) {
        dashboardRecyclerHolder.txtTopSuggest.setText(mData.get(i).getLe_name_eng());
        Glide.with(mContext).load(mData.get(i).getImg_1()).apply(options).into(dashboardRecyclerHolder.imgTopSuggest);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DashboardRecyclerHolder extends RecyclerView.ViewHolder {

        LinearLayout view_container;
        ImageView imgTopSuggest;
        TextView txtTopSuggest;

        public DashboardRecyclerHolder(@NonNull View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            txtTopSuggest = itemView.findViewById(R.id.txtDashboardSlide);
            imgTopSuggest = itemView.findViewById(R.id.imgDashboardSlide);

        }
    }
}
