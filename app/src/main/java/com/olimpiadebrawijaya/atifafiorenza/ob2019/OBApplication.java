package com.olimpiadebrawijaya.atifafiorenza.ob2019;

import android.app.Application;
import android.content.Context;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.DataList;


import jonathanfinerty.once.Once;

import static com.olimpiadebrawijaya.atifafiorenza.ob2019.debug.Tag.OB_APPLICATION_TAG;

public class OBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // ditunggu tanggal 13 atau 14 malam, cek di firebase topic nanti
        FirebaseMessaging.getInstance().subscribeToTopic("liveScore");
        FirebaseMessaging.getInstance().unsubscribeFromTopic("news");


        DataList.initialize(this);
        Once.initialise(this);
        RestClient.initialize();

        Log.d(OB_APPLICATION_TAG, "OBApplication::onCreate: ");

    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

}
