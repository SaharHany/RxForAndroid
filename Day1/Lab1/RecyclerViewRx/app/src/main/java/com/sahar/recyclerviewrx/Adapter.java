package com.sahar.recyclerviewrx;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private ArrayList<String> mDataset ;
    private ArrayList<String> mDataset2 ;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View view ;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public Adapter(ArrayList<String> mDataset, ArrayList<String> mDataset2){
        this.mDataset = mDataset ;
        this.mDataset2 = mDataset2 ;

    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = (View) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row,viewGroup,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(mDataset.size()>0){
        TextView textView = viewHolder.view.findViewById(R.id.textView);
        textView.setText(mDataset.get(i));
        }
        if(mDataset2.size()>0){
            TextView textView2 = viewHolder.view.findViewById(R.id.textView2);
            textView2.setText(mDataset2.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
