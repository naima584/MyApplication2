package com.bunny.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Ticket extends AppCompatActivity {
    private EditText typeTET,quantityTET,dateTET,contactTET,priceTET,vehicleTET;

    private Button postBTN;
    private String type,quantity,vehicle,date,price,contact;
    private DatabaseReference databaseReference;
    private List<Ticpost>ticposts;
    private PostAdapter postAdapter;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private String userId;
    Button sale;
    Button post;
    LinearLayout postL;

    String ticpostId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        init();

        //userId=firebaseAuth.getCurrentUser().getUid();

        sale = findViewById(R.id.exchangeviewBTN1);
        post = findViewById(R.id.saleviewBTN1);
        postL = findViewById(R.id.saleL);
        recyclerView=findViewById(R.id.postrecyclerRV);


        dateTET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        userId="ticpostss";


        postBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                type=typeTET.getText().toString();
                quantity=quantityTET.getText().toString();
                vehicle=vehicleTET.getText().toString();
                date=dateTET.getText().toString();
                contact=contactTET.getText().toString();
                price=priceTET.getText().toString();

                if (type.length()!=0 && quantity.length()!=0 && vehicle.length()!=0 && date.length()!=0 && contact.length()!=0 && price.length()!=0){
                    saveTicket(type,quantity,vehicle,date,price,contact);
                    typeTET.setText("");
                    quantityTET.setText("");
                    dateTET.setText("");
                    contactTET.setText("");
                    priceTET.setText("");
                    vehicleTET.setText("");

                    recyclerView.setVisibility(View.VISIBLE);
                    postL.setVisibility(View.GONE);
                }
                else {
                    Toast.makeText(Ticket.this, "Invalid input", Toast.LENGTH_LONG).show();


                }






            }
        });




        getTicPost();
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

    private void saveTicket(String type, String quantity, String vehicle, String date, String price, String contact) {

        DatabaseReference ticketRef=databaseReference.child("users").child(userId);

        String ticpostId=ticketRef.push().getKey();

        Ticpost ticpost=new Ticpost(type,quantity,vehicle,date,price,contact,ticpostId);
        ticketRef.child(ticpostId).setValue(ticpost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Ticket.this, "post successful", Toast.LENGTH_LONG).show();
                }

            }
        });


    }



    private void openDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month =month+1;
                String currentDate=year+"/"+month+"/"+day;
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                Date date=null;
                try {
                    date=simpleDateFormat.parse(currentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dateTET.setText(simpleDateFormat.format(date));



            }
        };
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this,onDateSetListener,year,month,day);
        datePickerDialog.show();
    }






    private void getTicPost() {

        DatabaseReference ticketRef=databaseReference.child("users").child("ticpostss");
        ticketRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    ticposts.clear();
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                         Ticpost ticpost= data.getValue(Ticpost.class);
                         ticposts.add(ticpost);
                         postAdapter.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {
        ticposts=new ArrayList<>();
        recyclerView=findViewById(R.id.postrecyclerRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postAdapter=new PostAdapter(ticposts,this);
        recyclerView.setAdapter(postAdapter);
        typeTET=findViewById(R.id.typeTET);
        quantityTET=findViewById(R.id.quantityTET);
        vehicleTET=findViewById(R.id.vehicleTET);
        dateTET=findViewById(R.id.dateTET);
        priceTET=findViewById(R.id.priceTET);
        contactTET=findViewById(R.id.contactTET);


        postBTN=findViewById(R.id.postBTN);
        databaseReference=FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();



    }


}
