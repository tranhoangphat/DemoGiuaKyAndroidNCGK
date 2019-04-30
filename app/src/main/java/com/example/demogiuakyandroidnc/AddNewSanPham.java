package com.example.demogiuakyandroidnc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.demogiuakyandroidnc.SanPhamActivity.adapter;
import static com.example.demogiuakyandroidnc.SanPhamActivity.models;

public class AddNewSanPham extends AppCompatActivity {
    EditText edt_newProductName;
    EditText edt_newPrice;
    EditText edt_newDescription;
    EditText edt_newProducer;
    Button btn_Save;
    Button btn_Cancle;
    String mUserId = "";
    Map<String,String> mMap = new HashMap<>();

    String ProductName ="", Price="", Description="", Producter="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_san_pham);
        onIt();
        final Intent intent = getIntent();
        mUserId = intent.getStringExtra("userId");

       btn_Save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(valuedayform()){
                   mMap.put("user_id",mUserId);
                   mMap.put("product_name",ProductName);
                   mMap.put("price",Price);
                   mMap.put("description",Description);
                   mMap.put("producer",Producter);
                   new LoginAsyncTask(AddNewSanPham.this, new ILoginView() {
                       @Override
                       public void onLoginSuccess(String m) {

                       }

                       @Override
                       public void onLoginFail(String m) {

                       }

                       @Override
                       public void onLoginUserId(String id) {

                       }
                   },mMap).execute("http://www.vidophp.tk/api/account/dataaction?context=insert");


                   Intent intent2 = new Intent(AddNewSanPham.this,SanPhamActivity.class);
                   intent2.putExtra("userid",mUserId);
                   startActivity(intent2);
                   finish();
               }
           }
       });

        btn_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private boolean valuedayform() {
        ProductName = edt_newProductName.getText().toString();
        if(ProductName.length()<1){
            edt_newProductName.setError("this can not Blank");
            return false;
        }
        Price = edt_newPrice.getText().toString();
        if(Price.length()<1){
            edt_newPrice.setError("this can not Blank");
            return false;
        }
        Description = edt_newDescription.getText().toString();
        if(Description.length()<1){
            edt_newDescription.setError("this can not Blank");
            return false;
        }
        Producter = edt_newProducer.getText().toString();
        if(Producter.length()<1){
            edt_newProducer.setError("this can not Blank");
            return false;
        }

        return true;
    }

    private void onIt() {
        edt_newProductName = (EditText)findViewById(R.id.edt_newProductName);
        edt_newPrice = (EditText)findViewById(R.id.edt_newPrice);
        edt_newDescription = (EditText)findViewById(R.id.edt_newDescription);
        edt_newProducer = (EditText)findViewById(R.id.edt_newProducer);
        btn_Save = (Button)findViewById(R.id.btn_Save);
        btn_Cancle = (Button)findViewById(R.id.btn_Cancle);
    }
}
