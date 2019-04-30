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

public class EditActivity extends AppCompatActivity {
    EditText edt_EditId,edt_EditProductName,edt_EditPrice,edt_EditDescription,edt_EditProducer;
    Button btn_Update,btn_Edit_Cancle;
    Map<String, String> map = new HashMap<>();
    String Userid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        onIt();
        onGetIntent();
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("user_id",Userid);
                map.put("id",edt_EditId.getText().toString());
                map.put("product_name",edt_EditProductName.getText().toString());
                map.put("price",edt_EditPrice.getText().toString());
                map.put("description",edt_EditDescription.getText().toString());
                map.put("producer",edt_EditProducer.getText().toString());

                new LoginAsyncTask(EditActivity.this, new ILoginView() {
                    @Override
                    public void onLoginSuccess(String m) {
                        Toast.makeText(EditActivity.this,m,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onLoginFail(String m) {

                    }

                    @Override
                    public void onLoginUserId(String id) {

                    }
                },map).execute("http://www.vidophp.tk/api/account/dataaction?context=update");

                Intent intent = new Intent(EditActivity.this,SanPhamActivity.class);
                intent.putExtra("userid",Userid);
                startActivity(intent);
                finish();
            }
        });

        btn_Edit_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void onGetIntent() {
        Intent intent = getIntent();
        SanPham item = (SanPham) intent.getSerializableExtra("itemSP");
        edt_EditId.setText(item.getId()+"");
        edt_EditProductName.setText(item.getProductName());
        edt_EditPrice.setText(item.getPrice()+"");
        edt_EditDescription.setText(item.getSdescription());
        edt_EditProducer.setText(item.getProducer());
        Userid = intent.getStringExtra("userid");

    }

    private void onIt() {
        edt_EditId = (EditText)findViewById(R.id.edt_EditId);
        edt_EditProductName = (EditText)findViewById(R.id.edt_EditProductName);
        edt_EditPrice = (EditText)findViewById(R.id.edt_EditPrice);
        edt_EditDescription = (EditText)findViewById(R.id.edt_EditDescription);
        edt_EditProducer = (EditText)findViewById(R.id.edt_EditProducer);

        btn_Edit_Cancle = (Button)findViewById(R.id.btn_Edit_Cancle);
        btn_Update = (Button)findViewById(R.id.btn_Update);
    }
}
