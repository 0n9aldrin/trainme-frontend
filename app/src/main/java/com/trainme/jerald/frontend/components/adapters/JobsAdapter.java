package com.trainme.jerald.frontend.components.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.models.Jobs;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.MyViewHolder> {

    private List<Jobs> jobsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre =  view.findViewById(R.id.genre);
            year =  view.findViewById(R.id.year);
        }
    }


    public JobsAdapter(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jobs_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Jobs jobs = jobsList.get(position);
        holder.title.setText(jobs.getTitle());
        holder.genre.setText(jobs.getGenre());
        holder.year.setText(jobs.getYear());
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }
}
