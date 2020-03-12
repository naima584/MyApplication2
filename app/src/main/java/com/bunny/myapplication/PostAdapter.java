package com.bunny.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Ticpost>tickposts;
    private Context context;

    public PostAdapter(List<Ticpost> tickposts, Context context) {
        this.tickposts = tickposts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Ticpost ticpost=tickposts.get(position);
        holder.typeTV.setText(ticpost.getTicketType());
        holder.quantityTV.setText(ticpost.getTicketQuantity());
        holder.vehicleTV.setText(ticpost.getVehicleName());
        holder.dateTV.setText(ticpost.getTicketDate());
        holder.priceTV.setText(ticpost.getTicketPrice());
        holder.contactTV.setText(ticpost.getTicketContact());

    }

    @Override
    public int getItemCount() {
        return tickposts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView typeTV,quantityTV,dateTV,priceTV,vehicleTV,contactTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTV=itemView.findViewById(R.id.typeTV);
            quantityTV=itemView.findViewById(R.id.quantityTV);
            dateTV=itemView.findViewById(R.id.dateTV);
            priceTV=itemView.findViewById(R.id.priceTV);
            vehicleTV=itemView.findViewById(R.id.vehicleTV);
            contactTV=itemView.findViewById(R.id.contactTV);

        }
    }
}
