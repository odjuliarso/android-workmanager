package com.example.background.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BlurWorker extends Worker {

    // Constructor
    public BlurWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    //Overriding the doWork() method to use WorkerUtil's makeStatusNotification
    @NonNull
    @Override
    public  doWork() {
        Context applicationContextgetApplicationContext();

    }
}
