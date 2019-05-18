package com.sahar.day2rx;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private ArrayList<Integer> mDataset ;


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
                .inflate(R.layout.numcardview,viewGroup,false);

        Adapter.ViewHolder viewHolder = new Adapter.ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        TextView nameTV = viewHolder.view.findViewById(R.id.numberTV);
        nameTV.setText(mDataset.get(i)+"");

        CardView cardView = viewHolder.view.findViewById(R.id.numCardView);
        cardView.setBackgroundColor(Color.rgb(220,220,220));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public NumbersAdapter (ArrayList<Integer> mDataset){
        this.mDataset = mDataset;

    }



}
