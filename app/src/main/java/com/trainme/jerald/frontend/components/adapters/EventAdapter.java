package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.models.AllEvents;

import java.util.List;


/**
 * Created by Marthin on 3/28/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private List<AllEvents> listData;
    private LayoutInflater layoutInflater;
    public static Context mContext;

    private OnClickDataListener onClickItem;
    public EventAdapter(Context context, List<AllEvents> listData, OnClickDataListener onClickItem) {
        mContext =context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem =onClickItem;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_event, parent, false);
        return new EventAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AllEvents data = listData.get(position);
        holder.titleNews.setText(data.getTitle());
        holder.shortContent.setText(data.getShortDescription());
        String[] dateEvent = data.getStartDate().split(" ");
        holder.dateEvent.setText(dateEvent[0]);
        holder.locationEvent.setText(data.getLocation());
        Glide glide = null;
        glide.with(holder.imageNews.getContext())
                .load(data.getPoster())
                .thumbnail(.5f)
                .into(holder.imageNews);
        holder.cardView.setOnClickListener(v -> onClickItem.OnClickdata(data));
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNews;
        TextView titleNews;
        TextView shortContent;
        TextView dateEvent;
        TextView locationEvent;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            dateEvent = itemView.findViewById(R.id.dateEvent);
            cardView = itemView.findViewById(R.id.newsCardView);
            titleNews = itemView.findViewById(R.id.titleNews);
            shortContent = itemView.findViewById(R.id.shortContent);
            imageNews = itemView.findViewById(R.id.imageNews);
            locationEvent = itemView.findViewById(R.id.locationEvent);
        }
    }
    public interface OnClickDataListener {
        void OnClickdata(AllEvents allEvents);
    }
}