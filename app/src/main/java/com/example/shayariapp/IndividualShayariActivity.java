package com.example.shayariapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shayariapp.databinding.ActivityIndividualShayariBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividualShayariActivity extends AppCompatActivity {
    String TAG="individual";
    ActivityIndividualShayariBinding binding;
    List<String> shayariList=new ArrayList<>();
    int[] bgArray;
    int top=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIndividualShayariBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bgArray= new int[]{R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5};

        Bundle bundle=getIntent().getBundleExtra("bundle");
        int pos= bundle.getInt("pos");
        shayariList= (ArrayList<String>) bundle.getSerializable("shayariList");
        binding.tvShayari.setText(shayariList.get(pos));
        top=pos;

        Log.d(TAG, "onCreate: size= "+shayariList.size());
        setBg();

        //on prev click
        binding.ivPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top--;
                if (top<0)
                {
                    top=0;
                    Toast.makeText(getApplicationContext(), "No prev shayari available", Toast.LENGTH_SHORT).show();
                }else {
                    setBg();
                    binding.tvShayari.setText(shayariList.get(top));
                }
            }
        });
        //on next click
        binding.ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (top>=shayariList.size()-1)
                {
                    top=shayariList.size()-1;
                    Toast.makeText(getApplicationContext(), "No shayari available", Toast.LENGTH_SHORT).show();
                }else {
                    top++;
                    setBg();
                    binding.tvShayari.setText(shayariList.get(top));
                }
            }
        });

        //on copy click
        binding.ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String data=binding.tvShayari.getText().toString();
                ClipboardManager clipboardManager= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText("label",binding.tvShayari.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

        //on share click
        binding.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toShare=new Intent(Intent.ACTION_SEND);
                toShare.setType("text/plain");
                toShare.putExtra(Intent.EXTRA_TEXT,binding.tvShayari.getText());
                startActivity(Intent.createChooser(toShare,"Share amazing shayari through.."));
            }
        });
    }

    private void setBg() {
        Random random=new Random();
        int i=random.nextInt(5);
        binding.flShayari.setBackgroundResource(bgArray[i]);
        binding.llIcons.setBackgroundResource(bgArray[i]);
    }
}