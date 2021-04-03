package com.example.soltanayoucan.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soltanayoucan.Adapters.OnListClickListenerView;
import com.example.soltanayoucan.Adapters.RecyclerAdapterHomeListTop;
import com.example.soltanayoucan.Adapters.RecyclerAdapterListPosts;
import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Constants;
import com.example.soltanayoucan.Utils.EndDrawerToggle;
import com.example.soltanayoucan.Utils.Variables;
import com.example.soltanayoucan.database.PostsDBHelper;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private int listSize = 3;
    private RecyclerAdapterListPosts recyclerAdapterListPosts;
    private ImageView imageView_add_more_posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //offline mode Get Data From SQLite and put it in List
        if (Variables.OffLineMode) {
            if (!getDataFromSQLite()) {
                return;
            }
        } else {
            //This is online Mode we are going to copy all data to SQLite database
            PostsDBHelper postsDBHelper = new PostsDBHelper(this);
            if(postsDBHelper.getAllPosts().size() != 0) {
                postsDBHelper.removeAllPosts();
            }
            //insert data to SQLite
            postsDBHelper.insertPostsToDB();
        }

        SetupToolBar();
        initialRecyclerViewTopList();
        initialRecyclerViewListPosts();

        //button more posts
        imageView_add_more_posts = findViewById(R.id.image_view_add_more_posts);
        imageView_add_more_posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSize += Constants.ADD_MORE_POSTS;
                updateListPosts();
            }
        });
    }

    private boolean getDataFromSQLite() {
        PostsDBHelper postsDBHelper = new PostsDBHelper(this);
        if (postsDBHelper.getAllPosts().size() == 0) {
            Toast.makeText(this, "There no Data Available!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Variables.dataPostModels = postsDBHelper.getAllPosts();
            return true;
        }
    }

    private void updateListPosts() {
        //Update RecyclerView
        boolean ending = recyclerAdapterListPosts.addMorePosts(listSize);
        if (ending) {
            imageView_add_more_posts.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, getString(R.string.add_successfully), Toast.LENGTH_SHORT).show();
        }
        //recyclerView_posts.scrollToPosition(recyclerAdapterListPosts.getItemCount() - 1);
    }

    private void SetupToolBar() {
        //Setup ToolBar and Icon And drawer
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        drawer = findViewById(R.id.drawer_layout);
        EndDrawerToggle drawerToggle = new EndDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void initialRecyclerViewListPosts() {
        //Setup RecyclerView for Posts
        RecyclerView recyclerView_posts = findViewById(R.id.recycler_view_list_posts);
        recyclerAdapterListPosts = new RecyclerAdapterListPosts(listSize);
        recyclerView_posts.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_posts.setAdapter(recyclerAdapterListPosts);

        recyclerAdapterListPosts.setLickingListener(new OnListClickListenerView() {
            @Override
            public void onClicking(int position, ImageView shareView) {
                Intent intent = new Intent(MainActivity.this, PostDetailsActivity.class);
                if (shareView == null) {
                    startActivity(intent);
                    return;
                }
                //this code for Animation ImageView to the next Activity
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, shareView, ViewCompat.getTransitionName(shareView));
                intent.putExtra(Constants.EXTRA_DATA_CONTENT, position);
                startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    private void initialRecyclerViewTopList() {
        //Setup RecyclerView for the Top Menu
        RecyclerView recyclerView_top_list = findViewById(R.id.recycler_view_list_top);
        recyclerView_top_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        RecyclerAdapterHomeListTop recyclerAdapterHomeListTop = new RecyclerAdapterHomeListTop();
        recyclerView_top_list.setAdapter(recyclerAdapterHomeListTop);
        recyclerAdapterHomeListTop.setClickingListener(position -> Toast.makeText(MainActivity.this, Constants.home_top_list[position], Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }
}