package com.zmcursor.daily.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zmcursor.daily.Conf;
import com.zmcursor.daily.MVPModel.ContentParse.StoryContentParse;
import com.zmcursor.daily.MVPModel.NetLink.RawContent;
import com.zmcursor.daily.MVPModel.Struct.StoryContent;
import com.zmcursor.daily.R;
import com.zmcursor.daily.UI.ThemeManager;
import com.zmcursor.daily.Utils.DoInNewThread;
import com.zmcursor.daily.Utils.Utils;

import org.json.JSONException;

public class ReadActivity extends SwipeBackActivity {

    private final String TAG = "ReadActivity";

    private WebView webView;
    private WebSettings webSettings;
    private Toolbar toolbar;
    private StoryContent content;

    private boolean isFavoriteStory = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ThemeManager.getThemeManager().getRead_layout());
        Intent intent = getIntent();
        int id = intent.getIntExtra(Conf.id, 0);


        initView();
        loadStory(id);
//        webView.loadUrl("http://daily.zhihu.com/story/9528956");
    }

    private void loadStory(final int id) {
        if (id == 0) return;
        new DoInNewThread() {
            @Override
            public void Do() {
                String url = getString(R.string.url_story) + id;
                String json = (String) RawContent.getRawContent(url, false);
                try {
                    content = StoryContentParse.parse(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDone() {
                showStory();
            }
        }.run();
    }

    private void showStory() {

        if (content.getBody() != null) {
            String result = content.getBody();
            result = result.replace("<div class=\"img-place-holder\">", "");
            result = result.replace("<div class=\"headline\">", "");

            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";

            String theme = "<body className=\"\" onload=\"onLoaded()\">";

            result = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "\t<meta charset=\"utf-8\" />"
                    + css
                    + "\n</head>\n"
                    + theme
                    + result
                    + "</body></html>";

            webView.loadDataWithBaseURL("x-data://base", result, "text/html", "utf-8", null);
        } else {
            webView.loadDataWithBaseURL("x-data://base", content.getShare_url(), "text/html", "utf-8", null);
        }
//        webView.get

    }

    private void initView() {

        Utils.setNavigationBarPadding(findViewById(R.id.nested_scroll_view));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setM

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        webView = (WebView) findViewById(R.id.web_view);

//        Utils.setNavigationBarPadding(webView);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
//                builder.build().launchUrl(this, url.parse(url));
                return true;
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.read_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_favorite:
                if (isFavoriteStory) {
                    item.setIcon(R.drawable.ic_favorite_border);
                    isFavoriteStory = false;
                } else {
                    item.setIcon(R.drawable.ic_favorite);
                    isFavoriteStory = true;
                }
                break;
            case R.id.btn_share:
                break;
            case R.id.btn_night_mode:
                break;
            case R.id.btn_setting:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
