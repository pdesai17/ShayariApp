package com.example.shayariapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shayariapp.R;
import com.example.shayariapp.ShayariActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    int catNum;
    List<String> catList;
    Context context;
    int[] bgArray={R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5};
    public CategoryAdapter()
    {
        catList=new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
            holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return catNum;
    }

    public void setData(String catNum, List<String> newCategoriesList) {
        this.catNum=Integer.parseInt(catNum);
        catList.clear();
        catList.addAll(newCategoriesList);
        notifyItemChanged(catList.size()-1);
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCatName;
        LinearLayout llCat;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCatName=itemView.findViewById(R.id.tv_catName);
            llCat=itemView.findViewById(R.id.ll_cat);
        }

        public void bind(int position) {
            Random random=new Random();
            llCat.setBackgroundResource(bgArray[random.nextInt(5)]);
            tvCatName.setText(catList.get(position));
            String titleName=catList.get(position).replace(" ","").toLowerCase();

            llCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toShayari=new Intent(context, ShayariActivity.class);
                    toShayari.putExtra("titleName",titleName);
                    context.startActivity(toShayari);
                }
            });
        }
    }
}
