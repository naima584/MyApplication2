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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Bookpost>bookposts;
    private Context context;

    public BookAdapter(List<Bookpost> bookposts, Context context) {
        this.bookposts = bookposts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final  Bookpost bookpost=bookposts.get(position);
        holder.nameBTV.setText(bookpost.getBookName());
        holder.quantityBTV.setText(bookpost.getBookQuantity());
        holder.writterBTV.setText(bookpost.getBookWritter());
        holder.editionBTV.setText(bookpost.getBookEdition());
        holder.priceBTV.setText(bookpost.getBookPrice());
        holder.bookdetailsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,BookDetails.class);
                intent.putExtra("name",bookpost.getBookName());
                intent.putExtra("quantity",bookpost.getBookQuantity());
                intent.putExtra("writter",bookpost.getBookWritter());
                intent.putExtra("edition",bookpost.getBookEdition());
                intent.putExtra("price",bookpost.getBookPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookposts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameBTV,quantityBTV,writterBTV,editionBTV,priceBTV;
        private Button  bookdetailsBTN;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameBTV=itemView.findViewById(R.id.nameBTV);
            quantityBTV=itemView.findViewById(R.id.quantityBTV);
            writterBTV=itemView.findViewById(R.id.writterBTV);
            editionBTV=itemView.findViewById(R.id.editionBTV);
            priceBTV=itemView.findViewById(R.id.priceBTV);
            bookdetailsBTN=itemView.findViewById(R.id.bookdetailsBTN);

        }
    }
}
