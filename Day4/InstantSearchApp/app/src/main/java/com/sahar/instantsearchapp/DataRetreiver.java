package com.sahar.instantsearchapp;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRetreiver  {
    RetrofitClient retrofitClient ;
    RetrofitInterface retrofit;
    ArrayList<Contact> contacts ;

    public DataRetreiver() {
        contacts = new ArrayList<Contact>();
        retrofitClient = new RetrofitClient();
        retrofit = retrofitClient.getRetrofitService();

    }
    public ArrayList<Contact> getContacts(){
        Call<ArrayList<Contact>> call = retrofit.getContacts();
        call.enqueue(new Callback<ArrayList<Contact>>() {
            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                contacts.clear();
                contacts.addAll(response.body());
                for (Contact contact : contacts){
                    System.out.println("contact : "+contact.getName());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {
                System.out.println("Failed");
            }
        });
        return contacts ;
    }

    public ArrayList<Contact> searchBy(String searchKey){
        Call<ArrayList<Contact>> call = retrofit.search(searchKey);
        call.enqueue(new Callback<ArrayList<Contact>>() {
            @Override
            public void onResponse(Call<ArrayList<Contact>> call, Response<ArrayList<Contact>> response) {
                contacts.clear();
                contacts.addAll(response.body());
                for (Contact contact : contacts){
                    System.out.println("contact : "+contact.getName());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contact>> call, Throwable t) {
                System.out.println("Failed");
            }
        });
        return contacts ;
    }


}
