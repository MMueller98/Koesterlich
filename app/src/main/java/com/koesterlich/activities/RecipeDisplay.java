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
    private String recipeName;
    private String titleUrl;
    private String ingredientsUrl;
    private String guidanceUrl;
    private String imageId;
    private String nutritionUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        mImageViewIngredients = findViewById(R.id.image_view_ingredients);
        mImageViewGuidance = findViewById(R.id.image_view_guidance);
        mImageViewNutritions = findViewById(R.id.image_view_nutritions);

        Intent intent = getIntent();
        CharSequence[] data = intent.getCharSequenceArrayExtra(ImageAdapter.EXTRA_MESSAGE);

        recipeName = data[0].toString();
        titleUrl = data[1].toString();
        ingredientsUrl = data[2].toString();
        guidanceUrl = data[3].toString();
        nutritionUrl = data[4].toString();
        imageId = data[5].toString();



        Picasso.get()
                .load(ingredientsUrl)
                .fit()
                .centerCrop()
                .into(mImageViewIngredients);

        Picasso.get()
                .load(guidanceUrl)
                .fit()
                .centerCrop()
                .into(mImageViewGuidance);

        Picasso.get()
                .load(nutritionUrl)
                .fit()
                .centerCrop()
                .into(mImageViewNutritions);



    }
}