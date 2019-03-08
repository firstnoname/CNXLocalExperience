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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ifirst.cnxlocalexperience.Model.NewPromotion;
import com.example.ifirst.cnxlocalexperience.Promotion.PromotionDetail;
import com.example.ifirst.cnxlocalexperience.R;

import java.util.List;

public class PromotionRecyclerAdapter extends RecyclerView.Adapter<PromotionRecyclerAdapter.PromotionRecyclerHolder> {
    private Context mContext;
    private List<NewPromotion> mData;
    RequestOptions options;

    public PromotionRecyclerAdapter(Context mContext, List<NewPromotion> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public PromotionRecyclerAdapter.PromotionRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_promo_slide, viewGroup, false);

        return new PromotionRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PromotionRecyclerAdapter.PromotionRecyclerHolder promotionRecyclerHolder, final int i) {
        //promotionRecyclerHolder.txtPromotionCode.setText(mData.get(i).getPromoCode());
        Glide.with(mContext).load(mData.get(i).getPromoImgPercent()).apply(options).into(promotionRecyclerHolder.imgPromotion);
        //promotionRecyclerHolder.txtPromotionType.setText(mData.get(i).getPromoType());

        promotionRecyclerHolder.imgPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PromotionDetail.class);
                i.putExtra("promo_img", mData.get(promotionRecyclerHolder.getAdapterPosition()).getPromoImgPercent());
                i.putExtra("promo_info", mData.get(promotionRecyclerHolder.getAdapterPosition()).getPromoDesc());
                i.putExtra("promo_code", mData.get(promotionRecyclerHolder.getAdapterPosition()).getPromoCode());
                i.putExtra("promo_type", mData.get(promotionRecyclerHolder.getAdapterPosition()).getPromoType());
                i.putExtra("promo_expire", mData.get(promotionRecyclerHolder.getAdapterPosition()).getPromoExpireDate());

                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class PromotionRecyclerHolder extends RecyclerView.ViewHolder{
        ImageView imgPromotion;
        TextView txtPromotionCode;
        //TextView txtPromotionType;


        public PromotionRecyclerHolder(@NonNull View itemView) {
            super(itemView);

            imgPromotion = itemView.findViewById(R.id.imgPromotion);
            //txtPromotionType = itemView.findViewById(R.id.txtPromoType);
        }
    }
}
