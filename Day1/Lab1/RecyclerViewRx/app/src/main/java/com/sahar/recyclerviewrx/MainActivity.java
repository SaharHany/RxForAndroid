package com.sahar.recyclerviewrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    CompositeDisposable compositeDisposable ;
    ArrayList<String> adapterList ;
    ArrayList<String> adapterList2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterList = new ArrayList<String>();
        adapterList2 = new ArrayList<String>();


        recyclerView = (RecyclerView) findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter(adapterList,adapterList2);
        recyclerView.setAdapter(mAdapter);

        compositeDisposable = new CompositeDisposable();
        Observable<String> observable = Observable.just("Sahar","Esraa","Amr");

        DisposableObserver<String> observer1 = getObserver1();
        DisposableObserver<String> observer2 = getObserver2();

        compositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer1));

        compositeDisposable.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer2));
    }

    private DisposableObserver<String> getObserver2() {
        return  new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                adapterList2.add(s.toLowerCase());

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error");

            }

            @Override
            public void onComplete() {
                mAdapter.notifyDataSetChanged();

            }
        };
    }

    private DisposableObserver<String> getObserver1() {
        return  new DisposableObserver<String>() {
            @Override
            public void onNext(String string) {
                 adapterList.add(string.toUpperCase());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error");
            }

            @Override
            public void onComplete() {
                mAdapter.notifyDataSetChanged();
            }
        };
    }

}
