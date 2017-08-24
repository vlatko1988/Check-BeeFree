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

       // int[] icons = {R.drawable.vlatko, R.drawable.insta, R.drawable.insta, R.drawable.vlatko, R.drawable.vlatko, R.drawable.rakic};

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
            //Toast.makeText(IconActivity.this,String.valueOf(imgResourceId),Toast.LENGTH_SHORT).show();
        }


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewIcon);
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);


        //RecyclerIconList listItem1 = new RecyclerIconList(R.drawable.vlatko);
        //allItems.add(listItem1);

        /*for (int i = 0; i < icons.length; i++) {

            allItems.add(new RecyclerIconList(icons[i]));
            //Toast.makeText(IconActivity.this,String.valueOf(icons),Toast.LENGTH_SHORT).show();
        }*/


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
