package com.sahar.instantsearchapp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/json/contacts.php")
    Call<ArrayList<Contact>> getContacts();

    @GET("/json/contacts.php")
    Call<ArrayList<Contact>> search(@Query("search") String searchParam);

}
