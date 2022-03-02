package com.koesterlich.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
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


        // get screen-size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int dpi = getResources().getDisplayMetrics().densityDpi;
        int widthDP = (int) (width*160)/dpi;

        // set toolbar
        mToolbar = findViewById(R.id.display_toolbar);
        setSupportActionBar(mToolbar);

        // set size of image-views
        mImageViewIngredients = findViewById(R.id.image_view_ingredients);
        mImageViewIngredients.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) (widthDP * 1.046), getResources().getDisplayMetrics());
        mImageViewGuidance = findViewById(R.id.image_view_guidance);
        mImageViewGuidance.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) (widthDP * 1.148), getResources().getDisplayMetrics());
        mImageViewNutritions = findViewById(R.id.image_view_nutritions);
        mImageViewNutritions.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) (widthDP * 1.148), getResources().getDisplayMetrics());

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