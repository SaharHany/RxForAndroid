package com.sahar.day2rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class InstantSearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> words = getWords();
    ArrayList<String> resultWords ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_search);
        resultWords = new ArrayList<String>();
        resultWords.addAll(words
        ) ;
        recyclerView = (RecyclerView) findViewById(R.id.instSearchRecView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new InstantSearchAdapter(resultWords);
        recyclerView.setAdapter(mAdapter);
        TextView textView = findViewById(R.id.textView2);
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
                        textView.setText(data);
                        if(!data.isEmpty()){
                            System.out.println("hereee");
                        resultWords.clear();
                        for (int i=0;i<words.size();i++){
                            boolean check = words.get(i).startsWith(data);
                            System.out.println("hereee: "+check+"");
                            if(words.get(i).startsWith(data))
                            {
                                resultWords.add(words.get(i));
                                System.out.println(words.get(i));

                            }
                        }
                        mAdapter.notifyDataSetChanged();
                        }else{
                           resultWords.addAll(words) ;
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

    private ArrayList<String> getWords() {
        ArrayList<String> word = new ArrayList<String>();
        word.add("append");
        word.add("apple");
        word.add("bad");
        word.add("back");
        word.add("ball");
        word.add("banana");
        word.add("boat");
        word.add("call");
        word.add("car");
        return  word ;

    }
}
