package com.koesterlich.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;

import com.koesterlich.R;
import com.koesterlich.helpers.CookbookAdapter;
import com.koesterlich.helpers.ImageAdapter;
import com.koesterlich.helpers.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Cookbook extends AbstractPage {

    // Layout
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressCircle;

    // Variables
    private List<Recipe> likedRecipesContainer;
    private CookbookAdapter mAdapter;
    public static final String EXTRA_MESSAGE = "com.koesterlich.activities.RecipeDatabase";

    public Cookbook() {
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(Cookbook.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);


        // Layout
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager((new LinearLayoutManager(this)));

        mProgressCircle = findViewById(R.id.progress_circle);
        mProgressCircle.setVisibility(View.INVISIBLE);
        mToolbar = findViewById(R.id.cookbook_toolbar);
        setSupportActionBar(mToolbar);

        // Extract liked Recipes from Recipe-Container and save them in likedRecipesContainer
        List<Recipe> recipeContainer = AbstractPage.getAllRecipes();
        List<String> likedRecipeIDs = AbstractPage.getLikedRecipesIDs();
        likedRecipesContainer = new ArrayList<>();

        for (Recipe recipe: recipeContainer) {
            if(likedRecipeIDs.contains(recipe.getUploadId())){
                likedRecipesContainer.add(recipe);
            }
        }

        // Set Images in RecyclerView
        mAdapter = new CookbookAdapter(Cookbook.this, likedRecipesContainer, "Cookbook");
        mRecyclerView.setAdapter(mAdapter);
        mProgressCircle.setVisibility(View.INVISIBLE);
    }
}