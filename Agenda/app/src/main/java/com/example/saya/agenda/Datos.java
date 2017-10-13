package com.example.saya.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Datos extends AppCompatActivity {
    Button Save;
    EditText Name;
    EditText Email;
    EditText Twitter;
    EditText Phone;
    EditText Birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        Save = (Button) findViewById(R.id.btnSave);
        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Name = (EditText) findViewById(R.id.txtName);
                Email = (EditText) findViewById(R.id.txtEmail);
                Twitter = (EditText) findViewById(R.id.txtTwitter);
                Phone = (EditText) findViewById(R.id.txtPhone);
                Birthdate = (EditText) findViewById(R.id.txtBirthdate);
                    Contact student = new Contact();
                    student.set_Name(Name.getText().toString());
                    student.set_Email(Email.getText().toString());
                    student.set_Twitter(Twitter.getText().toString());
                    student.set_Phone(Phone.getText().toString());
                    student.set_Birthdate(Birthdate.getText().toString());
                    Intent Back = new Intent();
                    Back.putExtra("My-Contact", student);
                    setResult(RESULT_OK, Back);
                    finish();

            }
        });
    }

}
