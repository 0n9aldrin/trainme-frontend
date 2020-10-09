package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;
import com.trainme.jerald.frontend.dependencies.response.model.SparringModel;

import java.util.List;

public class HistorySparringAdapter extends RecyclerView.Adapter<HistorySparringAdapter.MyViewHolder> {
    private List<SparringModel> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    private OnClickListener onClickItem;
    public HistorySparringAdapter(Context context, List<SparringModel> listData, OnClickListener onClickItem) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem = onClickItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sparring_layout, parent, false);
        return new HistorySparringAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SparringModel data = listData.get(position);
        holder.tournamentName.setText(data.getTitle());
        holder.date.setText("Sparring Date: "+data.getSparingDate());
        holder.address.setText("Address: "+data.getAddress());
        holder.description.setText("Description: "+data.getDescription());
        holder.level.setVisibility(View.VISIBLE);
        holder.level.setText("Level: "+data.getLevel());
        holder.cardView.setOnClickListener(v -> onClickItem.OnClickItem(data));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tournamentName;
        TextView address;
        TextView description;
        TextView date;
        CardView cardView;
        TextView level;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tournamentName = itemView.findViewById(R.id.tournamentName);
            address = itemView.findViewById(R.id.address);
            level = itemView.findViewById(R.id.level);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }
    }

    public interface OnClickListener {
        void OnClickItem(SparringModel data);
    }
}
