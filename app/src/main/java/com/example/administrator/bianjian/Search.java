package com.example.administrator.bianjian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Search extends AppCompatActivity implements View.OnClickListener {
    ImageView find_bt,back_bt;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        find_bt = findViewById(R.id.find);
        editText = findViewById(R.id.search_edit);
        back_bt = findViewById(R.id.back_bt);
        find_bt.setOnClickListener(this);
        back_bt.setVisibility(View.VISIBLE);
        back_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.find:

            case R.id.back_bt:
                this.finish();
        }
    }
}
