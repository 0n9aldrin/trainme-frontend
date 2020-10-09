package com.trainme.jerald.frontend.components.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.models.InformasiUmum;

import java.util.List;

/**
 * Created by Marthin on 3/28/2018.
 */

public class InformasiUmumAdapter extends RecyclerView.Adapter<InformasiUmumAdapter.MyViewHolder> {
    private List<InformasiUmum> listData;
    private LayoutInflater layoutInflater;
    public static Context mContext;

    public InformasiUmumAdapter(Context context, List<InformasiUmum> listData) {
        mContext =context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_informasi, parent, false);
        return new InformasiUmumAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        InformasiUmum data = listData.get(position);
        holder.number.setText(data.getNomor()+".");
        holder.shortContent.setText(data.getKeterangan());
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView number;
        TextView shortContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            shortContent = itemView.findViewById(R.id.shortContent);
        }
    }

}