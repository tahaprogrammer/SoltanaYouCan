package com.example.soltanayoucan.Utils;

import com.example.soltanayoucan.R;

public class Constants {

    public static final String BASE_URL_POSTS = "http://soltana.ma/wp-json/wp/v2/posts?page=1";
    public static final String[] home_top_list = {"الرئيسية", "سياسة", "مجتمع", "اقتصاد", "رياضة", "ثقافة"};
    public static final int[] home_top_list_images = {R.drawable.icon_home, R.drawable.icon_politics, R.drawable.icon_society, R.drawable.icon_economy, R.drawable.icon_sports, R.drawable.icon_culture};

    //these variable are Name of the GET Request (posts)
    public static final String ID = "id";
    public static final String DATE_GMT = "date_gmt";
    public static final String TITLE = "title";
    public static final String RENDERED = "rendered";
    public static final String CONTENT = "content";
}