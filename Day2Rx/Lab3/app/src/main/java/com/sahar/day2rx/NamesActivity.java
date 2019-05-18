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

public class NamesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<User> users ;
    ArrayList<User> resultUsers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewlayout);

        users = new ArrayList<User>();
        resultUsers = new ArrayList<User>();

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter(resultUsers);
        recyclerView.setAdapter(mAdapter);

        getUsersObservable()
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGender().equalsIgnoreCase("Female");
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(User user) {
                        Log.e("User Details : ", user.getName() + ", " + user.getGender());
                        //    namesTV.append(user.getName() + ", " + user.getGender()+"\n");
                        resultUsers.add(user);
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

    private Observable<User> getUsersObservable() {

        String [] namesMal = {"Amr","Ahmed","Kamal"};
        String [] namesFem = {"Sahar","Esraa","Salma"};

        for(int i=0;i<namesMal.length;i++){
            users.add(new User(namesMal[i],"Male"));
        }

        for(int i=0;i<namesFem.length;i++){
            users.add(new User(namesFem[i],"Female"));
        }

        return Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        for (User user: users) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(user);
                            }
                        }
                        if (!emitter.isDisposed()) {
                            emitter.onComplete();
                        }
                    }
                }).subscribeOn(Schedulers.io());

    }
}
