package com.sahar.instantsearchapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RemoteSearchAdapter  extends RecyclerView.Adapter<RemoteSearchAdapter.ViewHolder> {
    private ArrayList<Contact> contactList;
    private Context context ;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view ;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }


    @Override
    public RemoteSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contactcardview,viewGroup,false);

        RemoteSearchAdapter.ViewHolder viewHolder = new RemoteSearchAdapter.ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull RemoteSearchAdapter.ViewHolder viewHolder, int i) {
        System.out.println("inside onBindViewHolder");
        System.out.println(contactList.get(i).getName());
        TextView nameTV = viewHolder.view.findViewById(R.id.contactName);
        nameTV.setText(contactList.get(i).getName()+"");

        TextView phoneTV = viewHolder.view.findViewById(R.id.contactPhone);
        phoneTV.setText(contactList.get(i).getPhone()+"");

        TextView emailTV = viewHolder.view.findViewById(R.id.contactEmail);
        emailTV.setText(contactList.get(i).getEmail()+"");

        ImageView img = viewHolder.view.findViewById(R.id.contactImg);
        Glide.with(context)
                .load(contactList.get(i).getImage()).apply(RequestOptions.circleCropTransform())
                .into(img);

       /* CardView cardView = viewHolder.view.findViewById(R.id.contactCardView);
        cardView.setBackgroundColor(Color.rgb(220,220,220));
*/
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public RemoteSearchAdapter (ArrayList<Contact> mDataset, Context context){
        this.contactList = mDataset;
        this.context = context ;
    }



}

