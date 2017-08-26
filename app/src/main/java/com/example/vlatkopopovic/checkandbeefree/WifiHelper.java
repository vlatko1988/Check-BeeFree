package com.example.vlatkopopovic.checkandbeefree;

/**
 * Created by Aleksandar on 26-Aug-17.
 */

public class WifiHelper
{
    private static boolean isConnectedToWifi;
    private static WifiConnectionChange sListener;

    public interface WifiConnectionChange {
        void wifiConnected(boolean connected);
    }

    /**
     * Only used by Connectivity_Change broadcast receiver
     * @param connected
     */
    public static void setWifiConnected(boolean connected) {
        isConnectedToWifi = connected;
        if (sListener!=null)
        {
            sListener.wifiConnected(connected);
            sListener = null;
        }
    }

    public static void setWifiListener(WifiConnectionChange listener) {
        sListener = listener;
    }
}
