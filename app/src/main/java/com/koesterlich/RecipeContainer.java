package com.koesterlich;

public class RecipeContainer {
    private String recipeName;
    private String titleUrl;
    private String ingredientsUrl;
    private String guidanceUrl;
    private String uploadId;
    private String nutritionUrl;



    public RecipeContainer(String recipeName, String titleUrl, String ingredientsUrl, String guidanceUrl, String nutritionUrl, String uploadId){
        this.recipeName = recipeName;
        this.titleUrl = titleUrl;
        this.ingredientsUrl = ingredientsUrl;
        this.guidanceUrl = guidanceUrl;
        this.nutritionUrl = nutritionUrl;
        this.uploadId = uploadId;
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getIngredientsUrl() {
        return ingredientsUrl;
    }

    public void setIngredientsUrl(String ingredientsUrl) {
        this.ingredientsUrl = ingredientsUrl;
    }

    public String getGuidanceUrl() {
        return guidanceUrl;
    }

    public void setGuidanceUrl(String guidanceUrl) {
        this.guidanceUrl = guidanceUrl;
    }

    public String getNutritionUrl() {
        return nutritionUrl;
    }

    public void setNutritionUrl(String nutritionUrl) {
        this.nutritionUrl = nutritionUrl;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }



}
