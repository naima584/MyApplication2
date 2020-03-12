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
import android.widget.RadioButton;
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

public class Book extends AppCompatActivity {

    private EditText nameBET,quantityBET,writterBET,editionBET,priceBET,contactBET;
    private Button postBBTN;
    private String bname,bquantity,bwritter,bedition,bprice,bcontact;
    private DatabaseReference databaseReference;
    private List<Bookpost> bookposts;
    private BookAdapter bookAdapter;
    private FirebaseAuth firebaseAuth;
    private TextView detailTV;
    private RecyclerView recyclerView;
    private String userId;
    String bookpostId;
    TextView bookinfo;
    String emailid;
    Button salevbtn;
    Button postvbtn;
    LinearLayout postLayout;
    private TextView userdep,userid,usersec;
    String stdep,stid,stsec;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookinfo = findViewById(R.id.bookinfo);
        salevbtn = findViewById(R.id.saleviewBTN);
        contactBET=findViewById(R.id.contactBET);

        postvbtn = findViewById(R.id.exchangeviewBTN);
        postLayout = findViewById(R.id.postLayout);
        userdep=findViewById(R.id.userdep);
        userid=findViewById(R.id.userid);
        usersec=findViewById(R.id.usersec);

    //    recyclerView = findViewById(R.id.bookrecyclerRV);

        init();

        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        emailid = prefs.getString("emails", "No name defined");//"No name defined" is the default value.

        SharedPreferences sharedPreferences= getSharedPreferences("studentdetails", MODE_PRIVATE);
        stdep = sharedPreferences.getString("studentdep", "No name defined");//"No name defined" is the default value.
        stid = sharedPreferences.getString("studentid", "No name defined");//"No name defined" is the default value.

        stsec = sharedPreferences.getString("studentsec", "No name defined");//"No name defined" is the default value.

        userdep.setText(stdep);
        userid.setText(stid);
        usersec.setText(stsec);




        // getBookPost();
       // userId=firebaseAuth.getCurrentUser().getUid();
        userId="bookpostss";

       // bookinfo.setText(emailid);

        postBBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bname=nameBET.getText().toString();
                bquantity=quantityBET.getText().toString();
                bwritter=writterBET.getText().toString();
                bedition=editionBET.getText().toString();
                bprice=priceBET.getText().toString();
                bcontact=contactBET.getText().toString();


                saveBook(bname,bquantity,bwritter,bedition,bprice);

            }
        });
        getBookPost();



        salevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                recyclerView.setVisibility(View.VISIBLE);
                postLayout.setVisibility(View.GONE);



            }
        });
        postvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.GONE);
                postLayout.setVisibility(View.VISIBLE);


            }
        });



    }

    private void getBookPost() {


        DatabaseReference bookRef=databaseReference.child("users").child("bookpostss");
        bookRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        Bookpost bookpost= data.getValue(Bookpost.class);
                        bookposts.add(bookpost);
                        bookAdapter.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveBook(String bname, String bquantity, String bwritter, String bedition, String bprice) {

        DatabaseReference bookRef=databaseReference.child("users").child(userId);




       // String boll = bookinfo.getText().toString();


      String bookpostId=bookRef.push().getKey();
      //  bookpostId= boll ;


        Bookpost bookpost=new Bookpost(bname,bquantity,bwritter,bedition,bprice,bookpostId);


        //-Luh_jUjCTqyzJsmcgB8
        bookRef.child(bookpostId).setValue(bookpost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Book.this, "post successful", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void init() {
        bookposts=new ArrayList<>();
        recyclerView=findViewById(R.id.bookrecyclerRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter=new BookAdapter(bookposts,this);
        recyclerView.setAdapter(bookAdapter);
        nameBET=findViewById(R.id.nameBET);
        quantityBET=findViewById(R.id.quantityBET);
        writterBET=findViewById(R.id.writterBET);
        editionBET=findViewById(R.id.editionBET);
        priceBET=findViewById(R.id.priceBET);
        postBBTN=findViewById(R.id.postBBTN);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();


    }
}
