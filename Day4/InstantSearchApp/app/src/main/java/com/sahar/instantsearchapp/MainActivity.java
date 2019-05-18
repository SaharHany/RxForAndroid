package com.sahar.instantsearchapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    DataRetreiver dataRetreiver ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataRetreiver = new DataRetreiver();
        ArrayList<Contact> getter = dataRetreiver.getContacts();
        System.out.println("getter size :"+getter.size());
        setContentView(R.layout.activity_main);
        Button localBtn = findViewById(R.id.localSearchBtn);
        Button remoteBtn = findViewById(R.id.remoteSearchBtn);
        localBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InstantSearchActivity.class);
                intent.putExtra("data", getter);
                startActivity(intent);
            }
        });

        remoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RemoteSearchActivity.class);
                intent.putExtra("data", getter);
                startActivity(intent);
            }
        });

    }
}
