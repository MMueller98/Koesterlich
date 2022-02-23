package com.koesterlich.activities;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koesterlich.helpers.ImageAdapter;
import com.koesterlich.R;
import com.koesterlich.helpers.RecipeContainer;
import com.koesterlich.helpers.Upload;

import java.util.ArrayList;
import java.util.List;

public class RecipeDatabase extends AbstractPage {

    private final float SCALING_FACTOR = 4/5f;

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;
    private ImageView mImageView;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private String recipeName;
    public List<RecipeContainer> contentContainer;
    private String id_rotw;


    private DatabaseReference rotwDatabaseRef;
    private List<Upload> rotwUploads;

    private Toolbar mToolbar;

    private Upload rotw;

    public RecipeDatabase(){
        super();

        // Activity Context MUST BE SET to create menu via Abstract-Page-Class
        setActivityContext(RecipeDatabase.this);
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

        rotwUploads = new ArrayList<>();
        contentContainer = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        rotwDatabaseRef = FirebaseDatabase.getInstance().getReference("rotw");


        // Database Query
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    mUploads = new ArrayList<>();
                    recipeName = postSnapshot.getKey();
                    for (DataSnapshot contentSnapshot : postSnapshot.getChildren()){
                        //name is used as type-variable of the image
                        Upload upload = contentSnapshot.getValue(Upload.class);
                        mUploads.add(upload);
                    }
                    contentContainer.add(new RecipeContainer(recipeName, mUploads.get(3).getImageUrl(), mUploads.get(1).getImageUrl(),mUploads.get(0).getImageUrl(), mUploads.get(2).getImageUrl(), mUploads.get(0).getUploadId()));

                }

                rotwDatabaseRef.get().addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        task.getException();
                    }
                    else {
                        rotw = task.getResult().getValue(Upload.class);
                        for(DataSnapshot rotwSnapshot : task.getResult().getChildren()){
                            mUploads = new ArrayList<>();
                            recipeName = rotwSnapshot.getKey();
                            for(DataSnapshot contentSnapshot : rotwSnapshot.getChildren()){
                                Upload upload = contentSnapshot.getValue(Upload.class);
                                mUploads.add(upload);
                            }
                            contentContainer.add(0, new RecipeContainer(recipeName, mUploads.get(3).getImageUrl(), mUploads.get(1).getImageUrl(),mUploads.get(0).getImageUrl(), mUploads.get(2).getImageUrl(), mUploads.get(0).getUploadId()));

                        }

                    }
                });

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