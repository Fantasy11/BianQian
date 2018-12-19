package com.example.administrator.bianjian;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Search extends AppCompatActivity implements View.OnClickListener {
    ImageView find_bt,back_bt;
    EditText editText;
    FilterAdapter filterAdapter;
    ListView listView;
    List<DBa> list;
    static final String TAG ="Find11";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                filterAdapter.getFilter().filter(editText.getText().toString());
                //Log.d(TAG,editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void init(){
        find_bt = findViewById(R.id.find);
        editText = findViewById(R.id.search_edit);
        back_bt = findViewById(R.id.back_bt);
        listView = findViewById(R.id.find_list);
        find_bt.setOnClickListener(this);
        back_bt.setVisibility(View.VISIBLE);
        back_bt.setOnClickListener(this);
        list=DataSupport.findAll(DBa.class);
        filterAdapter = new FilterAdapter(this,list);
        listView.setAdapter(filterAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.find:
                break;
            case R.id.back_bt:
                this.finish();
                break;
        }
    }
}
