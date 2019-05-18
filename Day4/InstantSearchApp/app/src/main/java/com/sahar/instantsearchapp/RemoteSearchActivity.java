package com.sahar.instantsearchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RemoteSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RemoteSearchAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Contact> contacts ;
   // ArrayList<Contact> resultContacts ;
    DataRetreiver dataRetreiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_search);
        dataRetreiver = new DataRetreiver();

        contacts = new ArrayList<Contact>();
        contacts = dataRetreiver.getContacts();

        //resultContacts = new ArrayList<Contact>();

       // resultContacts.addAll(contacts) ;
        recyclerView = (RecyclerView) findViewById(R.id.instSearchRecView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RemoteSearchAdapter(contacts,this);
        recyclerView.setAdapter(mAdapter);

        EditText editText = findViewById(R.id.incoDataET);

        RxTextView.textChangeEvents(editText).debounce(10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String data = textViewTextChangeEvent.text().toString();
                        System.out.println("here :"+data);
                        contacts.clear();
                        contacts = dataRetreiver.searchBy(data);
                        mAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
