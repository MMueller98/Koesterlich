package com.koesterlich.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.koesterlich.R;

import org.w3c.dom.Text;

import java.util.HashMap;

public class CookingMode extends AbstractPage {

    // Layout
    private TextView txtCooking;
    private TextView txtHeading;
    private Toolbar mToolbar;
    private ImageButton btnPrev;
    private ImageButton btnNext;
    private Button btnBackToRecipe;

    // Constants
    private final int NO_PREVIOUS_STEP = -1;
    private final int NO_NEXT_STEP = -2;
    private final String STEP = "Schritt ";


    // Variables
    private String recipeStepCount;
    private HashMap<String, String> stepByStep;
    private String ingredientsImageURL;
    private String guidanceImageURL;
    private String nutritionImageURL;

    private int currentStep;
    private int nextStep;
    private int previousStep;



    public CookingMode() {
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(CookingMode.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_mode);

        // Disable lock-screen in cooking mode
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        // set Toolbar (menu)
        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        // Get extra-data from Intent-Call
        Intent intent = getIntent();
        recipeStepCount = intent.getStringExtra("recipeStepCount");
        stepByStep = (HashMap<String, String>) intent.getSerializableExtra("stepByStep");
        ingredientsImageURL = intent.getStringExtra("ingredientsImageURL");
        guidanceImageURL = intent.getStringExtra("guidanceImageURL");
        nutritionImageURL = intent.getStringExtra("nutritionImageURL");

        // Get Layout-Elements
        txtCooking = findViewById(R.id.txt_cooking);
        txtHeading = findViewById(R.id.txt_heading);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        btnBackToRecipe = findViewById(R.id.btn_backToRecipe);

        // Initialize Step-Counter
        currentStep = 1;
        nextStep = 2;
        previousStep = NO_PREVIOUS_STEP;

        txtHeading.setText(STEP + 1 + ":");
        txtCooking.setText(stepByStep.get("step_1"));
        txtCooking.setMovementMethod(new ScrollingMovementMethod());

        // Onclick-Listener for next- and previous button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextStep();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousStep();
            }
        });

        btnBackToRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CookingMode.this, RecipeDisplay.class);
                i.putExtra("recipeStepCount", recipeStepCount);
                i.putExtra("stepByStep", stepByStep);
                i.putExtra("ingredientsImageURL", ingredientsImageURL);
                i.putExtra("guidanceImageURL", guidanceImageURL);
                i.putExtra("nutritionImageURL", nutritionImageURL);
                startActivity(i);
            }
        });
    }

    private void showPreviousStep() {
        if(previousStep != NO_PREVIOUS_STEP){
            txtHeading.setText(STEP + previousStep + ":");
            txtCooking.setText(stepByStep.get("step_" + previousStep));

            currentStep -= 1;
            if(nextStep == NO_NEXT_STEP){
                nextStep = Integer.valueOf(recipeStepCount);
            }else{
                nextStep -= 1;
            }


            if((previousStep-1) < 1){
                previousStep = NO_PREVIOUS_STEP;
            }else{
                previousStep -=1;
            }
        }
    }

    private void showNextStep() {
        if(nextStep != NO_NEXT_STEP){
            txtHeading.setText(STEP + nextStep + ":");
            txtCooking.setText(stepByStep.get("step_" + nextStep));

            currentStep += 1;

            if(previousStep == NO_PREVIOUS_STEP){
                previousStep = 1;
            }else{
                previousStep +=1;
            }

            if((nextStep+1) > Integer.valueOf(recipeStepCount)){
                nextStep = NO_NEXT_STEP;
            }else{
                nextStep +=1;
            }
        }
    }
}