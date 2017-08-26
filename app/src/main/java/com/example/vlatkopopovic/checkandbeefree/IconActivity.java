package com.example.vlatkopopovic.checkandbeefree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vlatkopopovic.checkandbeefree.RecyclerIconAdapter.RecyclerIconAdapter;
import com.example.vlatkopopovic.checkandbeefree.RecyclerIconAdapter.RecyclerIconList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class IconActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);


        final List<RecyclerIconList> allItems = new ArrayList<>();
        //first we create an array list to hold all the resources ids
        ArrayList<Integer> imageListId = new ArrayList<>();

        //we iterate through all the items in the drawable folder
        Field[] drawables = R.drawable.class.getFields();
        for (Field f : drawables) {
            //if the drawable name contains "pic" in the filename...
            if (f.getName().contains("insta"))
                imageListId.add(getResources().getIdentifier(f.getName(), "drawable", getPackageName()));
        }

        //now the ArrayList "imageListId" holds all ours image resource ids
        for (int imgResourceId : imageListId) {
            //do whatever you want here
            allItems.add(new RecyclerIconList(imgResourceId));

        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIcon);
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        recyclerView.setNestedScrollingEnabled(false);


        adapter = new RecyclerIconAdapter(allItems, this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        finish();

    }


}
