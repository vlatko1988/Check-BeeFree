package com.example.vlatkopopovic.checkandbeefree;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.vlatkopopovic.checkandbeefree.RecyclerIconAdapter.RecyclerIconAdapter;
import com.example.vlatkopopovic.checkandbeefree.RecyclerIconAdapter.RecyclerIconList;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerViewMainAdapter;

import java.util.ArrayList;
import java.util.List;

public class IconActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon);

        int[] icons = {R.drawable.vlatko, R.drawable.insta, R.drawable.insta, R.drawable.vlatko, R.drawable.vlatko, R.drawable.rakic};

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIcon);
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        final List<RecyclerIconList> allItems = new ArrayList<>();
        //RecyclerIconList listItem1 = new RecyclerIconList(R.drawable.vlatko);
        //allItems.add(listItem1);

        for (int i = 0; i < icons.length; i++) {

            allItems.add(new RecyclerIconList(icons[i]));
        }



        adapter = new RecyclerIconAdapter(allItems, this);
        recyclerView.setAdapter(adapter);


       /* Intent returnIntent = new Intent();
        //returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();*/

      /*  Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.putExtra("intVariableName", intValue);
        startActivity(myIntent);*/


    }
    @Override
    public void onBackPressed() {
       finish();

    }




}
