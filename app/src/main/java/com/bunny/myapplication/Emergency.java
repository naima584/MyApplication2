package com.bunny.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class Emergency extends AppCompatActivity {
    private EditText helptypeET,helpquantityET,buildET,floorET,contacthelpET;
    private Button posthelpBTN;
    private String helptype,helpquan,helpbuild,helpfloor,helpcontact;
    private DatabaseReference databaseReference;
    private List<Emergencypost> emergencyposts;
    private EmeregencyAdapter emeregencyAdapter;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private String userId;

    Button sale;
    Button post;
    LinearLayout postL;
    String emergencypostId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);



        sale = findViewById(R.id.exchangeviewBTNemr);
        post = findViewById(R.id.saleviewBTNemr);
        postL = findViewById(R.id.postVL);
        recyclerView=findViewById(R.id.postrecyclerRV);

        init();
        userId="helppostss";
        posthelpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helptype=helptypeET.getText().toString();
                helpquan=helpquantityET.getText().toString();
                helpbuild=buildET.getText().toString();
                helpfloor=floorET.getText().toString();
                helpcontact=contacthelpET.getText().toString();

                saveHelp(helptype,helpquan,helpbuild,helpfloor,helpcontact);

            }
        });
        getHelpPost();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                recyclerView.setVisibility(View.VISIBLE);
                postL.setVisibility(View.GONE);


            }
        });
        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                recyclerView.setVisibility(View.GONE);
                postL.setVisibility(View.VISIBLE);


            }
        });


    }

    private void getHelpPost() {

        DatabaseReference helpRef=databaseReference.child("users").child("helppostss");

        helpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    emergencyposts.clear();
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Emergencypost emergencypost= data.getValue(Emergencypost.class);
                        emergencyposts.add(emergencypost);
                        emeregencyAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {




            }
        });
    }

    private void saveHelp(String helptype, String helpquan, String helpbuild, String helpfloor, String helpcontact) {

        DatabaseReference helpRef=databaseReference.child("users").child(userId);

        String emergencypostId=helpRef.push().getKey();

        Emergencypost emergencypost=new Emergencypost(helptype,helpquan,helpbuild,helpfloor,helpcontact,emergencypostId);
        helpRef.child(emergencypostId).setValue(emergencypost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Emergency.this, "post successful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void init() {

        emergencyposts=new ArrayList<>();
        recyclerView=findViewById(R.id.helprecyclerRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emeregencyAdapter=new EmeregencyAdapter(emergencyposts,this);
        recyclerView.setAdapter(emeregencyAdapter);
        helptypeET=findViewById(R.id.helptypeET);
        helpquantityET=findViewById(R.id.helpquantityET);
        buildET=findViewById(R.id.buildET);
        floorET=findViewById(R.id.floorET);
        contacthelpET=findViewById(R.id.contacthelpET);

        posthelpBTN=findViewById(R.id.posthelpBTN);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();




    }
}
