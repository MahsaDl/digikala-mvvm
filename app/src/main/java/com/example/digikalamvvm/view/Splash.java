package com.example.digikalamvvm.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.digikalamvvm.Const;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.view.activity.MainActivity;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Splash extends AppCompatActivity {

    InternetBroadCast internetBroadCast;

    MyView myView;
    Timer timer=new Timer();
    ProductViewmodel viewmodel;
    private static final String TAG = "splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView= new MyView(this);
        setContentView(myView);

        internetBroadCast=new InternetBroadCast();

        viewmodel= ViewModelProviders.of(this).get(ProductViewmodel.class);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(myView.cx>myView.height-1500){
                    myView.cx-=25;
                    myView.invalidate();
                }

            }
        },0,10);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(internetBroadCast,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }


    public boolean checkSplash(){
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_SPLASH,MODE_PRIVATE);
        if (sharedPreferences.contains(Const.PREF_KEY_SPLASH_FLAG)){
            boolean result = sharedPreferences.getBoolean(Const.PREF_KEY_SPLASH_FLAG, false);
            if (result==true){
                return true;
            }else{
                return false;
            }

        }else {
            return false;
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetBroadCast);

    }

    class InternetBroadCast extends BroadcastReceiver {
        AlertDialog alertDialog;
        public InternetBroadCast() {
            AlertDialog.Builder builder=new AlertDialog.Builder(Splash.this);
            View alertDialogView= LayoutInflater.from(Splash.this).inflate(R.layout.alert_dialog_layout,null);
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
                if(checkSplash()==false){
                    Log.e(TAG, "0" );
                    viewmodel.selectRetrofit().observe(Splash.this, new Observer<List<Product>>() {
                        @Override
                        public void onChanged(List<Product> products) {

                            if (products!=null){
                                SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_SPLASH,MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putBoolean(Const.PREF_KEY_SPLASH_FLAG,true);
                                editor.apply();
                                Intent newIntent=new Intent(Splash.this, MainActivity.class);
                                startActivity(newIntent);
                                finish();
                            }else {
                                Toast.makeText(Splash.this,"خطا در برقراری ارتباط با سرور",Toast.LENGTH_LONG).show();
                            }





                        }
                    });



                }else {
                    Log.e(TAG, "1" );

                    Intent newIntent=new Intent(Splash.this,MainActivity.class);
                    startActivity(newIntent);
                    finish();
                }


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