package com.koesterlich;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{

    private Context mContext;
    private List<RecipeContainer> mContentContainer;

    public static final String EXTRA_MESSAGE = "com.koesterlich.RecipeDatabase";

    public ImageAdapter(Context context, List<RecipeContainer> uploads){
        mContext = context;
        mContentContainer = uploads;
    }


    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        } else{
            return 1;
        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        if(viewType == 0){
            View v = LayoutInflater.from(mContext).inflate(R.layout.recipe_of_the_week_cardview, parent, false);
            return new ImageViewHolder(v, viewType);
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.database_cardview, parent, false);
        return new ImageViewHolder(v, viewType);


    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        RecipeContainer recipeCurrent = mContentContainer.get(position);

        //Set attributes
        holder.setRecipeName(recipeCurrent.getRecipeName());
        holder.setTitleUrl(recipeCurrent.getTitleUrl());
        holder.setIngredientsUrl(recipeCurrent.getIngredientsUrl());
        holder.setGuidanceUrl(recipeCurrent.getGuidanceUrl());
        holder.setNutritionUrl(recipeCurrent.getNutritionUrl());
        holder.setImageId(recipeCurrent.getUploadId());

        //Set Content to views
        holder.textViewName.setText(recipeCurrent.getRecipeName());
        Picasso.get()
                .load(recipeCurrent.getTitleUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return mContentContainer.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewName;
        public ImageView imageView;


        //Attributes from RecipeContainer
        private String recipeName;
        private String titleUrl;
        private String ingredientsUrl;
        private String guidanceUrl;
        private String nutritionUrl;
        private String imageId;

        public ImageViewHolder(@NonNull View itemView, int position) {
            super(itemView);

            if(position == 0){
                textViewName = itemView.findViewById(R.id.roftw_text_view_title);
                imageView = itemView.findViewById(R.id.roftw_image_view_upload);
            }else {
                textViewName = itemView.findViewById(R.id.text_view_title);
                imageView = itemView.findViewById(R.id.image_view_upload);
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, RecipeDisplay.class);
                    String[] data = new String[]{recipeName,titleUrl,ingredientsUrl,guidanceUrl,nutritionUrl,imageId};
                    i.putExtra(EXTRA_MESSAGE, data);
                    mContext.startActivity(i);

                }
            });
        }

        //Setters
        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public void setTitleUrl(String titleUrl) {
            this.titleUrl = titleUrl;
        }

        public void setIngredientsUrl(String ingredientsUrl) {
            this.ingredientsUrl = ingredientsUrl;
        }

        public void setGuidanceUrl(String guidanceUrl) {
            this.guidanceUrl = guidanceUrl;
        }

        public void setNutritionUrl(String nutritionUrl) {
            this.nutritionUrl = nutritionUrl;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }
    }
}
