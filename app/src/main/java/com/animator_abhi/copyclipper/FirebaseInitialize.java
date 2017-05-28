package com.animator_abhi.copyclipper;

import android.app.Application;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ANIMATOR ABHI on 08/04/2017.
 */

public class FirebaseInitialize extends Application{

    public FirebaseDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database=FirebaseDatabase.getInstance();

    }
}
