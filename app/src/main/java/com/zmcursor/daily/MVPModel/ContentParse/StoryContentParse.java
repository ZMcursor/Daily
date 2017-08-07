package com.zmcursor.daily.MVPModel.ContentParse;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.Struct.StoryContent;
import com.zmcursor.daily.Utils.Loger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZMcursor on 2017/7/17 0017.
 */

public class StoryContentParse {

    public static StoryContent parse(String json) throws JSONException {
        StoryContent storyContent = new StoryContent();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray;

        storyContent.setBody(jsonObject.getString(Conf.body));
        storyContent.setImage_source(jsonObject.getString(Conf.image_source));
        storyContent.setTitle(jsonObject.getString(Conf.title));
        storyContent.setImageUrl(jsonObject.getString(Conf.image));
        storyContent.setShare_url(jsonObject.getString(Conf.share_url));

//        jsonArray = jsonObject.getJSONArray(Conf.js);
//        String js;
//        for (int i = 0; i < jsonArray.length(); i++) {
//
//        }
//        storyContent.setJs(jsonArray.getString(0));

        storyContent.setGa_prefix(jsonObject.getInt(Conf.ga_prefix));

//        jsonArray = jsonObject.getJSONArray(Conf.images);
//        storyContent.setImages(jsonArray.getString(0));

        storyContent.setType(jsonObject.getInt(Conf.type));
        storyContent.setId(jsonObject.getInt(Conf.id));

        storyContent.setCss(jsonObject.getString(Conf.css));
        Loger.NET("StoryContentParse->", "css-" + storyContent.getCss());
        return storyContent;
    }

}
