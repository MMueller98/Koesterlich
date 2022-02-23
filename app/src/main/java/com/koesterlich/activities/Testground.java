package com.koesterlich.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.koesterlich.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Testground extends AbstractPage {

    Button mBtnClearLikedRecipes;
    Button mBtnShowLikedRecipes;
    Toolbar mToolbar;

    public Testground() {
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(Testground.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testground);

        // Create Top menu
        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        // Buttons
        mBtnClearLikedRecipes = findViewById(R.id.btn_clearLikedRecipes);
        mBtnShowLikedRecipes = findViewById(R.id.btn_showLikedRecipes);

        mBtnClearLikedRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbstractPage.setLikedRecipes(new ArrayList<String>());
                // Clear current content
                FileOutputStream fos = null;
                try{
                    fos = openFileOutput(AbstractPage.likedRecipesFile, Context.MODE_PRIVATE);
                    String input = "";
                    fos.write(input.getBytes(StandardCharsets.UTF_8));
                }catch (Exception e){

                }finally {
                    if(fos != null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        mBtnShowLikedRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String output = "";
                for(String recipe : AbstractPage.getLikedRecipes()){
                    output += (recipe + "\n");
                }

                Toast.makeText(Testground.this, output, Toast.LENGTH_SHORT).show();
            }
        });



    }
}