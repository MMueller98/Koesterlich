package com.koesterlich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class RecipeDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        Intent intent = getIntent();
        String message = intent.getStringExtra(ImageAdapter.EXTRA_MESSAGE);

        Toast.makeText(this, "Called by " + message, Toast.LENGTH_SHORT).show();

    }
}