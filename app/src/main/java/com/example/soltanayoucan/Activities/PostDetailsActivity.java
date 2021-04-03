package com.example.soltanayoucan.Activities;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Constants;
import com.example.soltanayoucan.Utils.DataPostModel;
import com.example.soltanayoucan.Utils.Variables;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //get position of this current Post
        int position = getIntent().getIntExtra(Constants.EXTRA_DATA_CONTENT, 0);

        //Single Post
        DataPostModel dataPostModel = Variables.dataPostModels.get(position);

        ((TextView) findViewById(R.id.text_view_content_posts_title)).setText(dataPostModel.getTitle());
        ((TextView) findViewById(R.id.text_view_content_posts_date)).setText(dataPostModel.getDate_gmt());


        //set Html code to TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ((TextView) findViewById(R.id.text_view_content_posts_body)).setText(Html.fromHtml(dataPostModel.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            ((TextView) findViewById(R.id.text_view_content_posts_body)).setText(Html.fromHtml(dataPostModel.getContent()));
        }

        //Back Button
        findViewById(R.id.image_view_content_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDetailsActivity.super.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}