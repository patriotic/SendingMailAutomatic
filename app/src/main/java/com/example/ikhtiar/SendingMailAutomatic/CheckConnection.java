package com.example.ikhtiar.SendingMailAutomatic;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ikhtiar on 10/27/2015.
 */
public final class CheckConnection {

    private static ConnectivityManager connManager;
    private static NetworkInfo info;


    private CheckConnection(){
    }

    public static boolean isAvailable(Context context){
        connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        info = connManager.getActiveNetworkInfo();

        if(info!=null && info.isAvailable())return true;
        else return false;
    }
}
