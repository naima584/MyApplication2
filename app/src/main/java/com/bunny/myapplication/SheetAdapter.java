package com.bunny.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.ViewHolder> {

    private List<Sheetpost> sheetposts;
    private Context context;

    public SheetAdapter(List<Sheetpost> sheetposts, Context context) {
        this.sheetposts = sheetposts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_sheet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Sheetpost sheetpost=sheetposts.get(position);
        holder.nameSTV.setText(sheetpost.getSheetName());
        holder.quantitySTV.setText(sheetpost.getSheetQuantity());
        holder.topicSTV.setText(sheetpost.getSheetTopic());
        holder.priceSTV.setText(sheetpost.getSheetPrice());
        holder.sheetdetailsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,BookDetails.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sheetposts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  nameSTV,quantitySTV,topicSTV,priceSTV;
        private Button sheetdetailsBTN;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameSTV=itemView.findViewById(R.id.nameSTV);
            quantitySTV=itemView.findViewById(R.id.quantitySTV);
            topicSTV=itemView.findViewById(R.id.topicSTV);
            priceSTV=itemView.findViewById(R.id.priceSTV);
            sheetdetailsBTN=itemView.findViewById(R.id.sheetdetailsBTN);
        }
    }
}
