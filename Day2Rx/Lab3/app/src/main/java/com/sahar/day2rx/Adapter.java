package com.sahar.day2rx;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private ArrayList<User> mDataset ;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public View view ;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }


    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView nameTV = viewHolder.view.findViewById(R.id.nameTV);
        nameTV.setText(mDataset.get(i).name);

        TextView genderTV = viewHolder.view.findViewById(R.id.genderTV);
        genderTV.setText(mDataset.get(i).gender);

        CardView cardView = viewHolder.view.findViewById(R.id.cardView);
        cardView.setBackgroundColor(Color.rgb(220,220,220));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public Adapter (ArrayList<User> mDataset){
        this.mDataset = mDataset;

    }



}
