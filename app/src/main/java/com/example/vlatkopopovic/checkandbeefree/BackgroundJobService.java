package com.example.vlatkopopovic.checkandbeefree;

import android.app.job.JobParameters;

import android.app.job.JobService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aleksandar on 07-Sep-17.
 */

public class BackgroundJobService extends JobService {

    private static final String TAG = BackgroundJobService.class.getSimpleName();
    boolean isWorking = false;
    boolean jobCancelled = false;

    // Called by the Android system when it's time to run the job
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "Job started!");
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


        isWorking = true;
        // We need 'jobParameters' so we can call 'jobFinished'
        startWorkOnNewThread(jobParameters); // Services do NOT run on a separate thread

        return isWorking;
    }

    private void startWorkOnNewThread(final JobParameters jobParameters) {
        new Thread(new Runnable() {
            public void run() {
                doWork(jobParameters);
            }
        }).start();
    }

    private void doWork(JobParameters jobParameters) {
        // 10 seconds of working (1000*10ms)
        for (int i = 0; i < 1000; i++) {
            // If the job has been cancelled, stop working; the job will be rescheduled.
            if (jobCancelled)
                return;

            try { Thread.sleep(10); } catch (Exception e) { }
        }

        Log.i(TAG, "Job finished!");
        isWorking = false;
        boolean needsReschedule = false;
        jobFinished(jobParameters, needsReschedule);
    }

    // Called if the job was cancelled before being finished
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "Job cancelled before being completed.");
        jobCancelled = true;
        boolean needsReschedule = isWorking;
        jobFinished(jobParameters, needsReschedule);
        return needsReschedule;
    }




    /*@Override
    public boolean onStartJob(JobParameters jobParameters) {


        mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, jobParameters ) );
        Log.i("WifiReceiver", "Have Wifi Connection");


        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }



    *//*private Handler mJobHandler = new Handler(new Handler.Callback() {

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

    } );*//*
    private Handler mJobHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage( Message msg ) {
            Toast.makeText(BackgroundJobService.this,
                    "JobService task running", Toast.LENGTH_LONG )
                    .show();
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }

    } );*/

}
