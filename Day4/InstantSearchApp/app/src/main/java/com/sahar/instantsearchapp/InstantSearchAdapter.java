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

public class InstantSearchAdapter extends RecyclerView.Adapter<InstantSearchAdapter.ViewHolder>
        //implements Filterable
         {
  //  private ArrayList<Contact> contactList;
    private ArrayList<Contact> contactListFiltered ;
    private Context context ;
/*
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered.clear();
                    contactListFiltered = contactList;
                    notifyDataSetChanged();
                } else {
                    ArrayList<Contact> filteredList = new ArrayList<>();
                    for (Contact row : contactList) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPhone().contains(charSequence) || row.getEmail().contains(charSequence)) {
                            System.out.println("key: "+charString);
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered.clear();
                    contactListFiltered = filteredList;
                    notifyDataSetChanged();
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Contact>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }
*/

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view ;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }


    @Override
    public InstantSearchAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contactcardview,viewGroup,false);

        InstantSearchAdapter.ViewHolder viewHolder = new InstantSearchAdapter.ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull InstantSearchAdapter.ViewHolder viewHolder, int i) {
        System.out.println("inside onBindViewHolder");
        System.out.println(contactListFiltered.get(i).getName());
        Contact contact = contactListFiltered.get(i);
        TextView nameTV = viewHolder.view.findViewById(R.id.contactName);
        nameTV.setText(contact.getName()+"");

        TextView phoneTV = viewHolder.view.findViewById(R.id.contactPhone);
        phoneTV.setText(contact.getPhone()+"");

        TextView emailTV = viewHolder.view.findViewById(R.id.contactEmail);
        emailTV.setText(contact.getEmail()+"");

        ImageView img = viewHolder.view.findViewById(R.id.contactImg);
        Glide.with(context)
                .load(contact.getImage()).apply(RequestOptions.circleCropTransform())
                .into(img);

       /* CardView cardView = viewHolder.view.findViewById(R.id.contactCardView);
        cardView.setBackgroundColor(Color.rgb(220,220,220));
*/
    }

    @Override
    public int getItemCount() {
        return contactListFiltered.size();
    }


    public InstantSearchAdapter (ArrayList<Contact> mDataset, Context context){
      //  this.contactList = mDataset;
     //   contactListFiltered = new ArrayList<Contact>();
      //  contactListFiltered.addAll(this.contactList) ;
        contactListFiltered = mDataset;
        this.context = context ;
    }



}
