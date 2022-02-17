package com.example.shayariapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shayariapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //to all categories
        binding.flAllShayari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAllCategories=new Intent(getApplicationContext(),AllCategoriesActivity.class);
                startActivity(toAllCategories);
            }
        });
        //rate us

        //share app
        binding.flShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toShare=new Intent(Intent.ACTION_SEND);
                toShare.setType("text/plain");
                toShare.putExtra(android.content.Intent.EXTRA_TEXT,"Share this app");
                startActivity(Intent.createChooser(toShare,"Share"));
            }
        });

        //exit
        binding.flExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit")
                        .setMessage("Are you sure u really want to exit")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel",null);
                AlertDialog dialog=builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.exit_dialog_bg);
                dialog.show();
            }
        });
    }
}