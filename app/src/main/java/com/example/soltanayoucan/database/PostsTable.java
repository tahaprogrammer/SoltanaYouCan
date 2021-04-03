package com.example.soltanayoucan.database;

import android.provider.BaseColumns;

public class PostsTable {

    private PostsTable() {
    }

    public static class InnerTable implements BaseColumns {
        //Variables names for the SQLite Database
        public static final String TABLE_NAME = "posts_database";
        public static final String ID_POST = "id_post";
        public static final String DATE_GMT = "date_gmt";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
    }
}