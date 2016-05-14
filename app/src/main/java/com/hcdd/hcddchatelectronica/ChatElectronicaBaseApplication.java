package com.hcdd.hcddchatelectronica;

import android.app.Application;

import com.firebase.client.Firebase;


public class ChatElectronicaBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
