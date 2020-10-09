package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class SparringAdapter extends RecyclerView.Adapter<SparringAdapter.MyViewHolder> {
    private List<SparringInvitation> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    private OnClickListener onClickItem;
    public SparringAdapter(Context context, List<SparringInvitation> listData, OnClickListener onClickItem) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem = onClickItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sparring_layout, parent, false);
        return new SparringAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SparringInvitation data = listData.get(position);
        holder.tournamentName.setText(data.getName());
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
        TextView date;
        TextView address;
        TextView description;
        TextView level;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            tournamentName = itemView.findViewById(R.id.tournamentName);
            date = itemView.findViewById(R.id.date);
            level = itemView.findViewById(R.id.level);
            address = itemView.findViewById(R.id.address);
            description = itemView.findViewById(R.id.description);
        }
    }

    public interface OnClickListener {
        void OnClickItem(SparringInvitation data);
    }
}
