package com.example.digikalamvvm.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.digikalamvvm.Const;
import com.example.digikalamvvm.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check(binding.etName.getText().toString(),binding.etPassword.getText().toString())){
                    SharedPreferences sharedPreferences=getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN,MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(Const.PREF_KEY_USERNAME,binding.etName.getText().toString().trim());
                    editor.putString(Const.PREF_KEY_PASSWORD,binding.etPassword.getText().toString().trim());
                    editor.putBoolean(Const.PREF_KEY_LOGIN_FLAG,true);
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"خوش آمدید",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }

    public boolean check(String username,String password){

        if (username==null||username.equals("")){
            binding.etName.setError("لطفا نام خود را وارد کنید");
            binding.etName.requestFocus();
            return false;
        }
        if (username.length()<3){
            binding.etName.setError("نام باید حداقل بیشتر از دو حرف باشد");
            binding.etName.requestFocus();
            return false;
        }
        if (password==null||password.equals("")){
            binding.etPassword.setError("لطفا رمز عبور خود را وارد کنید");
            binding.etPassword.requestFocus();
            return false;
        }
        if (password.length()<6){
            binding.etPassword.setError("رمز عبور حداقل باید بیشتر از پنج حرف باشد");
            binding.etPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
