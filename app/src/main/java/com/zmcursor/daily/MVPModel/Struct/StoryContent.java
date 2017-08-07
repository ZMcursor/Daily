package com.zmcursor.daily.MVPModel.Struct;

/**
 * Created by ZMcursor on 2017/7/20 0020.
 */

public class StoryContent extends EStory {

    private String body;
    private String image_source;
    private String share_url;
    private String js;
    private String css;
//    private String[] images;

    public StoryContent() {
        super(0, 0, null, null, null, 0);
        body = null;
        image_source = null;
        share_url = null;
        js = null;
        css = null;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}