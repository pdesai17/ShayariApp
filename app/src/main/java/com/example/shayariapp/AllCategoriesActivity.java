package com.example.shayariapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shayariapp.adapters.CategoryAdapter;
import com.example.shayariapp.databinding.ActivityAllCategoriesBinding;
import com.example.shayariapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {
    String TAG="AllCat";
    private ActivityAllCategoriesBinding binding;
    CategoryAdapter categoryAdapter;
    List<String> categoriesList;
    List<String> titleList;
    List<String> newCategoriesList;
    static int catNum=0;
    Boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        flag=false;

        categoriesList=new ArrayList<>();
        newCategoriesList=new ArrayList<>();

       /* categoriesList.add("Good Morning Shayari");
        categoriesList.add("Good Night Shayari");
        categoriesList.add("Funny Shayari");
        categoriesList.add("Love Shayari");
        categoriesList.add("Attitude Shayari");
        categoriesList.add("Emoji Shayari");
        categoriesList.add("Birthday Shayari");
        categoriesList.add("Breakup Shayari");
        categoriesList.add("Sad Shayari");
        categoriesList.add("Friendship Shayari");
        categoriesList.add("Happy Shayari");
        categoriesList.add("Dard Shayari");
        categoriesList.add("New Year Shayari");
        categoriesList.add("Valentine Shayari");
        categoriesList.add("Maa Shayari");*/

        titleList= Arrays.asList(getResources().getStringArray(R.array.title));
        Log.d(TAG, "onCreate: tl size= "+titleList.size());
        setUpRV();

        readPreferences();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: catNum before= "+catNum);
                catNum=catNum+5;
                Log.d(TAG, "onClick: catNum= "+catNum);
                if (catNum>titleList.size()-11)
                {
                    catNum=titleList.size()-11;
                    Toast.makeText(getApplicationContext(), "No catories available", Toast.LENGTH_SHORT).show();
                }else {
                    savePrefences(catNum);
                    readPreferences();
                }
            }
        });

    }

    private void savePrefences(int catNum) {
        SharedPreferences sp=getSharedPreferences("Preferences1",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("catNum",String.valueOf(catNum));
        editor.commit();
        flag=true;
    }
    private void readPreferences() {
        SharedPreferences sp=getSharedPreferences(String.valueOf("Preferences1"),MODE_PRIVATE);
        String catNum1=sp.getString("catNum","");
        Log.d(TAG, "readPreferences: flag= "+flag);
        Log.d(TAG, "readPreferences: catNum= "+catNum1);
        if (catNum1.isEmpty())
        {
            binding.tvNoData.setVisibility(View.VISIBLE);
        }else{
            catNum=Integer.parseInt(catNum1);
            if (flag)
            {
                binding.tvNoData.setVisibility(View.GONE);

                for (int i = catNum-5; i <catNum ; i++) {
                    newCategoriesList.add(titleList.get(i));
                    Log.d(TAG, "readPreferences: categorylist= "+titleList.get(i));
                }
                categoryAdapter.setData(catNum1,newCategoriesList);
            }else {
                binding.tvNoData.setVisibility(View.GONE);
                for (int i = 0; i < catNum; i++) {
                    newCategoriesList.add(titleList.get(i));
                    Log.d(TAG, "readPreferences: categorylist= " + titleList.get(i));
                }
                categoryAdapter.setData(catNum1, newCategoriesList);
            }
        }


    }

    private void setUpRV() {
        categoryAdapter=new CategoryAdapter();
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCategories.setAdapter(categoryAdapter);
    }
}