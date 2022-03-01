package com.koesterlich.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.koesterlich.helpers.ImageAdapter;
import com.koesterlich.R;
import com.squareup.picasso.Picasso;

public class RecipeDisplay extends AppCompatActivity {

    //Layout Views
    ImageView mImageViewIngredients;
    ImageView mImageViewGuidance;
    ImageView mImageViewNutritions;


    //Attributes
    private String ingredientsImageURL;
    private String guidanceImageURL;
    private String nutritionImageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        mImageViewIngredients = findViewById(R.id.image_view_ingredients);
        mImageViewGuidance = findViewById(R.id.image_view_guidance);
        mImageViewNutritions = findViewById(R.id.image_view_nutritions);

        Intent intent = getIntent();

        ingredientsImageURL = intent.getStringExtra("ingredientsImageURL");
        guidanceImageURL = intent.getStringExtra("guidanceImageURL");
        nutritionImageURL = intent.getStringExtra("nutritionImageURL");



        Picasso.get()
                .load(ingredientsImageURL)
                .fit()
                .centerCrop()
                .into(mImageViewIngredients);

        Picasso.get()
                .load(guidanceImageURL)
                .fit()
                .centerCrop()
                .into(mImageViewGuidance);

        Picasso.get()
                .load(nutritionImageURL)
                .fit()
                .centerCrop()
                .into(mImageViewNutritions);



    }
}