package com.example.digikalamvvm.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.digikalamvvm.R;

import pl.droidsonroids.gif.GifImageView;

public class BaseActivity extends AppCompatActivity {
    InternetBroadCast internetBroadCast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        internetBroadCast=new InternetBroadCast();
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(internetBroadCast,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetBroadCast);
    }

    class InternetBroadCast extends BroadcastReceiver {
        AlertDialog alertDialog;
        public InternetBroadCast() {
            AlertDialog.Builder builder=new AlertDialog.Builder(BaseActivity.this);
            View alertDialogView= LayoutInflater.from(BaseActivity.this).inflate(R.layout.alert_dialog_layout,null);
            builder.setView(alertDialogView);
            builder.setCancelable(false);
            alertDialog=builder.create();
            GifImageView gifImageView=alertDialogView.findViewById(R.id.gif);
            ImageButton btnWifi=alertDialogView.findViewById(R.id.btnWifi);
            ImageButton btnNetwork=alertDialogView.findViewById(R.id.btnNetwork);
            btnWifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentWifi=new Intent();
                    intentWifi.setAction(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intentWifi);
                    //alertDialog.dismiss();
                }
            });
            btnNetwork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentNet=new Intent();
                    intentNet.setAction(Settings.ACTION_DATA_USAGE_SETTINGS);
                    startActivity(intentNet);
                    //alertDialog.dismiss();

                }
            });

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (isNetworkConnected()){
                alertDialog.dismiss();


            }else{
                alertDialog.show();
            }

        }
        private boolean isNetworkConnected() {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        }
    }
}