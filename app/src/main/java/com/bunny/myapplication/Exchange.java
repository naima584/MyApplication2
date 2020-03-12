package com.bunny.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Exchange extends AppCompatActivity {
    private GridLayout mainGrid;
    private FirebaseAuth firebaseAuth;
    private TextView logoutTV;
    String emailid;
    TextView mytv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        mytv = findViewById(R.id.myprofileTV);



        SharedPreferences prefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        emailid = prefs.getString("emails", "No name defined");//"No name defined" is the default value.
        mytv.setText(emailid);


        firebaseAuth=FirebaseAuth.getInstance();
        mainGrid=findViewById(R.id.mainGrid);
        logoutTV=(TextView)findViewById(R.id.logoutTV);
        logoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Exchange.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        setSingleEvent(mainGrid);


    }

    private void setSingleEvent(GridLayout mainGrid) {

        for(int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView=(CardView)mainGrid.getChildAt(i);
            final int finalI=i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        Intent intent=new Intent(Exchange.this,Ticket.class);
                        startActivity(intent);
                    }else if(finalI==1){
                        Intent intent=new Intent(Exchange.this,Book.class);
                        startActivity(intent);
                    }else if(finalI==2){
                        Intent intent=new Intent(Exchange.this,Sheet.class);
                        startActivity(intent);
                    } else if(finalI==3){
                        Intent intent=new Intent(Exchange.this,Emergency.class);
                        startActivity(intent);
                    }

                }
            });
        }

    }

    public void myprofile(View view) {

        Intent intent=new Intent(Exchange.this,StudentDetails.class);
        startActivity(intent);




    }


}
