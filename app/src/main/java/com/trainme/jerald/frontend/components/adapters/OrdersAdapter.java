package com.trainme.jerald.frontend.components.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.dependencies.models.Orders;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    private List<Orders> ordersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre =  view.findViewById(R.id.genre);
            year =  view.findViewById(R.id.year);
        }
    }


    public OrdersAdapter(List<Orders> moviesList) {
        this.ordersList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orders_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Orders movie = ordersList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
