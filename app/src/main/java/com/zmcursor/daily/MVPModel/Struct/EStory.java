package com.zmcursor.daily.MVPModel.Struct;

import android.graphics.Bitmap;

/**
 * Created by ZMcursor on 2017/7/28 0028.
 */

public class EStory {

    private int id;
    private int type;
    private String title;
    private String imageUrl;
    private Bitmap imageBitmap;
    private boolean multipic;
    private int ga_prefix;

    public EStory(int id, int type, String title, String imageUrl, Bitmap imageBitmap, boolean multipic, int ga_prefix) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.imageUrl = imageUrl;
        this.imageBitmap = imageBitmap;
        this.multipic = multipic;
        this.ga_prefix = ga_prefix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public int getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(int ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

}
