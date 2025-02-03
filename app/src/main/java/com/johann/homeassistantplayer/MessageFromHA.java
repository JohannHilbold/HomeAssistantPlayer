package com.johann.homeassistantplayer;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessageFromHA extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        System.out.println(message);
    }
}
