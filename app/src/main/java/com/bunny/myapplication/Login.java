package com.bunny.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText emailET,passwordET,emailaddressET;
    private Button loginBTN;
    private FirebaseAuth firebaseAuth;
    String email ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailET=findViewById(R.id.emailET);
        emailaddressET=findViewById(R.id.emailaddressET);
        passwordET=findViewById(R.id.passwordET);
        loginBTN=findViewById(R.id.loginBTN);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(this,Exchange.class));
            finish();
        }
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 email=emailET.getText().toString().trim() ;
                final String diumail= email+emailaddressET.getText().toString().trim();
                final String email= emailET.getText().toString()+emailaddressET.getText().toString().trim();
                String password=passwordET.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "please enter email", Toast.LENGTH_LONG).show();
                    return;
                }if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "please enter password" , Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Login.this, "password is too short", Toast.LENGTH_SHORT).show();
                }




                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                                    editor.putString("emails", email);

                                    editor.apply();






                                    Intent intent=new Intent(Login.this,StudentInfo.class);
                                    startActivity(intent);
                                    finish();



                                } else {
                                    Toast.makeText(Login.this, "user not available", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });

            }
        });







    }

    public void createaccount(View view) {
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);

    }





}
