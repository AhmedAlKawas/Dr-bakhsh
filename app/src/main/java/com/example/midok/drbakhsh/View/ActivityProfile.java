package com.example.midok.drbakhsh.View;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.midok.drbakhsh.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class ActivityProfile extends AppCompatActivity {

    FancyButton saveBtn;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        saveBtn = findViewById(R.id.save);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityProfile.this,HomePageFragmentView.class));
            }
        });
    }
}
