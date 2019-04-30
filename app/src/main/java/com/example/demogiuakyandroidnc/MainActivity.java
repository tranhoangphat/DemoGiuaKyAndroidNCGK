package com.example.demogiuakyandroidnc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    String mUserName = "";
    String mPassword = "";
    String userId="";
    boolean loginsuccess= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onValidateForm()){
                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("user_name",mUserName);
                    mMap.put("password",mPassword);
                    new LoginAsyncTask(MainActivity.this,new ILoginView() {
                        @Override
                        public void onLoginSuccess(String m) {
                            Toast.makeText(MainActivity.this,m,Toast.LENGTH_SHORT).show();
                            loginsuccess = true;
                        }

                        @Override
                        public void onLoginFail(String m) {
                            Toast.makeText(MainActivity.this,m,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoginUserId(String id) {
                            userId = id;
                        }
                    },mMap).execute(" http://www.vidophp.tk/api/account/signin");
                }
                if (loginsuccess){
                    Intent intent = new Intent(MainActivity.this,SanPhamActivity.class);
                    intent.putExtra("userid",userId);
                    startActivity(intent);
                }
            }
        });

    }

    private void onInit() {
        edtUserName = findViewById(R.id.login_edt_username);
        edtPassword = findViewById(R.id.login_edt_password);
        btnLogin = (Button)findViewById(R.id.login_btn_login);
    }

    private boolean onValidateForm(){
        mUserName = edtUserName.getText().toString();
        if (mUserName.length() < 1){
            edtUserName.setError("Username field cannot be blank");
            return false;
        }

        mPassword = edtPassword.getText().toString();
        if (mPassword.length() < 1){
            edtPassword.setError("Password field cannot be blank");
            return  false;
        }
        return true;
    }
}