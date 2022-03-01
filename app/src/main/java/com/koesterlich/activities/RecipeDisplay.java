package com.koesterlich.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.koesterlich.helpers.ImageAdapter;
import com.koesterlich.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RecipeDisplay extends AbstractPage {

    //Layout Views
    ImageView mImageViewIngredients;
    ImageView mImageViewGuidance;
    ImageView mImageViewNutritions;

    Button mStartCookingMode;
    private Toolbar mToolbar;


    //Attributes
    private String ingredientsImageURL;
    private String guidanceImageURL;
    private String nutritionImageURL;
    private String recipeStepCount;
    private HashMap<String, String> stepByStep;

    // Constructor
    public RecipeDisplay(){
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(RecipeDisplay.this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        mImageViewIngredients = findViewById(R.id.image_view_ingredients);
        mImageViewGuidance = findViewById(R.id.image_view_guidance);
        mImageViewNutritions = findViewById(R.id.image_view_nutritions);

        Intent intent = getIntent();

        ingredientsImageURL = intent.getStringExtra("ingredientsImageURL");
        guidanceImageURL = intent.getStringExtra("guidanceImageURL");
        nutritionImageURL = intent.getStringExtra("nutritionImageURL");
        recipeStepCount = intent.getStringExtra("recipeStepCount");
        stepByStep = (HashMap<String, String>) intent.getSerializableExtra("stepByStep");

        mStartCookingMode = findViewById(R.id.start_cooking_mode);
        mStartCookingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecipeDisplay.this, CookingMode.class);
                i.putExtra("recipeStepCount", recipeStepCount);
                i.putExtra("stepByStep", stepByStep);
                startActivity(i);
            }
        });




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