package com.example.ikhtiar.SendingMailAutomatic;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Ikhtiar on 10/25/2015.
 */
public class MyService extends Service {

    private boolean mail_sent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        if(sendMail()){
            disableBroadcastReceiver(getApplicationContext());

            Toast.makeText(this, "Mail Sent ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Mail Not Sent", Toast.LENGTH_LONG).show();
        }
        stopSelf();
        return START_NOT_STICKY;
    }

    private boolean sendMail() {
        MailingInformation mailInfo = new MailingInformation(getApplicationContext());
        mail_sent=false;
        //*******
        // Provide Required mail id and password of sender.
        String sender_mail = "*****@gmail.com";
        String sender_password ="*****";
        //*******
        final Mail sender = new Mail(sender_mail,sender_password);
        String[] toArr = {mailInfo.getMailTo()};
        sender.setTo(toArr);
        sender.setFrom(sender_mail);
        sender.setSubject(mailInfo.getMailSubject());
        sender.setBody(mailInfo.getMailBody());

        try {
            if(sender.send(getApplicationContext())) {
                mail_sent=true;
                mailInfo.ClearSession();
            } else {
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return mail_sent;
    }

    public void disableBroadcastReceiver(Context context){
        PackageManager pm = context.getPackageManager();
        ComponentName compName =
                new ComponentName(context.getApplicationContext(),
                        NetworkStateReceiver.class);
        pm.setComponentEnabledSetting(
                compName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
