package com.bunny.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {
    private String name,writter,edition;
    private int quantity,price;
    private TextView nameBDetailTV,quantityBDetailTV,writterBDetailTV,editionBDetailTV,priceBDetailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        init();
        if(getIntent().getExtras()!=null){
            name=getIntent().getStringExtra("name");
            quantity=getIntent().getIntExtra("quantity",0);
            writter=getIntent().getStringExtra("writter");
            edition=getIntent().getStringExtra("edition");
            price=getIntent().getIntExtra("price",0);



        }






    }

    private void init() {
        nameBDetailTV=findViewById(R.id.nameBDetailTV);
        quantityBDetailTV=findViewById(R.id.quantityBDetailTV);
        writterBDetailTV=findViewById(R.id.writterBDetailTV);
        editionBDetailTV=findViewById(R.id.editionBDetailTV);
        priceBDetailTV=findViewById(R.id.priceBDetailTV);


    }

    public void back(View view) {
        onBackPressed();
    }
}
