package com.zmcursor.daily.MVPModel.ContentParse;


import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.Struct.Story;
import com.zmcursor.daily.MVPModel.Struct.TopStory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/6/30 0030.
 */

public class TimelineParse {

    private final static String TAG = "TimelineParse";

    private int date;
    private String topStoriesJson;
    private String StoriesJson;
    private ArrayList<Story> storyArray;
    private ArrayList<TopStory> topStoryArray;

    public void Parse(String Json, boolean isLatest) throws JSONException {
        storyArray = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(Json);
        date = jsonObject.getInt(Conf.date);
        storiesParse(jsonObject.getJSONArray(Conf.stories));
        if (isLatest) {
            topStoryArray = new ArrayList<>();
            topStoriesParse(jsonObject.getJSONArray(Conf.top_stories));
        }
    }

    public int getDate() {
        return date;
    }

    public String getTopStoriesJson() {
        return topStoriesJson;
    }

    public String getStoriesJson() {
        return StoriesJson;
    }

    public ArrayList<Story> getStories() {
        return storyArray;
    }

    public ArrayList<TopStory> getTopStories() {
        return topStoryArray;
    }

    public void storiesParse(String json) throws JSONException {
        storiesParse(new JSONArray(json));
    }

    public void topStoriesParse(String json) throws JSONException {
        topStoriesParse(new JSONArray(json));
    }

    private void storiesParse(JSONArray jStories) throws JSONException {
        StoriesJson = jStories.toString();
        JSONObject jStory;
        Story story;
        for (int i = 0; i < jStories.length(); i++) {
            jStory = jStories.getJSONObject(i);
            story = new Story();

            story.setId(jStory.getInt(Conf.id));
            story.setType(jStory.getInt(Conf.type));
            story.setTitle(jStory.getString(Conf.title));
            story.setGa_prefix(jStory.getInt(Conf.ga_prefix));
            story.setImageUrl(jStory.getJSONArray(Conf.images).getString(0));
            storyArray.add(story);
        }
    }

    private void topStoriesParse(JSONArray jTopStories) throws JSONException {
        topStoriesJson = jTopStories.toString();
        JSONObject jTopStory;
        TopStory topStory;
        for (int i = 0; i < jTopStories.length(); i++) {
            jTopStory = jTopStories.getJSONObject(i);
            topStory = new TopStory();
            topStory.setId(jTopStory.getInt(Conf.id));
            topStory.setType(jTopStory.getInt(Conf.type));
            topStory.setTitle(jTopStory.getString(Conf.title));
            topStory.setImageUrl(jTopStory.getString(Conf.image));
            topStory.setGa_prefix(jTopStory.getInt(Conf.ga_prefix));

            topStoryArray.add(topStory);
        }
    }

    public void recycle() {
        topStoriesJson = null;
        StoriesJson = null;
        storyArray = null;
        topStoryArray = null;
    }
}
