package com.example.vlatkopopovic.checkandbeefree;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aleksandar on 26-Aug-17.
 */

public class ConnectivityReceiver extends BroadcastReceiver {
    //ConnectivityReceiver cr = new ConnectivityReceiver();

    //ConnectivityReceiver cr = new ConnectivityReceiver();
    //IntentFilter filter =  new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);


    @Override
    public void onReceive(Context context, Intent intent) {
         //context.registerReceiver(this,filter);
/*
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Toast.makeText(context, "Konektovan", Toast.LENGTH_LONG).show();
            Log.i("WifiReceiver", "Have Wifi Connection");
            WifiHelper.setWifiConnected(true);
        } else {
            Toast.makeText(context, "Nije konektovan", Toast.LENGTH_LONG).show();
            Log.i("WifiReceiver", "Don't have Wifi Connection");
            WifiHelper.setWifiConnected(false);
        }*/
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.registerReceiver(cr, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }*/
    }

}
