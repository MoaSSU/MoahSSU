package com.example.myapplication;

public class RecyclerViewItem {
    private String mImgName;
    private String mMainText;
    private String mSubText;

    public String getImagName() {
        return mImgName;
    }

    public void setImagName(String imgName) {
        this.mImgName = imgName;
    }

    public String getMainText() {
        return this.mMainText;
    }

    public void setMainText(String mainText) {
        this.mMainText = mainText;
    }

    public String getSubText() {
        return mSubText;
    }

    public void setSubText(String subText) {
        this.mSubText = subText;
    }
}
