package com.koesterlich.activities;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.koesterlich.R;

public class AbstractPage extends AppCompatActivity {

    Context activityContext;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_fav){
            Intent i = new Intent(activityContext, Cookbook.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }


}
