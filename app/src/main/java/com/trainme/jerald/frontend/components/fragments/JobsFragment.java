package com.trainme.jerald.frontend.components.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trainme.jerald.frontend.dependencies.models.Jobs;
import com.trainme.jerald.frontend.components.adapters.JobsAdapter;
import com.trainme.jerald.frontend.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class JobsFragment extends Fragment {
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        private List<Jobs> jobsList = new ArrayList<>();
        private JobsAdapter mAdapter;


    public JobsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_jobs, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mAdapter = new JobsAdapter(jobsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        Jobs job = new Jobs("Mad Max: Fury Road", "Action & Adventure", "2015");
        jobsList.add(job);

        job = new Jobs("Inside Out", "Animation, Kids & Family", "2015");
        jobsList.add(job);

        job = new Jobs("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        jobsList.add(job);

        job = new Jobs("Shaun the Sheep", "Animation", "2015");
        jobsList.add(job);

        job = new Jobs("The Martian", "Science Fiction & Fantasy", "2015");
        jobsList.add(job);

        job = new Jobs("Mission: Impossible Rogue Nation", "Action", "2015");
        jobsList.add(job);

        job = new Jobs("Up", "Animation", "2009");
        jobsList.add(job);

        job = new Jobs("Star Trek", "Science Fiction", "2009");
        jobsList.add(job);

        job = new Jobs("The LEGO Movie", "Animation", "2014");
        jobsList.add(job);

        job = new Jobs("Iron Man", "Action & Adventure", "2008");
        jobsList.add(job);

        job = new Jobs("Aliens", "Science Fiction", "1986");
        jobsList.add(job);

        job = new Jobs("Chicken Run", "Animation", "2000");
        jobsList.add(job);

        job = new Jobs("Back to the Future", "Science Fiction", "1985");
        jobsList.add(job);

        job = new Jobs("Raiders of the Lost Ark", "Action & Adventure", "1981");
        jobsList.add(job);

        job = new Jobs("Goldfinger", "Action & Adventure", "1965");
        jobsList.add(job);

        job = new Jobs("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        jobsList.add(job);

        mAdapter.notifyDataSetChanged();
    }
}


