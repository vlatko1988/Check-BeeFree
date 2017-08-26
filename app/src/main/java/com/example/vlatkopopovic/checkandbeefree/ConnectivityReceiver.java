package com.example.vlatkopopovic.checkandbeefree;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Aleksandar on 26-Aug-17.
 */

public class ConnectivityReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            Log.d("WifiReceiver", "Have Wifi Connection");
            WifiHelper.setWifiConnected(true);
        } else
        {
            Log.d("WifiReceiver", "Don't have Wifi Connection");
            WifiHelper.setWifiConnected(false);
        }

    }
}
