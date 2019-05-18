package com.sahar.day2rx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
//import com.jakewharton.rxbinding.view.RxView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     Button nameBtn = findViewById(R.id.namesBtn);
     Button numBtn = findViewById(R.id.numBtn);
     Button clickBtn = findViewById(R.id.clickActBtn);
     Button instantBtn = findViewById(R.id.instantSearchActBtn);

        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nameIntent = new Intent(MainActivity.this,NamesActivity.class);
                startActivity(nameIntent);

            }
        });

        numBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numIntent = new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numIntent);

            }
        });

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickIntent = new Intent(MainActivity.this,ClickBtnActivity.class);
                startActivity(clickIntent);

            }
        });

        instantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instantIntent = new Intent(MainActivity.this,InstantSearchActivity.class);
                startActivity(instantIntent);

            }
        });
    }

}
