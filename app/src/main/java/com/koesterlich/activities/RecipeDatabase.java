package com.koesterlich.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koesterlich.helpers.ImageAdapter;
import com.koesterlich.R;
import com.koesterlich.helpers.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase extends AbstractPage {

    // Layout
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressCircle;
    private Toolbar mToolbar;

    // Variables
    public List<Recipe> recipeContainer;
    private ImageAdapter mAdapter;

    // Database references
    private DatabaseReference mDatabaseRef;
    private DatabaseReference rotwDatabaseRef;

    // Constructor
    public RecipeDatabase(){
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(RecipeDatabase.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_database);

        // Layout
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager((new LinearLayoutManager(this)));

        mProgressCircle = findViewById(R.id.progress_circle);
        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        // Container to save Recipes from Firebase Database
        recipeContainer = new ArrayList<>();

        // Reference to Firebase Realtime-Database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        rotwDatabaseRef = FirebaseDatabase.getInstance().getReference("rotw");


        // On-Change Listener for Firebase Database
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Load recipes from Firebase an insert them into recycler view
                loadData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecipeDatabase.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    // Overwrite onBackPress-Method to prevent user to get stuck in loading-screen
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    // Helpers
    public void loadData(DataSnapshot snapshot){

        // Onchange-Listener gets all data, so clear recipe container first
        recipeContainer.clear();

        // Save Data in Recipe-Container
        for(DataSnapshot postSnapshot : snapshot.getChildren()) {
            Recipe recipe = postSnapshot.getValue(Recipe.class);
            recipeContainer.add(recipe);
        }

        // Get recipe of the week and save it at position 0 of Recipe-Container
        rotwDatabaseRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                task.getException();
            }
            else {
                for(DataSnapshot rotwSnapshot : task.getResult().getChildren()){
                    Recipe rotw = rotwSnapshot.getValue(Recipe.class);
                    recipeContainer.add(0, rotw);
                }
            }

            // Save all Recipes in global Variable in Abstract-Page
            AbstractPage.setAllRecipes(recipeContainer);

            // Set Images in RecyclerView
            mAdapter = new ImageAdapter(RecipeDatabase.this, recipeContainer, "RecipeDatabase");
            mRecyclerView.setAdapter(mAdapter);
            mProgressCircle.setVisibility(View.INVISIBLE);
        });

    }
}