package com.sahar.day2rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class NumbersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Integer [] numbers ;
    ArrayList<Integer> emittedNumbers ;
    ArrayList<Integer> resNumbers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewlayout);

        emittedNumbers = new ArrayList<Integer>();
        resNumbers = new ArrayList<Integer>();
        numbers = new Integer[]{2, 3, 10, 4, 1, 6, 9, 8,12};

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NumbersAdapter(resNumbers);
        recyclerView.setAdapter(mAdapter);

        getNumsObservable()
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer num) throws Exception {
                        return num%2 == 0;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Integer num) {
                        Log.e("Numbers : ", num.toString());
                        //numsTV.append(num.toString()+"\n");
                        resNumbers.add(num);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                        mAdapter.notifyDataSetChanged();
                    }
                });

    }

    private Observable<Integer> getNumsObservable() {

        return Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        for (int i=0;i<numbers.length;i++) {
                            if (!emitter.isDisposed()) {
                                if(i%2 ==0){
                                    emitter.onNext(numbers[i]);
                                }
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());

    }

}
