package com.example.shayariapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shayariapp.adapters.ShayariAdapter;
import com.example.shayariapp.databinding.ActivityShayariBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShayariActivity extends AppCompatActivity {
    String TAG="ShayariActivity";
    ActivityShayariBinding binding;
    ShayariAdapter shayariAdapter;
    List<String> shayariList;
    List<String> newShayariList;
    Boolean flag=false;
    int shayariNum=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityShayariBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent data=getIntent();
        String titleName=data.getStringExtra("titleName");
        newShayariList=new ArrayList<>();

        setUpRV();


        int id=0;
        switch (titleName)
        {
            case "goodmorningshayri":
                id=R.array.goodmorningshayri;
                break;
            case "goodnightshayri":
                id=R.array.goodnightshayri;
                break;
            case "loveshayri":
                id=R.array.loveshayri;
                break;
            case "friendshayri":
                id=R.array.friendshayri;
                break;
            case "attitudeshayri":
                id=R.array.attitudeshayri;
                break;
            case "emojishayri":
                id=R.array.emojishayri;
                break;
            case "funnyshayri":
                id=R.array.funnyshayri;
                break;
            case "birthdayshayri":
                id=R.array.birthdayshayri;
                break;
            case "romanticshayri":
                id=R.array.romanticshayri;
                break;
            case "lifeshayri":
                id=R.array.lifeshayri;
                break;
            case "godshayri":
                id=R.array.godshayri;
                break;
            case "sadshayri":
                id=R.array.sadshayri;
                break;
            case "happyshayri":
                id=R.array.happyshayri;
                break;
            case "dardshayri":
                id=R.array.dardshayri;
                break;
            case "intezarshayri":
                id=R.array.intezarshayri;
                break;
            case "bewafashayri":
                id=R.array.bewafashayri;
                break;

        }
        shayariList= Arrays.asList(getResources().getStringArray(id));
        Log.d(TAG, "onCreate: shayari list size= "+shayariList.size());
        readPreferences();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shayariNum+=20;
                Log.d(TAG, "onClick: shayariNum before= "+shayariNum);
                if (shayariNum>shayariList.size())
                {
                    Toast.makeText(getApplicationContext(), "No more Shayari available", Toast.LENGTH_SHORT).show();
                    shayariNum=shayariList.size();
                    Log.d(TAG, "onClick: shayariNum after= "+shayariNum);
                }else {
                    savePreferences(shayariNum);
                    readPreferences();
                }
            }
        });

    }
    private void savePreferences(int shayariNum) {
        SharedPreferences sp=getSharedPreferences("Preferences1",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("shayariNum", String.valueOf(shayariNum));
        editor.commit();
        flag=true;
    }
    private void readPreferences() {
        SharedPreferences sp=getSharedPreferences("Preferences1",MODE_PRIVATE);
        String shayariNum1=sp.getString("shayariNum","");
        Log.d(TAG, "readPreferences: flag= "+flag);
        Log.d(TAG, "readPreferences: shayariNum1= "+shayariNum1);
        if (shayariNum1.isEmpty())
        {
            shayariNum=20;
            for (int i = 0; i < shayariNum; i++) {
                newShayariList.add(shayariList.get(i));
                Log.d(TAG, "readPreferences: categorylist= " + shayariList.get(i));
            }
            shayariAdapter.setData(shayariNum, newShayariList);
        }else {
            int num=Integer.parseInt(shayariNum1);
            Log.d(TAG, "readPreferences: num= "+num);
            if (flag) {
                for (int i = num - 20; i < num; i++) {
                    if (i>shayariList.size())
                    {
                        break;
                    }else {
                        newShayariList.add(shayariList.get(i));
                        //Log.d(TAG, "readPreferences: categorylist= " + shayariList.get(i));
                    }
                }
                shayariAdapter.setData(num, newShayariList);
            } else {
                for (int i = 0; i < num; i++) {
                    newShayariList.add(shayariList.get(i));
                    //Log.d(TAG, "readPreferences: categorylist= " + shayariList.get(i));
                }
                shayariAdapter.setData(num, newShayariList);
            }
        }
        Log.d(TAG, "readPreferences: list size= "+newShayariList.size());
    }

    private void setUpRV() {
        shayariAdapter =new ShayariAdapter();
        binding.rvShayari.setLayoutManager(new LinearLayoutManager(this));
        binding.rvShayari.setAdapter(shayariAdapter);
    }
}