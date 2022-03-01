package com.koesterlich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.koesterlich.activities.AbstractPage;
import com.koesterlich.activities.RecipeDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;

        // Read likeRecipes.txt from local storage and save it in global variable from AbstractPage
        readLikedRecipesTxt();

        // Start loading screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, RecipeDatabase.class);
                startActivity(i);
            }
        }, 2000);
    }

    public void readLikedRecipesTxt(){
        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(AbstractPage.likedRecipesFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;

            while((text = br.readLine()) != null){
                if(text != ""){
                    AbstractPage.getLikedRecipesIDs().add(text);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}