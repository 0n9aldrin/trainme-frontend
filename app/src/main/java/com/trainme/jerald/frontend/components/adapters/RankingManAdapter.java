package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.models.Ranking;
import com.trainme.jerald.frontend.dependencies.response.model.SparringInvitation;

import java.util.List;

public class RankingManAdapter extends RecyclerView.Adapter<RankingManAdapter.MyViewHolder> {
    private List<Ranking> listData;
    private LayoutInflater layoutInflater;
    Context mContext;
    private OnClickListener onClickItem;
    public RankingManAdapter(Context context, List<Ranking> listData, OnClickListener onClickItem) {
        mContext = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.onClickItem = onClickItem;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ranking, parent, false);
        return new RankingManAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Ranking data = listData.get(position);
        holder.age.setText("PA - "+ data.getAge());
        holder.link.setOnClickListener(v -> onClickItem.OnClickItem(data));
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView age;
        TextView link;

        public MyViewHolder(View itemView) {
            super(itemView);
            age = itemView.findViewById(R.id.age);
            link = itemView.findViewById(R.id.link);
        }
    }

    public interface OnClickListener {
        void OnClickItem(Ranking data);
    }
}
