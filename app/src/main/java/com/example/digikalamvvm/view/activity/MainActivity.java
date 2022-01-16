package com.example.digikalamvvm.view.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.digikalamvvm.Const;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.ActivityMainBinding;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    ProductViewmodel viewmodel;
    NavController navController;
    Toolbar toolbar;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.e(TAG, "MainActivity");


       if(checkLogin()==true){

        }else{
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"لطفا ثبت نام کنید",Toast.LENGTH_LONG).show();
        }


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout);
        

    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, binding.drawerLayout);
    }


    public boolean checkLogin(){
        SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN,MODE_PRIVATE);
        if (sharedPreferences.contains(Const.PREF_KEY_LOGIN_FLAG)){
            boolean result = sharedPreferences.getBoolean(Const.PREF_KEY_LOGIN_FLAG, false);
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
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }


}