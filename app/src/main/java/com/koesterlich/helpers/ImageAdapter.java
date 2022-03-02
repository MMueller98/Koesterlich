package com.koesterlich.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.koesterlich.R;
import com.koesterlich.activities.AbstractPage;
import com.koesterlich.activities.RecipeDisplay;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private Context mContext;
    private List<Recipe> mRecipeContainer;
    public String adapterType;

    public static final String EXTRA_MESSAGE = "com.koesterlich.activities.RecipeDatabase";

    public ImageAdapter(Context context, List<Recipe> recipeContainer, String type){
        this.mContext = context;
        this.mRecipeContainer = recipeContainer;
        this.adapterType = type;
    }

    @Override
    public int getItemViewType(int position) {
        // return 0 as View type if position is 0 at recipe-database call
        if(this.adapterType == AbstractPage.VIEW_TYPE_RECIPE_DATABASE){
            if(position == 0){
                return 0;
            }
        }
        return 1;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // View Type 0: first element in recipe-database call
        if(viewType == 0){
            View v = LayoutInflater.from(mContext).inflate(R.layout.recipe_of_the_week_cardview, parent, false);
            return new ImageViewHolder(v, viewType);
        }

        // View Type 1: every other element
        View v = LayoutInflater.from(mContext).inflate(R.layout.database_cardview, parent, false);
        return new ImageViewHolder(v, viewType);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Recipe recipeCurrent = mRecipeContainer.get(position);

        //Set attributes
        holder.setRecipeTitle(recipeCurrent.getRecipeTitle());
        holder.setUploadId(recipeCurrent.getUploadId());
        holder.setTitleImageURL(recipeCurrent.getTitleImageURL());
        holder.setIngredientsImageURL(recipeCurrent.getIngredientsImageURL());
        holder.setGuidanceImageURL(recipeCurrent.getGuidanceImageURL());
        holder.setNutritionImageURL(recipeCurrent.getNutritionImageURL());
        holder.setRecipeStepCount(recipeCurrent.getRecipeStepCount());
        holder.setStepByStep(recipeCurrent.getStepByStep());
        holder.setCookingTimeMinutes(recipeCurrent.getCookingTimeMinutes());
        holder.setBuzzwordCount(recipeCurrent.getBuzzwordCount());
        holder.setBuzzwords(recipeCurrent.getBuzzwords());


        //Set Content to views
        holder.textViewName.setText(recipeCurrent.getRecipeTitle());
        Picasso.get()
                .load(recipeCurrent.getTitleImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

        holder.textViewCookingTime.setText("Kochzeit: " + recipeCurrent.getCookingTimeMinutes() + " Minuten");
        Integer buzzWordCount = Integer.valueOf(recipeCurrent.getBuzzwordCount());
        String buzzWords = "";
        for(int i = 1; i <= buzzWordCount; i++){
            buzzWords += recipeCurrent.getBuzzwords().get("buzzword" + i) + " ,";
        }
        holder.textViewBuzzwords.setText(buzzWords.substring(0,buzzWords.length()-2));


        // Check if recipe is liked or not
        boolean liked = false;
        for(String recipes : AbstractPage.getLikedRecipesIDs()){
            if(recipes.trim().equals(recipeCurrent.getUploadId().trim())){
                holder.favButton.setImageResource(R.drawable.ic_favorite_orange_full);
                liked = true;
            }
        }
        holder.setLiked(liked);
    }

    @Override
    public int getItemCount() {
        return mRecipeContainer.size();
    }

    // =============================================================================================
    // IMAGEVIEWHOLDER
    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public TextView textViewCookingTime;
        public TextView textViewBuzzwords;
        public ImageView imageView;
        public ImageButton favButton;
        public ImageButton shareButton;


        //Attributes from RecipeContainer
        private String recipeTitle;
        private String uploadId;

        private String titleImageURL;
        private String ingredientsImageURL;
        private String guidanceImageURL;
        private String nutritionImageURL;

        private String recipeStepCount;
        private HashMap<String, String> stepByStep;

        private String cookingTimeMinutes;
        private String buzzwordCount;
        private HashMap<String, String> buzzwords;

        private boolean isLiked;



        // Constructor
        public ImageViewHolder(@NonNull View itemView, int position) {
            super(itemView);

            // Set ItemView depending on position
            if(position == 0){
                textViewName = itemView.findViewById(R.id.roftw_text_view_title);
                imageView = itemView.findViewById(R.id.roftw_image_view_upload);
                textViewCookingTime = itemView.findViewById(R.id.rotw_textview_cookingtime);
                textViewBuzzwords = itemView.findViewById(R.id.rotw_textvie_buzzwords);

            }else {
                textViewName = itemView.findViewById(R.id.text_view_title);
                imageView = itemView.findViewById(R.id.image_view_upload);
                textViewCookingTime = itemView.findViewById(R.id.textview_cooking_time);
                textViewBuzzwords = itemView.findViewById(R.id.textview_buzzwords);
            }

            // Items of ItemView
            favButton = itemView.findViewById(R.id.btn_favorite);
            shareButton = itemView.findViewById(R.id.btn_share);

            if(isLiked){
                favButton.setImageResource(R.drawable.ic_favorite_orange_full);
            }else{
                favButton.setImageResource(R.drawable.ic_favorite_orange_hollow);
            }

            // OnClickListener for Imageview
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, RecipeDisplay.class);
                    i.putExtra("uploadID", uploadId);
                    i.putExtra("recipeTitle", recipeTitle);

                    i.putExtra("titleImageURL", titleImageURL);
                    i.putExtra("ingredientsImageURL", ingredientsImageURL);
                    i.putExtra("guidanceImageURL", guidanceImageURL);
                    i.putExtra("nutritionImageURL", nutritionImageURL);

                    i.putExtra("recipeStepCount", recipeStepCount);
                    i.putExtra("stepByStep", stepByStep);

                    i.putExtra("cookingTimeMinutes", cookingTimeMinutes);
                    i.putExtra("buzzwordCount", buzzwordCount);
                    i.putExtra("buzzwords", buzzwords);
                    mContext.startActivity(i);
                }
            });

            // OnClickListener for favorite icon
            if(adapterType == AbstractPage.VIEW_TYPE_RECIPE_DATABASE){
                favButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isLiked){
                            // User disliked recipe
                            favButton.setImageResource(R.drawable.ic_favorite_orange_hollow);
                            AbstractPage.getLikedRecipesIDs().remove(uploadId);
                            isLiked = false;
                        }else{
                            // User liked recipe
                            favButton.setImageResource(R.drawable.ic_favorite_orange_full);
                            AbstractPage.getLikedRecipesIDs().add(uploadId);
                            isLiked = true;
                        }
                        updateLikedRecipesFile();

                    }
                });
            }

            // To-Do: Implement Share-Button functionality
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Coming soon :)", Toast.LENGTH_SHORT).show();
                }
            });
            /*
            // Print local data
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileInputStream fis = null;
                    try {
                        fis = mContext.openFileInput("example.txt");
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        StringBuilder sb = new StringBuilder();
                        String text;

                        while((text = br.readLine()) != null){
                            sb.append(text).append("\n");

                        }

                        Toast.makeText(mContext, sb.toString(), Toast.LENGTH_SHORT).show();
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
            });*/

        }
        // Helper-Methods
        public void updateLikedRecipesFile(){
            FileOutputStream fos = null;

            // Clear current content
            try{
                fos = mContext.openFileOutput(AbstractPage.LIKED_RECIPES_FILE, Context.MODE_PRIVATE);
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

            // write new content
            try {
                fos = mContext.openFileOutput(AbstractPage.LIKED_RECIPES_FILE, Context.MODE_APPEND);
                for(String recipes : AbstractPage.getLikedRecipesIDs()){
                    String input = recipes + "\n";
                    fos.write(input.getBytes(StandardCharsets.UTF_8));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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

        //Setters

        public void setRecipeTitle(String recipeTitle) {
            this.recipeTitle = recipeTitle;
        }

        public void setUploadId(String uploadId) {
            this.uploadId = uploadId;
        }

        public void setTitleImageURL(String titleImageURL) {
            this.titleImageURL = titleImageURL;
        }

        public void setIngredientsImageURL(String ingredientsImageURL) {
            this.ingredientsImageURL = ingredientsImageURL;
        }

        public void setGuidanceImageURL(String guidanceImageURL) {
            this.guidanceImageURL = guidanceImageURL;
        }

        public void setNutritionImageURL(String nutritionImageURL) {
            this.nutritionImageURL = nutritionImageURL;
        }

        public void setRecipeStepCount(String recipeStepCount) {
            this.recipeStepCount = recipeStepCount;
        }

        public void setStepByStep(HashMap<String, String> stepByStep) {
            this.stepByStep = stepByStep;
        }

        public void setLiked(boolean liked) {
            isLiked = liked;
        }

        public boolean getLiked(){
            return this.isLiked;
        }


        public void setCookingTimeMinutes(String cookingTimeMinutes) {
            this.cookingTimeMinutes = cookingTimeMinutes;
        }

        public void setBuzzwordCount(String buzzwordCount) {
            this.buzzwordCount = buzzwordCount;
        }

        public void setBuzzwords(HashMap<String, String> buzzwords) {
            this.buzzwords = buzzwords;
        }

    }
}
