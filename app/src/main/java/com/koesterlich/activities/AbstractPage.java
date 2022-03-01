package com.koesterlich.activities;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.koesterlich.R;
import com.koesterlich.helpers.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AbstractPage extends AppCompatActivity {

    private Context activityContext;
    private static List<String> likedRecipesIDs = new ArrayList<>();
    private static List<Recipe> allRecipes = new ArrayList<>();
    public static final String likedRecipesFile = "example.txt";

    // Create Topbar-Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_fav) {
            if(!this.getActivityContext().toString().contains("Cookbook")){
                Intent i = new Intent(activityContext, Cookbook.class);
                startActivity(i);
            }

        }

        if(id == R.id.menu_test){
            Intent i = new Intent(activityContext, Testground.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    // GETTERS AND SETTERS
    public Context getActivityContext() {
        return activityContext;
    }

    public void setActivityContext(Context activityContext) {
        this.activityContext = activityContext;
    }

    public static List<String> getLikedRecipesIDs() {
        return likedRecipesIDs;
    }

    public static void setLikedRecipesIDs(List<String> likedRecipesIDs) {
        AbstractPage.likedRecipesIDs = likedRecipesIDs;
    }

    public static List<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public static void setAllRecipes(List<Recipe> allRecipes) {
        AbstractPage.allRecipes = allRecipes;
    }




}
