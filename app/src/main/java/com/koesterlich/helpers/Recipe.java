package com.koesterlich.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    private String recipeTitle;
    private String uploadId;
    private String titleImageURL;
    private String ingredientsImageURL;
    private String guidanceImageURL;
    private String nutritionImageURL;
    private String recipeStepCount;
    private HashMap<String, String> stepByStep;


    public Recipe(){
        //empty constructor needed
    }


    public Recipe(String recipeTitle, String uploadId, String titleImageURL, String ingredientsImageURL, String guidanceImageURL, String nutritionImageURL, String recipeStepCount, HashMap<String, String> stepByStep) {
        this.recipeTitle = recipeTitle;
        this.uploadId = uploadId;
        this.titleImageURL = titleImageURL;
        this.ingredientsImageURL = ingredientsImageURL;
        this.guidanceImageURL = guidanceImageURL;
        this.nutritionImageURL = nutritionImageURL;
        this.recipeStepCount = recipeStepCount;
        this.stepByStep = stepByStep;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getTitleImageURL() {
        return titleImageURL;
    }

    public void setTitleImageURL(String titleImageURL) {
        this.titleImageURL = titleImageURL;
    }

    public String getIngredientsImageURL() {
        return ingredientsImageURL;
    }

    public void setIngredientsImageURL(String ingredientsImageURL) {
        this.ingredientsImageURL = ingredientsImageURL;
    }

    public String getGuidanceImageURL() {
        return guidanceImageURL;
    }

    public void setGuidanceImageURL(String guidanceImageURL) {
        this.guidanceImageURL = guidanceImageURL;
    }

    public String getNutritionImageURL() {
        return nutritionImageURL;
    }

    public void setNutritionImageURL(String nutritionImageURL) {
        this.nutritionImageURL = nutritionImageURL;
    }

    public String getRecipeStepCount() {
        return recipeStepCount;
    }

    public void setRecipeStepCount(String recipeStepCount) {
        this.recipeStepCount = recipeStepCount;
    }

    public HashMap<String, String> getStepByStep() {
        return stepByStep;
    }

    public void setStepByStep(HashMap<String, String> stepByStep) {
        this.stepByStep = stepByStep;
    }
}
