package com.bunny.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StudentDetails extends AppCompatActivity {
    private EditText nameTV,idTV,batchTV,depTV,semTV,secTV;
    String stname,stid,stbatch,stdep,stsem,stsec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);





        //nameTV=findViewById(R.id.nameTV);
        idTV=findViewById(R.id.idTV);
        batchTV=findViewById(R.id.batchTV);
        depTV=findViewById(R.id.depTV);
        secTV=findViewById(R.id.secTV);
        semTV=findViewById(R.id.semTV);

        SharedPreferences sharedPreferences= getSharedPreferences("studentdetails", MODE_PRIVATE);
        //stname = sharedPreferences.getString("studentname", "No name defined");//"No name defined" is the default value.
        stid = sharedPreferences.getString("studentid", "No name defined");//"No name defined" is the default value.
        stbatch = sharedPreferences.getString("studentbatch", "No name defined");//"No name defined" is the default value.
        stdep = sharedPreferences.getString("studentdep", "No name defined");//"No name defined" is the default value.
        stsem = sharedPreferences.getString("studentsem", "No name defined");//"No name defined" is the default value.
        stsec = sharedPreferences.getString("studentsec", "No name defined");//"No name defined" is the default value.

       // nameTV.setText(stname);
        idTV.setText(stid);
        batchTV.setText(stbatch);
        depTV.setText(stdep);
        semTV.setText(stsem);
        secTV.setText(stsec);

    }
}
