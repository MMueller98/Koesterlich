package com.koesterlich;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.view.MotionEvent;

public class RecipeDatabase extends AppCompatActivity {

    private final float SCALING_FACTOR = 4/5f;

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private ImageView mImageView;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private String recipeName;
    private List<RecipeContainer> contentContainer;
    private String id_rotw;


    private DatabaseReference rotwDatabaseRef;
    private List<Upload> rotwUploads;

    private Toolbar mToolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.database_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        int id = item.getItemId();
        
        if(id == R.id.menu_add){
            Intent i = new Intent(RecipeDatabase.this, AdminUpload.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_database);

        mImageView = (ImageView) findViewById(R.id.image_view_upload);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager((new LinearLayoutManager(this)));
        mProgressCircle = findViewById(R.id.progress_circle);

        mToolbar = findViewById(R.id.database_toolbar);
        setSupportActionBar(mToolbar);

        mUploads = new ArrayList<>();
        rotwUploads = new ArrayList<>();
        contentContainer = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        rotwDatabaseRef = FirebaseDatabase.getInstance().getReference("recipe_of_the_week");


        /*
        rotwDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(0,upload);
                }

                mAdapter = new ImageAdapter(RecipeDatabase.this,mUploads);
                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecipeDatabase.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });*/

        mDatabaseRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){

                    recipeName = postSnapshot.getKey();
                    for (DataSnapshot contentSnapshot : postSnapshot.getChildren()){
                        //name is used as type-variable of the image
                        Upload upload = contentSnapshot.getValue(Upload.class);
                        mUploads.add(upload);
                    }
                    contentContainer.add(new RecipeContainer(recipeName, mUploads.get(3).getImageUrl(), mUploads.get(1).getImageUrl(),mUploads.get(0).getImageUrl(), mUploads.get(2).getImageUrl()));

                }

                mAdapter = new ImageAdapter(RecipeDatabase.this, contentContainer);
                mRecyclerView.setAdapter(mAdapter);
                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecipeDatabase.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });
    }

}