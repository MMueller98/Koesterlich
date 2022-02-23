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

import com.koesterlich.R;

public class Cookbook extends AbstractPage {

    private Toolbar mToolbar;

    public Cookbook() {
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        this.activityContext = Cookbook.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);

        // Create Top menu
        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

    }
}