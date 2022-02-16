package com.example.background.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.background.R;

public class BlurWorker extends Worker {

    private static final String TAG = BlurWorker.class.getSimpleName();

    // Constructor
    public BlurWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    //Overriding the doWork() method to use WorkerUtil's makeStatusNotification
    @NonNull
    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();

        //Creating a bitmap using a try/catch statement
        try {
            //Bitmap file from the test image
            Bitmap picture = BitmapFactory.decodeResource(
                    applicationContext.getResources(),
                    R.drawable.android_cupcake);
            //Blur the bitmap
            Bitmap outpuit = WorkerUtils.blurBitmap(picture, applicationContext);

            //Write bitmap to a temp file
            Uri outputUri = WorkerUtils.writeBitmapToFile(applicationContext, outpuit);

            WorkerUtils.makeStatusNotification("Output is" + outputUri.toString(), applicationContext);

            //If there were no errors, return SUCEESS
            return Result.success();

        } catch (Throwable throwable) {
            //Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were error, we return FAILURE
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}
