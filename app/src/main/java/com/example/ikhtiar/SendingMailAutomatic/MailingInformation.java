package com.example.ikhtiar.SendingMailAutomatic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Ikhtiar on 10/26/2015.
 */
public class MailingInformation {
    SharedPreferences pref;
    Editor editor;
    Context context;

    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "MailPref";

    private String MailTo="MailTo";
    private String MailSubject="MailSubject";
    private String MailBody="MailBody";

    public MailingInformation(Context context){
        this.context=context;
        pref = context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void CreateSession(String to,String subject,String body){
        editor.putString(MailTo,to);
        editor.putString(MailSubject,subject);
        editor.putString(MailBody,body);
        editor.commit();
    }

    public void ClearSession(){
        editor.clear();
        editor.commit();
    }

    public String getMailTo(){
        return pref.getString(MailTo,null);
    }

    public String getMailSubject(){
        return pref.getString(MailSubject,null);
    }

    public String getMailBody(){
        return pref.getString(MailBody,null);
    }
}
