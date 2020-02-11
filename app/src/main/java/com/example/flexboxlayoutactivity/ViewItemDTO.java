package com.example.flexboxlayoutactivity;
public class ViewItemDTO {

    private String text = "";

    private int imageId = 0;

    public int getImageId() {
        return imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}