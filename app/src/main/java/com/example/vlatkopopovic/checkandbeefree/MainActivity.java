package com.example.vlatkopopovic.checkandbeefree;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.vlatkopopovic.checkandbeefree.Database.SQLite;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;
import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerViewMainAdapter;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SQLite dbAdapter;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Dialog dialog;
    ImageView alertDialogIcon, mainIcon;
    EditText title, question;
    Button add, cancel;
    Switch switchButton;
    int result;
    List<RecyclerListItem> allItems;
    String getTitle;
    FloatingActionButton fab;
    WifiManager wifiManager;
    boolean wasWifiEnabled;
    String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeDatabase();
        loadList();


     /*   final WifiManager manager = (WifiManager) super.getApplicationContext().getSystemService(WIFI_SERVICE);
        final DhcpInfo dhcp = manager.getDhcpInfo();
        final String address = Formatter.formatIpAddress(dhcp.gateway);

        WifiInfo wifiinfo = manager.getConnectionInfo();
        byte[] myIPAddress = BigInteger.valueOf(wifiinfo.getIpAddress()).toByteArray();
        // you must reverse the byte array before conversion. Use Apache's commons library
        ArrayUtils.reverse(myIPAddress);
        InetAddress myInetIP = null;
        try {
            myInetIP = InetAddress.getByAddress(myIPAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String myIP = myInetIP.getHostAddress();



        Toast.makeText(MainActivity.this, address, Toast.LENGTH_LONG).show();*/

/*
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wasWifiEnabled = (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED || wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING);
        WifiHelper.setWifiListener(new WifiHelper.WifiConnectionChange() {
            @Override
            public void wifiConnected(boolean connected) {
                //Do logic here
                if (connected == true) {
                    //Toast.makeText(MainActivity.this, "Konektovan", Toast.LENGTH_LONG).show();

                } else {
                    //Toast.makeText(MainActivity.this, "Nije konektovan", Toast.LENGTH_LONG).show();
                }

            }
        });*/





    }


    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.alert_dialog);
                alertDialogIcon = dialog.findViewById(R.id.dialogImage);

                title = dialog.findViewById(R.id.editTextTitle);
                title.requestFocus();
                question = dialog.findViewById(R.id.editTextQuestion);
                add = dialog.findViewById(R.id.buttonAdd);
                cancel = dialog.findViewById(R.id.buttonCancel);
                alertDialogIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //LOAD PICTURE FROM DRAWABLE

                        Intent i = new Intent(MainActivity.this, IconActivity.class);
                        startActivityForResult(i, 1);


                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                if (window != null) {
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                }


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String title1 = title.getText().toString();
                        String question1 = question.getText().toString();

                        if (title1.isEmpty()) {

                            Toast.makeText(MainActivity.this, "Please fill title!", Toast.LENGTH_SHORT).show();


                        } else if (question1.isEmpty()) {

                            Toast.makeText(MainActivity.this, "Please fill question!", Toast.LENGTH_SHORT).show();
                        } else if (result == 0) {

                            Toast.makeText(MainActivity.this, "Please choose icon!", Toast.LENGTH_SHORT).show();

                        } else {

                            dbAdapter.addItem(title1, question1, result, 0, getDateTime());
                            dialog.dismiss();
                            result = 0;

                        }


                        mainIcon = (ImageView) findViewById(R.id.imageViewIcon);
                        switchButton = (Switch) findViewById(R.id.switchButton);
                        loadList();


                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
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
        // recyclerView.setNestedScrollingEnabled(false);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (fab.isShown()) fab.hide();
                } else {
                    if (!fab.isShown()) fab.show();
                }
            }
        });


    }

    private void initializeDatabase() {
        dbAdapter = new SQLite(MainActivity.this);
        dbAdapter.open();


    }

    private void loadList() {

        allItems = dbAdapter.selectAllItems();
        adapter = new RecyclerViewMainAdapter(allItems, this);
        recyclerView.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView


                Cursor cursor = dbAdapter.list_all_list();
                cursor.moveToPosition(viewHolder.getAdapterPosition());
                getTitle = cursor.getString(1);


                //Toast.makeText(MainActivity.this, broj, Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)

                        .setTitle("Delete")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                dbAdapter.deleteItem(getTitle);
                                int position = viewHolder.getAdapterPosition();
                                allItems.remove(position);
                                //allItems = dbAdapter.selectAllItems();
                                adapter.notifyItemRemoved(position);
                                // adapter.notifyItemRangeChanged(position,allItems.size());
                                //loadList();


                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                loadList();

                              /*  int position = viewHolder.getAdapterPosition();

                                adapter.notifyItemInserted(position);*/

                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd. MMM yyyy. HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                result = data.getIntExtra("result", 0);
                alertDialogIcon.setImageResource(result);
                //Toast.makeText(MainActivity.this,String.valueOf(result),Toast.LENGTH_SHORT).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {

                //Write your code if there's no result
            }
        }
    }


    public String getWifiApIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en
                    .hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                if (intf.getName().contains("wl") || (intf.getName().contains("ap"))) {
                    for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
                            .hasMoreElements(); ) {
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress()
                                && (inetAddress.getAddress().length == 4)) {
                            //Log.d(TAG, inetAddress.getHostAddress());
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            //Log.e(TAG, ex.toString());
        }
        return null;
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
   /*Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture, 0);*/


                      /*Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 1);*/
                        /*Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);*/

}
