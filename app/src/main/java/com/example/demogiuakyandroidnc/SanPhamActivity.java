package com.example.demogiuakyandroidnc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamActivity extends AppCompatActivity {
    Button btnAddNewSP;
    RecyclerView recyclerView;
    public static List<SanPham> models;
    Map<String,String> mMap = new HashMap<>();
    String userID = "";
    public static MyStudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);
        models = new ArrayList<>();
        // anh xa
        recyclerView = findViewById(R.id.recyclerView_sanPham);
        btnAddNewSP = (Button)findViewById(R.id.btnAddNewSP);
        //
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        onGetinten();
        userID = String.valueOf(userID);
        mMap.put("id",userID);
        loadsp();

        btnAddNewSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SanPhamActivity.this,AddNewSanPham.class);
                intent.putExtra("userId",userID);
                startActivity(intent);
            }
        });


    }

    private void loadsp() {
        new SanPhamAsyncTask(SanPhamActivity.this,new IStudentView() {
            @Override
            public void onGetDataSuccess(JSONArray jsonArray) {
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SanPham model = new SanPham();
                        model.setId(Integer.valueOf(jsonObject.getString("id")));
                        model.setProductName(jsonObject.getString("product_name"));
                        model.setPrice(Integer.valueOf(jsonObject.getString("price")));
                        model.setSdescription(jsonObject.getString("description"));
                        model.setProducer(jsonObject.getString("producer"));
                        models.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MyStudentAdapter(SanPhamActivity.this,R.layout.sanpham_item,models,userID);
                recyclerView.setAdapter(adapter);
            }
        },mMap).execute( "http://www.vidophp.tk/api/account/getdata");
    }

    private void onGetinten() {
        Intent intent = getIntent();
        userID = (String) intent.getStringExtra("userid");

    }
}

