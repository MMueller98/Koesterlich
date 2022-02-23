package com.koesterlich.helpers;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mUploadId;

    public Upload(){
        //empty constructor needed
    }

    public Upload(String name, String ImageUrl, String uploadId){
        if(name.trim().equals("")){
            mName = "No Name";
        }
        mName = name;
        mImageUrl = ImageUrl;
        mUploadId = uploadId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getUploadId() {
        return mUploadId;
    }

    public void setUploadId(String mUploadId) {
        this.mUploadId = mUploadId;
    }



}
