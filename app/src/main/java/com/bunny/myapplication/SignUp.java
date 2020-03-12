package com.bunny.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText emailET,passwordET,confirmpasswordET,nameET,diumailET;
    Button registerBTN;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        emailET=findViewById(R.id.emailET);
        diumailET=findViewById(R.id.diumailET);
        passwordET=findViewById(R.id.passwordET);
        nameET=findViewById(R.id.nameET);
        confirmpasswordET=findViewById(R.id.confirmpasswordET);
        registerBTN=findViewById(R.id.registerBTN);
        progressBar=findViewById(R.id.progressbar);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name=nameET.getText().toString().trim();
                final String email=emailET.getText().toString().trim()+diumailET.getText().toString().trim();
                String password=passwordET.getText().toString().trim();
                String confirmPassword=confirmpasswordET.getText().toString().trim();



                if(TextUtils.isEmpty(name)){
                    Toast.makeText(SignUp.this, "please type your name", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUp.this, "please enter email", Toast.LENGTH_LONG).show();
                    return;
                }if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignUp.this, "please enter password" , Toast.LENGTH_LONG).show();
                    return;
                }if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUp.this, "please confirm password", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(SignUp.this, "password is too short", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(view.VISIBLE);
                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                                        editor.putString("emails", email);

                                        editor.apply();



                                        String userId=firebaseAuth.getCurrentUser().getUid();
                                        Map<String,Object> userMap=new HashMap<>();
                                        userMap.put("name",name);
                                        userMap.put("email",email);

                                        DatabaseReference userRef=databaseReference.child("users").child(userId);
                                        userRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(SignUp.this,StudentInfo.class);
                                                    startActivity(intent);
                                                    finish();


                                                }

                                            }
                                        });


                                    } else {
                                        Toast.makeText(SignUp.this, "authentication failed", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });

                }


            }
        });

    }
}
