package com.example.ikhtiar.SendingMailAutomatic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ikhtiar on 10/20/2015.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if(CheckConnection.isAvailable(context)) {
            context.startService(new Intent(context, MyService.class));
        }
   }
}
