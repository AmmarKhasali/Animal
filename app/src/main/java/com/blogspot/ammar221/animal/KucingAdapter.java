package com.blogspot.ammar221.animal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by USER on 30/04/2019.
 */

class KucingAdapter extends RecyclerView.Adapter<KucingAdapter.KucingViewHolder> {
    private  ArrayList<HashMap<String,String>> kucingData;
    private Context context;
    public KucingAdapter(FragmentActivity activity, ArrayList<HashMap<String, String>> Kucing) {
         kucingData = Kucing;
         context = activity;

    }

    @NonNull
    @Override
    public KucingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kucing,viewGroup,false);

        return new KucingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KucingViewHolder kucingViewHolder, int i) {

        kucingViewHolder.txtnmkucing.setText(kucingData.get(i).get("name"));
        kucingViewHolder.txtorigin.setText(kucingData.get(i).get("origin"));

    }

    @Override
    public int getItemCount() {
        return kucingData.size();
    }


    public class KucingViewHolder extends RecyclerView.ViewHolder {

        TextView txtnmkucing, txtorigin;
        public KucingViewHolder(@NonNull View itemView) {
            super(itemView);

            txtnmkucing = itemView.findViewById(R.id.item_nmkucing);
            txtorigin = itemView.findViewById(R.id.item_origin);
        }
    }
}
