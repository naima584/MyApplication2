package com.bunny.myapplication;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmeregencyAdapter extends RecyclerView.Adapter<EmeregencyAdapter.ViewHolder> {

    private List<Emergencypost> emergencyposts;
    private Context context;

    public EmeregencyAdapter(List<Emergencypost> emergencyposts, Context context) {
        this.emergencyposts = emergencyposts;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_helppost,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final  Emergencypost emergencypost=emergencyposts.get(position);

        holder.helptypeTV.setText(emergencypost.getHelpType());
        holder.helpquantityTV.setText(emergencypost.getHelpQuantity());
        holder.buildTV.setText(emergencypost.getHelpBuild());
        holder.floorTV.setText(emergencypost.getHelpFloor());
        holder.helpcontactTV.setText(emergencypost.getHelpContact());


    }

    @Override
    public int getItemCount() {
        return emergencyposts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView helptypeTV,helpquantityTV,buildTV,floorTV,helpcontactTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            helptypeTV=itemView.findViewById(R.id.helptypeTV);
            helpquantityTV=itemView.findViewById(R.id.helpquantityTV);
            buildTV=itemView.findViewById(R.id.buildTV);
            floorTV=itemView.findViewById(R.id.floorTV);
            helpcontactTV=itemView.findViewById(R.id.helpcontactTV);
        }
    }
}
