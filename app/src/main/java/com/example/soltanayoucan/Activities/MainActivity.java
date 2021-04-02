package com.example.soltanayoucan.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soltanayoucan.Adapters.OnListClickListener;
import com.example.soltanayoucan.Adapters.RecyclerAdapterHomeListTop;
import com.example.soltanayoucan.R;
import com.example.soltanayoucan.Utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialRecyclerViewTopList();
    }

    private void initialRecyclerViewTopList() {
        RecyclerView recyclerView_top_list = findViewById(R.id.recycler_view_list_top);
        recyclerView_top_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        RecyclerAdapterHomeListTop recyclerAdapterHomeListTop = new RecyclerAdapterHomeListTop();
        recyclerView_top_list.setAdapter(recyclerAdapterHomeListTop);
        recyclerAdapterHomeListTop.setClickingListener(position -> Toast.makeText(MainActivity.this, Constants.home_top_list[position], Toast.LENGTH_SHORT).show());
    }
}