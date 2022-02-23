package com.koesterlich.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koesterlich.R;

import java.util.ArrayList;

public class Cookbook extends AbstractPage {

    private Toolbar mToolbar;
    private Button mBtn;

    public Cookbook() {
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(Cookbook.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);

        // Create Top menu
        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        mBtn = findViewById(R.id.btn_cookbook);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}