package com.example.duan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class manhinhchao extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.manhinhchao);

        ImageView ivlogo = findViewById(R.id.ivLogo);

        Glide
                .with(this)
                .load(R.mipmap.giphy)
                .into(ivlogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(manhinhchao.this, Login.class));
            }
        },3000);
    }
}

