package com.example.bound2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
//manualy imported
import android.os.Binder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MyService extends Service {

    //IBinder object
    private final IBinder BinderObject = new MyLocalBinder(); // this will be the bridge between client and service

    public MyService() {
    }

    //This is the first thing our main activity will interact with
    @Override
    public IBinder onBind(Intent intent) {
        return BinderObject; //Return binder object here
    }

    //This is all our service is gonna do
    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("HH.MM.SS", Locale.US);
        return (df.format(new Date()));
    }

    //whenever we want to bind a client to the service we need to create this class that has ability
    // to bind
    public class MyLocalBinder extends Binder{
        // we want this class to return a reference to our service class(MyService) so that we can
        // use all the stuff inside of our service class(MyService)
        MyService getService(){
            return MyService.this;
        }
    }
}
