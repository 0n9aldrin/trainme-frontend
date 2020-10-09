package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.response.model.RequesterSparring;

import java.util.List;

public class RequesterCoachingAdapter extends RecyclerView.Adapter<RequesterCoachingAdapter.MyViewHolder> {
    private List<RequesterSparring> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    private OnClickListener onClickItem;
    public RequesterCoachingAdapter(Context context, List<RequesterSparring> listData, OnClickListener onClickItem) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem = onClickItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.requester_coaching_layout,
                parent, false);
        return new RequesterCoachingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RequesterSparring data = listData.get(position);
        holder.name.setText(data.getName());
        holder.phoneNumber.setText("Phone Number: "+data.getPhoneNumber());
        holder.status.setText("Status: "+data.getStatus());
        holder.notes.setText("Notes: "+data.getNotes());
        holder.pricePerHour.setText("Price Per Hour: Rp "+ data.getPrice());
        holder.experience.setText("Experience : "+ data.getExperience());
        Glide glide = null;
        glide.with(holder.image.getContext())
                .load(data.getImage())
                .thumbnail(.5f)
                .into(holder.image);
        holder.certificate.setOnClickListener(view -> {
            onClickItem.showImage(data.getCertificate());
        });
        holder.cardView.setOnClickListener(v -> onClickItem.OnClickItem(data));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phoneNumber;
        TextView status;
        TextView notes;
        TextView pricePerHour;
        TextView experience;
        ImageView image;
        Button certificate;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
            certificate = itemView.findViewById(R.id.certificate);
            name = itemView.findViewById(R.id.name);
            pricePerHour = itemView.findViewById(R.id.pricePerHour);
            experience = itemView.findViewById(R.id.experience);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            status = itemView.findViewById(R.id.status);
            notes = itemView.findViewById(R.id.notes);
        }
    }

    public interface OnClickListener {
        void OnClickItem(RequesterSparring data);
        void showImage(String url);
    }
}

