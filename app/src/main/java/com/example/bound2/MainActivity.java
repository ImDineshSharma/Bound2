package com.example.bound2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import com.example.bound2.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService BuckyService; //Service class instance
    boolean isBound = false; // true false check for con

    //click method and reference to TextView
    public void ShowTime(){
        String currentTime = BuckyService.getCurrentTime();
        TextView MessageText = (TextView)findViewById(R.id.TextMessage);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finally the intent
        Intent i = new Intent(this, MyService.class);
        bindService(i, buckysConnection, BIND_AUTO_CREATE);
    }

    //this is gonna be the class that will be responsible for binding the service
    // or Connecting the activity with service
    // or the interface to connect with service
    private ServiceConnection buckysConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            BuckyService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;
        }
    };
}
