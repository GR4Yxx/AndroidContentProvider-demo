package com.example.contentpro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String>listdata;
    private Button contactsB,calB,smsB;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsB=findViewById(R.id.contactButton);
        calB=findViewById(R.id.calButton);
        smsB=findViewById(R.id.smsButton);


        db=new DBHelper(this);



        Intent iContacts=new Intent(MainActivity.this,Contacts.class);
        Intent iCalendar=new Intent(MainActivity.this,CalendarDemo.class);
        Intent iMessages=new Intent(MainActivity.this,Messaging.class);

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iContacts);
            }
        });
        calB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iCalendar);
            }
        });
        smsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(iMessages);
            }
        });
        insertDummy();
    }

    public void insertDummy(){
        Boolean insert;
        insert=db.insertData("181034","Joshua Dsouza","9090909090","6/10/2000");
        insert=db.insertData("181035","Leni Wilson","312983012","7/9/2000");
        insert=db.insertData("181036","Nikhil Lobo","392180312","6/12/2000");
        if(insert==true){
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "not successfull", Toast.LENGTH_SHORT).show();
        }
    }


}