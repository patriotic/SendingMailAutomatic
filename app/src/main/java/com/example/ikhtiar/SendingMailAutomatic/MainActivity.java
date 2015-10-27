package com.example.ikhtiar.SendingMailAutomatic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String intent = "android.net.conn.CONNECTIVITY_CHANGE";
    private final IntentFilter intent_filter = new IntentFilter(intent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSharedPref();

        Button btn = (Button) findViewById(R.id.btn_br);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context =  MainActivity.this;

                if(CheckConnection.isAvailable(context)) {
                    context.startService(new Intent(context, MyService.class));
                }else {
                    enableBroadcastReceiver();
                }
            }
        });

    }

    private void enableBroadcastReceiver() {
        PackageManager pm = getPackageManager();
        ComponentName compName =
                new ComponentName(getApplicationContext(),
                        NetworkStateReceiver.class);
        pm.setComponentEnabledSetting(
                compName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void setUpSharedPref() {
        //*****
        //provide required mail id of recipient.(Instead of mailTo)
        new MailingInformation(this).CreateSession("mailTo","mailSubject","mailBody");
        //*****
    }
}
