package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.response.model.MyRequestSparringStatus;

import java.util.List;

public class CoachingRequestStatusAdapter extends RecyclerView.Adapter<CoachingRequestStatusAdapter.MyViewHolder> {
    private List<MyRequestSparringStatus> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    public CoachingRequestStatusAdapter(Context context, List<MyRequestSparringStatus> listData) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_request,
                parent, false);
        return new CoachingRequestStatusAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyRequestSparringStatus data = listData.get(position);
        holder.name.setText(data.getTitle());
        holder.createdAt.setText("Request At: "+data.getCreatedAt());
        holder.status.setText("Status: "+data.getStatus());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView createdAt;
        TextView status;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            name = itemView.findViewById(R.id.name);
            createdAt = itemView.findViewById(R.id.createdAt);
            status = itemView.findViewById(R.id.status);
        }
    }
}
