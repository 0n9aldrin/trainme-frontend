package com.trainme.jerald.frontend.components.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.trainme.jerald.frontend.dependencies.models.Orders;
import com.trainme.jerald.frontend.components.adapters.OrdersAdapter;
import com.trainme.jerald.frontend.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<Orders> orderslist = new ArrayList<>();
    private OrdersAdapter mAdapter;


    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new OrdersAdapter(orderslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        Orders order = new Orders("Mad Max: Fury Road", "Action & Adventure", "2015");
        orderslist.add(order);

        order = new Orders("Inside Out", "Animation, Kids & Family", "2015");
        orderslist.add(order);

        order = new Orders("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        orderslist.add(order);

        order = new Orders("Shaun the Sheep", "Animation", "2015");
        orderslist.add(order);

        order = new Orders("The Martian", "Science Fiction & Fantasy", "2015");
        orderslist.add(order);

        order = new Orders("Mission: Impossible Rogue Nation", "Action", "2015");
        orderslist.add(order);

        order = new Orders("Up", "Animation", "2009");
        orderslist.add(order);

        order = new Orders("Star Trek", "Science Fiction", "2009");
        orderslist.add(order);

        order = new Orders("The LEGO Movie", "Animation", "2014");
        orderslist.add(order);

        order = new Orders("Iron Man", "Action & Adventure", "2008");
        orderslist.add(order);

        order = new Orders("Aliens", "Science Fiction", "1986");
        orderslist.add(order);

        order = new Orders("Chicken Run", "Animation", "2000");
        orderslist.add(order);

        order = new Orders("Back to the Future", "Science Fiction", "1985");
        orderslist.add(order);

        order = new Orders("Raiders of the Lost Ark", "Action & Adventure", "1981");
        orderslist.add(order);

        order = new Orders("Goldfinger", "Action & Adventure", "1965");
        orderslist.add(order);

        order = new Orders("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        orderslist.add(order);

        mAdapter.notifyDataSetChanged();
    }


}
