package com.bunny.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StudentInfo extends AppCompatActivity {

    private EditText nameSET,idSET,deptSET,batchSET,semSET,secSET;
    String sname,sid,sdep,sbatch,ssem,ssec;


    private Button saveBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

       // nameSET=findViewById(R.id.nameSET);
        idSET=findViewById(R.id.idSET);
        deptSET=findViewById(R.id.depSET);
        batchSET=findViewById(R.id.batchSET);
        semSET=findViewById(R.id.semSET);
        secSET=findViewById(R.id.secSET);
        saveBTN=findViewById(R.id.saveBTN);
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sname=nameSET.getText().toString().trim();
                sid=idSET.getText().toString().trim();
                sdep=deptSET.getText().toString().trim();
                sbatch=batchSET.getText().toString().trim();
                ssem=semSET.getText().toString().trim();
                ssec=secSET.getText().toString().trim();


                if (sid.length()!=0 && sdep.length()!=0 && sbatch.length()!=0 && ssem.length()!=0 && ssec.length()!=0){

                    SharedPreferences sharedPreferences=getSharedPreferences("studentdetails",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //editor.putString("Studentname",sname);
                    editor.putString("studentid",sid);
                    editor.putString("studentdep",sdep);
                    editor.putString("studentbatch",sbatch);
                    editor.putString("studentsem",ssem);
                    editor.putString("studentsec",ssec);
                    editor.apply();

                    Toast.makeText(StudentInfo.this, "saved your information succesfully", Toast.LENGTH_LONG).show();





                }
                else {
                    Toast.makeText(StudentInfo.this, "Invalid input", Toast.LENGTH_LONG).show();


                }










                Intent intent=new Intent(StudentInfo.this,Exchange.class);
                startActivity(intent);





            }
        });



    }


}
