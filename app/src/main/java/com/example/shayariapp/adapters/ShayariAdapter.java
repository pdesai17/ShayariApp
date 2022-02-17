package com.example.shayariapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shayariapp.IndividualShayariActivity;
import com.example.shayariapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShayariAdapter extends RecyclerView.Adapter<ShayariAdapter.ShayariViewHolder>{
    List<String> shayariList;
    int shayariNum;
    Context context;
    int[] bgArray={R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5};
    public ShayariAdapter()
    {
        shayariList=new ArrayList<>();
    }
    @NonNull
    @Override
    public ShayariViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shayari_layout,parent,false);
        return new ShayariViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShayariViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return shayariNum;
    }

    public void setData(int num, List<String> newShayariList) {
        this.shayariNum=num;
        this.shayariList.clear();
        this.shayariList.addAll(newShayariList);
        notifyItemInserted(this.shayariList.size()-1);
    }

    class ShayariViewHolder extends RecyclerView.ViewHolder {
        TextView tvShayari;
        LinearLayout llShayari;
        public ShayariViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShayari=itemView.findViewById(R.id.tv_shayari_item);
            llShayari=itemView.findViewById(R.id.ll_shayari);
        }

        public void bind(int position) {
            tvShayari.setText(shayariList.get(position));

            Random random=new Random();
            llShayari.setBackgroundResource(bgArray[random.nextInt(5)]);
            llShayari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toIndividualShayari=new Intent(context, IndividualShayariActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("pos",position);
                    bundle.putSerializable("shayariList", (Serializable) shayariList);
                    toIndividualShayari.putExtra("bundle",bundle);
                    context.startActivity(toIndividualShayari);
                }
            });
        }
    }
}
