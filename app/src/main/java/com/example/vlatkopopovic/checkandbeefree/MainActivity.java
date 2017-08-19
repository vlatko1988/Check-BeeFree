package com.example.vlatkopopovic.checkandbeefree;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.vlatkopopovic.checkandbeefree.Database.SQLite;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerViewMainAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static int RESULT_LOAD_IMAGE = 1;

    private SQLite dbAdapter;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<RecyclerListItem> listItems;
    Dialog dialog;
    ImageView alertDialogIcon,mainIcon;
    EditText title,question;
    Button add, cancel;
    String picturePath;
    Switch switchButton;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeDatabase();
        loadList();





    }


    private void initializeViews()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //dbAdapter.deleteAll();
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.alert_dialog);
                alertDialogIcon = (ImageView) dialog.findViewById(R.id.dialogImage);

                title = (EditText) dialog.findViewById(R.id.editTextTitle);
                question = (EditText) dialog.findViewById(R.id.editTextQuestion);
                add = (Button) dialog.findViewById(R.id.buttonAdd);
                alertDialogIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        /*Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);*/


                      /*Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);*/
                        /*Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);*/

                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title1 = title.getText().toString();
                        String question1 = question.getText().toString();
                        dbAdapter.addItem(title1, question1, picturePath,0);

                        mainIcon = (ImageView) findViewById(R.id.imageViewIcon);
                        switchButton = (Switch) findViewById(R.id.switchButton);
                        loadList();
                        dialog.dismiss();
                        picturePath = "";
                    }
                });





            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);



    }

    private void initializeDatabase()
    {
        dbAdapter = new SQLite(MainActivity.this);
        dbAdapter.open();
    }

    private void loadList()
    {
        List<RecyclerListItem> allItems;
        allItems = dbAdapter.selectAllItems();
        adapter = new RecyclerViewMainAdapter(allItems,this);
        recyclerView.setAdapter(adapter);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            bm = decodeFile(picturePath);

            cursor.close();
            alertDialogIcon.setImageBitmap(bm);
            *//*slika = (ImageView) dialog.findViewById(R.id.slika);
            slika.setImageBitmap(bm);
            ikonica = (ImageView) findViewById(R.id.imageViewIcon);*//*


            //ikonica.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //Toast.makeText(MainActivity.this, picturePath, Toast.LENGTH_LONG).show();
        }
    }


    private Bitmap decodeFile(String fPath) {
        // Decode image size
        BitmapFactory.Options opts = new BitmapFactory.Options();
    *//*
     * If set to true, the decoder will return null (no bitmap), but the
     * out... fields will still be set, allowing the caller to query the
     * bitmap without having to allocate the memory for its pixels.
     *//*
        opts.inJustDecodeBounds = true;
        opts.inDither = false; // Disable Dithering mode
        opts.inPurgeable = true; // Tell to gc that whether it needs free
        // memory, the Bitmap can be cleared
        opts.inInputShareable = true; // Which kind of reference will be used to
        // recover the Bitmap data after being
        // clear, when it will be used in the
        // future

        BitmapFactory.decodeFile(fPath, opts);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 40;

        // Find the correct scale value.
        int scale = 1;

        if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) opts.outHeight
                    / (float) REQUIRED_SIZE);
            final int widthRatio = Math.round((float) opts.outWidth
                    / (float) REQUIRED_SIZE);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
        }

        // Decode bitmap with inSampleSize set
        opts.inJustDecodeBounds = false;

        opts.inSampleSize = scale;

        Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(
                Bitmap.Config.RGB_565, false);

        return bm;

    }*/



}
