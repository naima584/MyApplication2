package com.bunny.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sheet extends AppCompatActivity {

    private EditText nameSET,quantitySET,topicSET,priceSET;
    private Button postSBTN;
    private String sname,squantity,stopic,sprice;
    private DatabaseReference databaseReference;
    private List<Sheetpost> sheetposts;
    private SheetAdapter sheetAdapter;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    TextView  sheetinfo;
    private String userId;
    String emailid;
    String sheetpostId;
     Button sheetsaleBTN;
     Button  sheetpostBTN;
    LinearLayout viewLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        sheetinfo=findViewById(R.id.sheetinfo);

        sheetsaleBTN = findViewById(R.id.sheetsaleBTN);

        sheetpostBTN = findViewById(R.id.sheetpostBTN);
        viewLL = findViewById(R.id.sheetpostLL);

        init();

        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        emailid = prefs.getString("emails", "No name defined");//"No name defined" is the default value.


        userId="sheetpostss";
        postSBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname=nameSET.getText().toString();
                squantity=quantitySET.getText().toString();
                stopic=topicSET.getText().toString();
                sprice=priceSET.getText().toString();
                saveBook(sname,squantity,stopic,sprice);

            }
        });
        getSheetPost();

        sheetpostBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.GONE);
                viewLL.setVisibility(View.VISIBLE);


            }
        });

        sheetsaleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.VISIBLE);
                viewLL.setVisibility(View.GONE);



            }
        });

    }

    private void getSheetPost() {

        DatabaseReference sheetRef=databaseReference.child("users").child("sheetpostss");
        sheetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Sheetpost sheetpost= data.getValue(Sheetpost.class);
                        sheetposts.add(sheetpost);
                        sheetAdapter.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveBook(String sname, String squantity, String stopic, String sprice) {

        DatabaseReference sheetRef=databaseReference.child("users").child(userId);
        String sheetpostId=sheetRef.push().getKey();
        Sheetpost sheetpost=new Sheetpost(sname,squantity,stopic,sprice,sheetpostId);
        sheetRef.child(sheetpostId).setValue(sheetpost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sheet.this, "post successful", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private void init() {

        sheetposts=new ArrayList<>();
        recyclerView=findViewById(R.id.sheetrecyclerRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetAdapter=new SheetAdapter(sheetposts,this);
        recyclerView.setAdapter(sheetAdapter);
        nameSET=findViewById(R.id.nameSET);
        quantitySET=findViewById(R.id.quantitySET);
        topicSET=findViewById(R.id.topicSET);
        priceSET=findViewById(R.id.priceSET);
        postSBTN=findViewById(R.id.postSBTN);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();


    }



    }

