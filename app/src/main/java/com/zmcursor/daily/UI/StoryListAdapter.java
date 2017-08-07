package com.zmcursor.daily.UI;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zmcursor.daily.MVPModel.Struct.Date;
import com.zmcursor.daily.MVPModel.Struct.StoriesOfDate;
import com.zmcursor.daily.MVPModel.Struct.Story;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.View.LoadingRecyclerView.OnItemClickListener;
import com.zmcursor.daily.UI.View.MyImageView;

import java.util.ArrayList;

/**
 * Created by ZMcursor on 2017/7/14 0014.
 */

public class StoryListAdapter extends RecyclerView.Adapter {


    private ArrayList<Item> itemArray = new ArrayList<>();
    private LayoutInflater inflater;
    private LoadingView loadingView;

    private OnItemClickListener onItemClickListener = null;

    public enum ITEM_TYPE {
        ITEM_DATE,
        ITEM
    }

    public StoryListAdapter(Context context, LoadingView loadingView) {
        inflater = LayoutInflater.from(context);
        this.loadingView = loadingView;
    }

    public void onRefreshFinish(ArrayList<StoriesOfDate> storiesOfDateArray) {
        getItemArray(storiesOfDateArray);
        notifyDataSetChanged();
    }

    public void onLoadingFinish(ArrayList<StoriesOfDate> storiesOfDateArray) {
        getItemArray(storiesOfDateArray);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void getItemArray(ArrayList<StoriesOfDate> storiesOfDateArray) {
        itemArray.clear();
        Item item;
        Date date;
        ArrayList<Story> storyArray;
        for (int i = 0; i < storiesOfDateArray.size(); i++) {
            item = new Item();
            date = storiesOfDateArray.get(i).getDate();

            item.type = ITEM_TYPE.ITEM_DATE;
            item.date = date;
            itemArray.add(item);

            storyArray = storiesOfDateArray.get(i).getStories();
            for (int j = 0; j < storyArray.size(); j++) {
                item = new Item();
                item.type = ITEM_TYPE.ITEM;
                item.date = date;
                item.story = storyArray.get(j);
                itemArray.add(item);
            }
        }
        item = new Item();
        item.type = ITEM_TYPE.ITEM_DATE;
        itemArray.add(item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM.ordinal()) {
            View item = inflater.inflate(ThemeManager.getThemeManager().getStory_item(), parent, false);
            return new ItemViewHolder(item);
        } else {
            TextView date = new TextView(inflater.getContext());
            Resources resources = inflater.getContext().getResources();
            date.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.size_24)));
            date.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_12));
            date.setTextColor(resources.getColor(R.color.d_gray));
            date.setPadding(resources.getDimensionPixelSize(R.dimen.Spacing_16), 0, resources.getDimensionPixelSize(R.dimen.Spacing_16), 0);
            date.setClickable(false);
            return new DateViewHolder(date);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.storyTitle.setText(itemArray.get(position).story.getTitle());
            Bitmap bitmap = itemArray.get(position).story.getImageBitmap();
            if (bitmap != null) itemViewHolder.storyImage.setImage(bitmap);

            itemViewHolder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(v, itemArray.get(position).date, itemArray.get(position).story));
        } else if (holder instanceof DateViewHolder) {
            TextView textView = ((DateViewHolder) holder).date;
            if (position < (getItemCount() - 1)) {
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setText(itemArray.get(position).date.getStringDate());
            } else {
                loadingView.onShow(textView);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemArray.get(position).type.ordinal();
    }

    @Override
    public int getItemCount() {
        return itemArray.size();
    }

    private class DateViewHolder extends RecyclerView.ViewHolder {

        private TextView date;

        private DateViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView;
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView storyTitle;
        private MyImageView storyImage;

        private ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            storyTitle = (TextView) itemView.findViewById(R.id.story_title);
            storyImage = (MyImageView) itemView.findViewById(R.id.story_image);
        }
    }

    private class Item {
        private ITEM_TYPE type;
        private Date date;
        private Story story;
    }

//    private class ItemClickListener implements View.OnClickListener {
//
//        Date date;
//        Story story;
//
//        public ItemClickListener(Date date, Story story) {
//            this.date = date;
//            this.story = story;
//        }
//
//        public void sync(Date date, Story story) {
//            this.date = date;
//            this.story = story;
//        }
//
//        @Override
//        public void onClick(View v) {
//            onItemClickListener.onItemClick(v, date, story);
//        }
//    }
}
