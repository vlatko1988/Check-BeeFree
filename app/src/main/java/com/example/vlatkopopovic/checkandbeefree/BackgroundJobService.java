package com.example.vlatkopopovic.checkandbeefree;

import android.app.job.JobParameters;

import android.app.job.JobService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aleksandar on 07-Sep-17.
 */

public class BackgroundJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {


        mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, jobParameters ) );
        Log.i("WifiReceiver", "Have Wifi Connection");


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        mJobHandler.removeMessages( 1 );
        return false;
    }



    /*private Handler mJobHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage( Message msg ) {
            ConnectivityManager conMan = (ConnectivityManager) BackgroundJobService.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMan.getActiveNetworkInfo();
            if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(BackgroundJobService.this, "Konektovan", Toast.LENGTH_LONG).show();
                Log.i("WifiReceiver", "Have Wifi Connection");
                WifiHelper.setWifiConnected(true);
            } else {
                Toast.makeText(BackgroundJobService.this, "Nije konektovan", Toast.LENGTH_LONG).show();
                Log.i("WifiReceiver", "Don't have Wifi Connection");
                WifiHelper.setWifiConnected(false);
            }

            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }

    } );*/
    private Handler mJobHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage( Message msg ) {
            Toast.makeText(BackgroundJobService.this,
                    "JobService task running", Toast.LENGTH_LONG )
                    .show();
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }

    } );

}
