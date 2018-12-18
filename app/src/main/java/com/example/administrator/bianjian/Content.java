package com.example.administrator.bianjian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    ImageView save_bt,back_bt;
    Boolean exist=false;
    final static String TAG ="DataBaseTT";
    Boolean isDebug =true;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        editText =findViewById(R.id.input);
        save_bt=findViewById(R.id.save_bt);
        back_bt=findViewById(R.id.back_bt);
        save_bt.setVisibility(View.VISIBLE);
        back_bt.setVisibility(View.VISIBLE);

        back_bt.setOnClickListener(this);
        save_bt.setOnClickListener(this);

        id=getIntent().getExtras().getInt("Id");
        if (isDebug){
            Log.d(TAG,id+"");
        }
        if(id!=0){
            DBa dBa =DataSupport.find(DBa.class,id);
            editText.setText(dBa.getContent());
            exist=true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_bt:
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String times = formatter.format(curDate);
                String s =editText.getText().toString();
                DBa dBa =new DBa();
                dBa.setContent(s);
                dBa.setDate(times);
                if(exist){
                    dBa.update(id);
                }
                else {

                    dBa.save();
//                    if(isDebug){
//                        Log.d("DataBaseTT",s+"   "+dBa.getId()+dBa.getNum());
//                        Log.d("DataBaseTT",s+"   "+times+"   "+dBa.getId());
//                        Log.d("DataBaseTT",DataSupport.find(DBa.class,0).getContent()+"  " );
//                    }
                }
                Intent intent =new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();

            case R.id.back_bt:
                intent =new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
        }
    }
}
