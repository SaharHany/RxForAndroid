package com.sahar.instantsearchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InstantSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InstantSearchAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Contact> contacts ;
    ArrayList<Contact> resultContacts ;
    ArrayList<Contact> getter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_instant_search);
        getter = (ArrayList<Contact>) getIntent().getSerializableExtra("data");

        contacts = new ArrayList<Contact>();
        resultContacts = new ArrayList<Contact>();

        contacts.addAll(getter);
        resultContacts.addAll(getter);
        System.out.println("contacts Size"+ contacts.size());

        System.out.println("rescontacts Size"+ resultContacts.size());


        // resultContacts.addAll(contacts) ;
        recyclerView = (RecyclerView) findViewById(R.id.instSearchRecView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new InstantSearchAdapter(resultContacts,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        super.onCreate(savedInstanceState);
        EditText editText = findViewById(R.id.incoDataET);

        RxTextView.textChangeEvents(editText).debounce(300, TimeUnit.MILLISECONDS)
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
                       // mAdapter.getFilter().filter(data);

                        if(!data.isEmpty()){
                            resultContacts.clear();
                            System.out.println("hereee");
                            for (int i=0;i<contacts.size();i++){
                                boolean check = contacts.get(i).getName().toLowerCase().contains(data.toLowerCase())||
                                        contacts.get(i).getEmail().toLowerCase().contains(data.toLowerCase())||
                                        contacts.get(i).getPhone().contains(data);

                                if(contacts.get(i).getName().toLowerCase().contains(data.toLowerCase())||
                                        contacts.get(i).getEmail().toLowerCase().contains(data.toLowerCase())||
                                        contacts.get(i).getPhone().contains(data))
                                {
                                    resultContacts.add(contacts.get(i));
                                    System.out.println("inside loop: "+contacts.get(i));

                                }
                            }
                            mAdapter.notifyDataSetChanged();
                        }else if(data.isEmpty()){

                            resultContacts.addAll(contacts) ;
                            System.out.println("arrsize: "+ contacts.size());

                            System.out.println("arrsize: "+ resultContacts.size());
                            mAdapter.notifyDataSetChanged();

                        }

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
